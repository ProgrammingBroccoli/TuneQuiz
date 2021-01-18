import Controllers.AccountController;
import Controllers.LobbyController;
import Data.LobbyData;
import GUI.TuneQuizApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class IntergrationTest {
    AccountController accountController;
    LobbyController lobbyController;
    LobbyData lobbyData;
    TuneQuizApplication tuneQuizApplication;

    public IntergrationTest() throws IOException {
        accountController = new AccountController();
        lobbyController = new LobbyController(accountController);
        lobbyData = LobbyData.getInstance();
        tuneQuizApplication = new TuneQuizApplication();
    }
    @Test
    void LoginPlayerWithWrongUsernameTest() throws IOException {
        //FR2 TESTCASE 4
        String username = "Jan";
        String password = "Test";

        boolean result = accountController.LoginPlayer(username, password);

        Assertions.assertEquals(false, result);
    }
    @Test
    void LoginPlayerWithWrongPasswordTest() throws IOException {
        //FR2 TESTCASE 5
        String username = "Ruben";
        String password = "Wrong";

        boolean result = accountController.LoginPlayer(username, password);

        Assertions.assertEquals(false, result);
    }
    @Test
    void LoginPlayerWithValidCredentialsTest() throws IOException {
        //FR2 TESTCASE 6
        String username = "Ruben";
        String password = "Test";

        boolean result = accountController.LoginPlayer(username, password);

        Assertions.assertEquals(true, result);
    }
}
