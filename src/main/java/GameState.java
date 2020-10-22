import java.io.Serializable;

public class GameState implements Serializable {

    private Player player;
    private Level level;
    private LevelActions currentAction;
    private String levelName;

    public void setCurrentAction(LevelActions action) { this.currentAction = action; }

    public LevelActions getCurrentAction(){ return this.currentAction; }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Level getLevel() {
        return level;
    }

    public String  getLevelName() {
        return level.getClass().getSimpleName();
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
