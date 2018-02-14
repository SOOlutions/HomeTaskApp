package soolutions.hometaskapp.account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.account.Deposit;
import soolutions.hometaskapp.account.Transaction;
import soolutions.hometaskapp.account.Transfer;
import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.user.BasicUser;

public class TransferTests {

    @Test
    void transferShouldFailForClosedAccounts() {
        Transfer transfer = new Transfer();
        Account from = createAccount(5, "USD");
        from.close();
        Account to = createAccount(0, "USD");

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.apply(from, to, new Amount(5, "USD"));
        }, "should fail");
    }

    @Test
    void transferAmountShouldBeLessThanSourceAccountBalance() {
        Transfer transfer = new Transfer();
        Account from = createAccount(5, "USD");
        Account to = createAccount(0, "USD");

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.apply(from, to, new Amount(10, "USD"));
        }, "should fail");
    }

    @Test
    void successfulTransferShouldUpdateBalanace() {
        Transfer transfer = new Transfer();
        Account from = createAccount(5, "USD");
        Account to = createAccount(0, "USD");

        transfer.apply(from, to, new Amount(5, "USD"));

        assertEquals(from.balance(), new Amount(0, "USD"));
        assertEquals(to.balance(), new Amount(4, "USD"));
    }

    private Account createAccount(double openingBalance) {
      return createAccount(openingBalance, "USD");
    }

    private Account createAccount(double openingBalance, String currency) {
        Account newAccount = new BasicAccount(new BasicUser(), currency);
        Transaction deposit = new Deposit(new Amount(openingBalance, currency), "");
        newAccount.apply(deposit);
        return newAccount;
    }
}
