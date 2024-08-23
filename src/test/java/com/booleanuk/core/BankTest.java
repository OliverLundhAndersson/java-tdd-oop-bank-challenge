package com.booleanuk.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    Bank bank;

    @Test
    public void testBankCreate() {
        bank = new Bank();
        assertEquals(0, bank.getIdCounter());

    }

    @Test
    public void testCreateAccount() {
        bank = new Bank();
        int accountId = bank.createAccount("Savings", "Private");
        assertEquals(0, accountId);
        assertEquals(1, bank.createAccount( "Savings", "Private"));

        assertEquals(0, bank.getAccount(accountId).getId());
        accountId = bank.createAccount("Current", "Private");
        assertEquals(bank.getIdCounter(), bank.getAccount(accountId).getBank().getIdCounter());


        assertEquals(-1, bank.createAccount("Current", "Brivate"));
    }

    @Test
    public void testDepositAndWithdraw() {
        bank = new Bank();
        int accountId = bank.createAccount("Savings", "Private");
        assertTrue(bank.deposit(accountId, 500));
        assertEquals(525, bank.getAccount(accountId).calcCurrentAmount());
        assertTrue(bank.withdraw(accountId, 500));
        assertEquals(25, bank.getAccount(accountId).calcCurrentAmount());
        assertFalse(bank.withdraw(accountId, 500));

        accountId = bank.createAccount("Current", "Private");
        assertTrue(bank.deposit(accountId, 500));
        assertEquals(500, bank.getAccount(accountId).calcCurrentAmount());
        assertTrue(bank.withdraw(accountId, 500));
        assertEquals(0, bank.getAccount(accountId).calcCurrentAmount());

        assertTrue(bank.withdraw(accountId, 250));
        assertEquals(-250, bank.getAccount(accountId).calcCurrentAmount());
        assertTrue(bank.withdraw(accountId, 250));
        assertEquals(-500, bank.getAccount(accountId).calcCurrentAmount());

        assertFalse(bank.withdraw(accountId, 250));
        assertEquals(-500, bank.getAccount(accountId).calcCurrentAmount());

        bank.createAccount("Current", "Private");

        assertTrue(bank.withdraw(accountId, 250));
        assertEquals(-750, bank.getAccount(accountId).calcCurrentAmount());
    }
}
