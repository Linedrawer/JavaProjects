package org.petprojects.java.Archiver.command;


import org.petprojects.java.Archiver.ConsoleHelper;
import org.petprojects.java.Archiver.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Removing file from archive");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Enter full path name of file in archive: ");
        Path sourcePath = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(sourcePath);

        ConsoleHelper.writeMessage("Deleting is completed");
    }
}