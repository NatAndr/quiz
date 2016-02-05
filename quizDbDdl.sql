-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.27-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.3.0.5016
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных quiz
CREATE DATABASE IF NOT EXISTS `quiz` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `quiz`;


-- Дамп структуры для таблица quiz.answer
CREATE TABLE IF NOT EXISTS `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) DEFAULT NULL,
  `answer` varchar(300) DEFAULT NULL,
  `is_correct` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_question_idx` (`question_id`),
  CONSTRAINT `FK_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица quiz.question
CREATE TABLE IF NOT EXISTS `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_id` int(11) DEFAULT NULL,
  `question` varchar(500) DEFAULT NULL,
  `type` int(11) DEFAULT '1',
  `weight` int(11) DEFAULT '1',
  `image` longblob,
  PRIMARY KEY (`id`),
  KEY `FK_quizID_idx` (`quiz_id`),
  KEY `FK_type_idx` (`type`),
  CONSTRAINT `FK_quiz` FOREIGN KEY (`quiz_id`) REFERENCES `quiz_header` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица quiz.quiz_generated_questions
CREATE TABLE IF NOT EXISTS `quiz_generated_questions` (
  `quiz_start_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  KEY `FK_quiz_start` (`quiz_start_id`),
  KEY `FK_question_id` (`question_id`),
  CONSTRAINT `FK_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK_quiz_start` FOREIGN KEY (`quiz_start_id`) REFERENCES `quiz_start` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица quiz.quiz_header
CREATE TABLE IF NOT EXISTS `quiz_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица quiz.quiz_start
CREATE TABLE IF NOT EXISTS `quiz_start` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `quiz_id` int(11) NOT NULL,
  `quiz_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `quizID` (`quiz_id`),
  CONSTRAINT `quiz_start_ibfk_2` FOREIGN KEY (`quiz_id`) REFERENCES `quiz_header` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица quiz.result
CREATE TABLE IF NOT EXISTS `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `answer_id` int(11) DEFAULT NULL,
  `input_answer` varchar(100) DEFAULT NULL,
  `quiz_start_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_answer_idx` (`answer_id`),
  KEY `FK_student_idx` (`student_id`),
  KEY `quiz_start_id` (`quiz_start_id`),
  CONSTRAINT `FK_answer` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`quiz_start_id`) REFERENCES `quiz_start` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица quiz.student
CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_groupID_idx` (`group_id`),
  CONSTRAINT `FK_groupID` FOREIGN KEY (`group_id`) REFERENCES `study_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица quiz.study_group
CREATE TABLE IF NOT EXISTS `study_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idCroup_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

-- Экспортируемые данные не выделены.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
