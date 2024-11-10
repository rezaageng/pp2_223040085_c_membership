package dao;

import db.MySqlConnection;
import model.MemberType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberTypeDao {
    public int insert(MemberType memberType) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO member_types (id, name) VALUES (?, ?)");
            statement.setString(1, memberType.getId());
            statement.setString(2, memberType.getName());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int update(MemberType memberType) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE member_types SET name = ? WHERE id = ?");
            statement.setString(1, memberType.getName());
            statement.setString(2, memberType.getId());

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(String id) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM member_types WHERE id = ?");
            statement.setString(1, id);

            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MemberType> findAll() {
        List<MemberType> list = new ArrayList<>();

        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            Statement statement = connection.createStatement();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM member_types")) {
                while (resultSet.next()) {
                    MemberType memberType = new MemberType();
                    memberType.setId(resultSet.getString("id"));
                    memberType.setName(resultSet.getString("name"));
                    list.add(memberType);
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
