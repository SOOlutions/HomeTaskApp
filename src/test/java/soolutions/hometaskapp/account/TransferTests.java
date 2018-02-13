package soolutions.hometaskapp.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.account.Transaction;
import soolutions.hometaskapp.account.Deposit;
import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.user.BasicUser;

public class TransferTests {

    @Test
    void transferShouldFailForClosedAccounts() {
    }

    @Test
    void transferAmountShouldBeLessThanSourceAccountBalance() {
    }

    @Test
    void successfulTransferShouldUpdateBalanace() {
        Account from = createAccount(5, "USD");
        Account to = createAccount(0, "USD");
        from.transfer(to, new Amount(5, "USD"));

        assertEquals(from.balance(), new Amount(0, "USD"));
        assertEquals(to.balance(), new Amount(5, "USD"));
    }

    @Test
    void failedTransferShouldNotUpdateBalanace() {
        assertEquals(true, true);
    }

    private Account createAccount(double openingBalance) {
      return createAccount(openingBalance, "USD");
    }

    private Account createAccount(double openingBalance, String currency) {
        Account newAccount = new BasicAccount(new BasicUser(), currency);
        Transaction deposit = new Deposit(new Amount(openingBalance, currency), "");
        newAccount.apply(deposit);
    }
}
