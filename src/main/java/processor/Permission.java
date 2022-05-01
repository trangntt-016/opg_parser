package processor;

import model.ValueAccessSpecific;
import parser.CommonTypeParser;
import utils.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        Map<String, List<ValueAccessSpecific>>userAccessList = FileUtil.parseJson(userInputFile);

        BufferedReader br = new BufferedReader(new FileReader(fXmlFile));
        String line;
        StringBuilder sb = new StringBuilder();
        String currentRole = "";
        while((line=br.readLine())!= null){
            if(line.contains("profile")){
                currentRole = line.replace("<profile>","").replace("</profile>","");
            }
            if(line.contains("valueAccess")){
                System.out.println(currentRole);
                while(!line.contains("</valueAccess>")){
                    System.out.println(line);
                    line=br.readLine();
                }
                System.out.println(line);
                break;

            }
            sb.append(line.trim());
        }

    }
}
