-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Хост: localhost:3306
-- Время создания: Мар 12 2017 г., 11:24
-- Версия сервера: 5.6.35
-- Версия PHP: 5.6.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `arma`
--

-- --------------------------------------------------------

--
-- Структура таблицы `email_detail`
--

CREATE TABLE `email_detail` (
  `id` varchar(50) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `HOST` varchar(255) NOT NULL,
  `TYPE` varchar(55) NOT NULL,
  `PORT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `email_detail`
--

INSERT INTO `email_detail` (`id`, `USERNAME`, `PASSWORD`, `HOST`, `TYPE`, `PORT`) VALUES
('002553b4-ec51-4a6d-8a91-d92cc2f8d0df', 'kukanabiev99@gmail.com', 'T1ahYFB+a3kUEMhCFsYj/w==', 'smtp.gmail.com', 'smtp', 465);

-- --------------------------------------------------------

--
-- Структура таблицы `email_message`
--

CREATE TABLE `email_message` (
  `ID` varchar(55) NOT NULL,
  `ID_TEMPLATE` varchar(55) NOT NULL,
  `U_NAME` varchar(55) NOT NULL,
  `MSG_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `STATE` int(11) NOT NULL,
  `DESCRIPTION` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `games`
--

CREATE TABLE `games` (
  `id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_game` date NOT NULL,
  `archive` int(11) NOT NULL DEFAULT '0',
  `date_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `games`
--

INSERT INTO `games` (`id`, `name`, `date_game`, `archive`, `date_add`) VALUES
('3a918f6e-bde3-4324-a6ce-716845af98eb', 'Test 2', '2017-03-12', 0, '2017-03-12'),
('55f1c845-19fd-43a0-9784-91a9c08108a7', 'Test 1', '2017-03-12', 1, '2017-03-12');

-- --------------------------------------------------------

--
-- Структура таблицы `groupmembers`
--

CREATE TABLE `groupmembers` (
  `G_NAME` varchar(255) NOT NULL,
  `G_MEMBER` varchar(255) NOT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `groupmembers`
--

INSERT INTO `groupmembers` (`G_NAME`, `G_MEMBER`, `ID`) VALUES
('kitchen_role', 'kitchen', 2),
('admin_role', 'weblogic456', 72),
('kitchen_role', 'weblogic123', 75),
('kitchen_role', 'asd', 76),
('kitchen_role', '123', 77),
('kitchen_role', 'weblogic', 78),
('admin_role', 'weblogic', 79),
('kitchen_role', '11111', 82),
('kitchen_role', '12121212', 83),
('kitchen_role', '123456', 85),
('kitchen_role', 'mysql', 86),
('admin_role', 'mysql', 87),
('admin_role', 'admin', 88);

-- --------------------------------------------------------

--
-- Структура таблицы `groups`
--

CREATE TABLE `groups` (
  `G_NAME` varchar(255) NOT NULL,
  `G_DESCRIPTION` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `groups`
--

INSERT INTO `groups` (`G_NAME`, `G_DESCRIPTION`) VALUES
('admin_role', 'root - право'),
('kitchen_role', 'кухня');

-- --------------------------------------------------------

--
-- Структура таблицы `msg_template`
--

CREATE TABLE `msg_template` (
  `ID` varchar(55) NOT NULL,
  `TEMPLATE` text NOT NULL,
  `title` text NOT NULL,
  `code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `slide`
--

CREATE TABLE `slide` (
  `id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `question` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `answer` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ord` int(11) NOT NULL,
  `id_game` varchar(40) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `U_NAME` varchar(255) NOT NULL,
  `U_PASSWORD` varchar(255) NOT NULL,
  `U_DESCRIPTION` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`U_NAME`, `U_PASSWORD`, `U_DESCRIPTION`) VALUES
('admin', '64c5b12b7729e5076eaa577436042951', 's'),
('kitchen', 'e10adc3949ba59abbe56e057f20f883e', ''),
('mysql', 'e10adc3949ba59abbe56e057f20f883e', ''),
('weblogic', 'e10adc3949ba59abbe56e057f20f883e', 'asd');

-- --------------------------------------------------------

--
-- Структура таблицы `user_detail`
--

CREATE TABLE `user_detail` (
  `U_NAME` varchar(255) NOT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `MIDDLENAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `LOCKED` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_detail`
--

INSERT INTO `user_detail` (`U_NAME`, `FIRSTNAME`, `LASTNAME`, `MIDDLENAME`, `EMAIL`, `LOCKED`) VALUES
('admin', 'asd', 'asd', 'asdasd', 'abzal_amanzhol_94@mail.ru', 0),
('kitchen', 'кухня', 'кухня', 'кухня', '', 0),
('mysql', 'Test', 'Test', 'Test', '', 0),
('weblogic', '????asdsa', '???dasd', '', '', 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `email_detail`
--
ALTER TABLE `email_detail`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `email_message`
--
ALTER TABLE `email_message`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `groupmembers`
--
ALTER TABLE `groupmembers`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`G_NAME`);

--
-- Индексы таблицы `msg_template`
--
ALTER TABLE `msg_template`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`U_NAME`);

--
-- Индексы таблицы `user_detail`
--
ALTER TABLE `user_detail`
  ADD PRIMARY KEY (`U_NAME`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `groupmembers`
--
ALTER TABLE `groupmembers`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `user_detail`
--
ALTER TABLE `user_detail`
  ADD CONSTRAINT `user_detail_ibfk_1` FOREIGN KEY (`U_NAME`) REFERENCES `users` (`U_NAME`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
