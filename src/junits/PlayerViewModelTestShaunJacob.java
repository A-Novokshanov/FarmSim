package junits;

import models.PlayerModel;
import models.UserModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import viewmodels.PlayerViewModel;

public class PlayerViewModelTestShaunJacob {
    PlayerViewModel playerViewModel;
    PlayerModel player;
    UserModel user;

    @Before
    public void setUp() {
        playerViewModel = new PlayerViewModel();
        player = new PlayerModel(120);
        user = new UserModel(player);
    }

    @Test
    public void testGetPlayer() {
        assertEquals(playerViewModel.getPlayerDetails(), user);
    }
}
