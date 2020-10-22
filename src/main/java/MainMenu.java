import java.awt.*;

public class MainMenu extends Level{
    public LevelActions mainMenu;

    public void initMenu(){
        mainMenu = new LevelActions(
                "Menu",
                new Commands[]{Commands.NewGame,Commands.Continue,Commands.Settings,Commands.Exit},
                "Welcome to [Bostrom's Dream].",
                "",
                null,
                new Color(86,22,95),
                "idle_background.jpg",
                "MainMenu");
        mainMenu.activateLevel();
    }

    public void initSettings(){
        mainMenu = new LevelActions(
                "Settings",
                new Commands[]{Commands.EffectVolume,Commands.MusicVolume,Commands.Fullscreen,Commands.Menu,Commands.Exit},
                "You can change the game settings from here. Remember you can't access this menu in-game.",
                "",
                null,
                new Color(52, 6,95),
                "idle_background.jpg",
                "Settings");
        mainMenu.activateLevel();
    }
}
