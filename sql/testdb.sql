-- Create database
CREATE DATABASE testdb;
\connect testdb;

-- Create tables
CREATE TABLE user_account (
    uid SERIAL PRIMARY KEY,
    nickname VARCHAR(50) UNIQUE NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE message (
    message_id SERIAL PRIMARY KEY,
    sender_uid integer NOT NULL REFERENCES user_account(uid),
    receiver_uid integer NOT NULL REFERENCES user_account(uid),
    content VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Insert testing data

INSERT INTO user_account(nickname) VALUES('testuser1');
INSERT INTO user_account(nickname) VALUES('testuser2');

SELECT *
FROM user_account;


INSERT INTO message(sender_uid, receiver_uid, content) VALUES(1, 2, 'This is a test message 1');
INSERT INTO message(sender_uid, receiver_uid, content) VALUES(1, 2, 'This is a test message 2');
INSERT INTO message(sender_uid, receiver_uid, content) VALUES(2, 1, 'This is a test message 3');

SELECT *
FROM message;
