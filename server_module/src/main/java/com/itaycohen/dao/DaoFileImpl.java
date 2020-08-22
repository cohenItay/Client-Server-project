package com.itaycohen.dao;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation for {@link IDao}.
 * Can read a file content.
 */
public class DaoFileImpl implements IDao {

    private final Logger logger = Logger.getLogger(DaoFileImpl.class.getSimpleName());
    private final @Nullable File file;

    /**
     * @param fileName The file name which must be inside the java Resources directory.
     */
    public DaoFileImpl(String fileName) {
        this.file = createFileFor(fileName);
    }

    @Override
    public String readFileContent() {
        if (file == null)
            return  "";

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

    private File createFileFor(String fileName) {
        URL resource = getClass().getResource("/" + fileName);
        File file = null;
        try {
            file = Paths.get(resource.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        };
        return file;
    }
}
