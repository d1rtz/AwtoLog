-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 10-11-2020 a las 15:29:21
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `awto_log`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `awlog_hashtag`
--

CREATE TABLE IF NOT EXISTS `awlog_hashtag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Volcado de datos para la tabla `awlog_hashtag`
--

INSERT INTO `awlog_hashtag` (`id`, `description`) VALUES
(5, '#apiadmin'),
(6, '#users'),
(7, '#commands'),
(8, '#nullpointer'),
(9, 'nullpointer'),
(10, 'apiadmin'),
(11, 'users'),
(12, 'commands'),
(13, 'newVdasalue');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `awlog_logger`
--

CREATE TABLE IF NOT EXISTS `awlog_logger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `host` varchar(100) NOT NULL,
  `origin` varchar(100) NOT NULL,
  `details` text NOT NULL,
  `stacktrace` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Volcado de datos para la tabla `awlog_logger`
--

INSERT INTO `awlog_logger` (`id`, `creation_date`, `host`, `origin`, `details`, `stacktrace`) VALUES
(4, '2020-11-10 05:10:35', 'http://host.com', 'ms-api-admin', 'user_id 132453: no puede enviar comandos', '...stacktrace...'),
(5, '2020-11-10 05:25:49', 'http://host.com', 'ms-api-admin', 'user_id 132453: no puede enviar comandos', '...stacktrace...'),
(6, '2020-11-10 16:58:42', 'http://host.com', 'ms-api-admin', 'user_id 132453: no puede enviar comandos', '...stacktrace...'),
(7, '2020-11-10 16:58:57', 'http://host.com', 'ms-api-admin', 'user_id 132453: no puede enviar comandos', '...stacktrace...'),
(8, '2020-11-10 16:59:00', 'http://host.com', 'ms-api-admin', 'user_id 132453: no puede enviar comandos', '...stacktrace...'),
(9, '2020-11-10 16:59:08', 'http://host.com', 'ms-api-admin', 'user_id 132453: no puede enviar comandos', '...stacktrace...'),
(10, '2020-11-10 18:26:16', 'http://host.com', 'ms-api-admin', 'user_id 132453: no puede enviar comandos', '...stacktrace...');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `awlog_logger_hashtag`
--

CREATE TABLE IF NOT EXISTS `awlog_logger_hashtag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_id` int(11) NOT NULL,
  `hashtag_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `awlog_logger_hashtag_fk_1` (`hashtag_id`),
  KEY `awlog_logger_hashtag_fk` (`log_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Volcado de datos para la tabla `awlog_logger_hashtag`
--

INSERT INTO `awlog_logger_hashtag` (`id`, `log_id`, `hashtag_id`) VALUES
(5, 4, 5),
(6, 4, 8),
(7, 4, 6),
(8, 4, 7),
(9, 5, 6),
(10, 5, 7),
(11, 5, 8),
(12, 5, 5),
(13, 6, 12),
(14, 6, 10),
(15, 6, 11),
(16, 6, 9),
(17, 7, 11),
(18, 8, 11),
(19, 9, 10),
(20, 9, 11),
(21, 10, 11),
(22, 10, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `awlog_logger_hashtag`
--
ALTER TABLE `awlog_logger_hashtag`
  ADD CONSTRAINT `awlog_logger_hashtag_fk` FOREIGN KEY (`log_id`) REFERENCES `awlog_logger` (`id`),
  ADD CONSTRAINT `awlog_logger_hashtag_fk_1` FOREIGN KEY (`hashtag_id`) REFERENCES `awlog_hashtag` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
