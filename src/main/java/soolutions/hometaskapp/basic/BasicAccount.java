package soolutions.hometaskapp.basic;

import soolutions.hometaskapp.Account;
import soolutions.hometaskapp.User;

public class BasicAccount implements Account {
    public BasicAccount(User user) {
    }

    @Override
    public boolean open() {
        return true;
    }

    @Override
    public int balance() {
        return 0;
    }
}
