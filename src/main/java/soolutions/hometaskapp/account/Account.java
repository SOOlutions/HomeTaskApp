package soolutions.hometaskapp.account;

import soolutions.hometaskapp.core.Amount;

public interface Account {
    boolean open();

    Amount balance();
}
