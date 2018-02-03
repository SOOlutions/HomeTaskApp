package soolutions.hometaskapp.account;

import soolutions.hometaskapp.core.Amount;
import soolutions.hometaskapp.user.User;

public class BasicAccount implements Account {
    private final Amount amount;
    private final User user;

    public BasicAccount(User user) {
      this(user, new Amount(0, "USD"));
    }

    public BasicAccount(User user, Amount amount) {
      this.user = user;
      this.amount = amount;
    }

    @Override
    public boolean open() {
        return true;
    }

    @Override
    public Amount balance() {
      return amount;
    }
}
