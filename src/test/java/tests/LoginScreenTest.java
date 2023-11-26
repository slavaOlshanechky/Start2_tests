package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jdk.jfr.Description;
import screens.LoginScreen;

public class LoginScreenTest extends BaseTest {
    private static LoginScreen loginScreen;
    private String registeredPhone = "+972522245678";

    @BeforeAll
    public static void init() {
        loginScreen = new LoginScreen();
    }

    @Test
    @Description("Login screen test")
    public void testLogin(){
        loginScreen.closeNotificationPopUp()
                //.isCursorInField()
                .openPhoneField()
                .typePhoneNumber(registeredPhone)
                .login()
                .secretCodeAuthorization()
                .closeLocationPopUp();
    }
}
