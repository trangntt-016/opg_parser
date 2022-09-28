package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Profile;
import model.AccessRightsByProfile.ValueAccessSpecific;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());
    
    public static Map<String, List<ValueAccessSpecific>> parseJson(File jsonFile){
        List<Profile> profileList = new ArrayList<>();
        try{
            FileReader reader = new FileReader(jsonFile);
            Gson gson = new GsonBuilder().create();

            Type profileListType = new TypeToken<ArrayList<Profile>>(){}.getType();

            profileList = gson.fromJson(reader, profileListType);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return convertFromProfileListToMap(profileList);
    }

    private static Map<String, List<ValueAccessSpecific>> convertFromProfileListToMap(List<Profile>profileList){
        Map<String, List<ValueAccessSpecific>>result = new HashMap<>();
        for(Profile p : profileList){
            result.put(p.getProfile(), p.getValuesAccessSpecifics());
        }
        return result;
    }
    
    public static boolean isFileValid(File file, String expectedExtension) throws Exception {
    	// check if filename has correct extension that we want to parse
    	if(!hasCorrectExtension(file, expectedExtension)) {
    		throw new Exception("Current file doesn't have correct file type. It should be a " + expectedExtension + " file type.");
    	}
    	if(!isFileExists(file.getAbsolutePath())) {
    		throw new IOException("File not found!");
    	}
    	return true;
    	
    }
    
    
    private static boolean hasCorrectExtension(File file, String expectedExtension) {
    	String fileName = file.getName();
    	return fileName.contains(expectedExtension);
    }
    
    private static boolean isFileExists(String filePathStr) {
    	Path distPath = Paths.get(filePathStr);
    	
    	return Files.exists(distPath);
    }
    
}
