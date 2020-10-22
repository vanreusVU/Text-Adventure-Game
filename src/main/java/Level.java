import java.io.Serializable;
import java.util.ArrayList;

abstract public class Level implements Serializable {
    private ArrayList<LevelActions> levelActions = new ArrayList<LevelActions>();
    private LevelActions startingAction;

    void addLevelAction(LevelActions act){
        levelActions.add(act);
    }

    LevelActions getLevelActionWithName(String actName){
        if(levelActions == null)
            return null;

        for(LevelActions act:levelActions){
            if(act.getActionName().equals(actName)){
                return act;
            }
        }
        return null;
    }

    void setStartingAction(LevelActions startingAction){
        this.startingAction = startingAction;
    }

    LevelActions getStartingAction(){
        return startingAction;
    }

    void updateLevelActionWithName(String name,LevelActions newAction){
        if(levelActions == null)
            return;

        for(int i = 0; i < levelActions.size(); i++){
            if(levelActions.get(i).getActionName().equals(name)){
                levelActions.set(i,newAction);
                return;
            }
        }
    }

    void setCurrentAction(LevelActions action){
        GameData.gameData.getCurrentGameState().setCurrentAction(action);
    }
}
