package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import sample.Dialogs;

public class EndPointsConfiguration {
    private static EndPointsConfiguration instance = new EndPointsConfiguration();
    public final String SQL_ENDPOINT = "rmi://localhost:8080/sql-server";
    public final String JSON_ENDPOINT = "rmi://localhost:8080/json-server";
    public final String XML_ENDPOINT = "rmi://localhost:8080/xml-server";

    static {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, true);
        try {
            EndPointsConfiguration.instance = mapper.readValue(EndPointsConfiguration.class.getClassLoader().getResource("endPointConfiguration.yaml"), EndPointsConfiguration.class);
        } catch (Exception e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }
    }

    public static EndPointsConfiguration getInstance() {
        return instance;
    }
}
