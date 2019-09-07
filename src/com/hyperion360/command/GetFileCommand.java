package com.hyperion360.command;

import com.hyperion360.manager.FileServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Processing file based on user input command
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public class GetFileCommand implements CommandStrategy<InputStream> {
    @Override
    public InputStream runCommand(String input) {
        String fileName = input.replace("get", "").trim();

        File file = new File(FileServer.FILE_DIR + File.separator + fileName + ".txt");
        try {
            if (file.exists()) {
                return new FileInputStream(file);
            }
        }catch (Exception ex){
            System.out.println("Exception in GetFileCommand: " + ex);
        }
        return null;
    }
}
