CREATE SEQUENCE auto_id_answer;
CREATE SEQUENCE auto_id_question;
CREATE SEQUENCE auto_id_question_type;
CREATE SEQUENCE auto_id_quiz_header;
CREATE SEQUENCE auto_id_quiz_start;
CREATE SEQUENCE auto_id_result;
CREATE SEQUENCE auto_id_student;
CREATE SEQUENCE auto_id_study_group;

ALTER SEQUENCE auto_id_answer RESTART WITH 105;
ALTER SEQUENCE auto_id_question RESTART WITH 105;
ALTER SEQUENCE auto_id_question_type RESTART WITH 105;
ALTER SEQUENCE auto_id_quiz_header RESTART WITH 105;
ALTER SEQUENCE auto_id_quiz_start RESTART WITH 105;
ALTER SEQUENCE auto_id_result RESTART WITH 105;
ALTER SEQUENCE auto_id_student RESTART WITH 105;
ALTER SEQUENCE auto_id_study_group RESTART WITH 105;

DROP TABLE IF EXISTS answer;
CREATE TABLE IF NOT EXISTS answer (
  id          INTEGER      NOT NULL DEFAULT nextval('auto_id_answer'),
  question_id INTEGER      DEFAULT NULL,
  answer      VARCHAR(300) DEFAULT NULL,
  is_correct  INTEGER       DEFAULT '0',
  PRIMARY KEY (id)
);

-- Дамп структуры для таблица quiz.question
DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question (
  id       INTEGER      NOT NULL DEFAULT nextval('auto_id_question'),
  quiz_id  INTEGER      DEFAULT NULL,
  question VARCHAR(500) DEFAULT NULL,
  type     INTEGER      DEFAULT '1',
  weight   INTEGER      DEFAULT '1',
  image    bytea,
  PRIMARY KEY (id)
);

-- Дамп структуры для таблица quiz.question_type
DROP TABLE IF EXISTS question_type;
CREATE TABLE IF NOT EXISTS question_type (
  id   INTEGER     NOT NULL DEFAULT nextval('auto_id_question_type'),
  type VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Дамп структуры для таблица quiz.quiz_header
DROP TABLE IF EXISTS quiz_header;
CREATE TABLE IF NOT EXISTS quiz_header (
  id        INTEGER      NOT NULL DEFAULT nextval('auto_id_quiz_header'),
  quiz_name VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Дамп структуры для таблица quiz.quiz_start
DROP TABLE IF EXISTS quiz_start;
CREATE TABLE IF NOT EXISTS quiz_start (
  id        INTEGER  NOT NULL DEFAULT nextval('auto_id_quiz_start'),
  quiz_id   INTEGER  DEFAULT NULL,
  quiz_date timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

-- Дамп структуры для таблица quiz.result
DROP TABLE IF EXISTS result;
CREATE TABLE IF NOT EXISTS result (
  id            INTEGER      NOT NULL DEFAULT nextval('auto_id_result'),
  student_id    INTEGER      DEFAULT NULL,
  answer_id     INTEGER      DEFAULT NULL,
  input_answer  VARCHAR(100) DEFAULT NULL,
  quiz_start_id INTEGER      DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Дамп структуры для таблица quiz.student
DROP TABLE IF EXISTS student;
CREATE TABLE IF NOT EXISTS student (
  id         INTEGER     NOT NULL DEFAULT nextval('auto_id_student'),
  group_id   INTEGER     DEFAULT NULL,
  first_name VARCHAR(45) DEFAULT NULL,
  last_name  VARCHAR(45) DEFAULT NULL,
  login varchar(50) DEFAULT NULL,
  password varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Дамп структуры для таблица quiz.study_group
DROP TABLE IF EXISTS study_group;
CREATE TABLE IF NOT EXISTS study_group (
  id         INTEGER     NOT NULL DEFAULT nextval('auto_id_study_group'),
  group_name VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Дамп структуры для таблица quiz.quiz_generated_questions
DROP TABLE IF EXISTS quiz_generated_questions;
CREATE TABLE IF NOT EXISTS quiz_generated_questions (
  quiz_start_id INTEGER NULL DEFAULT NULL,
  question_id   INTEGER NULL DEFAULT NULL
);


