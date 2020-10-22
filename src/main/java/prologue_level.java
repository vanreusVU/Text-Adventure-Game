import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*TODO: How to create a level
*  1) Call addLevelAction() and create your action in there
*  2) Give attributes to your Level Action after you are done witimport org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*TODO: How to create a level
*  1) Call addLevelAction() and create your action in there
*  2) Give attributes to your Level Action after you are done with creating all of the levelActions
*  4) Call setStartingAction() with your starting action as the parameter at the end of the class*/


public class prologue_level extends Level implements Serializable{
    public prologue_level() {
        //TODO: To add ways and takes always use these variables and reinit them everytime you use it in an action
        java.util.Map<Direction,String> ways;
        java.util.Map<InteractiveObjects,LevelActions> takes;
        java.util.Map<InteractiveObjects,LevelActions> uses;

        //TODO: Put the objects here
        InteractiveObjects key = new InteractiveObjects("key",ItemType.Inspectable,"It's and old looking key",1
                ,false,false,false);

        addLevelAction(new LevelActions(
                "bedroom",
                new Commands[]{Commands.Move,Commands.Take,Commands.Inspect,Commands.LookAround,Commands.Inventory,Commands.Use,Commands.SaveGame,Commands.Exit},
                "You are in the bedroom. There is nothing around that glances to your eye.",
                "You see a wardrobe on your left, a door that opens to the hallway in front of you, and a toilet on your right",
                null,
                new Color(49, 48, 49),
                "home_background.jpg",
                "Bedroom"));

        addLevelAction(new LevelActions(
                "bedroom_wardrobe",
                new Commands[]{Commands.Move,Commands.Take,Commands.Inspect,Commands.LookAround,Commands.Inventory,Commands.Use,Commands.SaveGame,Commands.Exit},
                "You opened the wardrobe and you saw a [key] in the wardrobe.",
                "Wardrobe seems pretty empty other then the key.",
                new ArrayList<InteractiveObjects>(Arrays.asList(key)),
                new Color(36, 36, 36),
                "home_background.jpg",
                "Wardrobe"));

        addLevelAction(new LevelActions(
                "bedroom_window",
                new Commands[]{Commands.Move,Commands.Take,Commands.Inspect,Commands.LookAround,Commands.Inventory,Commands.Use,Commands.SaveGame,Commands.Exit},
                "You looked out. It seems like its going to rain",
                "Nothing to inspect",
                null,
                new Color(68, 73, 75),
                "home_background.jpg",
                "Window"));

        addLevelAction(new LevelActions(
                "bedroom_toilet",
                new Commands[]{Commands.Move,Commands.Take,Commands.Inspect,Commands.LookAround,Commands.Inventory,Commands.Use,Commands.SaveGame,Commands.Exit},
                "Toilet is quite nice and clean. Nothing seems off.",
                "Nothing to inspect",
                null,
                new Color(92, 98, 100),
                "toilet_background.jpg",
                "Toilet"));

        addLevelAction(new LevelActions(
                "main_hall",
                new Commands[]{Commands.Move,Commands.Take,Commands.Inspect,Commands.LookAround,Commands.Inventory,Commands.Use,Commands.SaveGame,Commands.Exit},
                "You can see a dim light coming under the door across the hallway.",
                "Because of the little light in the hallway you cant really see anything.",
                null,
                new Color(36, 36, 36),
                "hallway_background.jpg",
                "Main Hall"));

        addLevelAction(new LevelActions(
                "main_hall_locked_door",
                new Commands[]{Commands.Move,Commands.LookAround,Commands.Inventory,Commands.Use,Commands.SaveGame,Commands.Exit},
                "You are in-front of a door.",
                "The door seems to be locked.",
                null,
                new Color(36, 36, 36),
                "hallway_background.jpg",
                "The Door"));

        //BEDROOM MAIN
        ways = new HashMap<Direction,String>();
        takes = new HashMap<InteractiveObjects,LevelActions>();
        uses = new HashMap<InteractiveObjects,LevelActions>();

        ways.put(Direction.Forward, "main_hall");
        ways.put(Direction.Backwards, "bedroom_window");
        ways.put(Direction.Left, "bedroom_wardrobe");
        ways.put(Direction.Right, "bedroom_toilet");
        getLevelActionWithName("bedroom").setMoveResults(ways);

        //BEDROOM WARDROBE
        ways = new HashMap<Direction,String>();
        takes = new HashMap<InteractiveObjects,LevelActions>();
        uses = new HashMap<InteractiveObjects,LevelActions>();

        ways.put(Direction.Right, "bedroom");
        getLevelActionWithName("bedroom_wardrobe").setMoveResults(ways);

        LevelActions keyTaken = new LevelActions(
                "bedroom_wardrobe",
                new Commands[]{Commands.Move,Commands.Take,Commands.Inspect,Commands.LookAround,Commands.Inventory,Commands.Use,Commands.SaveGame,Commands.Exit},
                "The wardrobe is empty.",
                "The wardrobe looks pretty empty without the key",
                null,
                new Color(36, 36, 36),
                "home_background.jpg",
                "Wardrobe");
        keyTaken.setMoveResults(getLevelActionWithName("bedroom_wardrobe").getDirections());
        takes.put(key, keyTaken);
        getLevelActionWithName("bedroom_wardrobe").setTakeResults(takes);

        //BEDROOM WINDOW
        ways = new HashMap<Direction,String>();
        takes = new HashMap<InteractiveObjects,LevelActions>();
        uses = new HashMap<InteractiveObjects,LevelActions>();

        ways.put(Direction.Forward, "bedroom");
        getLevelActionWithName("bedroom_window").setMoveResults(ways);

        //BEDROOM TOILET
        ways = new HashMap<Direction,String>();
        takes = new HashMap<InteractiveObjects,LevelActions>();
        uses = new HashMap<InteractiveObjects,LevelActions>();

        ways.put(Direction.Left, "bedroom");
        getLevelActionWithName("bedroom_toilet").setMoveResults(ways);

        //MAIN HALL
        ways = new HashMap<Direction,String>();
        takes = new HashMap<InteractiveObjects,LevelActions>();
        uses = new HashMap<InteractiveObjects,LevelActions>();

        ways.put(Direction.Backwards, "bedroom");
        ways.put(Direction.Forward, "main_hall_locked_door");
        getLevelActionWithName("main_hall").setMoveResults(ways);

        //MAIN HALL LOCKED DOOR
        ways = new HashMap<Direction,String>();
        takes = new HashMap<InteractiveObjects,LevelActions>();
        uses = new HashMap<InteractiveObjects,LevelActions>();

        ways.put(Direction.Backwards, "main_hall");
        getLevelActionWithName("main_hall_locked_door").setMoveResults(ways);
        LevelActions keyUsed = new LevelActions(
                "Demo_end",
                new Commands[]{Commands.Exit},
                "Thank you for trying out the demo.",
                "There is nothing to look around bro... the game is done",
                null,
                new Color(86,22,95),
                "idle_background.jpg",
                "Demo Ended");
        uses.put(key,keyUsed);
        getLevelActionWithName("main_hall_locked_door").setUsingResults(uses);


        //TODO: Set starting ACT
        this.setStartingAction(getLevelActionWithName("bedroom"));
    }

}
