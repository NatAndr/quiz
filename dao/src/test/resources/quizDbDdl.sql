-- Дамп структуры базы данных quiz
-- DROP DATABASE IF EXISTS `quiz`;
-- CREATE DATABASE IF NOT EXISTS `quiz` /*!40100 DEFAULT CHARACTER SET utf8 */;
-- USE `quiz`;

-- Дамп структуры для таблица quiz.answer
DROP TABLE IF EXISTS `answer`;
CREATE TABLE IF NOT EXISTS `answer` (
  `id`          INT(11)      AUTO_INCREMENT,
  `question_id` INT(11)      DEFAULT NULL,
  `answer`      VARCHAR(300) DEFAULT NULL,
  `is_correct`  INT(4)       DEFAULT '0',
  PRIMARY KEY (`id`)
);

-- Дамп структуры для таблица quiz.question
DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `id`       INT(11)      AUTO_INCREMENT,
  `quiz_id`  INT(11)      DEFAULT NULL,
  `question` VARCHAR(500) DEFAULT NULL,
  `type`     INT(11)      DEFAULT '1',
  `weight`   INT(11)      DEFAULT '1',
  `image`    BLOB,
  PRIMARY KEY (`id`)
);

-- Дамп структуры для таблица quiz.question_type
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE IF NOT EXISTS `question_type` (
  `id`   INT(11)     AUTO_INCREMENT,
  `type` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Дамп структуры для таблица quiz.quiz_header
DROP TABLE IF EXISTS `quiz_header`;
CREATE TABLE IF NOT EXISTS `quiz_header` (
  `id`        INT(11)      AUTO_INCREMENT,
  `quiz_name` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Дамп структуры для таблица quiz.quiz_start
DROP TABLE IF EXISTS `quiz_start`;
CREATE TABLE IF NOT EXISTS `quiz_start` (
  `id`        INT(10)  AUTO_INCREMENT,
  `quiz_id`   INT(11)  DEFAULT NULL,
  `quiz_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- Дамп структуры для таблица quiz.result
DROP TABLE IF EXISTS `result`;
CREATE TABLE IF NOT EXISTS `result` (
  `id`            INT(11)      AUTO_INCREMENT,
  `student_id`    INT(11)      DEFAULT NULL,
  `answer_id`     INT(11)      DEFAULT NULL,
  `input_answer`  VARCHAR(100) DEFAULT NULL,
  `quiz_start_id` INT(10)      DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Дамп структуры для таблица quiz.student
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id`         INT(11)     AUTO_INCREMENT,
  `group_id`   INT(11)     DEFAULT NULL,
  `first_name` VARCHAR(45) DEFAULT NULL,
  `last_name`  VARCHAR(45) DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Дамп структуры для таблица quiz.study_group
DROP TABLE IF EXISTS `study_group`;
CREATE TABLE IF NOT EXISTS `study_group` (
  `id`         INT(11)     AUTO_INCREMENT,
  `group_name` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Дамп структуры для таблица quiz.quiz_generated_questions
DROP TABLE IF EXISTS `quiz_generated_questions`;
CREATE TABLE IF NOT EXISTS `quiz_generated_questions` (
  `quiz_start_id` INT(11) NULL DEFAULT NULL,
  `question_id`   INT(11) NULL DEFAULT NULL
);


