package usergame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserGameTest {

    @Test
    public void userGameInputsGuessesToHitBattleship() {
        UserGame userGame = new UserGame();

        System.out.println(userGame.checkYourself("1, 2, 3"));

        assertEquals(3, userGame.checkYourself("1,2,3").size());
        assertEquals("1", userGame.checkYourself("1,2,3").get(0));
        assertEquals("3", userGame.checkYourself("1,2,3").get(2));
    }
    
}
