package soolutions.hometaskapp.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AmountTests {

    @Test
    void addCurrencyMismatch() {
        final Amount amount = new Amount(1, "USD");

        assertThrows(CurrencyMismatchException.class, () -> {
            amount.add(new Amount(1, "EUR"));
        }, "should fail");
    }

    @Test
    void withdrawCurrencyMismatch() {
        final Amount amount = new Amount(1, "USD");

        assertThrows(CurrencyMismatchException.class, () -> {
            amount.withdraw(new Amount(1, "EUR"));
        }, "should fail");
    }
}
