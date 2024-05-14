package edu.sandiego.cs.comp305.sp24;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient implements Communicable {
    /**
     * Allows a one time socket call to a server, sends message, and returns confirmation response.
     * @param sIP
     * @param iPort
     * @param sMessage
     * @return
     */
    public String connectForOneMessage(String sIP, int iPort, String sMessage){
        try(Socket oSocket = new Socket()){
            connectToServer(oSocket, sIP, iPort);

            sendMessage(oSocket, sMessage);

            return receiveMessage(oSocket);
        }
        catch(Exception e){
            handleException(e);
            return null;
        }
    }

    /**
     * Attempts to connect to server on the given socket at the given IP address and port
     * @param socket
     * @param ip
     * @param port
     * @throws IOException
     */
    void connectToServer(Socket socket, String ip, int port) throws IOException {
        socket.connect(new InetSocketAddress(ip, port), 5000);
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

    void handleException(Exception e) {
        System.out.println("[client]: Client exception: " + e.getMessage());
//        e.printStackTrace();
    }
}
