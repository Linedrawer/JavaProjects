package org.petprojects.java.Archiver;


import org.petprojects.java.Archiver.exception.WrongZipFileException;

import java.io.IOException;

public class Archiver {
    public static void main(String[] args) throws IOException {

        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("You did not chose an archive file or incorrect file was chosen");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("An error has occurred, check input data");
            }

        } while (operation != Operation.EXIT);
    }


    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Choose operation:");
        ConsoleHelper.writeMessage(String.format("\t %d - pack files to archive", Operation.CREATE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - add file to archive", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - delete file from archive", Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - extract files from archive", Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - Overview archive content", Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - Exit", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}