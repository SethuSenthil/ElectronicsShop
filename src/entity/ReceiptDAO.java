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
public class ReceiptDAO implements DAO<Receipt>
{
    public ReceiptDAO() {

    }
    List<Receipt> receipts;
    /**
     * Get a single receipt entity as a receipt object
     */
    @Override
    public Optional<Receipt> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM AP_Receipt WHERE Receipt_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Receipt receipt = null;
            while (rs.next()) {
                receipt = new Receipt(rs.getInt("Receipt_ID"), rs.getInt("Price"), rs.getString("Billing_Address"), rs.getString("Fist_Name"), rs.getString("Last_Name"), rs.getInt("Card_Number"), rs.getInt("Product_ID"));
            }
            return Optional.ofNullable(receipt);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Get all receipt entities as a List
     * @return
     */
    @Override
    public List<Receipt> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        receipts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AP_Receipt";
            rs = db.executeQuery(sql);
            Receipt receipt = null;
            while (rs.next()) {
                receipt = new Receipt(rs.getInt("Receipt_ID"), rs.getInt("Price"), rs.getString("Billing_Address"), rs.getString("Fist_Name"), rs.getString("Last_Name"), rs.getInt("Card_Number"), rs.getInt("Product_ID"));
                receipts.add(receipt);
            }
            return receipts;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Insert a receipt object into receipt table
     * @param customer
     */
    @Override
    public void insert(Receipt receipt)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO AP_Receipt(Receipt_ID, Price, Billing_Address, Fist_Name, Last_Name, Card_Number, Product_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            System.out.println("inserting a recpt of" + receipt);
            
            stmt.setInt(1, receipt.getID());
            stmt.setDouble(2, receipt.getPrice());
            stmt.setString(3, receipt.getBillingAddress());
            stmt.setString(4, receipt.getFirstName());
            stmt.setString(5, receipt.getLastName());
            stmt.setInt(6, receipt.getCardNumber());
            stmt.setInt(7, receipt.getProductID());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new receipt was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Update a receipt entity in database if it exists using a customer object
     * @param receipt
     */
    @Override
    public void update(Receipt receipt) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE AP_Receipt SET Product_ID = ?, Price=?, Billing_Address=?, Fist_Name=?, Last_Name = ?, Card_Number = ? WHERE Receipt_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            
            stmt.setInt(1, receipt.getProductID());
            stmt.setDouble(2, receipt.getPrice());
            stmt.setString(3, receipt.getBillingAddress());
            stmt.setString(4, receipt.getFirstName());
            stmt.setString(5, receipt.getLastName());
            stmt.setInt(6, receipt.getCardNumber());
           
             stmt.setInt(7, receipt.getID());
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing receipt was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Delete a customer from customer table if the entity exists
     * @param customer
     */
    @Override
    public void delete(Receipt receipt) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM AP_Receipt WHERE Receipt_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, receipt.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A receipt was deleted successfully!");
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
            String sql = "SELECT * FROM AP_Receipt WHERE Receipt_ID = -1";//We just need this sql query to get the column headers
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