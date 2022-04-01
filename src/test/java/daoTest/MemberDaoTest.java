package daoTest;

import dao.MemberDao;
import member.Member;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDaoTest {

    @Test
    void connection() throws SQLException {
        final MemberDao memberDao = new MemberDao();
        final Connection connection = memberDao.getConnection();
        connection.setAutoCommit(false);
        assertThat(connection).isNotNull();
        connection.rollback();
    }

    @Test
    void save() {
        final MemberDao memberDao = new MemberDao();
        memberDao.save(new Member("Rex", "오성원"));
    }

    @Test
    void findById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findById("Rex");
        assertThat(member.getName()).isEqualTo("오성원");
    }

    @Test
    void findWithRoleById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findWithRoleById("Rex");
        assertThat(member.getRole().getRole()).isEqualTo("admin");
    }

    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        final List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }
}
