package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Core.Player;
import Core.SocketWrapper;
import Exception.Login_fail;

public class Server {

    private ServerSocket serverSocket;
    WriteThread writeThread = new WriteThread();
    private boolean running = true;

    Server(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown. Saving data!!!");
            Server_Data.getInstance().save_data();
            Server_Data.getInstance().save_id_pass();
            running = false;
            closeServerSocket();
        }));

        try{
            serverSocket = new ServerSocket(43444);
            System.out.println("System Started");
            while(running){
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Server starting error:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException{
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        new ReadThread(socketWrapper, writeThread);
    }

    private void closeServerSocket() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server socket closed successfully.");
            }
        } catch (IOException e) {
            System.err.println("Error while closing the server socket: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
