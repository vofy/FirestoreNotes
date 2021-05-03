package tech.vofy.firestorenotes.objects;

import java.util.HashMap;
import java.util.Map;

public class Note {

    public String title;
    public String content;
    public String lastModified;

    public Note() {

    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateNote(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public HashMap<String, Object> getHashMap() {
        HashMap<String, Object> noteMap = new HashMap<>();
        noteMap.put("title", title);
        noteMap.put("content", content);
        noteMap.put("lastModified", lastModified);

        return noteMap;
    }
}