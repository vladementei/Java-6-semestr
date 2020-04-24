package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <b>Model to define List of {@link Product products}, that will be used by {@link repository.XMLDatabase} </b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductContainer {

    /**
     * List of {@link Product products}
     */
    @XmlElement(name = "product")
    private List<Product> products = new ArrayList<>();

    /**
     * getter for List of {@link Product products}
     * @return List of {@link Product products} of current instance
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * setter for List of {@link Product products}
     * @param products new List of {@link Product products} for current instance
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * override {@link Object} equals method for all class members
     * @param o List of {@link Product products} to compare
     * @return true if products are equal, else - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductContainer that = (ProductContainer) o;
        return Objects.equals(getProducts(), that.getProducts());
    }

    /**
     * override {@link Object} hashcode method for all class members
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getProducts());
    }

    /**
     * override {@link Object} toString method for all class members
     * @return string value of current instance
     */
    @Override
    public String toString() {
        return "ProductContainer{" +
                "products=" + products.stream().map(Product::toString).collect(Collectors.joining("; ")) +
                '}';
    }
}
