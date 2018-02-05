package soolutions.hometaskapp;

import org.junit.jupiter.api.Test;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.common.CurrencyMismatchException;
import soolutions.hometaskapp.common.InvalidAmountException;
import soolutions.hometaskapp.user.BasicUser;

import static org.junit.jupiter.api.Assertions.*;

public class DepositAccountTest {
    @Test
    void depositAmount() {
        final Account account = new BasicAccount(new BasicUser());
        account.deposit(new Amount(0, "USD"));
        assertEquals(new Amount(0, "USD"), account.balance());
        account.deposit(new Amount(1, "USD"));
        assertEquals(new Amount(1, "USD"), account.balance());
        account.deposit(new Amount(10.99, "USD"));
        assertEquals(new Amount(11.99, "USD"), account.balance());
    }

    @Test
    void depositAmountInvalidAmount() {
        final Account account = new BasicAccount(new BasicUser());
        account.deposit(new Amount(0, "USD"));
        assertThrows(InvalidAmountException.class, () -> {
          account.deposit(new Amount(-1, "EUR"));
        }, "should fail");
    }

    @Test
    void depositAmountCurrencyMismatch() {
        final Account account = new BasicAccount(new BasicUser());
        account.deposit(new Amount(0, "USD"));
        assertEquals(new Amount(0, "USD"), account.balance());
        account.deposit(new Amount(1, "USD"));
        assertEquals(new Amount(1, "USD"), account.balance());
        assertThrows(CurrencyMismatchException.class, () -> {
          account.deposit(new Amount(10.99, "EUR"));
        }, "should fail");
    }
}
