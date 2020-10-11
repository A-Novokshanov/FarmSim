//package junits.milestone2;
//
//import models.PlayerModel;
//import models.SettingModel;
//import models.UserModel;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class UserTestRohanKashi {
//    private PlayerModel typePlayer;
//    private UserModel userTest;
//    private SettingModel userSettings;
//
//    @Before
//    public void setup() {
//        userSettings = new SettingModel();
//        typePlayer = new PlayerModel(100);
//        this.userTest = new UserModel(typePlayer);
//    }
//
//    //Test getter method for UserModel
//    @Test
//    public void testGetUserType() {
//        assertEquals(typePlayer, this.userTest.getPlayerModel());
//    }
//
//    //Test setter method for UserModel
//    @Test
//    public void testSetUserType() {
//        PlayerModel newPlayer = new PlayerModel(3);
//        userTest.setPlayerModel(newPlayer);
//        assertEquals(newPlayer, this.userTest.getPlayerModel());
//    }
//
//}
