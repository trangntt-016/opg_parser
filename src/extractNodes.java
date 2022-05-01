import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class extractNodes {
    public static void main(String[] args) throws IOException {
        File fXmlFile = new File("D:\\Projects\\parsing-xml\\src\\AccessRightsByProfile.xml");
        BufferedReader br = new BufferedReader(new FileReader(fXmlFile));
        String line;
        StringBuilder sb = new StringBuilder();
        String currentRole = "";
        List<String> list = new ArrayList<>();
        boolean start = false;
        while((line=br.readLine())!= null){
            if(line.contains("valueAccess")) start = true;
            if(start == true && line.contains("<nodePath>")){
                String nodePath = line.replace("<nodePath>","").replace("</nodePath>","").trim() + "\n";
                list.add(nodePath);
            }
        }

        System.out.println(list.toString());
    }
}
