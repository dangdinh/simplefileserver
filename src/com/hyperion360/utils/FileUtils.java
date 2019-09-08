package com.hyperion360.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * File utilities
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public class FileUtils {
    public static final String NOT_FOUND_FILE_ERR = "File does not exist on server. please using index and then get another";
    /**
     * Get corresponding extension based on input file
     * @param f file
     * @return file extension
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    /**
     * Get list text fields by input file directory
     * @param fileDir file directory
     * @return list of text files
     */
    public static List<String> getListTextFileByDir(File fileDir){
        List<String> fileList = new ArrayList<String>();
        for(File file: fileDir.listFiles()){
            if(!file.isDirectory() && FileUtils.getExtension(file).equals("txt")){
                fileList.add(file.getName());
            }
        }
        return fileList;
    }
}
