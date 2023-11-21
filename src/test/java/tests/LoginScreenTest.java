package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jdk.jfr.Description;
import screens.LoginScreen;

public class LoginScreenTest extends BaseTest {
    private static LoginScreen loginScreen;

    @BeforeAll
    public static void init() {
        loginScreen = new LoginScreen();
    }

    @Description("Login screen test")
    @Test
    public void testLogin(){

    }
}
