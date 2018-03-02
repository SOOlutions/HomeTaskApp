package soolutions.hometaskapp.account;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public final class GetCurrentAccountsBalance {
  private final Collection<Account> accounts;

  public GetCurrentAccountsBalance(Collection<Account> accounts) {
    this.accounts = accounts;
  }

  public Map<String, String> execute() {
    Map<String, String> map = new HashMap<>();
    for (Account account : accounts) {
      if (account.open()) {
        map.put(account.userName(), account.toString());
      }
    }
    return map;
  }
}
