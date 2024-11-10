package view.main;

import dao.MemberDao;
import dao.MemberTypeDao;
import view.member.MemberFrame;
import view.membertype.MemberTypeFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final JButton memberTypeButton;
    private final JButton memberButton;
    private final MemberTypeDao memberTypeDao;
    private final MemberDao memberDao;
    private MemberTypeFrame memberTypeFrame;
    private MemberFrame memberFrame;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(400, 500);

        this.memberTypeDao = new MemberTypeDao();
        this.memberDao = new MemberDao();

        this.memberTypeFrame = new MemberTypeFrame(this.memberTypeDao);
        this.memberFrame = new MemberFrame(this.memberDao, this.memberTypeDao);

        this.setLayout(new FlowLayout());
        MainAction action = new MainAction(this);

        this.memberTypeButton = new JButton("Member Type");
        this.memberButton = new JButton("Member");

        this.memberTypeButton.addActionListener(action);
        this.memberButton.addActionListener(action);

        this.add(memberTypeButton);
        this.add(memberButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }

    public JButton getMemberTypeButton() {
        return memberTypeButton;
    }

    public JButton getMemberButton() {
        return memberButton;
    }

    public void showMemberTypeFrame() {
        if (memberTypeFrame == null) {
            memberTypeFrame = new MemberTypeFrame(memberTypeDao);
        }

        memberTypeFrame.setVisible(true);
    }

    public void showMemberFrame() {
        if (memberFrame == null) {
            memberFrame = new MemberFrame(memberDao, memberTypeDao);
        }

        memberFrame.setVisible(true);
    }
}
