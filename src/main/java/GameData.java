import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameData {

    public static GameData gameData = new GameData();
    private GameState currentGameState;
    private Stats gameStats;

    public Stats getGameStats() {
        return gameStats;
    }

    public void setGameStats(Stats gameStats) {
        this.gameStats = gameStats;
    }

    public void setCurrentGameState(GameState crState){
        this.currentGameState = crState;
    }

    public GameState getCurrentGameState(){
        return this.currentGameState;
    }

    public void loadSaveFile() {
        try {
            FileInputStream fi = new FileInputStream(new File( System.getProperty("user.dir") + "\\src\\main\\java\\saves\\gameSave.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            setCurrentGameState((GameState) oi.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("loadSaveFile Error: " + e.toString());
        }
    }


    public void saveGame(){
        try {
            FileOutputStream f = new FileOutputStream(new File(System.getProperty("user.dir") +"\\src\\main\\java\\saves\\gameSave.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(currentGameState);
            o.close();
            f.close();

            System.out.println("Game Saved");
        } catch (IOException e) {
            System.out.println("Save Game error: " + e.toString());
        }


    }
}

