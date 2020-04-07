package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@XmlRootElement(name = "warehouses")
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseContainer {

    @XmlElement(name = "warehouse")
    private List<Warehouse> warehouses = new ArrayList<>();

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseContainer that = (WarehouseContainer) o;
        return Objects.equals(getWarehouses(), that.getWarehouses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWarehouses());
    }

    @Override
    public String toString() {
        return "WarehouseContainer{" +
                "warehouses=" + warehouses.stream().map(Warehouse::toString).collect(Collectors.joining("; ")) +
                '}';
    }
}

