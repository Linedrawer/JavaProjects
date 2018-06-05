package org.petprojects.java.Archiver.command;


import org.petprojects.java.Archiver.ConsoleHelper;
import org.petprojects.java.Archiver.ZipFileManager;
import org.petprojects.java.Archiver.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Extracting archive.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Enter path for extracting: ");
            Path destinationPath = Paths.get(ConsoleHelper.readString());
            zipFileManager.extractAll(destinationPath);

            ConsoleHelper.writeMessage("Archive was extracted");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Incorrect path for extracting");
        }
    }
}
