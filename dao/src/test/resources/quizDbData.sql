-- Дамп данных таблицы quiz.answer: ~49 rows (приблизительно)
DELETE FROM answer;
/*!40000 ALTER TABLE answer DISABLE KEYS */;
INSERT INTO answer (id, question_id, answer, is_correct) VALUES
  (1, 1, '1. int -> 0', 1),
  (2, 1, '2. String -> "null"', 0),
  (3, 1, '3. Dog -> null', 1),
  (5, 2, 'A.	class, if, void, long, Int, continue', 0),
  (6, 2, 'B.	goto, instanceof, native, finally, default, throws', 1),
  (7, 2, 'C.	try, virtual, throw, final, volatile, transient', 0),
  (8, 2, 'D.	strictfp, constant, super, implements, do', 0),
  (9, 2, 'E.	byte, break, assert, switch, include', 0),
  (10, 3, 'A.	int [] myList = {"1", "2", "3"};', 0),
  (11, 3, 'B.	int [] myList = (5, 8, 2);', 0),
  (12, 3, 'C.	int myList [] [] = {4,9,7,0};', 0),
  (13, 3, 'D.	int myList [] = {4, 3, 7};', 1),
  (14, 4, 'A.	method', 0),
  (15, 4, 'B.	native', 1),
  (16, 4, 'C.	subclasses', 0),
  (17, 4, 'D.	reference', 0),
  (18, 4, 'E.	array', 0),
  (19, 5, 'A.	interface', 1),
  (20, 5, 'B.	string', 0),
  (21, 5, 'C.	Float', 0),
  (22, 5, 'D.	unsigned', 0),
  (23, 1, '5. float -> 0.0f', 1),
  (24, 1, '6. boolean -> true', 0),
  (25, 6, '1. int [] myScores [];', 1),
  (26, 6, '2. char [] myChars;', 1),
  (27, 6, '3. int [6] myScores;', 0),
  (28, 6, '4. Dog myDogs [];', 1),
  (29, 6, '5. Dog myDogs [7];', 0),
  (30, 7, 'final int k = 4;', 1),
  (31, 7, 'public int k = 4;', 1),
  (32, 7, 'static int k = 4;', 1),
  (33, 7, 'abstract int k = 4;', 0),
  (34, 7, 'volatile int k = 4;', 0),
  (35, 7, 'protected int k = 4;', 0),
  (36, 8, 'Array a = new Array(5);', 0),
  (37, 8, 'int [] a = {23,22,21,20,19};', 1),
  (38, 8, 'int a [] = new int[5];', 0),
  (39, 8, 'int [5] array;', 0),
  (40, 9, 'char c1 = 064770;', 1),
  (42, 9, 'char c3 = 0xbeef;', 1),
  (43, 9, 'char c4 = \\u0022;', 0),
  (46, 10, 'public double methoda();', 1),
  (47, 10, 'public final double methoda();', 0),
  (48, 10, 'static void methoda(double d1);', 0),
  (49, 10, 'protected void methoda(double d1);', 0),
  (50, 14, 'James Gosling', 1);
/*!40000 ALTER TABLE answer ENABLE KEYS */;

-- Дамп данных таблицы quiz.question: ~12 rows (приблизительно)
DELETE FROM question;
/*!40000 ALTER TABLE question DISABLE KEYS */;
INSERT INTO question (id, quiz_id, question, type, weight) VALUES
  (1, 1, 'Which four options describe the correct default values for array elements of the types indicated?', 1, 1),
  (2, 1, 'Which one of these lists contains only Java programming language keywords?', 0, 1),
  (3, 1, 'Which will legally declare, construct, and initialize an array?', 0, 1),
  (4, 1, 'Which is a reserved word in the Java programming language?', 0, 1),
  (5, 1, 'Which is a valid keyword in java?', 0, 1),
  (6, 1, 'Which three are legal array declarations?', 1, 1),
  (7, 1, 'public interface Foo \n{ \n    int k = 4; /* Line 3 */\n}\nWhich three piece of codes are equivalent to line 3?', 1, 1),
  (8, 1, 'Which one of the following will declare an array and initialize it with five numbers?', 0, 1),
  (9, 1, 'Which three are valid declarations of a char?', 1, 1),
  (10, 1, 'Which is the valid declarations within an interface definition?', 0, 1),
  (11, 2, 'Test question', 0, 1),
  (13, 2, 'Новый вопрос', 1, 1),
  (14, 1, 'Who initiated the Java language project?', 2, 1);
/*!40000 ALTER TABLE question ENABLE KEYS */;

-- Дамп данных таблицы quiz.question_type: ~3 rows (приблизительно)
DELETE FROM question_type;
/*!40000 ALTER TABLE question_type DISABLE KEYS */;
INSERT INTO question_type (id, type) VALUES
  (1, 'SINGLE'),
  (2, 'MULTI'),
  (3, 'INPUT');
/*!40000 ALTER TABLE question_type ENABLE KEYS */;

-- Дамп данных таблицы quiz.quiz_header: ~2 rows (приблизительно)
DELETE FROM quiz_header;
/*!40000 ALTER TABLE quiz_header DISABLE KEYS */;
INSERT INTO quiz_header (id, quiz_name) VALUES
  (1, 'Java Programming. Language Fundamentals'),
  (2, 'Vegetables');
/*!40000 ALTER TABLE quiz_header ENABLE KEYS */;

-- Дамп данных таблицы quiz.quiz_start: ~0 rows (приблизительно)
DELETE FROM quiz_start;
/*!40000 ALTER TABLE quiz_start DISABLE KEYS */;
INSERT INTO quiz_start (id, quiz_id, quiz_date) VALUES
  (2, 1, '2015-11-09 22:55:42'),
  (3, 1, '2015-11-10 11:38:17');
/*!40000 ALTER TABLE quiz_start ENABLE KEYS */;

-- Дамп данных таблицы quiz.result: ~0 rows (приблизительно)
DELETE FROM result;
/*!40000 ALTER TABLE result DISABLE KEYS */;
INSERT INTO result (id, student_id, answer_id, input_answer, quiz_start_id) VALUES
  (4, 2, 2, '', 2),
  (5, 1, 1, NULL, 2),
  (6, 1, 5, NULL, 2),
  (7, 1, 15, NULL, 2),
  (8, 1, 50, 'James Gosling', 2),
  (9, 1, 50, 'Mike Sheridan', 3),
  (10, 1, 19, NULL, 3),
  (11, 1, 37, NULL, 3);
/*!40000 ALTER TABLE result ENABLE KEYS */;

-- Дамп данных таблицы quiz.student: ~5 rows (приблизительно)
DELETE FROM student;
/*!40000 ALTER TABLE student DISABLE KEYS */;
INSERT INTO student (id, group_id, first_name, last_name, login, password) VALUES
  (1, 1, 'Ivan', 'Ivanov', 'l1', '1'),
  (2, 1, 'Petr', 'Petrov', 'l2', '1'),
  (3, 2, 'Oleg', 'Sokolov', 'l3', '1'),
  (4, 2, 'Artem', 'Artemov', 'l4', '1'),
  (5, 1, 'Sergey', 'Sergeev', 'l5', '1');
/*!40000 ALTER TABLE student ENABLE KEYS */;

-- Дамп данных таблицы quiz.study_group: ~5 rows (приблизительно)
DELETE FROM study_group;
/*!40000 ALTER TABLE study_group DISABLE KEYS */;
INSERT INTO study_group (id, group_name) VALUES
  (1, 'java-algo01'),
  (2, 'java-algo02'),
  (3, 'java-algo03'),
  (4, 'Группа 2'),
  (5, 'Группа 3'),
  (8, 'New group2');

-- Дамп данных таблицы quiz.quiz_generated_questions: ~6 rows (приблизительно)
DELETE FROM quiz_generated_questions;
/*!40000 ALTER TABLE quiz_generated_questions DISABLE KEYS */;
INSERT INTO quiz_generated_questions (quiz_start_id, question_id) VALUES
  (2, 1),
  (2, 4),
  (2, 6),
  (5, 1),
  (5, 4),
  (5, 8);


