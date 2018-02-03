package soolutions.hometaskapp;

import org.junit.jupiter.api.Test;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.core.Amount;
import soolutions.hometaskapp.user.BasicUser;

import static org.junit.jupiter.api.Assertions.*;

public class DepositAccountTest {
    @Test
    void depositAccount() {
        final Account account = new BasicAccount(new BasicUser());
        account.deposit(new Amount(0, "USD"));
        assertEquals(new Amount(0, "USD"), account.balance());
        account.deposit(new Amount(1, "USD"));
        assertEquals(new Amount(1, "USD"), account.balance());
        account.deposit(new Amount(10.99, "USD"));
        assertEquals(new Amount(11.99, "USD"), account.balance());
    }

    @Test
    void depositAccount_currency_mismatch() {
        final Account account = new BasicAccount(new BasicUser());
        account.deposit(new Amount(0, "USD"));
        assertEquals(new Amount(0, "USD"), account.balance());
        account.deposit(new Amount(1, "USD"));
        assertEquals(new Amount(1, "USD"), account.balance());
        assertThrows(RuntimeException.class, () -> {
          account.deposit(new Amount(10.99, "EUR"));
        }, "should fail");
    }
}
