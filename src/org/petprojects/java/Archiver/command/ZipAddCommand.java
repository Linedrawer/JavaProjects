package org.petprojects.java.Archiver.command;


import org.petprojects.java.Archiver.ConsoleHelper;
import org.petprojects.java.Archiver.ZipFileManager;
import org.petprojects.java.Archiver.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipAddCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Adding new file to archive.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Enter full file name you want to add:");
            Path sourcePath = Paths.get(ConsoleHelper.readString());

            zipFileManager.addFile(sourcePath);

            ConsoleHelper.writeMessage("Adding to archive is successful");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("File not found.");
        }
    }
}
