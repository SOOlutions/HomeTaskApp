package soolutions.hometaskapp.account;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.Deposit;
import soolutions.hometaskapp.account.Transaction;
import soolutions.hometaskapp.account.Withdrawal;
import soolutions.hometaskapp.common.Amount;

public final class Transfer {
    public void apply(Account from, Account to, Amount amount) {
        precondition(from, to, amount);
        from.apply(new Withdrawal(amount, "Transfer to " + to));
        to.apply(new Deposit(amount, "Transfer from " + from));
    }

    // TODO(vivek): create domain specific exception classes
    private void precondition(Account from, Account to, Amount amount) {
      if (!from.open() || !to.open()) {
        throw new IllegalArgumentException();
      }
      if (amount.compareTo(from.balance()) < 0) {
        throw new IllegalArgumentException();
      }
    }
}
