INSERT INTO users(id, username)
VALUES (1, 'admin'),
       (2, 'user2'),
       (3, 'user3'),
       (4, 'user4');

INSERT INTO payments(id, "sender_id", "card_number", amount, comment)
VALUES (1, 1, 1234567812345678, 10, 50000),
       (2, 2, 1234567812345678, 20, 90000),
       (3, 3, 1234567812345678, 30, 90000),
       (4, 4, 1234567812345678, 40, 90000),
       (5, 3, 1234567812345678, 50, 90000);

