CREATE TABLE AP_Receipt (
    Receipt_ID int NOT NULL PRIMARY KEY,
    Price float NOT NULL,
    Billing_Address VARCHAR(100) NOT NULL,
    Fist_Name VARCHAR(50) NOT NULL,
    Last_Name VARCHAR(50) NOT NULL,
    Card_Number DECIMAL(16,0) NOT NULL,
    Product_ID int NOT NULL,
    FOREIGN KEY (Product_ID) REFERENCES AP_Product(Product_ID)
);