package edu.sandiego.cs.comp305.sp24;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class SocketServerTest {

    @Test
    public void testGetPort() {
        int port = 8080;
        SocketServer socketServer = new SocketServer(8080);

        assertEquals(port, socketServer.getPort());
    }

    @Test
    public void testSetPort() {
        int port = 8080;
        SocketServer socketServer = new SocketServer(8081);
        socketServer.setPort(port);

        assertEquals(port, socketServer.getPort());
    }


    @Test
    public void testRun() throws IOException {
        SocketServer socketServer = Mockito.mock(SocketServer.class);

        // Mock the start method to do nothing
        doNothing().when(socketServer).start();

        // Call the run method
        socketServer.run();

        // Verify that start and stop were called
        verify(socketServer).start();
        verify(socketServer).stop();
    }

    @Test
    public void testReceiveMessage() throws IOException {
        String message = "Test message\n";
        InputStream inputMock = new ByteArrayInputStream(message.getBytes());

        Socket socketMock = mock(Socket.class);
        when(socketMock.getInputStream()).thenReturn(inputMock);

        SocketServer server = new SocketServer(8080);
        String receivedMessage = server.receiveMessage(socketMock);

        assertEquals("Test message", receivedMessage, "Received message should match expected message");
    }

    @Test
    public void testSendMessage() throws IOException {
        String message = "Test message";

        // Mock the Socket and OutputStream objects
        Socket socketMock = mock(Socket.class);
        OutputStream outputMock = mock(OutputStream.class);
        when(socketMock.getOutputStream()).thenReturn(outputMock);

        SocketServer socketServer = new SocketServer(8080);
        socketServer.sendMessage(socketMock, message);

        verify(outputMock, times(1)).flush();
        verify(outputMock, times(1)).write((message + "\n").getBytes());
    }
}