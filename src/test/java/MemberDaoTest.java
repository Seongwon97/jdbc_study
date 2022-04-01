import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberDaoTest {

    public static final String NAME = "verus";

    private MemberDao dao;

    @BeforeEach
    void setUp() {
        dao = new MemberDao();
        dao.saveMember(new MemberDto(NAME));
    }

    @AfterEach
    void tearDown() {
        dao.deleteAll();
    }

    @Test
    void findByName() {
        Optional<MemberDto> actual = dao.findByName(NAME);

        assertAll(() -> {
            assertThat(actual.isPresent()).isTrue();
            assertThat(actual.get()).isEqualTo(new MemberDto(NAME));
        });
    }

}
