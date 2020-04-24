package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * <b>Model to define Product</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">2.0</span>
 */
public class Product implements Serializable {
    /**
     * key to check serialization
     */
    private static final long serialVersionUID = 1234L;
    /**
     * product id - unique
     */
    private int id;
    /**
     * product name
     */
    private String name;
    /**
     * product description
     */
    private String description;

    /**
     * Constructor with all params
     * @param id product id
     * @param name product name
     * @param description product description
     */
    public Product(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * getter for id
     * @return product id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for id
     * @param id new product id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for name
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name new product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for description
     * @return product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for description
     * @param description new product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * override {@link Object} equals method for all class members
     * @param o product to compare
     * @return true if products are equal, else - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId() == product.getId() &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getDescription(), product.getDescription());
    }

    /**
     * override {@link Object} hashcode method for all class members
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }

    /**
     * override {@link Object} toString method for all class members
     * @return string value of current instance
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
