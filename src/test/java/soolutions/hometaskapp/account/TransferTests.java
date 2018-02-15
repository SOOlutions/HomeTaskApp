package soolutions.hometaskapp.account;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.account.Deposit;
import soolutions.hometaskapp.account.Transaction;
import soolutions.hometaskapp.account.Transfer;
import soolutions.hometaskapp.account.Transfer.InvalidTransactionException;
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
        Transfer transfer = new Transfer();
        Account from = createAccount(5);
        Account to = createAccount(0);
        from.close();

        assertThrows(InvalidTransactionException.class, () -> {
            transfer.apply(from, to, new Amount(5, "USD"));
        }, "should fail");
    }

    @Test
    void transferAmountShouldBeLessThanSourceAccountBalance() {
        Transfer transfer = new Transfer();
        Account from = createAccount(5);
        Account to = createAccount(0);

        assertThrows(InvalidTransactionException.class, () -> {
            transfer.apply(from, to, new Amount(10, "USD"));
        }, "should fail");
    }

    @Test
    void successfulTransferShouldUpdateBalanace() {
        Transfer transfer = new Transfer();
        Account from = createAccount(5);
        Account to = createAccount(0);

        transfer.apply(from, to, new Amount(5, "USD"));

        assertEquals(from.balance(), new Amount(0, "USD"));
        assertEquals(to.balance(), new Amount(4, "USD"));
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
          Transaction deposit = new Deposit(new Amount(openingBalance, currency), "");
          newAccount.apply(deposit);
          return newAccount;
      }
    }
}
