package edu.sandiego.cs.comp305.sp24;

import java.io.IOException;
import java.net.Socket;

public interface Communicable {
    /**
     * Sends a message by getting the OutputStream from socket, saves the output to a PrintWrite variable, and flushes
     * the message
     * @param socket
     * @param message
     * @throws IOException
     */
    void sendMessage(Socket socket, String message) throws IOException;

    /**
     * Receives a message by getting the InputStream from the socket and translates through BufferedReader
     * @param socket
     * @return
     * @throws IOException
     */
    String receiveMessage(Socket socket) throws IOException;
}
