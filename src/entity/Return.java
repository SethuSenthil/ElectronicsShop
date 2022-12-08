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
public class Return {

    private int ID;
    private int receiptID;
    private String reimbursementType;
    private String returnReason;

    public Return(int ID, int receiptID, String reimbursementType, String returnReason) {
        this.ID = ID;
        this.receiptID = receiptID;
        this.reimbursementType = reimbursementType;
        this.returnReason = returnReason;
    }

    public int getID() {
        return ID;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public String getReimbursementType() {
        return reimbursementType;
    }

    public String getReturnReason() {
        return returnReason;
    }

    @Override
    public String toString() {
        return "Receipt{" + "ID=" + ID + ", receiptID=" + receiptID + ", reimbursementType=" + reimbursementType + ", returnReason=" + returnReason + '}';
    }
}
