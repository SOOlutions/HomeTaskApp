package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.user.User;

public class BasicAccount implements Account {
    private Amount amount;
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

    @Override
    public void deposit(Amount amount) {
      this.amount = amount.add(this.amount);;
    }
}
