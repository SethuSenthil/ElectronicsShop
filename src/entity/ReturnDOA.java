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
public class ReturnDOA implements DAO<Return>
{
    public ReturnDOA() {

    }
    List<Return> returns;
    /**
     * Get a single returnn entity as a returnn object
     */
    @Override
    public Optional<Return> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM AP_Return WHERE Return_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Return returnn = null;
            while (rs.next()) {
                returnn = new Return(rs.getInt("Return_ID"), rs.getInt("Receipt_ID"), rs.getString("Reimbursement_Type"), rs.getString("Return_Reason"));
            }
            return Optional.ofNullable(returnn);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Get all returnn entities as a List
     * @return
     */
    @Override
    public List<Return> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        returns = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AP_Return";
            rs = db.executeQuery(sql);
            Return returnn = null;
            while (rs.next()) {
                 returnn = new Return(rs.getInt("Return_ID"), rs.getInt("Receipt_ID"), rs.getString("Reimbursement_Type"), rs.getString("Return_Reason"));
                returns.add(returnn);
            }
            return returns;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }

    /**
     * Insert a returnn object into returnn table
     * @param customer
     */
    @Override
    public void insert(Return returnn)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO AP_Return(Return_ID, Receipt_ID, Reimbursement_Type, Return_Reason) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, returnn.getID());
            stmt.setInt(2, returnn.getReceiptID());
            stmt.setString(3, returnn.getReimbursementType());
            stmt.setString(4, returnn.getReturnReason());
            
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new returnn was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * Update a returnn entity in database if it exists using a customer object
     * @param returnn
     */
    @Override
    public void update(Return returnn) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE AP_Return SET Receipt_ID=?, Reimbursement_Type=?, Return_Reason=?  WHERE Return_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
           
            stmt.setInt(1, returnn.getReceiptID());
            stmt.setString(2, returnn.getReimbursementType());
            stmt.setString(3, returnn.getReturnReason());
            
             stmt.setInt(4, returnn.getID());
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing returnn was updated successfully!");
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
    public void delete(Return returnn) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM AP_Return WHERE Return_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, returnn.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A returnn was deleted successfully!");
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
            String sql = "SELECT * FROM AP_Return WHERE Return_ID = -1";//We just need this sql query to get the column headers
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