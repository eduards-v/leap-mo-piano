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
        final AudioClip do_28 =
                new AudioClip(getClass().getResource("notes/28_do.wav").toString());
        final AudioClip re_30 =
                new AudioClip(getClass().getResource("notes/30_re.wav").toString());
        final AudioClip mi_32 =
                new AudioClip(getClass().getResource("notes/32_mi.wav").toString());
        final AudioClip fa_33 =
                new AudioClip(getClass().getResource("notes/33_fa.wav").toString());
        final AudioClip sol_35 =
                new AudioClip(getClass().getResource("notes/35_sol.wav").toString());
        final AudioClip la_37 =
                new AudioClip(getClass().getResource("notes/37_la.wav").toString());
        final AudioClip si_39 =
                new AudioClip(getClass().getResource("notes/39_si.wav").toString());
        final AudioClip do_40 =
                new AudioClip(getClass().getResource("notes/40_do.wav").toString());

        notesMap.put("do_28", do_28);
        notesMap.put("re_30", re_30);
        notesMap.put("mi_32", mi_32);
        notesMap.put("fa_33", fa_33);
        notesMap.put("sol_35", sol_35);
        notesMap.put("la_37", la_37);
        notesMap.put("si_39", si_39);
        notesMap.put("do_40", do_40);
    }

    public Map getContainer() {
        return notesMap;
    }
}
