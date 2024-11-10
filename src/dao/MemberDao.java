package dao;

import db.MySqlConnection;
import model.Member;
import model.MemberType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    public int insert(Member member) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO members (id, name, member_type_id) VALUES (?, ?, ?)");
            statement.setString(1, member.getId());
            statement.setString(2, member.getName());
            statement.setString(3, member.getMemberType().getId());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Member member) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE members SET name = ?, member_type_id = ? WHERE id = ?");
            statement.setString(1, member.getName());
            statement.setString(2, member.getMemberType().getId());
            statement.setString(3, member.getId());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(String id) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM members WHERE id = ?");
            statement.setString(1, id);

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();

        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            Statement statement = connection.createStatement();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM member_type")) {
                while (resultSet.next()) {
                    Member member = new Member();
                    member.setId(resultSet.getString("id"));
                    member.setName(resultSet.getString("name"));

                    MemberType memberType = new MemberType();
                    memberType.setId(resultSet.getString("member_type_id"));
                    memberType.setName(resultSet.getString("member_type_name"));

                    member.setMemberType(memberType);

                    list.add(member);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
