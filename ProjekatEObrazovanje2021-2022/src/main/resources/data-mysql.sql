INSERT INTO user_role(id, name, code) VALUES(1, 'student', 'st');
INSERT INTO user_role(id, name, code) VALUES(2, 'administrator', 'admin');
INSERT INTO user_role(id, name, code) VALUES(3, 'teacher', 'teach');

INSERT INTO USERS(id, first_name, last_name, username, password, user_role_id)
VALUES(1, 'Svetozar', 'Brboric', 'brboric99', '12345', 1);
INSERT INTO USERS(id, first_name, last_name, username, password, user_role_id)
VALUES(2, 'Milos', 'Milosevic', 'milosevoic123', '12345', 2);
INSERT INTO USERS(id, first_name, last_name, username, password, user_role_id)
VALUES(3, 'Goran', 'Savic', 'savic123', '12345', 3);

INSERT INTO STUDENTS(id, card_number, user_id) VALUES(1, 'SF-21-2018', 1);

INSERT INTO administrators(id, user_id) VALUES(1, 2);

INSERT INTO teachers(id, user_id) VALUES(1, 3);

INSERT INTO ACCOUNTS(id, amount, student_id) VALUES(1, 12, 1);

INSERT INTO course_specification(id, title, ects, code) VALUE(1, 'eObrazovanje', 7, 'eObr');

INSERT INTO course_instance(id, start_date, end_date, course_specification_id) VALUES(1, '2020-02-10', '2021-07-07', 1);

INSERT INTO teaching_type(id, name, code) VALUES(1, 'exercise', 'ex');

INSERT INTO enrollments(id, student_id, course_instance_id) VALUES(1, 1, 1);

INSERT INTO teachings(id, teaching_type_id, teacher_id, course_instance_id) VALUES(1, 1, 1, 1);