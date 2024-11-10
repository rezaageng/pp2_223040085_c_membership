package view.member;

import dao.MemberDao;
import model.Member;
import model.MemberType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class MemberSaveAction implements ActionListener {
    private final MemberFrame memberFrame;
    private final MemberDao memberDao;

    public MemberSaveAction(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = this.memberFrame.getName();

        if (name.isEmpty()) {
            this.memberFrame.showAlert("Name is required");
        } else {
            MemberType memberType = this.memberFrame.getMemberType();
            Member member = new Member();
            member.setId(UUID.randomUUID().toString());
            member.setName(name);
            member.setMemberType(memberType);

            this.memberFrame.addMember(member);
            this.memberDao.insert(member);
        }
    }
}
