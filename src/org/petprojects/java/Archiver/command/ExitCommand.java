package org.petprojects.java.Archiver.command;


import org.petprojects.java.Archiver.ConsoleHelper;

public class ExitCommand extends ZipCommand {

    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("See you soon!");
    }
}
