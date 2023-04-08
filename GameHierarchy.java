import com.seaBattle.Model.DeckSettings;
import com.seaBattle.View.MainFrameGUI;

public class GameHierarchy {
    public static void main(String[] args) {
        DeckSettings ds = new DeckSettings();
        ds.deckButtonTextListCreator();
        MainFrameGUI mfg = new MainFrameGUI();
        mfg.graphicPartStarter();
        mfg.getMainFrame().setVisible(true);
    }
}