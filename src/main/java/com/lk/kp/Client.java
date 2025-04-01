package com.lk.kp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            // Connect to the server at localhost and port 5000
            Socket socket = new Socket("localhost", 5000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);
            String msg = "";

            // Communication loop
            while (true) {
                // Read user input
                System.out.print("Enter message (type 'exit' to quit): ");
                msg = input.nextLine();

                // Send the message to the server
                dos.writeUTF(msg);
                dos.flush();

                // If the user types 'exit', terminate the connection
                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the client...");
                    break;
                }

                // Wait for a response from the server
                String serverMessage = in.readUTF();
                System.out.println("Server says: " + serverMessage);
            }

            // Close the connections
            dos.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
