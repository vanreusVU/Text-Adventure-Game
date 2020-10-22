import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) throws IOException, ParseException {

        MainMenu mainMenu = new MainMenu();
        mainMenu.initMenu();

        /*if(false) {
            //Create a temp player
            Player player = new Player();
            player.setPlayerName("Simo");
            player.setInventory(null);
            player.setSanityLevel(100);
            player.setObjectHeld(null);
            player.setPlayerHealth(100);

            //Put level and player information to the gameState
            GameState tempState = new GameState();
            tempState.setPlayer(player);

            GameData.gameData.setCurrentGameState(tempState);
            //Set level and action to start
            prologue_level prologue = new prologue_level();
            prologue.setCurrentAction(prologue.bedroom_wardrobe);
            tempState.setLevel(prologue);

            GameData.gameData.addSave("save1");
        }else {
            GameData.gameData.loadSaveFile("save1");
        }



        //System.out.println(GameData.gameData.getCurrentGameState().getCurrentAction().toString());
        GameData.gameData.getCurrentGameState().getCurrentAction().activateLevel();*/
    }
}
