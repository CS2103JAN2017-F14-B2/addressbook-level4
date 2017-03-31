package seedu.task.model.tag;

import java.util.HashMap;
import java.util.Random;

public class TagColorMap {
    private static HashMap<String, String> tagColorMap;

    //@@author A0142939W
    public static void setColor(String tag) {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        String hex = String.format("#%02x%02x%02x", r, g, b);
        tagColorMap.put(tag, hex);
    }

    //@@author A0142939W
    public static String getColor(String tag) {
        if (tagColorMap == null) {
            tagColorMap = new HashMap<String, String>();
        }
        if (!tagColorMap.containsKey(tag)) {
            setColor(tag);
        }

        return tagColorMap.get(tag);
    }
}