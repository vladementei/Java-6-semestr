package entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * <b>Model to define Warehouse</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">2.0</span>
 */
@XmlRootElement(name = "warehouse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse implements Serializable {
    /**
     * key to check serialization
     */
    private static final long serialVersionUID = 5678L;

    /**
     * warehouse id - unique
     */
    @XmlAttribute
    private int id;

    /**
     * warehouse title
     */
    @XmlElement
    private String title;

    /**
     * warehouse location
     */
    @XmlElement
    private String location;

    /**
     * default construct (used by {@link repository.XMLDatabase})
     */
    public Warehouse() {
        this.id = 0;
        this.title = "";
        this.location = "";
    }

    /**
     * Constructor with all params
     * @param id warehouse id
     * @param title warehouse title
     * @param location warehosue location
     */
    public Warehouse(int id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    /**
     * getter for id
     * @return warehouse id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for id
     * @param id new warehouse id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for title
     * @return warehouse title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for title
     * @param title new warehouse title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter for location
     * @return warehouse location
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter for location
     * @param location new warehouse location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * override {@link Object} equals method for all class members
     * @param o warehouse to compare
     * @return true if warehouses are equal, else - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return getId() == warehouse.getId() &&
                Objects.equals(getTitle(), warehouse.getTitle()) &&
                Objects.equals(getLocation(), warehouse.getLocation());
    }

    /**
     * override {@link Object} hashcode method for all class members
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getLocation());
    }

    /**
     * override {@link Object} toString method for all class members
     * @return string value of current instance
     */
    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
