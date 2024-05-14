# Communication App

## Description
This Java application demonstrates basic socket communication between a server and a client. The server listens for incoming connections on a specified port, and the client connects to the server and sends a message. The server then receives and displays the message from the client.

## Files
- **SocketClient.java:** Implements the client-side functionality for socket communication. It connects to a server, sends a message, and receives a confirmation response.
- **SocketServer.java:** Implements the server-side functionality for socket communication. It listens for incoming connections, handles client requests in separate threads, and sends confirmation responses back to clients.
- **SocketDriver.java:** The main driver program that users run. It prompts the user for server port, IP address, and port to connect to, then starts a socket server and enters a loop to send messages to the server.

## Installation
1. Clone the repository: `git clone https://github.com/your_username/Communication-App.git`
2. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).

## Usage
1. Run the `SocketDriver.java` file to start the program.
2. Follow the instructions in the console to enter the server port, IP address, and port to connect to.
3. Enter messages to send to the server and observe the responses.

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your_feature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your_feature`).
5. Create a new Pull Request.
