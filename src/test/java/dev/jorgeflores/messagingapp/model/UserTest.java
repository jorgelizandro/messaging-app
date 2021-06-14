package dev.jorgeflores.messagingapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    private static final Long EXPECTED_UID = 1L;
    private static final String EXPECTED_NICKNAME = "nickname";
    private User user;

    @Before
    public void setUp() {
        user = new User(EXPECTED_UID, EXPECTED_NICKNAME);
    }

    @Test
    public void testGetters() {
        Long uid = user.getUid();
        String nickname = user.getNickname();

        assertThat(uid, is(EXPECTED_UID));
        assertThat(nickname, is(EXPECTED_NICKNAME));
    }
}
