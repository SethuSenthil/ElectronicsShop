CREATE TABLE AP_Return(
    Return_ID int NOT NULL PRIMARY KEY,
    Receipt_ID int NOT NULL,
    Reimbursement_Type VARCHAR(50) NOT NULL,
    Return_Reason VARCHAR(200) NOT NULL,
    FOREIGN KEY (Receipt_ID) REFERENCES AP_Receipt(Receipt_ID)
);