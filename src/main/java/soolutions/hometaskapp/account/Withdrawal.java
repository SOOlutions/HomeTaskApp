package soolutions.hometaskapp.account;

import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.common.InvalidAmountException;

import java.time.Instant;

public class Withdrawal implements Transaction {
    private final Instant occurredAt;
    private final String description;
    private final Amount amount;

    public Withdrawal(Amount amount, String description) {
        this(amount, description, Instant.now());
    }

    public Withdrawal(Amount amount, String description, Instant occurredAt) {
        if (amount.compareTo(amount.zero()) == -1)
            throw new InvalidAmountException(amount);
        this.occurredAt = occurredAt;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public Amount apply(Amount amount) {
        return amount.subtract(this.amount);
    }
}
