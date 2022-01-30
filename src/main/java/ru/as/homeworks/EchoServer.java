package ru.as.homeworks;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    private Socket socket = null;


    public static void main( String[] args ) {
        new EchoServer();
    }


    public EchoServer(){

       start();

    }




    private void start(){
        try(ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Server start, waiting clients");
            socket = serverSocket.accept();
            System.out.println("Client connected");

            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String str;
            while (true){
                final String message = in.readUTF();
                System.out.println(message);
                if ("/end".equals(message)) {
                    break;
                }
                if (scanner.hasNext()) {
                    str = scanner.nextLine();
                    out.writeUTF(str);
                    out.flush();
                } else {
                    out.writeUTF(message);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (socket!=null) {
            try {
                socket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
