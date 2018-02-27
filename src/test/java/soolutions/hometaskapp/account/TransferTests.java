package soolutions.hometaskapp.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.account.Deposit;
import soolutions.hometaskapp.account.Transaction;
import soolutions.hometaskapp.account.Transfer.InvalidTransactionException;
import soolutions.hometaskapp.account.Transfer;
import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.user.BasicUser;

public class TransferTests {
    private AccountFactory dollarAccountFactory;

    @BeforeEach
    void setup() {
        dollarAccountFactory = new AccountFactory("USD");
    }

    @Test
    void transferShouldFailForClosedAccounts() {
        Account from = createAccount(5);
        Account to = createAccount(0);
        Transfer transfer = new Transfer(from, to, new Amount(5, "USD"));
        from.close();

        assertThrows(InvalidTransactionException.class, transfer::apply, "should fail");
    }

    @Test
    void transferAmountShouldBeLessThanSourceAccountBalance() {
        Account from = createAccount(5);
        Account to = createAccount(0);
        Transfer transfer = new Transfer(from, to, new Amount(10, "USD"));

        assertThrows(InvalidTransactionException.class, transfer::apply, "should fail");
    }

    @Test
    void successfulTransferShouldUpdateBalanace() {
        Account from = createAccount(5);
        Account to = createAccount(0);
        Transfer transfer = new Transfer(from, to, new Amount(5, "USD"));
        transfer.apply();

        assertEquals(from.balance(), new Amount(0, "USD"));
        assertEquals(to.balance(), new Amount(5, "USD"));
    }

    private Account createAccount(double openingBalance) {
        return dollarAccountFactory.create(openingBalance);
    }

    private static class AccountFactory {
      private final String currency;

      AccountFactory(String currency) {
          this.currency = currency;
      }

      Account create(double balance) {
          Account newAccount = new BasicAccount(new BasicUser(), currency);
          Transaction deposit = new Deposit(new Amount(balance, currency), "");
          newAccount.apply(deposit);
          return newAccount;
      }
    }
}
