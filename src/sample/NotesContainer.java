package sample;

import javafx.scene.media.AudioClip;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NotesContainer {

    private static NotesContainer container = new NotesContainer();
    private Map<String, AudioClip> notesMap;


    private NotesContainer() {
        notesMap = new HashMap<>();
        initNoteMap();
    }


    public static NotesContainer getInstance() {
        return container;
    }


    private void initNoteMap(){
        final AudioClip a1 =
                new AudioClip(getClass().getResource("notes/a1.wav").toString());
        final AudioClip b1 =
                new AudioClip(getClass().getResource("notes/b1.wav").toString());
        final AudioClip c1 =
                new AudioClip(getClass().getResource("notes/c1.wav").toString());
        final AudioClip d1 =
                new AudioClip(getClass().getResource("notes/d1.wav").toString());
        final AudioClip e1 =
                new AudioClip(getClass().getResource("notes/e1.wav").toString());
        final AudioClip f1 =
                new AudioClip(getClass().getResource("notes/f1.wav").toString());
        final AudioClip g1 =
                new AudioClip(getClass().getResource("notes/g1.wav").toString());

        notesMap.put("a1", a1);
        notesMap.put("b1", b1);
        notesMap.put("c1", c1);
        notesMap.put("d1", d1);
        notesMap.put("e1", e1);
        notesMap.put("f1", f1);
        notesMap.put("g1", g1);
    }

    public Map getContainer() {
        return notesMap;
    }
}
