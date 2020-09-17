package com.itaycohen.dao;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public @Nullable String readFromFile(String dataSourceFileName) {
        if (lastUsedFileName== null || !lastUsedFileName.equalsIgnoreCase(dataSourceFileName) || file == null)
            file = createOrLoadFileFor(dataSourceFileName, false);
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
    public boolean saveToFile(String fileName, String content) {
        try {
            File file = createOrLoadFileFor(fileName, true);
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
    public boolean deleteFile(String fileName) {
        File file = createOrLoadFileFor(fileName, false);
        return file.delete();
    }

    private File createOrLoadFileFor(String fileName, boolean forceCreate) {
        String tempFileName = fileName;
        if (!fileName.endsWith(".txt"))
            tempFileName += ".txt";
        File file = null;
        try {
            file = new File(System.getProperty("user.dir")+"/server_module/src/main/resources/books", tempFileName);
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
