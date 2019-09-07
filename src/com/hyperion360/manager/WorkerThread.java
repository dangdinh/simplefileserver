package com.hyperion360.manager;

import com.hyperion360.command.CommandFactory;
import com.hyperion360.command.CommandStrategy;

import java.io.*;
import java.net.Socket;

/**
 * Worker works as single thread to handle request from client. Based on that, it send info to server to receive results.
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public class WorkerThread extends Thread {
    private Socket socket;

    public WorkerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        System.out.println("Processing..." + socket);
        BufferedReader in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedOutputStream(socket.getOutputStream());
            int buffer = 4096;
            String clientRequestCommand = "";
            while(true) {
                clientRequestCommand = in.readLine();

                CommandStrategy commandInstance = CommandFactory.getCommand(CommandFactory.CommandType.valueOfCommand(clientRequestCommand));
                if(commandInstance == null){
                    out.write(CommandFactory.getCommand(CommandFactory.CommandType.ERROR).runCommand("").toString().getBytes());
                } else {
                    Object finalResult = commandInstance.runCommand(clientRequestCommand);
                     if(finalResult instanceof String){
                         out.write(finalResult.toString().getBytes());
                     } else if (finalResult instanceof InputStream){
                         byte[] bufferArr = new byte[buffer];
                         BufferedInputStream bis = new BufferedInputStream((InputStream) finalResult);
                         int read = 0;
                         while ((read = bis.read(bufferArr)) != -1) {
                             out.write(bufferArr, 0, read);
                         }

                         bis.close();
                     }
                }
                out.flush();
            }

        } catch(IOException ex){
            System.out.println("Exception WorkerThread: " + ex);
        } finally{
            try {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }
            } catch (Exception ex){
                System.out.println("Exception when closing input stream and output stream of Worker Thread");
            }
        }
        System.out.println("Complete processing: " + socket);
    }
}
