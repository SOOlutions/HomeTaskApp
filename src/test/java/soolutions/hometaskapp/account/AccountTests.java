package soolutions.hometaskapp.account;

import org.junit.jupiter.api.Test;

import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.common.CurrencyMismatchException;
import soolutions.hometaskapp.common.InvalidAmountException;
import soolutions.hometaskapp.user.BasicUser;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {
    @Test
    void openAccount() {
        final Account account = createAccount();

        assertEquals(true, account.open());
    }

    @Test
    void newAccountHasZeroBalance() {
        final Account account = createAccount();

        assertEquals(new Amount(0, "USD"), account.balance());
    }

    @Test
    void deposit() {
        final Account account = createAccount();
        account.apply(new Deposit(new Amount(0, "USD"),"Sample Deposit 1"));
        account.apply(new Deposit(new Amount(1, "USD"),"Sample Deposit 2"));
        account.apply(new Deposit(new Amount(10.99, "USD"), "Sample Deposit 3"));

        assertEquals(new Amount(11.99, "USD"), account.balance());
    }

    @Test
    void depositInvalidAmount() {
        assertThrows(InvalidAmountException.class, () -> {
            new Deposit(new Amount(-1, "USD"),"Sample Deposit 1");
        }, "should fail");
    }

    @Test
    void withdrawal() {
        final Account account = createAccount();
        account.apply(new Deposit(new Amount(10, "USD"), "Sample Deposit 1"));
        account.apply(new Withdrawal(new Amount(5, "USD"),"Sample Withdrawal 1"));
        account.apply(new Withdrawal(new Amount(1, "USD"),"Sample Withdrawal 2"));
        account.apply(new Withdrawal(new Amount(4, "USD"), "Sample Withdrawal 3"));

        assertEquals(new Amount(0.0, "USD"), account.balance());
    }

    @Test
    void withdrawalInvalidAmount() {
        assertThrows(InvalidAmountException.class, () -> {
            new Withdrawal(new Amount(-1, "USD"),"Sample Withdrawal 1");
        }, "should fail");
    }

    @Test
    void getAccountTransactions() {
        final Account account = createAccount();
        Transaction deposit1 = new Deposit(new Amount(2, "USD"),"Sample Deposit 1");
        Transaction deposit2 = new Deposit(new Amount(3, "USD"),"Sample Deposit 2");

        account.apply(deposit1);
        account.apply(deposit2);

        assertIterableEquals(Arrays.asList(deposit1, deposit2), account.transactions());
    }

    private Account createAccount() {
        return new BasicAccount(new BasicUser(), "USD");
    }
}
