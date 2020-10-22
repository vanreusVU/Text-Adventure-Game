import java.io.Serializable;

public class InteractiveObjects implements Serializable {

    private String objectName;
    private ItemType objectType;
    private String objectInformation;
    private int objectAmount;
    private boolean isObjectUsed;
    private boolean isObjectPlaced;
    private boolean isObjectHeld;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public ItemType getObjectType() {
        return objectType;
    }

    public void setObjectType(ItemType objectType) {
        this.objectType = objectType;
    }

    public String getObjectInformation() {
        return objectInformation;
    }

    private String fitText(String text, int charLimit){
        StringBuffer newText = new StringBuffer(text);
        int devide = text.length()/charLimit;
        for(int i = 1; i <= devide; i++){
            newText.insert(30 * i, '\r');
        }
        return newText.toString();
    }

    public void setObjectInformation(String objectInformation) {

        this.objectInformation = fitText(objectInformation, 30);
    }

    public int getObjectAmount() {
        return objectAmount;
    }

    public void setObjectAmount(int objectAmount) {
        this.objectAmount = objectAmount;
    }

    public void setObjectPlaced(boolean objectPlaced) {
        isObjectPlaced = objectPlaced;
    }

    public boolean isObjectPlaced() {
        return isObjectPlaced;
    }

    public void setObjectHeld(boolean objectHeld) {
        isObjectHeld = objectHeld;
    }

    public boolean isObjectHeld() {
        return isObjectHeld;
    }

    public void setObjectUsed(boolean objectUsed) {
        isObjectUsed = objectUsed;
    }

    public boolean isObjectUsed() {
        return isObjectUsed;
    }

    public InteractiveObjects(String objectName, ItemType objectType, String objectInformation, int objectAmount, boolean isObjectUsed,
                              boolean isObjectPlaced, boolean isObjectHeld){
        setObjectName(objectName);
        setObjectType(objectType);
        setObjectInformation(objectInformation);
        setObjectAmount(objectAmount);
        setObjectUsed(isObjectUsed);
        setObjectPlaced(isObjectPlaced);
        setObjectHeld(isObjectHeld);
    }
}

enum ItemType{
    Weapon, Interactive,Inspectable
}