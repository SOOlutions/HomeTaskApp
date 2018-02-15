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

    private void precondition(Account from, Account to, Amount amount) {
        if (!from.open()) {
          throwException("Given account is closed " + from);
        }
        if (!to.open()) {
          throwException("Given account is closed " + to);
        }
        if (amount.compareTo(from.balance()) < 0) {
          throwException("Source account balance cannot be negative " + from);
        }
    }

    private void throwException(String msg) {
        throw new InvalidTransactionException(msg);
    }

    public static class InvalidTransactionException extends RuntimeException {
        public InvalidTransactionException(String msg) {
           super(msg);
        }
    }
}
