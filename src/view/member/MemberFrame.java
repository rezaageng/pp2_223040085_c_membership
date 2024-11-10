package view.member;

import dao.MemberDao;
import dao.MemberTypeDao;
import model.Member;
import model.MemberType;

import javax.swing.*;
import java.util.List;

public class MemberFrame extends JFrame {
    private final List<MemberType> memberTypeList;
    private final JTextField nameField;
    private final MemberTableModel tableModel;
    private final JComboBox memberTypeComboBox;

    public MemberFrame(MemberDao memberDao, MemberTypeDao memberTypeDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Member> memberList = memberDao.findAll();
        this.memberTypeList = memberTypeDao.findAll();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(15, 40, 350, 10);

        nameField = new JTextField();
        nameField.setBounds(15, 60, 350, 30);

        JLabel memberTypeLabel = new JLabel("Member Type");
        memberTypeLabel.setBounds(15, 100, 350, 10);

        memberTypeComboBox = new JComboBox();
        memberTypeComboBox.setBounds(15, 120, 350, 30);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(15, 160, 350, 50);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 220, 350, 200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        MemberSaveAction saveAction = new MemberSaveAction(this, memberDao);

        saveButton.addActionListener(saveAction);

        this.add(nameLabel);
        this.add(nameField);
        this.add(memberTypeLabel);
        this.add(memberTypeComboBox);
        this.add(saveButton);
        this.add(scrollPane);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public void populateMemberTypeComboBox() {
        memberTypeComboBox.removeAllItems();
        for (MemberType memberType : memberTypeList) {
            memberTypeComboBox.addItem(memberType);
        }
    }

    public String getName() {
        return this.nameField.getText();
    }

    public MemberType getMemberType() {
        return memberTypeList.get(memberTypeComboBox.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.add(member);
        nameField.setText("");

    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
