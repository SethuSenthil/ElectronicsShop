/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author Gokhan
 */
public class Product 
{
    private int ID;
    private String productType;
    private String productName;
    private String serialNumber;
    private int modelYear;
    private String conditions;
    
    public Product(int ID, String productType, String productName, String serialNumber, int modelYear, String conditions)
    {
        this.ID = ID;
        this.productType = productType;
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.modelYear = modelYear;
        this.conditions = conditions;
    }

    public int getID() {
        return ID;
    }

    public String getProductType() {
        return productType;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public int getModelYear() {
        return modelYear;
    }
    
    public String getConditions() {
        return conditions;
    }
 

    @Override
    public String toString() {
        return "Product{" + "ID=" + ID + ", productType=" + productType +  ", productName=" + productName + ", serialNumber=" + serialNumber + ", modelYear=" + modelYear + ", conditions=" + conditions +  '}';
    }
}
