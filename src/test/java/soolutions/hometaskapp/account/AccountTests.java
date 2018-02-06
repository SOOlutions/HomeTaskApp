package soolutions.hometaskapp.account;

import org.junit.jupiter.api.Test;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.common.CurrencyMismatchException;
import soolutions.hometaskapp.common.InvalidAmountException;
import soolutions.hometaskapp.user.BasicUser;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {
    @Test
    void openAccount() {
        final Account account = createAccount();
        assertEquals(true, account.open());
        assertEquals(new Amount(0, "USD"), account.balance());
    }

    @Test
    void depositAmount() {
        final Account account = createAccount();
        account.apply(new Deposit(new Amount(0, "USD"),"Sample Deposit 1"));
        assertEquals(new Amount(0, "USD"), account.balance());
        account.apply(new Deposit(new Amount(1, "USD"),"Sample Deposit 2"));
        assertEquals(new Amount(1, "USD"), account.balance());
        account.apply(new Deposit(new Amount(10.99, "USD"), "Sample Deposit 3"));
        assertEquals(new Amount(11.99, "USD"), account.balance());
    }

    @Test
    void depositAmountInvalidAmount() {
        final Account account = createAccount();
        assertThrows(InvalidAmountException.class, () -> {
            account.apply(new Deposit(new Amount(-1, "USD"),"Sample Deposit 1"));
        }, "should fail");
    }

    @Test
    void depositAmountCurrencyMismatch() {
        final Account account = createAccount();
        account.apply(new Deposit(new Amount(0, "USD"),"Sample Deposit 1"));
        assertEquals(new Amount(0, "USD"), account.balance());
        account.apply(new Deposit(new Amount(1, "USD"),"Sample Deposit 2"));
        assertEquals(new Amount(1, "USD"), account.balance());
        assertThrows(CurrencyMismatchException.class, () -> {
            account.apply(new Deposit(new Amount(7, "EUR"),"Sample Deposit 3"));
        }, "should fail");
    }

    @Test
    void getAccountTransactions() {
        final Account account = createAccount();
        account.apply(new Deposit(new Amount(2, "USD"),"Sample Deposit 1"));
        account.apply(new Deposit(new Amount(3, "USD"),"Sample Deposit 2"));

        assertEquals(2, account.transactions().size());
        assertEquals(new Amount(2, "USD"), account.transactions().get(0).amount());
        assertEquals("Sample Deposit 1", account.transactions().get(0).description());
        assertEquals(new Amount(3, "USD"), account.transactions().get(1).amount());
        assertEquals("Sample Deposit 2", account.transactions().get(1).description());
    }

    private Account createAccount() {
        return new BasicAccount(new BasicUser(), "USD");
    }
}
