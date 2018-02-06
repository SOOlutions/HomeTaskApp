package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.common.CurrencyMismatchException;
import soolutions.hometaskapp.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BasicAccount implements Account {
    private final List<Transaction> transactions;
    private final User user;
    private final String currency;

    public BasicAccount(User user, String currency) {
      this(user, currency, new ArrayList<>());
    }

    public BasicAccount(User user, String currency, List<Transaction> transactions) {
        this.transactions = transactions;
        this.currency = currency;
        this.user = user;
    }

    @Override
    public boolean open() {
        return true;
    }

    @Override
    public void apply(Transaction transaction) {
        if (!transaction.amount().matchesCurrency(currency))
            throw new CurrencyMismatchException("Account only accepts " + currency);
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> transactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public Amount balance() {
        Amount amount = new Amount(0, currency);
        for (Transaction t : transactions)
            amount = amount.add(t.amount());
        return amount;
    }
}
