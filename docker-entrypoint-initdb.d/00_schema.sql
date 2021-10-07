CREATE SEQUENCE user_id_seq;

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY DEFAULT nextval('user_id_seq'::regclass),
    username TEXT NOT NULL UNIQUE
);

CREATE SEQUENCE card_id_seq;
CREATE TABLE payments
(
    id         BIGSERIAL PRIMARY KEY DEFAULT nextval('card_id_seq'::regclass),
    "senderId" BIGINT NOT NULL REFERENCES users,
    amount     BIGINT NOT NULL,
    comment    TEXT   NOT NULL       DEFAULT 0
);

