package repository;

import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import exception.RepositoryException;

import java.io.*;

public abstract class JSONDatabase {
    protected String TABLE_NAME;
    protected DocumentContext document;
    protected Gson parser;

    private String getFilePath() {
        if (new File(".").getAbsolutePath().endsWith("target\\.")){
            return new File(".").getAbsoluteFile().getParentFile().getParentFile().getPath() + "\\db\\" + TABLE_NAME + ".json";
        } else {
            return new File(".").getAbsoluteFile().getParentFile().getPath() + "\\server\\db\\" + TABLE_NAME + ".json";
        }
    }

    public JSONDatabase(String tableName) throws RepositoryException {
        this.TABLE_NAME = tableName;
        parser = new Gson();
        System.out.println("Connecting to " + getFilePath());
        try (InputStream is = new FileInputStream(new File(getFilePath()))) {
            document = JsonPath
                    .using(Configuration.builder().jsonProvider(new GsonJsonProvider()).mappingProvider(new GsonMappingProvider()).build())
                    .parse(is);
        } catch (IOException | RuntimeException e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    protected void saveToDataBase() throws RepositoryException {
        try (Writer writer = new FileWriter(getFilePath())) {
            writer.write(document.jsonString());
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
