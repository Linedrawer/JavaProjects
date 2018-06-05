package org.petprojects.java.Chat.client;



import org.petprojects.java.Chat.Connection;
import org.petprojects.java.Chat.ConsoleHelper;
import org.petprojects.java.Chat.Message;
import org.petprojects.java.Chat.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {

    protected Connection connection;
    private volatile boolean clientConnected = false;


    /** PSVM Client **/
    public static void main(String[] args) {

        Client client = new Client();
        client.run();
    }


    /** Methods **/
    /** run **/
    public void run() {

        SocketThread socketThread = getSocketThread();
        //mark thread as daemon, so when user exits it closes automatically
        socketThread.setDaemon(true);
        //run auxiliary thread
        socketThread.start();

        //Make current thread wait until it receives a notification from another thread
        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Error");
            return;
        }

        //After thread receives notification, check clientConnected value
        if (clientConnected) {
            ConsoleHelper.writeMessage("Connected. To exit type 'exit' and press enter.");

            //Read data from console while client is connected, if it receives command 'exit', break the loop
            while (clientConnected) {
                String message;
                if (!(message = ConsoleHelper.readString()).equals("exit")) {
                    if (shouldSentTextFromConsole()) {
                        sendTextMessage(message);
                    }
                } else {
                    return;
                }
            }
        }
        else {
            ConsoleHelper.writeMessage("Error occurred during work of client");
        }
    }


    /** Request server address and return its value **/
    protected String getServerAddress() {

        ConsoleHelper.writeMessage("Enter server address: ");
        return ConsoleHelper.readString();
    }


    /** Request server port and return it **/

    protected int getServerPort() {

        ConsoleHelper.writeMessage("Enter server port: ");
        return ConsoleHelper.readInt();
    }


    /** Request user name and return it **/
    protected String getUserName() {

        ConsoleHelper.writeMessage("Enter user name: ");
        return ConsoleHelper.readString();
    }


    protected boolean shouldSentTextFromConsole() {

        return true;
    }


    /** create and return new SocketThread object **/
    protected SocketThread getSocketThread() {

        return new SocketThread();
    }


    /**  create new text message, using this text send it to server via 'connection' connection **/
    protected void sendTextMessage(String text) {

        try {
            connection.send(new Message(MessageType.TEXT, text));

        } catch (IOException e) {
            ConsoleHelper.writeMessage("Error during sending");
            clientConnected = false;
        }
    }


    /** SocketThread **/
    public class SocketThread extends Thread {

        /** Methods **/
        public void run() {

            try {

                Socket socket = new Socket(getServerAddress(), getServerPort());
                Client.this.connection = new Connection(socket);

                clientHandshake();
                clientMainLoop();


            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }

        }


        /** Method implements client main loop of message processing **/
        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            while (true) {

                Message message = connection.receive();

                switch (message.getType()) {

                    case TEXT:
                        processIncomingMessage(message.getData());
                        break;

                    case USER_ADDED:
                        informAboutAddingNewUser(message.getData());
                        break;

                    case USER_REMOVED:
                        informAboutDeletingNewUser(message.getData());
                        break;

                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }


        /** clientHandshake **/
        protected void clientHandshake() throws IOException, ClassNotFoundException {

            while (true) {

                Message message = connection.receive();

                switch (message.getType()) {

                    case NAME_REQUEST: {
                        String userName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, userName));
                        break;
                    }

                    case NAME_ACCEPTED: {
                        notifyConnectionStatusChanged(true);
                        return;
                    }

                    default: {
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }
        }


        /** return text of message to console **/
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }


        /** prints to console notification that participant with userName name has connected to chat **/
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("participant " + userName + " has joined chat");
        }


        /**  prints to console notification that participant with userName name has disconnected from chat **/
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("participant " + userName + " has left chat");
        }


        /** Set value of clientConnected of Client class according to received parameter.
         Notify main thread of Client class **/
        protected void notifyConnectionStatusChanged(boolean clientConnected) {


            Client.this.clientConnected = clientConnected;

            synchronized (Client.this) {
                Client.this.notify();
            }
        }
    }
}

