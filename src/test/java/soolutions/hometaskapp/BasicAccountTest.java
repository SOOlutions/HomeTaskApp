package soolutions.hometaskapp;

import org.junit.jupiter.api.Test;

import soolutions.hometaskapp.account.Account;
import soolutions.hometaskapp.account.BasicAccount;
import soolutions.hometaskapp.common.Amount;
import soolutions.hometaskapp.user.BasicUser;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAccountTest {
    @Test
    void openAccount() {
        final Account account = new BasicAccount(new BasicUser());
        assertEquals(true, account.open());
        assertEquals(new Amount(0, "USD"), account.balance());
    }
}
