package org.petprojects.java.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    // ключ -имя клиента, а значение - соединение с ним
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();


    /** MAIN **/
    public static void main(String[] args) throws IOException {

        ConsoleHelper.writeMessage("Enter server port: ");
        int serverPort = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {

            ConsoleHelper.writeMessage("Server is running");

            while (true) {
                //listen
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);

                handler.start();
            }
        }

    }


    /** send message to all **/
    public static void sendBroadcastMessage(Message message) {

        try {

            for (Connection connection : connectionMap.values()) {
                connection.send(message);
            }

        } catch (Exception e){
            e.printStackTrace();
            ConsoleHelper.writeMessage("Message has not been sent");
        }

    }


    /** handler where happen exchange of data with client **/
    private static class Handler extends Thread {

        private Socket socket;

        //Constructor
        public Handler(Socket socket) {

            this.socket = socket;
        }


        @Override
        public void run() {

            ConsoleHelper.writeMessage("Connection has been successful. Address:  " + socket.getRemoteSocketAddress());
            String clientName = null;
            try (Connection connection = new Connection(socket)) {
                //notify that new connection is being set
                ConsoleHelper.writeMessage("Connecting to port: " + connection.getRemoteSocketAddress());
                //Method performs handshake with client, saving new client name
                clientName = serverHandshake(connection);
                //Notify all participants regarding new chat participant (message with USER_ADDED type)
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                //Inform new user about existing users
                sendListOfUsers(connection, clientName);
                //Run main message processing loop with server
                serverMainLoop(connection, clientName);


            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Error while exchanging data with remote address");
            }

            //After exception are handled, delete clientName from connectionMap
            connectionMap.remove(clientName);
            //send notification message to all other users
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));

            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");

        }

        /** Handshake **/
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            while (true) {
                //Form and send user name request command
                connection.send(new Message(MessageType.NAME_REQUEST));
                //Receive client response
                Message message = connection.receive();

                //Check whether command was received together with user name
                if (message.getType() == MessageType.USER_NAME) {

                    //Get name from data and check that is not empty
                    if (message.getData() != null && !message.getData().isEmpty()) {

                        //if user which such user is not connected yet (use connectionMap)
                        if (connectionMap.get(message.getData()) == null) {

                            //Add new user and its connection in connectionMap
                            connectionMap.put(message.getData(), connection);
                            //Sent to user info that his name is accepted
                            connection.send(new Message(MessageType.NAME_ACCEPTED));

                            //Return accepted name as a return value
                            return message.getData();
                        }
                    }
                }
            }
        }


        /** Send list of all users **/
        private void sendListOfUsers(Connection connection, String userName) throws IOException {

            for (String key : connectionMap.keySet()) {
                Message message = new Message(MessageType.USER_ADDED, key);

                if (!key.equals(userName)) {
                    connection.send(message);
                }
            }
        }


        /** Main message processing loop with server **/
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true) {

                Message message = connection.receive();
                // If received message - it is a text
                if (message.getType() == MessageType.TEXT) {

                    String s = userName + ": " + message.getData();

                    Message formattedMessage = new Message(MessageType.TEXT, s);
                    sendBroadcastMessage(formattedMessage);
                } else {
                    ConsoleHelper.writeMessage("Error");
                }
            }
        }
    }
}


