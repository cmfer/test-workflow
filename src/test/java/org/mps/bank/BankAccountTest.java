package org.mps.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mps.bank.BankAccountAssert.assertBankAccount;

public class BankAccountTest {
    
    @Test
    @DisplayName("A deposit with a positive amount increments the balance")
    public void deposit_positiveAmount_IncrementBalance(){
        BankAccount account = new BankAccount(0);

        int balance = account.deposit(10);

        assertBankAccount(account).hasFunds();
        assertThat(balance).isEqualTo(10);
        assertThat(account.getMovements())
        .hasSize(1)
        .containsExactly(new BankMovement(BankMovement.MOVEMENT_TYPE.DEPOSIT, 10));
    }

    @Test
    @DisplayName("A deposit with a negative amount throws an exception")
    public void deposit_negativeAmount_ThrowException(){
        
        BankAccount account = new BankAccount(20);

        assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> { account.deposit(-10);})
        .withMessage("Amount cannot be negative");
    }

    @Test
    public void withdraw_positiveAmount_DecrementBalance(){
        BankAccount account = new BankAccount(20);

        boolean result = account.withdraw(10);

        assertThat(result).isTrue();
        assertThat(account.getBalance()).isEqualTo(10);
        assertThat(account.getMovements())
        .hasSize(1)
        .containsExactly(new BankMovement(BankMovement.MOVEMENT_TYPE.WITHDRAW, 10));
    }

    @Test
    public void withdraw_deposit_adjusttBalance(){
        BankAccount account = new BankAccount(20);

        boolean result = account.withdraw(10);
        int balance = account.deposit(30);

        assertThat(result).isTrue();
        assertThat(balance).isGreaterThan(20);
        assertBankAccount(account).hasFunds();
        assertThat(account.getMovements())
        .hasSize(2)
        .containsSequence(new BankMovement(BankMovement.MOVEMENT_TYPE.WITHDRAW, 10), new BankMovement(BankMovement.MOVEMENT_TYPE.DEPOSIT, 30));
    }
}
