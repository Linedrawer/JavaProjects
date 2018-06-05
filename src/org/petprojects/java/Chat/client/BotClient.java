package org.petprojects.java.Chat.client;



import org.petprojects.java.Chat.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    // Bots counter
    private static int botsCounter = 0;


    /** PSVM **/
    public static void main(String args []) {
        new BotClient().run();
    }


    /** inner class */
    public class BotSocketThread extends SocketThread {


        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            sendTextMessage("Hi. I am bot. I understand following messages: date, day, month, year, time, hour, minutes, seconds.");

            super.clientMainLoop();

        }


        @Override
        protected void processIncomingMessage(String message) {

            ConsoleHelper.writeMessage(message);

            String senderName = "";
            String senderMessageText;

            if (message.contains(": ")) {
                senderName = message.substring(0, message.indexOf(": "));
                senderMessageText = message.substring(message.indexOf(": ") + 2);
            }
            else {
                senderMessageText = message;
            }


            SimpleDateFormat format = null;
            if ("date".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("d.MM.YYYY");
            }
            else if ("date".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("d");
            }
            else if ("month".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("MMMM");
            }
            else if ("year".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("YYYY");
            }
            else if ("time".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("H:mm:ss");
            }
            else if ("hour".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("H");
            }
            else if ("minutes".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("m");
            }
            else if ("seconds".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("s");
            }

            if (format != null)
            {
                sendTextMessage("Info for " + senderName + ": " + format.format(Calendar.getInstance().getTime()));
            }

        }

    }

    /** methods **/

    @Override
    protected SocketThread getSocketThread() {
        //creates and returns BotSocketThread
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        //always returns false. We do not want the bot to send text sent to console
        return false;
    }

    @Override
    protected String getUserName() {
        //method generates new bot name, as date_bot_XX, where XX - number from 0 to 99
        //it is for case where multiple bot are running on a server
        if (botsCounter == 99) {
            botsCounter = 0;
        }

        return "date_bot_" + botsCounter++;

    }
}
