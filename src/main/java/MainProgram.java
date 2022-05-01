import parser.CommonTypeParser;
import parser.EPLParser;
import processor.Permission;
import utils.FileUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

public class MainProgram {
    public static void main(String[] args) {
        File commonTypeFile = new File("D:\\Projects\\parsing-xml\\src\\main\\java\\files\\CommonTypes.xsd");
        File eplFile = new File("D:\\Projects\\parsing-xml\\src\\main\\java\\files\\EPL.xsd");
        File permissionFile = new File("D:\\Projects\\parsing-xml\\src\\main\\java\\files\\AccessRightsByProfile.xml");
        File userInputJson = new File("D:\\Projects\\parsing-xml\\src\\main\\java\\files\\permissions.json");
        File modifiedFile = new File("D:\\Projects\\parsing-xml\\src\\main\\java\\files\\All_Profile.xml");
        // get the map of common type nodes
        Map<String, List<String[]>> output = new CommonTypeParser().parseCommonType(commonTypeFile);

        Map<String, String>eplOutput = new EPLParser().parseEPL(eplFile, output);
        //new Permission().showAllPermissionNodePath(permissionFile, eplOutput);
        new Permission().modifyPermission(userInputJson,modifiedFile);
    }



}
