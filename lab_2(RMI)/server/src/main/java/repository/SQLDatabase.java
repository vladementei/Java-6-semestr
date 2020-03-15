package repository;

import exception.RepositoryException;

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

    private static Connection connection;
    protected Statement statement;

    public SQLDatabase(String tableName) throws ClassNotFoundException, RepositoryException {
        this.TABLE_NAME = tableName;
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connectDatabase();
            } catch (SQLException se) {
                try {
                    connection = DriverManager.getConnection(DB_URL, USER, PASS);
                    this.statement = connection.createStatement();
                    this.statement.executeUpdate("CREATE DATABASE " + DB_NAME);
                    System.out.println("Database " + DB_NAME + " created successfully");
                    connectDatabase();
                } catch (SQLException e) {
                    System.out.println("Create database error");
                    throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
                }
            }
        }
        try {
            this.statement = connection.createStatement();
            connectTable();
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    private void connectDatabase() throws SQLException {
        System.out.println("Connecting to database " + DB_NAME);
        connection = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
    }

    public abstract void connectTable() throws RepositoryException;
}
