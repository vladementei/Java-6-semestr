package repository;

import exception.RepositoryException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * <b>Parent class for all XML repositories to establish connection with database</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public abstract class XMLDatabase<T> {

    /**
     * name of table for current instance
     */
    private String TABLE_NAME;

    /**
     * parser to convert from XML to Java object
     */
    private Marshaller marshaller;

    /**
     * parser to convert from Java object to XML
     */
    private Unmarshaller unmarshaller;

    /**
     * instance of XML file in memory
     */
    protected T entity;

    /**
     * get xml database location (if run jar then return from "if", if run from IntelijIDEA then return from "else")
     * @return absolute path to database
     */
    private String getFilePath() {
        if (new File(".").getAbsolutePath().endsWith("target\\.")){
            return new File(".").getAbsoluteFile().getParentFile().getParentFile().getPath() + "\\db\\" + TABLE_NAME + ".xml";
        } else {
            return new File(".").getAbsoluteFile().getParentFile().getPath() + "\\server\\db\\" + TABLE_NAME + ".xml";
        }
    }

    /**
     * constructor to establish connection with database and convert in to appropriate form
     * @param tableName name of XML file with database
     * @param entity instance of XML file in memory to write data in
     * @throws RepositoryException if connection throws error
     */
    public XMLDatabase(String tableName, T entity) throws RepositoryException {
        this.TABLE_NAME = tableName;
        this.entity = entity;
        try {
            JAXBContext context = JAXBContext.newInstance(entity.getClass());
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            unmarshaller = context.createUnmarshaller();
            System.out.println("Connecting to " + getFilePath());
            try (InputStream is = new FileInputStream(new File(getFilePath()))) {
                this.entity = (T) unmarshaller.unmarshal(is);
            }
        }catch (JAXBException | IOException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * saving current updates from memory to XML database
     * @throws RepositoryException if writing to database failed
     */
    protected void saveToDataBase() throws RepositoryException {
        try (Writer writer = new FileWriter(getFilePath())) {
            marshaller.marshal(entity, writer);
        } catch (JAXBException | IOException e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
