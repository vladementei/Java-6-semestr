package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import sample.Dialogs;

/**
 * <b>Class to read YAML file and configure endpoints of server</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class EndPointsConfiguration {
    /**
     * singleton instance
     */
    private static EndPointsConfiguration instance = new EndPointsConfiguration();
    /**
     * address of SQL endpoint
     */
    public final String SQL_ENDPOINT;
    /**
     * address of JSON endpoint
     */
    public final String JSON_ENDPOINT;
    /**
     * address of XML endpoint
     */
    public final String XML_ENDPOINT;

    /**
     * default constructor to define localhost endpoints if global fail
     */
    private EndPointsConfiguration() {
    }

    {
        SQL_ENDPOINT = "rmi://localhost:8080/sql-server";
        JSON_ENDPOINT = "rmi://localhost:8080/json-server";
        XML_ENDPOINT = "rmi://localhost:8080/xml-server";
    }

    static {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, true);
        try {
            EndPointsConfiguration.instance = mapper.readValue(EndPointsConfiguration.class.getClassLoader().getResource("endPointConfiguration.yaml"), EndPointsConfiguration.class);
            System.out.println(instance.SQL_ENDPOINT);
            System.out.println(instance.JSON_ENDPOINT);
            System.out.println(instance.XML_ENDPOINT);

        } catch (Exception e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }
    }

    /**
     * getter of singleton
     * @return instance of singleton
     */
    public static EndPointsConfiguration getInstance() {
        return instance;
    }
}
