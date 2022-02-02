package ru.as.homeworks;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    private Socket socket = null;
    private ServerSocket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        new EchoServer();
    }


    public EchoServer() {

        start();

    }


    private void start() {
        try {
            serverSocket = new ServerSocket(8989);
            socket = serverSocket.accept();
            System.out.println("Client connected");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while (true) {
                        msg = scanner.nextLine();
                        try {
                            out.writeUTF("Server: " + msg);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            sender.start();

            Thread echo = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {

                    while (true) {
                        try {
                            msg = in.readUTF();
                            if ("/end".equals(msg)){
                                out.close();
                                in.close();
                                socket.close();
                                serverSocket.close();
                                sender.interrupt();
                            } else {
                                System.out.println("Client: " + msg);
                                out.writeUTF("Echo: " + msg);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            echo.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
