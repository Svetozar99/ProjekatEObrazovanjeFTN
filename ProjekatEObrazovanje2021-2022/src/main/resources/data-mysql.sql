INSERT INTO user_role(id, name, code) VALUES(1, 'student', 'st');

INSERT INTO USERS(id, first_name, last_name, username, password, user_role_id)
VALUES(1, 'Svetozar', 'Brboric', 'brboric99', '12345', 1);

INSERT INTO STUDENTS(id, card_number, user_id) VALUES(1, 'SF-21-2018', 1);

INSERT INTO ACCOUNTS(id, amount, student_id) VALUES(1, 12, 1);