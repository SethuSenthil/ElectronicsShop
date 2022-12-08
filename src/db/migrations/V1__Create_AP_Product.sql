CREATE TABLE AP_Product(
    Product_ID int NOT NULL PRIMARY KEY,
    Product_Type VARCHAR(30) NOT NULL,
    Product_Name VARCHAR(200) NOT NULL,
    Serial_Number VARCHAR(200) NOT NULL,
    Model_Year DECIMAL(4,0) NOT NULL,
    Conditions VARCHAR(100) NOT NULL   
);