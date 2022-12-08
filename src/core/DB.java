/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.flywaydb.core.Flyway;

/**
 *
 * @author Gokhan
 */
public class DB {
    private static final String DB_URL = "jdbc:derby:eletronicsdb;create=true;user=eletronicsdb;password=eletronicsdb";
    private static final String MIGRATION_DIR = "db.migrations";
    private static DB instance = null;
    private final Connection mConnection;
    
    private DB() throws SQLException
    {
        migrateDb();//Migrate DB first before doing anything
        mConnection = DriverManager.getConnection(DB_URL);
    }
    
    /**
     * This is the Singleton method that returns only one database instance
     * @return 
     */
    public static DB getInstance() {
        if (instance == null) {
            try {
                instance = new DB();
            } catch (SQLException ex) {
                System.err.println("Database Error: " + ex.toString());
                System.exit(1);
            }
        }

        return instance;
    }
    
    /**
     * Use this method for INSERT, UPDATE, DELETE
     * @param sql
     * @return
     * @throws SQLException 
     */
    public int executeUpdate(String sql) throws SQLException {
        return mConnection.createStatement().executeUpdate(sql);
    }

    /**
     * Use this method to return ResultSet for SELECT SQL queries with no parameters
     * @param sql
     * @return
     * @throws SQLException 
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        return mConnection.createStatement().executeQuery(sql);
    }

    /**
     * Use this method to return PreparedStatement for SELECT SQL queries with parameters 
     * @param sql
     * @return
     * @throws SQLException 
     */
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return mConnection.prepareStatement(sql);
    }
    
    /**
     * This migrateDB method is used to create tables under db.migrations package
     * if they are not created before
     * We use FlyWay class from flyway library to perform the migration in our code
     */
    private void migrateDb() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(DB_URL, null, null);//Set the database
        flyway.setLocations(MIGRATION_DIR);//It will run these SQL scripts under Migration Directory
        flyway.migrate();//This performs the migration, basically creating tables and inserting values into tables if necessary
    }
}
