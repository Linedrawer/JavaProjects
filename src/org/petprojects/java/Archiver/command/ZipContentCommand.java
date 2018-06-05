package org.petprojects.java.Archiver.command;


import org.petprojects.java.Archiver.ConsoleHelper;
import org.petprojects.java.Archiver.FileProperties;
import org.petprojects.java.Archiver.ZipFileManager;

import java.util.List;

public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Overview of archive content");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Archive content:");

        List<FileProperties> files = zipFileManager.getFilesList();
        for (FileProperties file : files) {
            ConsoleHelper.writeMessage(file.toString());
        }

        ConsoleHelper.writeMessage("Archive content is read.");
    }
}
