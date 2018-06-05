package org.petprojects.java.Archiver.command;


import org.petprojects.java.Archiver.ConsoleHelper;
import org.petprojects.java.Archiver.ZipFileManager;
import org.petprojects.java.Archiver.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipCreateCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Creating archive.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Enter full file name or directory to be archived: ");
            Path sourcePath = Paths.get(ConsoleHelper.readString());
            zipFileManager.createZip(sourcePath);

            ConsoleHelper.writeMessage("Archive is created.");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Incorrect name of file or directory");
        }
    }
}
