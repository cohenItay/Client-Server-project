package com.itaycohen.dm;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class SearchParams implements Serializable {

    private final String pattern;
    private final String dataSourceFileName;

    public SearchParams(
            @NotNull String pattern,
            @NotNull String dataSourceFileName
    ) throws IllegalStateException {
        Objects.requireNonNull(pattern);
        Objects.requireNonNull(dataSourceFileName);
        if (pattern.isEmpty() || dataSourceFileName.isEmpty())
            throw new IllegalStateException("nor pattern and dataSourceFileName cannot be empty");

        this.pattern = pattern;
        this.dataSourceFileName = dataSourceFileName;
    }

    public String getPattern() {
        return pattern;
    }

    public String getDataSourceFileName() {
        return dataSourceFileName;
    }

    @Override
    public String toString() {
        return "SearchParams{" +
                "pattern='" + pattern + '\'' +
                ", dataSourceFileName='" + dataSourceFileName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchParams that = (SearchParams) o;
        return Objects.equals(pattern, that.pattern) &&
                Objects.equals(dataSourceFileName, that.dataSourceFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, dataSourceFileName);
    }
}
