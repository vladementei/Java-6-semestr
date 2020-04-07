package entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "warehouse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse implements Serializable {
    private static final long serialVersionUID = 5678L;

    @XmlAttribute
    private int id;
    @XmlElement
    private String title;
    @XmlElement
    private String location;

    public Warehouse() {
        this.id = 0;
        this.title = "";
        this.location = "";
    }

    public Warehouse(int id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return getId() == warehouse.getId() &&
                Objects.equals(getTitle(), warehouse.getTitle()) &&
                Objects.equals(getLocation(), warehouse.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getLocation());
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
