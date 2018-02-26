package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;

public final class Transfer {
    private final Account from;
    private final Account to;
    private final Amount amount;

    public Transfer(Account from, Account to, Amount amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public void apply() {
        precondition();
        from.apply(new Withdrawal(amount, "Transfer to " + to));
        to.apply(new Deposit(amount, "Transfer from " + from));
    }

    private void precondition() {
        if (!from.open()) {
          throwException("Given account is closed " + from);
        }
        if (!to.open()) {
          throwException("Given account is closed " + to);
        }
        if (from.balance().compareTo(amount) < 0) {
          throwException(
              "Source account doesn't have enough balance: transfering " +
              amount + " but available " + from.balance() + ".");
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
