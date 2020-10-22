import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class LevelActions implements Serializable {
    private Commands[] commands;
    private Color panelColor;
    private String backgroundImage, statusText, narration, lookAround, actionName;
    private Map<Direction,String> movingResults;
    private Map<InteractiveObjects,LevelActions> takingResults;
    private Map<InteractiveObjects,LevelActions> usingResults;
    private ArrayList<InteractiveObjects> interactiveObjects;

    public void setMoveResults(Map<Direction,String> resultingActions){
        movingResults = resultingActions;
    }

    public void setTakeResults(Map<InteractiveObjects,LevelActions> resultingActions) { takingResults = resultingActions; }

    public void setUsingResults(Map<InteractiveObjects,LevelActions> resultingActions) { usingResults = resultingActions; }

    public Commands[] getCommands(){
        return commands;
    }

    public Map<Direction,String> getDirections(){
        return movingResults;
    }

    public LevelActions getNewLevelActionFromUse(InteractiveObjects object){
        if(this.usingResults == null)
            return null;

        for(Map.Entry<InteractiveObjects,LevelActions> m: this.usingResults.entrySet()){
            if(m.getKey() == object){
                return (LevelActions) m.getValue();
            }
        }
        return null;
    }

    public LevelActions getNewLevelActionFromTake(InteractiveObjects object){
        if(this.takingResults == null)
            return null;

        for(Map.Entry<InteractiveObjects,LevelActions> m: this.takingResults.entrySet()){
            if(m.getKey() == object){
                return (LevelActions) m.getValue();
            }
        }
        return null;
    }

    public LevelActions getNewLevelActionFromMove(Direction direction){
        if(this.movingResults == null)
            return null;

        for(Map.Entry<Direction,String> m: this.movingResults.entrySet()){
            if(m.getKey() == direction){
                return GameData.gameData.getCurrentGameState().getLevel().getLevelActionWithName(m.getValue());
            }
        }
        return null;
    }

    public InteractiveObjects getInteractiveObject(String objectName){
        if(this.interactiveObjects == null)
            return null;

        for(InteractiveObjects object:this.interactiveObjects){
            if(object.getObjectName().equals(objectName)) {
                return object;
            }
        }
        return null;
    }

    public String getActionName(){
        return actionName;
    }

    public String getLookAround() {return lookAround;}

    private String fitText(String text, int charLimit){
        StringBuffer newText = new StringBuffer(text);
        int devide = text.length()/charLimit;
        for(int i = 1; i <= devide; i++){
            newText.insert(30 * i, '\r');
        }
        return newText.toString();
    }

    public void activateLevel(){
        Display.display.setStatusText(statusText);
        Display.display.setBackgroundImage(backgroundImage);
        Display.display.setNarrationText(narration);
        Display.display.setCommandList(commands);
        Display.display.setPanelColors(panelColor);
        GameLogic.gameLogic.setActiveCommands(commands);
    }

    public LevelActions(String actName, Commands[] cmd,String nrt,String lkAround,ArrayList<InteractiveObjects> objects, Color pnlClr, String bckImg, String stTxt ){
        actionName = actName;
        commands = cmd;
        narration = nrt;
        lookAround = fitText(lkAround,30);
        panelColor = pnlClr;
        backgroundImage = bckImg;
        statusText = stTxt;
        interactiveObjects = objects;
    }
}

enum ResponseType{
    Dialogue, ObjectInteraction, NumericDialogue
}
