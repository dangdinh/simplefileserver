package com.hyperion360.manager;

import java.io.*;
import java.net.Socket;

/**
 * File client support some main command to access server file such as
 *  1. index: get all text file name
 *  2. get [file_name]: get specific exist file name
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public class FileClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 7777;

    public static void main (String[] args){
        Socket socket = null;
        BufferedReader inputUserReader = null;
        BufferedInputStream socketReader = null;
        BufferedWriter socketWriter = null;
        BufferedOutputStream outputWriter = null;
        int buffer = 4096;

        String userCommand = "";
        String serverResponse = "";
        try{
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected: "+ socket);

            inputUserReader = new BufferedReader(new InputStreamReader(System.in));
            socketReader = new BufferedInputStream(socket.getInputStream());
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            outputWriter = new BufferedOutputStream(System.out);
            while(true){
                System.out.println("====================================================");
                System.out.println("Enter some below commands to access file on server :");
                System.out.println("--> index - get list all text fileds (.txt)");
                System.out.println("--> get [file-name] - download file content");
                userCommand = inputUserReader.readLine();
                socketWriter.write(userCommand);
                socketWriter.newLine();
                socketWriter.flush();

                byte[] bufferArr = new byte[buffer];
                int read = 0;
                System.out.println("Server Response: ");
                while ((read = socketReader.read(bufferArr)) != -1) {
                    outputWriter.write(bufferArr, 0, read);
                    outputWriter.flush();
                    if(socketReader.available() <= 0){
                        break;
                    }
                }
                System.out.println("\n");
            }
        } catch(Exception ex){
            System.out.println("Exception FileClient: " + ex);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch(IOException ioEx){
                System.out.println("Exception closing socket: " + ioEx);
            }
        }
    }
}
