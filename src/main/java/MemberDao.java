import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MemberDao {

    public void saveMember(MemberDto memberDto) {
        try (Connection con = getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO members(name) VALUES(?)");
            preparedStatement.setString(1, memberDto.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<MemberDto> findByName(String name) {
        try (Connection con = getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM members WHERE name = ?");
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                rs.next();
                return Optional.of(new MemberDto(rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void deleteAll() {
        try (Connection con = getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM members");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:13307/member?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                    "user", "password");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
