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
public class Receipt {

    private int ID;
    private double price;
    private String billingAddress;
    private String firstName;
    private String lastName;
    private int cardNumber;
    private int productID;

    public Receipt(int receiptID, double price, String billingAddress, String firstName, String lastName, int cardNumber, int productID) {
        this.ID = receiptID;
        this.price = price;
        this.billingAddress = billingAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNumber;
        this.productID = productID;
    }

    public int getID() {
        System.out.println("sent an ID with " + ID);
        return ID;
    }

    public double getPrice() {
        return price;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCardNumber() {
        return cardNumber;
    }
    
    public int getProductID() {
        return productID;
    }

    @Override
    public String toString() {
        return "Return{" + "ID=" + ID + ", price=" + price + ", billingAddress=" + billingAddress + ", firstName=" + firstName + ", lastName=" + lastName + ", cardNumber=" + cardNumber + ", productID=" + productID + '}';
    }
}
