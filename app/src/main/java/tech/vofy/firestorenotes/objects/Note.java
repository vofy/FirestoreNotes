package tech.vofy.firestorenotes.objects;

import java.util.HashMap;
import java.util.Map;

public class Note {

    private String title;
    private String content;
    private String lastModified;

    public Note() {

    }

    public HashMap<String, Object> getHashMap() {
        HashMap<String, Object> noteMap = new HashMap<>();
        noteMap.put("title", title);
        noteMap.put("content", content);
        noteMap.put("lastModified", lastModified);

        return noteMap;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModified() {
        return lastModified;
    }
}