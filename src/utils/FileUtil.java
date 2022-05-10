package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Profile;
import model.uValueAccessSpecific;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());
    public static Map<String, List<uValueAccessSpecific>> parseJson(File jsonFile){
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

    private static Map<String, List<uValueAccessSpecific>>convertFromProfileListToMap(List<Profile>profileList){
        Map<String, List<uValueAccessSpecific>>result = new HashMap<>();
        for(Profile p : profileList){
            result.put(p.getProfile(), p.getValuesAccessSpecifics());
        }
        return result;
    }

}
