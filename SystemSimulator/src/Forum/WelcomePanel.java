package Forum;

import ProgramGUI.GUIComponents.Panes.NullPanel;
import ProgramGUI.GUIComponents.SystemLabel;
import ProgramUtils.ForumUtils;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;

public class WelcomePanel extends NullPanel {
    public WelcomePanel() {
        GridBagConstraints gc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        NullPanel topicsList = new NullPanel();
        JScrollPane topics = new JScrollPane(topicsList);
        topics.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        NullPanel header = new NullPanel();
        topicsList.add(ForumUtils.getTopics());
        header.add(new SystemLabel("SYSTEM Forum"));
        //add(header, gc);
        add(topics, gc);
        setVisible(false);
    }    
}