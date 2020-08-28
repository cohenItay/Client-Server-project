package com.itaycohen.dao;

/**
 * An Data Access Object.  the Dao is an API which specifies functionality for save and retrieve data.
 */
public interface IDao {

    /**
     * @param dataSourceFileName The file to search in.
     */
    String readFileContent(String dataSourceFileName);
}
