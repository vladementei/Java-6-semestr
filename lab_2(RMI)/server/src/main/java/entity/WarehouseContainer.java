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
 * <b>Model to define List of {@link Warehouse warehouses}, that will be used by {@link repository.XMLDatabase} </b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
@XmlRootElement(name = "warehouses")
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseContainer {

    /**
     * List of {@link Warehouse warehouses}
     */
    @XmlElement(name = "warehouse")
    private List<Warehouse> warehouses = new ArrayList<>();

    /**
     * getter for List of {@link Warehouse warehouses}
     * @return List of {@link Warehouse warehouses} of current instance
     */
    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    /**
     * setter for List of {@link Warehouse warehouses}
     * @param warehouses new List of {@link Warehouse warehouses} for current instance
     */
    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    /**
     * override {@link Object} equals method for all class members
     * @param o List of {@link Warehouse warehouses} to compare
     * @return true if products are equal, else - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseContainer that = (WarehouseContainer) o;
        return Objects.equals(getWarehouses(), that.getWarehouses());
    }

    /**
     * override {@link Object} hashcode method for all class members
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getWarehouses());
    }

    /**
     * override {@link Object} toString method for all class members
     * @return string value of current instance
     */
    @Override
    public String toString() {
        return "WarehouseContainer{" +
                "warehouses=" + warehouses.stream().map(Warehouse::toString).collect(Collectors.joining("; ")) +
                '}';
    }
}

