package org.mps.bank;

import org.assertj.core.api.AbstractAssert;

public class BankAccountAssert extends AbstractAssert<BankAccountAssert, BankAccount> {

    public BankAccountAssert(BankAccount bank) {
        super(bank, BankAccountAssert.class);
    }

    public static BankAccountAssert assertBankAccount(BankAccount bank) {
        return new BankAccountAssert(bank);
    }

    public BankAccountAssert hasFunds() {
        isNotNull();
        if (actual.getBalance()<=0) {
            failWithMessage("The balance is 0 or negative");
        }
        return this;
    }
    
    public BankAccountAssert hasNoFunds() {
        isNotNull();
        if (actual.getBalance()>0) {
            failWithMessage("The balance positive");
        }
        return this;
    }
}
