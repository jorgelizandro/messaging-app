package dev.jorgeflores.messagingapp.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MessageTest {
    private static final Long EXPECTED_MESSAGE_ID = 1L;
    private static final Long EXPECTED_SENDER_UID = 2L;
    private static final Long EXPECTED_RECEIVER_UID = 3L;
    private static final String EXPECTED_CONTENT = "content";

    private Message message;

    @Before
    public void setUp() throws Exception {
        message = new Message(EXPECTED_MESSAGE_ID, EXPECTED_SENDER_UID, EXPECTED_RECEIVER_UID, EXPECTED_CONTENT);
    }

    @Test
    public void testGetters() {
        assertThat(message.getMessageId(), is(EXPECTED_MESSAGE_ID));
        assertThat(message.getSenderUid(), is(EXPECTED_SENDER_UID));
        assertThat(message.getReceiverUid(), is(EXPECTED_RECEIVER_UID));
        assertThat(message.getContent(), is(EXPECTED_CONTENT));
    }
}
