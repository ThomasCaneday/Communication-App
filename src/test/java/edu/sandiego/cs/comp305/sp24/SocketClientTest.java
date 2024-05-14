package edu.sandiego.cs.comp305.sp24;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SocketClientTest {

    @Test
    public void testConnectToServer() throws IOException {
        Socket mockSocket = mock(Socket.class);

        SocketClient socketClient = new SocketClient();
        socketClient.connectToServer(mockSocket, "127.0.0.1", 8080);

        verify(mockSocket).connect(new InetSocketAddress("127.0.0.1", 8080), 5000);
    }
    @Test
    void testConnectForOneMessage() {
        SocketClient socketClient = mock(SocketClient.class);
        when(socketClient.connectForOneMessage("word", 1, "message")).thenReturn(null);
        String result = socketClient.connectForOneMessage("word", 1, "message");
        assertEquals(null, result);
    }

    @Test
    public void testSendMessage() throws IOException {
        String message = "Test message";

        // Mock the Socket and OutputStream objects
        Socket socketMock = mock(Socket.class);
        OutputStream outputMock = mock(OutputStream.class);
        when(socketMock.getOutputStream()).thenReturn(outputMock);

        SocketClient socketClient = new SocketClient();
        socketClient.sendMessage(socketMock, message);

        verify(outputMock, times(1)).flush();
        verify(outputMock, times(1)).write((message + "\n").getBytes());
    }

    @Test
    public void testReceiveMessage() throws IOException {
        String message = "Test message\n";
        InputStream inputMock = new ByteArrayInputStream(message.getBytes());

        Socket socketMock = mock(Socket.class);
        when(socketMock.getInputStream()).thenReturn(inputMock);

        SocketClient client = new SocketClient();
        String receivedMessage = client.receiveMessage(socketMock);

        assertEquals("Test message", receivedMessage, "Received message should match expected message");
    }

    @Test
    void testHandleException() {
        // Create a mock System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Exception testException = new Exception("Test exception message");
        SocketClient socketClient = mock(SocketClient.class);
        socketClient.handleException(testException);

        // Verify that the exception message was printed to the console
        String expectedOutput = "[client]: Client exception: Test exception message\n";
        assertEquals(expectedOutput, outContent.toString());

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);
    }
}