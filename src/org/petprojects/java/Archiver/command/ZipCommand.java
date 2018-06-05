package org.petprojects.java.Archiver.command;


import org.petprojects.java.Archiver.ConsoleHelper;
import org.petprojects.java.Archiver.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ZipCommand implements Command {

    public ZipFileManager getZipFileManager() throws Exception{
        ConsoleHelper.writeMessage("Enter full path of archive file :");
        Path zipPath = Paths.get(ConsoleHelper.readString());
        return new ZipFileManager(zipPath);
    }
}