package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQLDatabase {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "shop_info";
    protected String TABLE_NAME;
    private static final String USER = "postgres";
    private static final String PASS = "1111";

    private Connection connection = null;
    protected Statement statement = null;

    public SQLDatabase(String tableName) throws ClassNotFoundException, SQLException {
        this.TABLE_NAME = tableName;
        try {
            Class.forName(JDBC_DRIVER);
            connectDatabase();
        } catch (SQLException se) {
            try {
                this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
                this.statement = connection.createStatement();
                this.statement.executeUpdate("CREATE DATABASE " + DB_NAME);
                System.out.println("Database " + DB_NAME + " created successfully");
                connectDatabase();
            } catch (SQLException e) {
                System.out.println("Create database error");
            }
        }
        connectTable();
    }

    private void connectDatabase() throws SQLException{
        System.out.println("Connecting to database " + DB_NAME);
        this.connection = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
        this.statement = this.connection.createStatement();
    }

    public abstract void connectTable() throws SQLException;
}
