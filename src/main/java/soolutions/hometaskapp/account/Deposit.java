package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;

import java.time.Instant;

public class Deposit implements Transaction {
    private final Instant occurredAt;
    private final String description;
    private final Amount amount;

    public Deposit(Amount amount, String description) {
        this(amount, description, Instant.now());
    }

    public Deposit(Amount amount, String description, Instant occurredAt) {
        this.occurredAt = occurredAt;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public Amount add(Amount amount) {
        return this.amount.add(amount);
    }
}
