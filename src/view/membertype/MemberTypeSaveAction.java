package view.membertype;

import dao.MemberTypeDao;
import model.MemberType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class MemberTypeSaveAction implements ActionListener {
    private final MemberTypeFrame memberTypeFrame;
    private final MemberTypeDao memberTypeDao;

    public MemberTypeSaveAction(MemberTypeFrame memberTypeFrame, MemberTypeDao memberTypeDao) {
        this.memberTypeFrame = memberTypeFrame;
        this.memberTypeDao = memberTypeDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = this.memberTypeFrame.getName();
        MemberType memberType = new MemberType();
        memberType.setId(UUID.randomUUID().toString());
        memberType.setName(name);

        this.memberTypeFrame.addMemberType(memberType);
        this.memberTypeDao.insert(memberType);
    }
}
