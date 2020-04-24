package repository;

import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import exception.RepositoryException;

import java.io.*;

/**
 * <b>Parent class for all JSON repositories to establish connection with database</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public abstract class JSONDatabase {
    /**
     * name of table for current instance
     */
    protected String TABLE_NAME;

    /**
     * instance of JSON file in memory
     */
    protected DocumentContext document;

    /**
     * parser to convert JSON file to JAVA object
     */
    protected Gson parser;

    /**
     * get json database location (if run jar then return from "if", if run from IntelijIDEA then return from "else")
     * @return absolute path to database
     */
    private String getFilePath() {
        if (new File(".").getAbsolutePath().endsWith("target\\.")){
            return new File(".").getAbsoluteFile().getParentFile().getParentFile().getPath() + "\\db\\" + TABLE_NAME + ".json";
        } else {
            return new File(".").getAbsoluteFile().getParentFile().getPath() + "\\server\\db\\" + TABLE_NAME + ".json";
        }
    }

    /**
     * constructor to establish connection with database and convert in to appropriate form
     * @param tableName name of JSON file with database
     * @throws RepositoryException if connection throws error
     */
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

    /**
     * saving current updates from memory to JSON database
     * @throws RepositoryException if writing to database failed
     */
    protected void saveToDataBase() throws RepositoryException {
        try (Writer writer = new FileWriter(getFilePath())) {
            writer.write(document.jsonString());
        } catch (IOException e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
