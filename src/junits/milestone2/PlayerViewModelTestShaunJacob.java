//package junits.milestone2;
//
//import models.PlayerModel;
//import models.UserModel;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//
//import viewmodels.PlayerViewModel;
//
//public class PlayerViewModelTestShaunJacob {
//    private PlayerViewModel playerViewModel;
//    private PlayerModel player;
//    private UserModel user;
//
//    @Before
//    public void setUp() {
//        playerViewModel = new PlayerViewModel();
//        player = new PlayerModel(120);
//        user = new UserModel(player);
//    }
//
//    @Test
//    public void testGetPlayer() {
//        assertEquals(playerViewModel.getPlayerDetails().getPlayerModel().getUserCurrentMoney(),
//                120);
//    }
//}
