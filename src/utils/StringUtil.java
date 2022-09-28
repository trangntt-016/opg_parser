package utils;

import java.util.LinkedList;

public class StringUtil {
    public static String toStringPath(LinkedList<String> currentPathList){
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        for(String p : currentPathList){
        	if(!p.equals("")) {
                sb.append(p).append("/");        		
        	}

        }
        sb.deleteCharAt(sb.lastIndexOf("/"));
        return sb.toString();
    }
    
    public static String toLabelName(String pathName,int level) {
    	String indentation = "";
    	while(level > 0) {
    		indentation += " - ";
    		level--;
    	}
    	return indentation + pathName;
    }

    public static String extractKeyFromPath(String path){
        StringBuilder sb = new StringBuilder(path.substring(1));
        return sb.substring(0, sb.indexOf("/")).toString().trim();
    }

    public static String extractContentFromNode(String text, String nodeName){
        String startNode = "<" + nodeName + ">";
        String endNode = "</" + nodeName + ">";
        return text.trim().replace(startNode,"").replace(endNode, "");
    }
}
