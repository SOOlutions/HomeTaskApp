package soolutions.hometaskapp;

import org.junit.jupiter.api.Test;
import soolutions.hometaskapp.basic.BasicAccount;
import soolutions.hometaskapp.basic.BasicUser;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAccountTest {
    @Test
    void openAccount() {
        final Account account = new BasicAccount(new BasicUser());
        assertEquals(true, account.open());
        assertEquals(0, account.balance());
    }
}
