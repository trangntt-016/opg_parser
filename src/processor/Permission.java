package processor;

import model.uValueAccessSpecific;
import parser.CommonTypeParser;
import utils.FileUtil;
import utils.StringUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Permission {
    private static final Logger logger = Logger.getLogger(CommonTypeParser.class.getName());

    public Permission(){};

    public void showAllPermissionNodePath(File permissionFile, Map<String, String> eplMap) {;
        try{
            BufferedReader br = new BufferedReader(new FileReader(permissionFile));
            String line;
            StringBuilder sb = new StringBuilder();
            String currentRole = "";
            List<String> list = new ArrayList<>();
            boolean start = false;
            while((line=br.readLine())!= null) {
                if(line.contains("valueAccess")) start = true;
                if(start == true && line.contains("<nodePath>")){
                    String nodePath = line.replace("<nodePath>","").replace("</nodePath>","").trim().substring(1);
                    list.add(nodePath);
                }
            }

            for(String l : list){
                System.out.println(l +" - "+ eplMap.get(l));
            }
        }
        catch(IOException ex){
            logger.log(Level.SEVERE, ex.getMessage());
        }

    }

    public void modifyPermission(File userInputFile, File modifiedFile){
        Map<String, List<uValueAccessSpecific>>userAccessMap = FileUtil.parseJson(userInputFile);

        try{
            BufferedReader br = new BufferedReader(new FileReader(modifiedFile));
            String line;
            StringBuilder sb = new StringBuilder();
            String currentProfile = "";
            List<uValueAccessSpecific> uValueAccessSpecificList = new ArrayList<>();
            while((line=br.readLine())!= null){
                if(line.contains("<profile>")){
                    currentProfile = StringUtil.extractContentFromNode(line,"profile");
                    uValueAccessSpecificList = userAccessMap.get(currentProfile);
                }
                if(uValueAccessSpecificList !=null && line.contains("<valuesAccessSpecifics>")){
                    String oldValueAccess = line;
                    // go to the next line that contains nodePath
                    line = br.readLine();
                    oldValueAccess+=line;
                    String nodePath = StringUtil.extractContentFromNode(line, "nodePath");
                    // go to the next line that contains valuesAccessSpecific
                    line = br.readLine();
                    oldValueAccess+=line;
                    Optional<uValueAccessSpecific> valueAccessSpecifics = uValueAccessSpecificList.stream().filter(v->v.getNodePath().contains(nodePath)).findFirst();
                    if(valueAccessSpecifics.isPresent()){
                        Character oldAccess = line.split("<valuesAccessSpecific>")[1].charAt(0);
                        oldValueAccess = oldValueAccess.replace(("<valuesAccessSpecific>" + oldAccess + "</valuesAccessSpecific>"),"<valuesAccessSpecific>" +valueAccessSpecifics.get().getValuesAccessSpecific()  + "</valuesAccessSpecific>");
                        line = oldValueAccess;
                    };

                }
                sb.append(line);
            }
        }
        catch(IOException ex){

        }


    }
}
