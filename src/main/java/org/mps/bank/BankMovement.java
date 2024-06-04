package org.mps.bank;

public class BankMovement {
    public static enum MOVEMENT_TYPE {WITHDRAW, DEPOSIT};
    private MOVEMENT_TYPE movement_type;
    public MOVEMENT_TYPE getMovement_type() {
        return movement_type;
    }
    public void setMovement_type(MOVEMENT_TYPE movement_type) {
        this.movement_type = movement_type;
    }
    private int amount;
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public BankMovement(MOVEMENT_TYPE movement_type, int amount){
        this.movement_type = movement_type;
        this.amount = amount;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((movement_type == null) ? 0 : movement_type.hashCode());
        result = prime * result + amount;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BankMovement other = (BankMovement) obj;
        if (movement_type != other.movement_type)
            return false;
        if (amount != other.amount)
            return false;
        return true;
    } 
}
