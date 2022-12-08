/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author Gokhan
 */
public class ProductDOA implements DAO<Product>
{
    public ProductDOA() {

    }
    List<Product> products;
    /**
     * Get a single order entity as an order object
     * @param id
     * @return
     */
    @Override
    public Optional<Product> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM AP_Product WHERE Product_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getInt("Product_ID"), rs.getString("Product_Type"), rs.getString("Product_Name"), rs.getString("Serial_Number"), rs.getInt("Model_Year"), rs.getString("Conditions"));
            }
            return Optional.ofNullable(product);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Get all order entities as a List
     * @return
     */
    @Override
    public List<Product> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AP_Product";
            rs = db.executeQuery(sql);
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getInt("Product_ID"), rs.getString("Product_Type"), rs.getString("Product_Name"), rs.getString("Serial_Number"), rs.getInt("Model_Year"), rs.getString("Conditions"));
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Insert an order object into order table
     * @param order
     */
    @Override
    public void insert(Product product)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO AP_Product(Product_ID, Product_Type, Product_Name, Serial_Number, Model_Year, Conditions) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, product.getID());
            stmt.setString(2, product.getProductType());
            stmt.setString(3, product.getProductName());
            stmt.setString(4, product.getSerialNumber());
            stmt.setInt(5, product.getModelYear());
            stmt.setString(6, product.getConditions());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new product was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Update an order entity in database if it exists using an order object
     * @param order
     */
    @Override
    public void update(Product product) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE AP_Product SET Product_Type=?, Product_Name=?, Serial_Number=?, Model_Year=?, Conditions=? WHERE Product_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, product.getProductType());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getSerialNumber());
            stmt.setInt(4, product.getModelYear());
            stmt.setString(5, product.getConditions());
            //where last
            stmt.setInt(6, product.getID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing check in station was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Delete an order from order table if the entity exists
     * @param order
     */
    @Override
    public void delete(Product product) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM AP_Product WHERE Product_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, product.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A check in station was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Get all column names in a list array
     * @return
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AP_Product WHERE Product_ID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
}