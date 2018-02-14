package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;

import java.util.List;

public interface Account {
    boolean open();
    void close();
    void apply(Transaction transaction);

    List<Transaction> transactions();
    Amount balance();
}
