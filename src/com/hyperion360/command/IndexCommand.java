package com.hyperion360.command;

import com.hyperion360.manager.FileServer;
import com.hyperion360.utils.FileUtils;

import java.io.File;
import java.util.stream.Collectors;

/**
 * Get all text files and display their name to client
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public class IndexCommand implements CommandStrategy<String> {
    @Override
    public String runCommand(String input) {
        try{
            File fileDir = new File(FileServer.FILE_DIR);
            if(fileDir.exists() && fileDir.isDirectory()){
              return FileUtils.getListTextFileByDir(fileDir).stream().collect(Collectors.joining("\n"));
            }
        } catch(Exception ex){
            System.out.println("Exception read file directory " + ex);
        }
        return "Not found any text files in directory: " + FileServer.FILE_DIR;
    }
}
