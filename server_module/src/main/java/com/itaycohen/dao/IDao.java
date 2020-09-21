package com.itaycohen.dao;

/**
 * An Data Access Object.  the Dao is an API which specifies functionality for save and retrieve data.
 */
public interface IDao {

    /**
     * @param parentPath The parent path for the dataSourceFileName location.
     * @param dataSourceFileName The file to search in.
     */
    String readFromFile(String parentPath, String dataSourceFileName);

    /**
     * Writes to a file
     * @param parentPath The parent path for the fileName location.
     * @param fileName the required existing file / file to create
     * @param content the content to write
     * @return true if write was successful
     */
    boolean saveToFile(String parentPath, String fileName, String content);

    /**
     * Deletes a file
     * @param parentPath The parent path for the fileName location.
     * @param fileName the desired file to delete.
     * @return true if the deletion was success.
     */
    boolean deleteFile(String parentPath, String fileName);

    /**
     * Iterates over the path to a file directory. supplies the .txt files name
     * @param path to the desired directory.
     * @return Array of files names.
     */
    String[] readAllTextFilesNamesIn(String path);
}
