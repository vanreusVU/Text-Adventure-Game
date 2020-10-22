import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private String playerName;
    private int sanityLevel;
    private int playerHealth;
    private ArrayList<InteractiveObjects> Inventory = new ArrayList<InteractiveObjects>();

    public ArrayList<String> getInventory() {
        ArrayList<String> inventoryString = new ArrayList<String>();
        for(InteractiveObjects object:Inventory){
            inventoryString.add(object.getObjectName() + "<br>");
        }
        return inventoryString;
    }

    public void removeItemFromInventory(InteractiveObjects objToDelete){
        this.Inventory.remove(objToDelete);
    }

    public InteractiveObjects getItemFromInventory(String objName){
        for(InteractiveObjects object:Inventory){
            if(object.getObjectName().equals(objName)){
                return object;
            }
        }
        return null;
    }

    public void addItemToInventory(InteractiveObjects obj) {
        System.out.println("Taken: " + obj.getObjectName());
        this.Inventory.add(obj);
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public int getSanityLevel() {
        return sanityLevel;
    }

    public void setSanityLevel(int sanityLevel) {
        this.sanityLevel = sanityLevel;
    }

}
