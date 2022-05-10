import model.AccessRightsTable;
import parser.CommonTypeParser;
import parser.EPLParser;
import processor.Permission;
import utils.FileUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class MainProgram {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        File commonTypeFile = new File("D:\\Projects\\parsing-xml\\src\\files\\CommonTypes.xsd");
        File eplFile = new File("D:\\Projects\\parsing-xml\\src\\files\\EPL.xsd");
        File permissionFile = new File("D:\\Projects\\parsing-xml\\src\\files\\AccessRightsByProfile.xml");
        File userInputJson = new File("D:\\Projects\\parsing-xml\\src\\files\\permissions.json");
        File allProfilesFile = new File("D:\\Projects\\parsing-xml\\src\\files\\All_Profile.xml");
        // get the map of common type nodes
        Map<String, List<String[]>> output = new CommonTypeParser().parseCommonType(commonTypeFile);

        Map<String, String>eplOutput = new EPLParser().parseEPL(eplFile, output);
        //new Permission().showAllPermissionNodePath(permissionFile, eplOutput);
        // parse xml file

        JAXBContext context = JAXBContext.newInstance(AccessRightsTable.class);
        AccessRightsTable accessRightsTable = (AccessRightsTable) context.createUnmarshaller()
                .unmarshal(new FileReader(allProfilesFile));

        System.out.println(accessRightsTable);
    }



}
