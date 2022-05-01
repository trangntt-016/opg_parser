import parser.CommonTypeParser;
import parser.EPLParser;

import java.io.File;
import java.util.List;
import java.util.Map;

public class MainProgram {
    public static void main(String[] args) {
        File commonTypeFile = new File("D:\\Projects\\parsing-xml\\src\\files\\CommonTypes.xsd");
        File eplFile = new File("D:\\Projects\\parsing-xml\\src\\files\\EPL.xsd");
        // get the map of common type nodes
        Map<String, List<String[]>> output = new CommonTypeParser().parseCommonType(commonTypeFile);

        Map<String, String>eplOutput = new EPLParser().parseEPL(eplFile, output);

        for(Map.Entry<String, String>m : eplOutput.entrySet()){
            System.out.println(m.getKey());
        }
    }



}
