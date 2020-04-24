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
 * <b>Model to define List of {@link Remnant remnants}, that will be used by {@link repository.XMLDatabase} </b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
@XmlRootElement(name = "remnants")
@XmlAccessorType(XmlAccessType.FIELD)
public class RemnantContainer {

    /**
     * List of {@link Remnant remnants}
     */
    @XmlElement(name = "remnant")
    private List<Remnant> remnants = new ArrayList<>();

    /**
     * getter for List of {@link Remnant remnants}
     * @return List of {@link Remnant remnants} of current instance
     */
    public List<Remnant> getRemnants() {
        return remnants;
    }

    /**
     * setter for List of {@link Remnant remnants}
     * @param remnants new List of {@link Remnant remnants} for current instance
     */
    public void setRemnants(List<Remnant> remnants) {
        this.remnants = remnants;
    }

    /**
     * override {@link Object} equals method for all class members
     * @param o List of {@link Remnant remnants} to compare
     * @return true if products are equal, else - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemnantContainer that = (RemnantContainer) o;
        return Objects.equals(getRemnants(), that.getRemnants());
    }

    /**
     * override {@link Object} hashcode method for all class members
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRemnants());
    }

    /**
     * override {@link Object} toString method for all class members
     * @return string value of current instance
     */
    @Override
    public String toString() {
        return "RemnantContainer{" +
                "remnants=" + remnants.stream().map(Remnant::toString).collect(Collectors.joining("; ")) +
                '}';
    }
}

