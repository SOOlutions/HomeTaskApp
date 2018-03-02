package soolutions.hometaskapp.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.account.Deposit;
import soolutions.hometaskapp.account.GetCurrentAccountsBalance;
import soolutions.hometaskapp.account.Transaction;
import soolutions.hometaskapp.account.Transfer;
import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.user.BasicUser;

public class GetCurrentAccountsBalanceTests {
  private Collection<Account> accounts;

  @BeforeEach
  void setup() {
    accounts = new ArrayList<>();
    Account foo = createAccount("Foo", 10);
    Account bar = createAccount("Bar", 5);
    Account zar = createAccount("Zar", 5);
    zar.close();
    Transfer transfer = new Transfer(foo, bar, new Amount(5, "USD"));

    accounts.add(foo);
    accounts.add(bar);
  }

  @Test
  void shouldListCurrentAccountBalances() {
    Map<String, String> expected = new HashMap<>();
    expected.put("Foo", "5 USD");
    expected.put("Bar", "15 USD");
    Map<String, String> actual = new GetCurrentAccountsBalance(accounts).execute();
    assertEquals(actual, expected);
  }

  private Account createAccount(String name, int balance) {
    Account account = new BasicAccount(new BasicUser(name), "USD");
    Transaction deposit = new Deposit(new Amount(balance, "USD"), "");
    account.apply(deposit);
    return account;
  }
}
