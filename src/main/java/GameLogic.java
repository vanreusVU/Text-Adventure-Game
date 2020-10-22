import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class GameLogic{

    public static GameLogic gameLogic = new GameLogic();
    private Commands[] activeCommands;
    private MainMenu mainMenu;

    public GameLogic(){
        mainMenu = new MainMenu();
    }

    public boolean isCommandActive(String command){
        for(Commands cm:activeCommands){
            if(cm.toString().equals(command)){
                return true;
            }
        }
        System.out.println("<font color='red'>Command not usable!");
        return false;
    }

    public boolean validateCommand(String command, String args[] ,int numberOfArgs){
        if(args[0].equals(command)&& args.length - 1 == numberOfArgs){
            return true;
        }
        return false;
    }

    public void setActiveCommands(Commands[] commands){
        this.activeCommands = commands;
    }


    public void useCommand(String[] args){
        String command = args[0];
        if(isCommandActive(command)) {
            if (validateCommand("Take",args,1)) {
                this.grabObject(args[1]);
            } else if (validateCommand("Move",args,1)) {
                if (args[1].equals("forward")) this.move(Direction.Forward);
                else if (args[1].equals("backwards")) this.move(Direction.Backwards);
                else if (args[1].equals("right")) this.move(Direction.Right);
                else if (args[1].equals("left")) this.move(Direction.Left);
                else System.out.println("<font color='red'>Not valid direction!");
            } else if (validateCommand("Use",args,1)) {
                this.useObject(args[1]);
            } else if (validateCommand("Inventory",args,0)) {
                Display.display.openInventory();
            } else if (validateCommand("Inspect",args,1)) {
                System.out.println(this.inspectObject(args[1]));
                Display.display.openOutputLog();
            } else if (validateCommand("LookAround",args,0)) {
                this.lookAround();
                Display.display.openOutputLog();
            } else if (validateCommand("NewGame",args,1)) {
                this.createAndStartGame(args);
            } else if (validateCommand("Continue",args,0)) {
                this.continueGame();
            } else if (validateCommand("SaveGame",args,0)) {
                this.saveGame();
            } else if (validateCommand("Settings",args,0)) {
                mainMenu.initSettings();
            } else if (validateCommand("MusicVolume",args,1)) {
                new Settings().setMusicVolume(Integer.parseInt(args[1]));
            } else if (validateCommand("EffectVolume",args,1)) {
                new Settings().setEffectVolume(Integer.parseInt(args[1]));
            } else if (validateCommand("Fullscreen",args,1)) {
                if(args[1].equals("true")) {
                    new Settings().setFullscreen(true);
                }
                else if(args[1].equals("false")) {
                    new Settings().setFullscreen(false);
                }
                Display.display.initFrame();
            } else if (validateCommand("Menu",args,0)) {
                mainMenu.initMenu();
            } else if (validateCommand("Credits",args,0)) {
                //TODO: Add a little credits
            } else if (validateCommand("Exit",args,0)) {
                System.exit(0);
            } else {
                System.out.println("<font color='red'>Not valid command!");
            }
        }
    }

    //Menu Actions
    public void createAndStartGame(String[] args){
        Player player = new Player();
        player.setPlayerName(args[1]);
        player.setSanityLevel(100);
        player.setPlayerHealth(100);

        GameState tempState = new GameState();
        tempState.setPlayer(player);

        GameData.gameData.setCurrentGameState(tempState);
        //Set level and action to start
        prologue_level prologue = new prologue_level();
        prologue.setCurrentAction(prologue.getStartingAction());
        tempState.setLevel(prologue);

        GameData.gameData.saveGame();
        GameData.gameData.getCurrentGameState().getCurrentAction().activateLevel();
        Display.display.setButtonsVisible(true);
    }

    public void continueGame(){
        GameData.gameData.loadSaveFile();
        GameData.gameData.getCurrentGameState().getCurrentAction().activateLevel();
        Display.display.setButtonsVisible(true);
    }

    public void saveGame(){
        GameData.gameData.saveGame();
    }

    //Game Actions

    private void grabObject(String objectName) {
        InteractiveObjects obj = GameData.gameData.getCurrentGameState().getCurrentAction().getInteractiveObject(objectName);
        if(obj != null) {
            GameData.gameData.getCurrentGameState().getPlayer().addItemToInventory(obj);
            LevelActions updatedAction = GameData.gameData.getCurrentGameState().getCurrentAction().getNewLevelActionFromTake(obj);
            if(updatedAction != null){
                GameData.gameData.getCurrentGameState().getLevel().updateLevelActionWithName(GameData.gameData.getCurrentGameState().getCurrentAction().getActionName(),updatedAction);
                GameData.gameData.getCurrentGameState().getLevel().setCurrentAction(updatedAction);
                GameData.gameData.getCurrentGameState().getCurrentAction().activateLevel();
            }
        }else{
            System.out.println("<font color='red'>Object doesn't exists!");
        }
    }

    private void useObject(String objectName){
        InteractiveObjects obj = GameData.gameData.getCurrentGameState().getPlayer().getItemFromInventory(objectName);
        if(obj != null) {
            LevelActions updatedAction = GameData.gameData.getCurrentGameState().getCurrentAction().getNewLevelActionFromUse(obj);
            if(updatedAction != null){
                GameData.gameData.getCurrentGameState().getLevel().updateLevelActionWithName(GameData.gameData.getCurrentGameState().getCurrentAction().getActionName(),updatedAction);
                GameData.gameData.getCurrentGameState().getLevel().setCurrentAction(updatedAction);
                GameData.gameData.getCurrentGameState().getCurrentAction().activateLevel();
                System.out.println("Object used: " + objectName);
                GameData.gameData.getCurrentGameState().getPlayer().removeItemFromInventory(obj);
            }else{
                System.out.println("<font color='red'>Can't use this item here!");
            }
            return;
        }

        obj = GameData.gameData.getCurrentGameState().getCurrentAction().getInteractiveObject(objectName);
        if(obj != null) {
            LevelActions updatedAction = GameData.gameData.getCurrentGameState().getCurrentAction().getNewLevelActionFromUse(obj);
            if(updatedAction != null){
                GameData.gameData.getCurrentGameState().getLevel().updateLevelActionWithName(GameData.gameData.getCurrentGameState().getCurrentAction().getActionName(),updatedAction);
                GameData.gameData.getCurrentGameState().getLevel().setCurrentAction(updatedAction);
                GameData.gameData.getCurrentGameState().getCurrentAction().activateLevel();
                System.out.println("Object used: " + objectName);
            }else{
                System.out.println("<font color='red'>Can't use this item here!");
            }
            return;
        }
        System.out.println("<font color='red'>Invalid object!");
    }

    private String inspectObject(String objectName){
        InteractiveObjects obj = GameData.gameData.getCurrentGameState().getCurrentAction().getInteractiveObject(objectName);
        if(obj != null) {
            return "<font color='green'>" + obj.getObjectInformation();
        }
        obj = GameData.gameData.getCurrentGameState().getPlayer().getItemFromInventory(objectName);
        if(obj != null){
            return "<font color='green'>" + obj.getObjectInformation();
        }
        return "<font color='red'>Object doesn't exists!";
    }

    private void move(Direction direction){
        if(GameData.gameData.getCurrentGameState().getCurrentAction().getNewLevelActionFromMove(direction) != null) {
            GameData.gameData.getCurrentGameState().setCurrentAction(GameData.gameData.getCurrentGameState().getCurrentAction().getNewLevelActionFromMove(direction));
            GameData.gameData.getCurrentGameState().getCurrentAction().activateLevel();
            System.out.println("Moved: " + direction.toString());
            return;
        }
        System.out.println("<font color='red'>Can't go there!");
    }

    private void lookAround(){ System.out.println("<font color='green'>" + GameData.gameData.getCurrentGameState().getCurrentAction().getLookAround()); }



    private boolean leaveNote(String note){return true;}
};

enum Direction{
    Forward,Backwards,Right,Left
}

enum Commands{
    Move,Take,Inspect,LookAround,NewGame,Settings,Exit,Credits,Inventory,Use,leaveNote,Continue,SaveGame,EffectVolume,MusicVolume,Fullscreen,Menu,Back
}
