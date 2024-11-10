package view.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAction implements ActionListener {
    private final MainFrame mainFrame;

    public MainAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFrame.getMemberTypeButton()) {
            mainFrame.showMemberTypeFrame();
        } else if (e.getSource() == mainFrame.getMemberButton()) {
            mainFrame.showMemberFrame();
        }
    }
}
