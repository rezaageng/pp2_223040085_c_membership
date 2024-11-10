package view.membertype;

import dao.MemberTypeDao;
import model.MemberType;

import javax.swing.*;
import java.util.List;

public class MemberTypeFrame extends JFrame {
    private final List<MemberType> memberTypeList;
    private final JTextField nameField;

    public MemberTypeFrame(MemberTypeDao memberTypeDao) {
        this.memberTypeList = memberTypeDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel inputLabel = new JLabel("Name");
        inputLabel.setBounds(15, 40, 350, 10);

        nameField = new JTextField();
        nameField.setBounds(15, 60, 350, 30);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(15, 100, 350, 50);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 150, 350, 200);

        MemberTypeTableModel memberTypeTableModel = new MemberTypeTableModel(memberTypeList);
        table.setModel(memberTypeTableModel);

        MemberTypeSaveAction saveAction = new MemberTypeSaveAction(this, memberTypeDao);

        saveButton.addActionListener(saveAction);

        this.add(inputLabel);
        this.add(nameField);
        this.add(saveButton);
        this.add(scrollPane);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public String getName() {
        return this.nameField.getText();
    }

    public void addMemberType(MemberType memberType) {
        this.memberTypeList.add(memberType);
        this.nameField.setText("");
    }
}
