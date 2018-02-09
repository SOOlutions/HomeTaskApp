package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;

public interface Transaction {
    Amount apply(Amount amount);
}
