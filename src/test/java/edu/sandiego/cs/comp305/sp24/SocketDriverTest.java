package edu.sandiego.cs.comp305.sp24;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SocketDriverTest {

    @Test
    public void testGetPortFromUser() {
        String port = "8080\n"; // Add a newline character to simulate pressing Enter
        System.setIn(new ByteArrayInputStream(port.getBytes()));
        SocketDriver socketDriver = Mockito.mock(SocketDriver.class);
        int userInput = socketDriver.getPortFromUser("Enter port for this server to listen on: ");
        assertEquals(8080, userInput);
    }

    @Test
    public void testGetInputFromUser() {
        String input = "127.0.0.1\n"; // Add a newline character to simulate pressing Enter
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SocketDriver socketDriver = Mockito.mock(SocketDriver.class);
        String userInput = socketDriver.getInputFromUser("Enter IP address of server to connect to: ");
        assertEquals("127.0.0.1", userInput);
    }

    @Test
    public void testStartSocketServer() {
        int port = 1234;
        SocketDriver server = mock(SocketDriver.class);
        Thread serverThread = server.startSocketServer(port);
        assertTrue(serverThread.isAlive(), "Server should be running");
    }

    @Test
    public void testConnectToSocketServer() {
        String ip = "127.0.0.1";
        int port = 8080;
        String message = "Test message";
        String expectedResult = "Message was ACTUALLY received (Test message)";

        SocketDriver socketDriver = Mockito.mock(SocketDriver.class);
        String actualResult = socketDriver.connectToSocketServer(ip, port, message);
        assertEquals(expectedResult, actualResult);
    }
}
