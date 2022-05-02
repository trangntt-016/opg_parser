package utils;

import java.util.LinkedList;

public class StringUtil {
    public static String toStringPath(LinkedList<String> currentPathList){
        StringBuilder sb = new StringBuilder();
        for(String p : currentPathList){
            sb.append(p).append("/");
        }
        sb.deleteCharAt(sb.lastIndexOf("/"));
        return sb.toString();
    }

    public static String extractKeyFromPath(String path){
        StringBuilder sb = new StringBuilder(path);
        return sb.substring(0, sb.indexOf("/")).toString().trim();
    }

    public static String extractContentFromNode(String text, String nodeName){
        String startNode = "<" + nodeName + ">";
        String endNode = "</" + nodeName + ">";
        return text.trim().replace(startNode,"").replace(endNode, "");
    }
}
