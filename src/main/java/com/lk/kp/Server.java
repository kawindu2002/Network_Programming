package com.lk.kp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Server starting...");

            // Create a server socket on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);

            // Accept client connections
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);
            String msg = "";

            // Communication loop
            while (true) {
                // Read message from the client
                String clientMessage = in.readUTF();
                System.out.println("Client says: " + clientMessage);

                // If the client sends 'exit', close the connection
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client has exited. Closing server...");
                    break;
                }

                // Read server's response from user input
                System.out.print("Enter your response (type 'exit' to quit): ");
                msg = input.nextLine();

                // Send the response back to the client
                dos.writeUTF(msg);
                dos.flush();

                // If the server types 'exit', terminate the communication
                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the server...");
                    break;
                }
            }

            // Close the connections
            dos.close();
            in.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
