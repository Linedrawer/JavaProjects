## Chat
Chat application to exchange text messages

## Motivation & Goal
Create a program for exchanging text messages,
consists of server and several clients, one for each chat participant.


## Workflow
1. Create classes:
 - Server - server main class
 - MessageType - enum, responsible for message type between server and client
 - Message - class responsible for transmitted messages
 - Connection - class responsible connection between server and client
 - ConsoleHelper - auxiliary class for I/O in console  

2. Implementation of ConsoleHelper class.

3. Formulation key points of application protocol.
 
 - When new user wants to connect, server should ask client name.
 - When client receives name request from server it should send its name to server.
 - When server receives client name it should accept it and request new one.
 - When new client has connected to chat server should inform other chat participants about it.
 - When client disconnects, server should inform other clients about it
 - when server receives text message from client, it sent it to all other chat participants.

3.1 For each point of protocol create corresponding enum value to MessageType.
 
 - NAME_REQUEST – name request
 - USER_NAME – user name
 - NAME_ACCEPTED – name has been accepted
 - TEXT – text message
 - USER_ADDED – user has been added
 - USER_REMOVED – user has been removed

4. Text message of Message object is data which one side sends and other receives.
Every message should have MessageType type, some even additional data for example the content of text message.
As messages will be created and sent in one object and received and read by another it is convenient to use 
serialisation mechanism for transforming object to byte array and vice versa.

4.1 Implementation of Message class.
 Was done:
 - Serializable interface interface support
 - MessageType type, responsible for type of message
 - String data, message content
 - Constructor which will receive only MessageType
 - Constructor which receives MessageType and String data
 
5. Socket connection between server and client implementation.

6. Server implementation.
 Server should support several connections at the same time, following algorithm was used:
 - Server creates socket connection
 - During loop waits when user connects to socket
 - Creates new stream handler Handler where exchange of messages with client happens
 
7. Handler class implantation.
 Handle should implement  communication protocol with the client. It is realised by 3 stages:
 - Handshake (method serverHandshake), method receives connection and returns new client name
 - Send to new client notification about all other chat participants
 - Main loop of message processing by server
 
8. Client implementation.
 
9. SocketThread class implementation.

10. Additional task: Chat bot implementation.

10.1 BotSocketThread implementation which will inherit SocketThread 
and override some of its parent methods (such as clientMainLoop() ).
 
11. Additional task: GUI client.

11.1 To complete this task MVC model was used. 

## How to use

1. Run server main method.


## Results

- Server to exchange messages
- Console client, which is able to connect to server, send and receive messages from other users
- Chat bot client, in case you feel lonely
- GUI client

