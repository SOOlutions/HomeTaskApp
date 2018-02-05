package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;

public interface Account {
    boolean open();
    void deposit(Amount amount);

    Amount balance();
}
