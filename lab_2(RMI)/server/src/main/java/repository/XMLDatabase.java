package repository;

import exception.RepositoryException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public abstract class XMLDatabase<T> {
    private String TABLE_NAME;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    protected T entity;

    private String getFilePath() {
        if (new File(".").getAbsolutePath().endsWith("target\\.")){
            return new File(".").getAbsoluteFile().getParentFile().getParentFile().getPath() + "\\db\\" + TABLE_NAME + ".xml";
        } else {
            return new File(".").getAbsoluteFile().getParentFile().getPath() + "\\server\\db\\" + TABLE_NAME + ".xml";
        }
    }

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

    protected void saveToDataBase() throws RepositoryException {
        try (Writer writer = new FileWriter(getFilePath())) {
            marshaller.marshal(entity, writer);
        } catch (JAXBException | IOException e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
