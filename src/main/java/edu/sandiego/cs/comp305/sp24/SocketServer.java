package edu.sandiego.cs.comp305.sp24;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable,Communicable {
    private int thisServerPort;
    private ServerSocket serverSocket;

    public SocketServer(int iPort){
        thisServerPort = iPort;
    }

    /**
     * Getters and setters for menu option later
     */

    public int getPort() {
        return thisServerPort;
    }
    public void setPort(int port) {
        thisServerPort = port;
    }

    /**
     * Starts the socket server and listens for incoming client connections.
     * Handles each client connection in a separate thread.
     * @throws IOException
     */
    public void start() throws IOException {
        serverSocket = new ServerSocket(thisServerPort);
        System.out.println("Server is listening on port " + thisServerPort);

        while(true){
            Socket oSocket = serverSocket.accept();
            System.out.println("[server]: New client connected: " + oSocket.getRemoteSocketAddress());

            handleClientRequest(oSocket);
        }
    }

    /**
     * Stops the socket server by closing the server socket.
     */
    public void stop() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("[server]: Error stopping server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles a client request by receiving a message from the client,
     * processing the message, and sending a confirmation response back to the client.
     * @param socket
     */
    private void handleClientRequest(Socket socket) {
        try {
            String receivedMessage = receiveMessage(socket);
            System.out.println("[server]: Message received: " + receivedMessage);

            sendMessage(socket, "Your message was ACTUALLY received (" + receivedMessage + ")");
        } catch (IOException e) {
            System.out.println("[server]: Error handling client request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Receives a message by getting the InputStream from the socket and translates through BufferedReader
     * @param socket
     * @return
     * @throws IOException
     */
    public String receiveMessage(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        return reader.readLine();
    }

    /**
     * Sends a message by getting the OutputStream from socket, saves the output to a PrintWrite variable, and flushes
     * the message
     * @param socket
     * @param message
     * @throws IOException
     */
    public void sendMessage(Socket socket, String message) throws IOException {
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        writer.println(message);
        writer.flush();
    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            System.out.println("[server]: Server exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            stop();
        }
    }

}
