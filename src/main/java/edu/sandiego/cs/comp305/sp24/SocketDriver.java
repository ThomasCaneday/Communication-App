package edu.sandiego.cs.comp305.sp24;

import java.util.Scanner;

public class SocketDriver {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main method to start the socket driver program.
     * Prompts the user for server port, IP address, and port to connect to,
     * then starts a socket server and enters a loop to send messages to the server.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int thisServerPort = getPortFromUser("Enter port for this server to listen on: ");
        scanner.nextLine(); //Get rid of \n after input
        String otherServerIP = getInputFromUser("Enter IP address of server to connect to: ");
        int otherServerPort = getPortFromUser("Enter port of server to connect to: ");
        scanner.nextLine(); //Get rid of \n after input

        startSocketServer(thisServerPort);

        while (true) {
            String message = getInputFromUser("Enter message: ");

            String reply = connectToSocketServer(otherServerIP, otherServerPort, message);
            System.out.println("[client]: Reply from server: " + reply);
        }
    }

    /**
     * Prompts the user for a port number and returns it.
     * @param message the message to display to the user
     * @return the port number entered by the user
     */
    static int getPortFromUser(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    /**
     * Prompts the user for input and returns it.
     * @param message the message to display to the user
     * @return the input entered by the user
     */
    static String getInputFromUser(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Starts a socket server on the specified port, creates a Thread, and starts that Thread.
     * @param port the port number to listen on
     */
    static Thread startSocketServer(int port) {
        SocketServer server = new SocketServer(port);
        Thread serverThread = new Thread(server);
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverThread;
    }

    /**
     * Connects to a socket server, sends a message, and returns the server's confirmation response.
     * @param ip the IP address of the server
     * @param port the port number of the server
     * @param message the message to send
     * @return the server's reply
     */
    public static String connectToSocketServer(String ip, int port, String message) {
        SocketClient client = new SocketClient();
        return client.connectForOneMessage(ip, port, message);
    }
}
