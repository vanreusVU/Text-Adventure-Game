import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {

    JSONParser jsonParser = new JSONParser();
    Object object;

        { try {
            object = jsonParser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\settings.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } }

    JSONObject jsonObject =(JSONObject) object;
    private Dimension resolution = new Dimension (Integer.parseInt(jsonObject.get("xPixels").toString()), Integer.parseInt(jsonObject.get("yPixels").toString()));
    private int musicVolume = Integer.parseInt(jsonObject.get("musicVolume").toString());
    private int effectVolume = Integer.parseInt(jsonObject.get("effectVolume").toString());
    private int fullscreen = Integer.parseInt(jsonObject.get("fullscreen").toString());



    @SuppressWarnings("unchecked")
    public void setFullscreen (boolean fullscreen){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xPixels", resolution.width);
        jsonObject.put("yPixels",  resolution.height);
        jsonObject.put("musicVolume", musicVolume);
        jsonObject.put("effectVolume", effectVolume);
        jsonObject.put("fullscreen", fullscreen ? 1: 0);
        try(FileWriter file = new FileWriter(System.getProperty("user.dir") + "/src/main/java/settings.json")) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fullscreen = fullscreen? 1 : 0;
    }

    @SuppressWarnings("unchecked")
    public void setResolution (Dimension resolution){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xPixels", resolution.width);
        jsonObject.put("yPixels",  resolution.height);
        jsonObject.put("musicVolume", musicVolume);
        jsonObject.put("effectVolume", effectVolume);
        jsonObject.put("fullscreen", fullscreen);
        try(FileWriter file = new FileWriter(System.getProperty("user.dir") + "/src/main/java/settings.json")) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
       this.resolution = resolution;
    }

    public Dimension getResolution () {
        return resolution;
    }

    public Boolean getFullscreen () {
        return fullscreen == 1;
    }

    @SuppressWarnings("unchecked")
    public void setEffectVolume(int effectVolume) {
        if(effectVolume >= 0 && effectVolume <= 100) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("xPixels", resolution.width);
            jsonObject.put("yPixels", resolution.height);
            jsonObject.put("musicVolume", musicVolume);
            jsonObject.put("effectVolume", effectVolume);
            jsonObject.put("fullscreen", fullscreen);
            try (FileWriter file = new FileWriter( System.getProperty("user.dir") + "/src/main/java/settings.json")) {
                file.write(jsonObject.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.effectVolume = effectVolume;
        }
    }

    public int getEffectVolume() {
        return effectVolume;
    }

    @SuppressWarnings("unchecked")
    public void setMusicVolume(int musicVolume) {
        if(musicVolume >= 0 && musicVolume <= 100) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("xPixels", resolution.width);
            jsonObject.put("yPixels", resolution.height);
            jsonObject.put("musicVolume", musicVolume);
            jsonObject.put("effectVolume", effectVolume);
            jsonObject.put("fullscreen", fullscreen);
            try (FileWriter file = new FileWriter( System.getProperty("user.dir") + "/src/main/java/settings.json")) {
                file.write(jsonObject.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.musicVolume = musicVolume;
        }
    }

    public int getMusicVolume() {
        return musicVolume;
    }
}
