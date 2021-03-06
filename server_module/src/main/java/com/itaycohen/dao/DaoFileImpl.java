package com.itaycohen.dao;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An implementation for {@link IDao}.
 * Can read a file content.
 */
public class DaoFileImpl implements IDao {

    private final Logger logger = Logger.getLogger(DaoFileImpl.class.getSimpleName());
    private @Nullable File file;
    private String lastUsedFileName;


    public DaoFileImpl() { }

    @Override
    public synchronized @Nullable String readFromFile(String parentPath, String dataSourceFileName) {
        if (lastUsedFileName== null || !lastUsedFileName.equalsIgnoreCase(dataSourceFileName) || file == null)
            file = createOrLoadFileFor(parentPath, dataSourceFileName, false);
        if (file == null || !file.exists())
            return null;

        lastUsedFileName = dataSourceFileName;
        @NotNull StringBuilder content = new StringBuilder();
        @Nullable String line;
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(file));
            while ((line = fileReader.readLine()) != null) {
                content.append(line);
            }
        } catch(FileNotFoundException fnfe) {
            logger.log(Level.SEVERE, String.format("File %s not found", file.getName()));
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            logger.log(Level.SEVERE, "Can't read file");
            ioe.printStackTrace();
            content.delete(0, content.length());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ioe) {
                    logger.log(Level.WARNING, "Can't close file inputstream");
                    ioe.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    public synchronized String[] readAllTextFilesNamesIn(String path) {
        File file = new File(path);
        if (!file.isDirectory())
            return null;

        try (Stream<Path> stream = Files.walk(Paths.get(path), 1)) {
            return stream
                    .filter(f -> !Files.isDirectory(f))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized boolean saveToFile(String parentPath, String fileName, String content) {
        try {
            File file = createOrLoadFileFor(parentPath, fileName, true);
            if (file == null)
                return false;
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(content == null ? "" : content);
            myWriter.close();
            return true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cannot write to file", e);
            return false;
        }
    }

    @Override
    public synchronized boolean deleteFile(String parentPath, String fileName) {
        File file = createOrLoadFileFor(parentPath, fileName, false);
        return file.delete();
    }

    private File createOrLoadFileFor(String parentPath, String fileName, boolean forceCreate) {
        String tempFileName = fileName;
        if (!fileName.endsWith(".txt"))
            tempFileName += ".txt";
        File file = null;
        try {
            file = new File(parentPath, tempFileName);
            if (forceCreate) {
                if (!file.createNewFile())
                    logger.log(Level.INFO, "File already exists");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cant create file", e);
        }
        return file;
    }
}
