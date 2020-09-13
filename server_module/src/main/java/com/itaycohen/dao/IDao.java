package com.itaycohen.dao;

/**
 * An Data Access Object.  the Dao is an API which specifies functionality for save and retrieve data.
 */
public interface IDao {

    /**
     * @param dataSourceFileName The file to search in.
     */
    String readFromFile(String dataSourceFileName);

    /**
     * Writes to a file
     * @param fileName the required existing file / file to create
     * @param content the content to write
     * @return true if write was successful
     */
    boolean saveToFile(String fileName, String content);
}
