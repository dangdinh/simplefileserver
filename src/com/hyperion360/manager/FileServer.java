package com.hyperion360.manager;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server file to manage all incoming connections from clients to access file on sever
 * 1. SEVER_PORT: 7777
 * 2. CONCURRENT THREAD: 10
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public class FileServer {
    public static final int SERVER_PORT = 7777;
    public static final int NUMBER_OF_THREADS = 10;
    public static String FILE_DIR = "D:\\";

    public static void main(String[] args) {
        if (args.length == 1 && args[0].contains("fileDir")) {
            FILE_DIR = String.valueOf(args[0]).replace("fileDir", "").replace("=", "").trim();
        } else {
            System.err.println("Missing fileDir argument when starting server");
            System.exit(0);
        }
        try {
            ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: "+ serverSocket);
            System.out.println("Waiting for client...");

            while(true){
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);

                    executor.execute(new WorkerThread(socket));
            }
        } catch(Exception ex){
            System.out.println("Exception inside File Server: " + ex);
        }

    }
}
