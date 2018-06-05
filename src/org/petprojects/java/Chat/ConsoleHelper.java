package org.petprojects.java.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void writeMessage(String message) {
        System.out.println(message);
    }


    public static String readString() {

        String message;

        while (true) {

            try {
                message = reader.readLine();
                break;

            } catch (IOException e) {
                System.out.println("An error has occurred during text input, try again");
            }
        }
        return message;
    }


    public static int readInt() {

        int i;

        while (true) {

            try {
                i = Integer.parseInt(readString());
                break;

            } catch (NumberFormatException e) {
                System.out.println("An error has occurred during text input, try again");
            }
        }
        return i;
    }
}

