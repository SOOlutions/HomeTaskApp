package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;

import java.time.Instant;

public interface Transaction {
    Instant occurredAt();
    String description();
    Amount amount();
}
