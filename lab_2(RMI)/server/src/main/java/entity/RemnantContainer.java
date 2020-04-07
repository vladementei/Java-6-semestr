package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@XmlRootElement(name = "remnants")
@XmlAccessorType(XmlAccessType.FIELD)
public class RemnantContainer {

    @XmlElement(name = "remnant")
    private List<Remnant> remnants = new ArrayList<>();

    public List<Remnant> getRemnants() {
        return remnants;
    }

    public void setRemnants(List<Remnant> remnants) {
        this.remnants = remnants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemnantContainer that = (RemnantContainer) o;
        return Objects.equals(getRemnants(), that.getRemnants());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRemnants());
    }

    @Override
    public String toString() {
        return "RemnantContainer{" +
                "remnants=" + remnants.stream().map(Remnant::toString).collect(Collectors.joining("; ")) +
                '}';
    }
}

