package repository;

import exception.RepositoryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>Parent class for all SQL repositories to establish connection with database</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public abstract class SQLDatabase {
    /**
     * driver to make connection to SQL server
     */
    private static final String JDBC_DRIVER = "org.postgresql.Driver";

    /**
     * address of SQL server
     */
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";

    /**
     * name of SQL database
     */
    private static final String DB_NAME = "shop_info";

    /**
     * name of table for current instance
     */
    protected String TABLE_NAME;

    /**
     * user name to connect to SQL database
     */
    private static final String USER = "postgres";

    /**
     * password to connect to SQL database
     */
    private static final String PASS = "1111";

    /**
     * instance of connection with SQL database
     */
    private static Connection connection;

    /**
     * instance to send and receive statements from database
     */
    protected Statement statement;

    /**
     * constructor (establish connection to database and specified table)
     * @param tableName name of SQL table to establish connection
     * @throws ClassNotFoundException if SQL driver not found
     * @throws RepositoryException if any SQL connection error happened
     */
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

    /**
     * method to establish connection with database
     * @throws SQLException if connection failed
     */
    private void connectDatabase() throws SQLException {
        System.out.println("Connecting to database " + DB_NAME);
        connection = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
    }

    /**
     * abstract method to override connection to specific table in repository
     * @throws RepositoryException if connection error happened
     */
    public abstract void connectTable() throws RepositoryException;
}
