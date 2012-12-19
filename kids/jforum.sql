-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generato il: Dic 11, 2012 alle 08:00
-- Versione del server: 5.5.27
-- Versione PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jforum`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_api`
--
-- Creazione: Nov 28, 2012 alle 09:22
--

CREATE TABLE IF NOT EXISTS `jforum_api` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT,
  `api_key` varchar(32) NOT NULL,
  `api_validity` datetime NOT NULL,
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_attach`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_attach` (
  `attach_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `privmsgs_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`attach_id`),
  KEY `idx_att_post` (`post_id`),
  KEY `idx_att_priv` (`privmsgs_id`),
  KEY `idx_att_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_attach_desc`
--
-- Creazione: Nov 28, 2012 alle 09:22
--

CREATE TABLE IF NOT EXISTS `jforum_attach_desc` (
  `attach_desc_id` int(11) NOT NULL AUTO_INCREMENT,
  `attach_id` int(11) NOT NULL,
  `physical_filename` varchar(255) NOT NULL,
  `real_filename` varchar(255) NOT NULL,
  `download_count` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `mimetype` varchar(50) DEFAULT NULL,
  `filesize` int(11) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `thumb` tinyint(1) DEFAULT '0',
  `extension_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`attach_desc_id`),
  KEY `idx_att_d_att` (`attach_id`),
  KEY `idx_att_d_ext` (`extension_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_attach_quota`
--
-- Creazione: Nov 28, 2012 alle 09:22
--

CREATE TABLE IF NOT EXISTS `jforum_attach_quota` (
  `attach_quota_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `quota_limit_id` int(11) NOT NULL,
  PRIMARY KEY (`attach_quota_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_banlist`
--
-- Creazione: Nov 28, 2012 alle 09:27
--

CREATE TABLE IF NOT EXISTS `jforum_banlist` (
  `banlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `banlist_ip` varchar(15) DEFAULT NULL,
  `banlist_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`banlist_id`),
  KEY `idx_user` (`user_id`),
  KEY `banlist_ip` (`banlist_ip`),
  KEY `banlist_email` (`banlist_email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_banner`
--
-- Creazione: Nov 28, 2012 alle 09:22
--

CREATE TABLE IF NOT EXISTS `jforum_banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(90) DEFAULT NULL,
  `banner_placement` int(11) NOT NULL DEFAULT '0',
  `banner_description` varchar(250) DEFAULT NULL,
  `banner_clicks` int(11) NOT NULL DEFAULT '0',
  `banner_views` int(11) NOT NULL DEFAULT '0',
  `banner_url` varchar(250) DEFAULT NULL,
  `banner_weight` tinyint(1) NOT NULL DEFAULT '50',
  `banner_active` tinyint(1) NOT NULL DEFAULT '0',
  `banner_comment` varchar(250) DEFAULT NULL,
  `banner_type` int(11) NOT NULL DEFAULT '0',
  `banner_width` int(11) NOT NULL DEFAULT '0',
  `banner_height` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`banner_id`),
  KEY `banner_id` (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_bookmarks`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_bookmarks` (
  `bookmark_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `relation_id` int(11) NOT NULL,
  `relation_type` int(11) NOT NULL,
  `public_visible` int(11) DEFAULT '1',
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookmark_id`),
  KEY `book_idx_relation` (`relation_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_categories`
--
-- Creazione: Nov 28, 2012 alle 09:27
--

CREATE TABLE IF NOT EXISTS `jforum_categories` (
  `categories_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `display_order` int(11) NOT NULL DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `jforum_categories`
--

INSERT INTO `jforum_categories` (`categories_id`, `title`, `display_order`, `moderated`) VALUES
(1, 'Prima categoria', 1, 0),
(2, 'Seconda categoria', 2, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_config`
--
-- Creazione: Nov 28, 2012 alle 09:26
--

CREATE TABLE IF NOT EXISTS `jforum_config` (
  `config_name` varchar(255) NOT NULL DEFAULT '',
  `config_value` varchar(255) NOT NULL DEFAULT '',
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `jforum_config`
--

INSERT INTO `jforum_config` (`config_name`, `config_value`, `config_id`) VALUES
('most.users.ever.online', '4', 1),
('most.users.ever.online.date', '1354521315984', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_extensions`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_extensions` (
  `extension_id` int(11) NOT NULL AUTO_INCREMENT,
  `extension_group_id` int(11) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `upload_icon` varchar(100) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `allow` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`extension_id`),
  KEY `extension_group_id` (`extension_group_id`),
  KEY `extension` (`extension`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_extension_groups`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_extension_groups` (
  `extension_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `allow` tinyint(1) DEFAULT '1',
  `upload_icon` varchar(100) DEFAULT NULL,
  `download_mode` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`extension_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_forums`
--
-- Creazione: Nov 28, 2012 alle 09:26
--

CREATE TABLE IF NOT EXISTS `jforum_forums` (
  `forum_id` int(11) NOT NULL AUTO_INCREMENT,
  `categories_id` int(11) NOT NULL DEFAULT '1',
  `forum_name` varchar(150) NOT NULL DEFAULT '',
  `forum_desc` varchar(255) DEFAULT NULL,
  `forum_order` int(11) DEFAULT '1',
  `forum_topics` int(11) NOT NULL DEFAULT '0',
  `forum_last_post_id` int(11) NOT NULL DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`forum_id`),
  KEY `categories_id` (`categories_id`),
  KEY `idx_forums_cats` (`categories_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `jforum_forums`
--

INSERT INTO `jforum_forums` (`forum_id`, `categories_id`, `forum_name`, `forum_desc`, `forum_order`, `forum_topics`, `forum_last_post_id`, `moderated`) VALUES
(1, 1, 'Sezione Giochi', 'This is a test forum', 1, 1, 9, 0),
(2, 1, 'Sezione Consigli', '', 2, 0, 0, 0),
(3, 2, 'Sezione cavie', 'Qui si testano le cavie', 3, 3, 3, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_forums_watch`
--
-- Creazione: Nov 28, 2012 alle 09:26
--

CREATE TABLE IF NOT EXISTS `jforum_forums_watch` (
  `forum_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `idx_fw_forum` (`forum_id`),
  KEY `idx_fw_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_groups`
--
-- Creazione: Nov 28, 2012 alle 09:26
--

CREATE TABLE IF NOT EXISTS `jforum_groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(40) NOT NULL DEFAULT '',
  `group_description` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dump dei dati per la tabella `jforum_groups`
--

INSERT INTO `jforum_groups` (`group_id`, `group_name`, `group_description`, `parent_id`) VALUES
(1, 'General', 'General Users', 0),
(2, 'Administration', 'Admin Users', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_karma`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_karma` (
  `karma_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `post_user_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `rate_date` datetime DEFAULT NULL,
  PRIMARY KEY (`karma_id`),
  KEY `post_id` (`post_id`),
  KEY `topic_id` (`topic_id`),
  KEY `post_user_id` (`post_user_id`),
  KEY `from_user_id` (`from_user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dump dei dati per la tabella `jforum_karma`
--

INSERT INTO `jforum_karma` (`karma_id`, `post_id`, `topic_id`, `post_user_id`, `from_user_id`, `points`, `rate_date`) VALUES
(1, 1, 1, 2, 3, 5, '2012-12-03 08:47:18');

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_mail_integration`
--
-- Creazione: Nov 28, 2012 alle 09:22
--

CREATE TABLE IF NOT EXISTS `jforum_mail_integration` (
  `forum_id` int(11) NOT NULL,
  `forum_email` varchar(100) NOT NULL,
  `pop_username` varchar(100) NOT NULL,
  `pop_password` varchar(100) NOT NULL,
  `pop_host` varchar(100) NOT NULL,
  `pop_port` int(11) DEFAULT '110',
  `pop_ssl` tinyint(4) DEFAULT '0',
  KEY `forum_id` (`forum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_moderation_log`
--
-- Creazione: Nov 28, 2012 alle 09:22
--

CREATE TABLE IF NOT EXISTS `jforum_moderation_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `log_description` text NOT NULL,
  `log_original_message` text,
  `log_date` datetime NOT NULL,
  `log_type` tinyint(4) DEFAULT '0',
  `post_id` int(11) DEFAULT '0',
  `topic_id` int(11) DEFAULT '0',
  `post_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`log_id`),
  KEY `user_id` (`user_id`),
  KEY `post_user_id` (`post_user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dump dei dati per la tabella `jforum_moderation_log`
--

INSERT INTO `jforum_moderation_log` (`log_id`, `user_id`, `log_description`, `log_original_message`, `log_date`, `log_type`, `post_id`, `topic_id`, `post_user_id`) VALUES
(1, 2, 'x', 'rthfh', '2012-11-30 22:04:18', 1, 3, 0, 1),
(2, 2, 'xxx', NULL, '2012-12-08 10:14:23', 0, 0, 1, 0),
(3, 2, 'xx', NULL, '2012-12-08 10:16:51', 0, 0, 1, 0),
(4, 2, 'xx', NULL, '2012-12-08 10:17:01', 0, 0, 1, 0),
(5, 2, 'x', 'c', '2012-12-10 15:21:49', 1, 4, 0, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_posts`
--
-- Creazione: Nov 28, 2012 alle 09:26
--

CREATE TABLE IF NOT EXISTS `jforum_posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `forum_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `post_time` datetime DEFAULT NULL,
  `poster_ip` varchar(15) DEFAULT NULL,
  `enable_bbcode` tinyint(1) NOT NULL DEFAULT '1',
  `enable_html` tinyint(1) NOT NULL DEFAULT '1',
  `enable_smilies` tinyint(1) NOT NULL DEFAULT '1',
  `enable_sig` tinyint(1) NOT NULL DEFAULT '1',
  `post_edit_time` datetime DEFAULT NULL,
  `post_edit_count` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(1) DEFAULT '1',
  `attach` tinyint(1) DEFAULT '0',
  `need_moderate` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_id` (`topic_id`),
  KEY `forum_id` (`forum_id`),
  KEY `post_time` (`post_time`),
  KEY `need_moderate` (`need_moderate`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dump dei dati per la tabella `jforum_posts`
--

INSERT INTO `jforum_posts` (`post_id`, `topic_id`, `forum_id`, `user_id`, `post_time`, `poster_ip`, `enable_bbcode`, `enable_html`, `enable_smilies`, `enable_sig`, `post_edit_time`, `post_edit_count`, `status`, `attach`, `need_moderate`) VALUES
(1, 1, 3, 2, '2005-01-04 16:59:54', '127.0.0.1', 1, 0, 1, 1, NULL, 0, 1, 0, 0),
(2, 2, 1, 2, '2012-11-28 10:29:12', '127.0.0.1', 1, 1, 1, 1, '2012-11-28 10:29:12', 0, 1, 0, 0),
(3, 1, 3, 3, '2012-12-03 08:46:38', '127.0.0.1', 1, 0, 1, 1, '2012-12-03 08:46:38', 0, 1, 0, 0),
(5, 3, 1, 2, '2012-12-06 08:20:15', '127.0.0.1', 1, 0, 1, 1, '2012-12-06 08:20:15', 0, 1, 0, 0),
(6, 3, 1, 2, '2012-12-07 08:56:54', '127.0.0.1', 1, 0, 1, 1, '2012-12-07 08:56:54', 0, 1, 0, 0),
(7, 3, 1, 3, '2012-12-08 12:34:20', '127.0.0.1', 1, 0, 1, 1, '2012-12-08 12:34:20', 0, 1, 0, 0),
(8, 3, 1, 3, '2012-12-08 12:59:16', '127.0.0.1', 1, 0, 1, 1, '2012-12-08 12:59:16', 0, 1, 0, 0),
(9, 4, 1, 2, '2012-12-09 17:34:23', '127.0.0.1', 1, 0, 1, 1, '2012-12-09 17:34:23', 0, 1, 0, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_posts_text`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_posts_text` (
  `post_id` int(11) NOT NULL,
  `post_text` text,
  `post_subject` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `jforum_posts_text`
--

INSERT INTO `jforum_posts_text` (`post_id`, `post_text`, `post_subject`) VALUES
(1, '[b][color=blue][size=18]Congratulations :!: [/size][/color][/b]\nYou have completed the installation, and JForum is up and running. \n\nTo start administering the board, login as [i]Admin / <the password you supplied in the installer>[/i] and access the [b][url=/admBase/login.page]Admin Control Panel[/url][/b] using the link that shows up in the bottom of the page. There you will be able to create Categories, Forums and much more  :D  \n\nFor more information and support, please refer to the following pages:\n\n:arrow: Community forum: http://www.jforum.net/community.jsp\n:arrow: Documentation: http://www.jforum.net/doc\n\nThank you for choosing JForum.\n\n[url=http://www.jforum.net/doc/Team]The JForum Team[/url]\n\n', 'Welcome to JForum'),
(2, '[color=#3AA315][size=18][b]Support JForum - Help the project[/b][/size][/color]<hr>This project is Open Source, and maintained by at least one full time Senior Developer, [i]which costs US$ 3,000.00 / month[/i]. If it helped you, please consider helping this project - especially with some [b][url=http://www.jforum.net/contribute.jsp]donation[/url][/b].\n\n[color=#137C9F][size=14][b]Why supporting this project is a good thing[/b][/size][/color]<hr>The JForum Project started four years ago as a completely free and Open Source program, initially entirely developed on my (Rafael Steil) free time. Today, with the help of some very valuable people, I can spend more time on JForum, to improve it and implement new features (lots of things, requested either on the [url=http://www.jforum.net/forums/list.page]forums[/url] or registered in the [url=http://www.jforum.net/jira]bug tracker[/url]).\nThat''s why I''m asking you to financially support this work. I love Open Source. I love to use good products without having to pay for it too. But when I see some program that is valuable to my work, that helps me making money, I think it''s a good idea to support this project.\n\n[b]Some reasons to support open projects[/b]:<ul><li>Because Open Source is cool? Yes<li>To thank for a great tool? Yes<li>To help the project evolve because this will help my work and my earnings? Yes</ul>Also, as the project grows more and more, it would be great to, sometimes, reward some of the great people who help JForum.\n\nSo, that''s what I''m asking you: if JForum helps your work, saves your time (time is money, remember?) and increase your earnings, support this project. The simpler way is to make [url=http://www.jforum.net/contribute.jsp]any donation[/url] via PayPal.\n\nJForum has grown a lot every day, since four years ago, which is a great thing, and initially it wasn''t my intention to fully work on this tool. Lately, I''m spending a lot of time on it, specially to make JForum 3 a reality, to help users, to improve the program, to research about better solutions. So, your support is very welcome!\n\nThanks!\n\n:arrow: [size=16][b][url=http://www.jforum.net/contribute.jsp]Click here[/url][/b] to go to the [i][b][url=http://www.jforum.net/contribute.jsp]"Support JForum"[/url][/b][/i] page.[/size]\n\n', 'Support JForum - Please read'),
(3, 'x', 'Welcome to JForum'),
(5, 'dd', 'ghhj'),
(6, 'x', 'Re:ghhj'),
(7, 'x', 'Re:ghhj'),
(8, 'f', 'Re:ghhj'),
(9, 'xxxx', 'x');

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_privmsgs`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_privmsgs` (
  `privmsgs_id` int(11) NOT NULL AUTO_INCREMENT,
  `privmsgs_type` tinyint(4) NOT NULL DEFAULT '0',
  `privmsgs_subject` varchar(255) NOT NULL DEFAULT '',
  `privmsgs_from_userid` int(11) NOT NULL DEFAULT '0',
  `privmsgs_to_userid` int(11) NOT NULL DEFAULT '0',
  `privmsgs_date` datetime DEFAULT NULL,
  `privmsgs_ip` varchar(15) NOT NULL DEFAULT '',
  `privmsgs_enable_bbcode` tinyint(1) NOT NULL DEFAULT '1',
  `privmsgs_enable_html` tinyint(1) NOT NULL DEFAULT '0',
  `privmsgs_enable_smilies` tinyint(1) NOT NULL DEFAULT '1',
  `privmsgs_attach_sig` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`privmsgs_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dump dei dati per la tabella `jforum_privmsgs`
--

INSERT INTO `jforum_privmsgs` (`privmsgs_id`, `privmsgs_type`, `privmsgs_subject`, `privmsgs_from_userid`, `privmsgs_to_userid`, `privmsgs_date`, `privmsgs_ip`, `privmsgs_enable_bbcode`, `privmsgs_enable_html`, `privmsgs_enable_smilies`, `privmsgs_attach_sig`) VALUES
(3, 2, 'x', 2, 2, '2012-12-09 19:13:00', '', 1, 0, 1, 1),
(5, 2, '7yu', 2, 2, '2012-12-09 19:16:02', '', 1, 0, 1, 1),
(13, 2, 'grg', 2, 3, '2012-12-09 19:29:03', '', 1, 0, 1, 1),
(14, 0, 'grg', 2, 3, '2012-12-09 19:29:03', '', 1, 0, 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_privmsgs_text`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_privmsgs_text` (
  `privmsgs_id` int(11) NOT NULL,
  `privmsgs_text` text,
  PRIMARY KEY (`privmsgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `jforum_privmsgs_text`
--

INSERT INTO `jforum_privmsgs_text` (`privmsgs_id`, `privmsgs_text`) VALUES
(3, 'd'),
(5, 'jj'),
(13, 'ccc'),
(14, 'ccc');

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_quota_limit`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_quota_limit` (
  `quota_limit_id` int(11) NOT NULL AUTO_INCREMENT,
  `quota_desc` varchar(50) NOT NULL,
  `quota_limit` int(11) NOT NULL,
  `quota_type` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`quota_limit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_ranks`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_ranks` (
  `rank_id` int(11) NOT NULL AUTO_INCREMENT,
  `rank_title` varchar(50) NOT NULL DEFAULT '',
  `rank_min` int(11) NOT NULL DEFAULT '0',
  `rank_special` tinyint(1) DEFAULT NULL,
  `rank_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_roles`
--
-- Creazione: Nov 28, 2012 alle 09:26
--

CREATE TABLE IF NOT EXISTS `jforum_roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT '0',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `idx_group` (`group_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=41 ;

--
-- Dump dei dati per la tabella `jforum_roles`
--

INSERT INTO `jforum_roles` (`role_id`, `group_id`, `name`) VALUES
(1, 1, 'perm_vote'),
(2, 1, 'perm_karma_enabled'),
(3, 1, 'perm_anonymous_post'),
(4, 1, 'perm_create_poll'),
(5, 1, 'perm_bookmarks_enabled'),
(6, 1, 'perm_attachments_download'),
(7, 1, 'perm_create_sticky_announcement_topics'),
(8, 1, 'perm_moderation_log'),
(9, 2, 'perm_administration'),
(10, 2, 'perm_moderation'),
(11, 2, 'perm_moderation_post_remove'),
(12, 2, 'perm_moderation_post_edit'),
(13, 2, 'perm_moderation_topic_move'),
(14, 2, 'perm_moderation_topic_lockUnlock'),
(15, 2, 'perm_moderation_approve_messages'),
(16, 2, 'perm_create_sticky_announcement_topics'),
(17, 2, 'perm_vote'),
(18, 2, 'perm_create_poll'),
(19, 2, 'perm_karma_enabled'),
(20, 2, 'perm_bookmarks_enabled'),
(21, 2, 'perm_attachments_download'),
(22, 2, 'perm_moderation_log'),
(23, 2, 'perm_full_moderation_log'),
(24, 1, 'perm_forum'),
(25, 2, 'perm_forum'),
(26, 1, 'perm_anonymous_post'),
(27, 2, 'perm_anonymous_post'),
(28, 1, 'perm_category'),
(29, 2, 'perm_category'),
(30, 1, 'perm_read_only_forums'),
(31, 2, 'perm_read_only_forums'),
(32, 1, 'perm_html_disabled'),
(33, 2, 'perm_html_disabled'),
(34, 1, 'perm_attachments_enabled'),
(35, 2, 'perm_attachments_enabled'),
(36, 1, 'perm_reply_only'),
(37, 2, 'perm_reply_only'),
(38, 1, 'perm_reply_without_moderation'),
(39, 2, 'perm_reply_without_moderation'),
(40, 2, 'perm_moderation_forums');

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_role_values`
--
-- Creazione: Nov 28, 2012 alle 09:26
--

CREATE TABLE IF NOT EXISTS `jforum_role_values` (
  `role_id` int(11) NOT NULL,
  `role_value` varchar(255) DEFAULT NULL,
  KEY `idx_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `jforum_role_values`
--

INSERT INTO `jforum_role_values` (`role_id`, `role_value`) VALUES
(24, '1'),
(25, '1'),
(26, '1'),
(27, '1'),
(28, '1'),
(29, '1'),
(30, '1'),
(31, '1'),
(32, '1'),
(33, '1'),
(34, '1'),
(35, '1'),
(36, '1'),
(37, '1'),
(38, '1'),
(39, '1'),
(40, '1'),
(29, '2'),
(28, '2'),
(25, '2'),
(24, '2'),
(27, '2'),
(3, '2'),
(31, '2'),
(30, '2'),
(37, '2'),
(36, '2'),
(33, '2'),
(32, '2'),
(25, '3'),
(24, '3'),
(27, '3'),
(3, '3'),
(31, '3'),
(30, '3'),
(37, '3'),
(36, '3'),
(33, '3'),
(32, '3');

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_sessions`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_sessions` (
  `session_id` varchar(150) NOT NULL DEFAULT '',
  `session_user_id` int(11) NOT NULL DEFAULT '0',
  `session_start` datetime DEFAULT NULL,
  `session_time` bigint(20) DEFAULT '0',
  `session_ip` varchar(15) NOT NULL DEFAULT '',
  `session_page` int(11) NOT NULL DEFAULT '0',
  `session_logged_int` tinyint(1) DEFAULT NULL,
  KEY `idx_sessions_users` (`session_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `jforum_sessions`
--

INSERT INTO `jforum_sessions` (`session_id`, `session_user_id`, `session_start`, `session_time`, `session_ip`, `session_page`, `session_logged_int`) VALUES
('8DE9FED590BA40DAC6940233107008B6', 2, '2012-12-10 17:02:40', 62, '', 0, NULL),
('B5926B0C89854F78735A871527A6D5F4', 3, '2012-12-10 14:07:16', 3937, '', 0, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_smilies`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_smilies` (
  `smilie_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL DEFAULT '',
  `url` varchar(100) DEFAULT NULL,
  `disk_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`smilie_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dump dei dati per la tabella `jforum_smilies`
--

INSERT INTO `jforum_smilies` (`smilie_id`, `code`, `url`, `disk_name`) VALUES
(1, ':)', '<img src="#CONTEXT#/images/smilies/3b63d1616c5dfcf29f8a7a031aaa7cad.gif" />', '3b63d1616c5dfcf29f8a7a031aaa7cad.gif'),
(2, ':-)', '<img src="#CONTEXT#/images/smilies/3b63d1616c5dfcf29f8a7a031aaa7cad.gif"/>', '3b63d1616c5dfcf29f8a7a031aaa7cad.gif'),
(3, ':D', '<img src="#CONTEXT#/images/smilies/283a16da79f3aa23fe1025c96295f04f.gif" />', '283a16da79f3aa23fe1025c96295f04f.gif'),
(4, ':-D', '<img src="#CONTEXT#/images/smilies/283a16da79f3aa23fe1025c96295f04f.gif" />', '283a16da79f3aa23fe1025c96295f04f.gif'),
(5, ':(', '<img src="#CONTEXT#/images/smilies/9d71f0541cff0a302a0309c5079e8dee.gif" />', '9d71f0541cff0a302a0309c5079e8dee.gif'),
(6, ':mrgreen:', '<img src="#CONTEXT#/images/smilies/ed515dbff23a0ee3241dcc0a601c9ed6.gif" />', 'ed515dbff23a0ee3241dcc0a601c9ed6.gif'),
(7, ':-o', '<img src="#CONTEXT#/images/smilies/47941865eb7bbc2a777305b46cc059a2.gif"  />', '47941865eb7bbc2a777305b46cc059a2.gif'),
(8, ':shock:', '<img src="#CONTEXT#/images/smilies/385970365b8ed7503b4294502a458efa.gif" />', '385970365b8ed7503b4294502a458efa.gif'),
(9, ':?:', '<img src="#CONTEXT#/images/smilies/0a4d7238daa496a758252d0a2b1a1384.gif" />', '0a4d7238daa496a758252d0a2b1a1384.gif'),
(10, '8)', '<img src="#CONTEXT#/images/smilies/b2eb59423fbf5fa39342041237025880.gif"  />', 'b2eb59423fbf5fa39342041237025880.gif'),
(11, ':lol:', '<img src="#CONTEXT#/images/smilies/97ada74b88049a6d50a6ed40898a03d7.gif" />', '97ada74b88049a6d50a6ed40898a03d7.gif'),
(12, ':x', '<img src="#CONTEXT#/images/smilies/1069449046bcd664c21db15b1dfedaee.gif"  />', '1069449046bcd664c21db15b1dfedaee.gif'),
(13, ':P', '<img src="#CONTEXT#/images/smilies/69934afc394145350659cd7add244ca9.gif" />', '69934afc394145350659cd7add244ca9.gif'),
(14, ':-P', '<img src="#CONTEXT#/images/smilies/69934afc394145350659cd7add244ca9.gif" />', '69934afc394145350659cd7add244ca9.gif'),
(15, ':oops:', '<img src="#CONTEXT#/images/smilies/499fd50bc713bfcdf2ab5a23c00c2d62.gif" />', '499fd50bc713bfcdf2ab5a23c00c2d62.gif'),
(16, ':cry:', '<img src="#CONTEXT#/images/smilies/c30b4198e0907b23b8246bdd52aa1c3c.gif" />', 'c30b4198e0907b23b8246bdd52aa1c3c.gif'),
(17, ':evil:', '<img src="#CONTEXT#/images/smilies/2e207fad049d4d292f60607f80f05768.gif" />', '2e207fad049d4d292f60607f80f05768.gif'),
(18, ':twisted:', '<img src="#CONTEXT#/images/smilies/908627bbe5e9f6a080977db8c365caff.gif" />', '908627bbe5e9f6a080977db8c365caff.gif'),
(19, ':roll:', '<img src="#CONTEXT#/images/smilies/2786c5c8e1a8be796fb2f726cca5a0fe.gif" />', '2786c5c8e1a8be796fb2f726cca5a0fe.gif'),
(20, ':wink:', '<img src="#CONTEXT#/images/smilies/8a80c6485cd926be453217d59a84a888.gif" />', '8a80c6485cd926be453217d59a84a888.gif'),
(21, ';)', '<img src="#CONTEXT#/images/smilies/8a80c6485cd926be453217d59a84a888.gif" />', '8a80c6485cd926be453217d59a84a888.gif'),
(22, ';-)', '<img src="#CONTEXT#/images/smilies/8a80c6485cd926be453217d59a84a888.gif" />', '8a80c6485cd926be453217d59a84a888.gif'),
(23, ':!:', '<img src="#CONTEXT#/images/smilies/9293feeb0183c67ea1ea8c52f0dbaf8c.gif" />', '9293feeb0183c67ea1ea8c52f0dbaf8c.gif'),
(24, ':?', '<img src="#CONTEXT#/images/smilies/136dd33cba83140c7ce38db096d05aed.gif" />', '136dd33cba83140c7ce38db096d05aed.gif'),
(25, ':idea:', '<img src="#CONTEXT#/images/smilies/8f7fb9dd46fb8ef86f81154a4feaada9.gif" />', '8f7fb9dd46fb8ef86f81154a4feaada9.gif'),
(26, ':arrow:', '<img src="#CONTEXT#/images/smilies/d6741711aa045b812616853b5507fd2a.gif" />', 'd6741711aa045b812616853b5507fd2a.gif'),
(27, ':hunf:', '<img src="#CONTEXT#/images/smilies/0320a00cb4bb5629ab9fc2bc1fcc4e9e.gif" />', '0320a00cb4bb5629ab9fc2bc1fcc4e9e.gif'),
(28, ':-(', '<img src="#CONTEXT#/images/smilies/9d71f0541cff0a302a0309c5079e8dee.gif"  />', '9d71f0541cff0a302a0309c5079e8dee.gif'),
(29, ':XD:', '<img src="#CONTEXT#/images/smilies/49869fe8223507d7223db3451e5321aa.gif" />', '49869fe8223507d7223db3451e5321aa.gif'),
(30, ':thumbup:', '<img src="#CONTEXT#/images/smilies/e8a506dc4ad763aca51bec4ca7dc8560.gif" />', 'e8a506dc4ad763aca51bec4ca7dc8560.gif'),
(31, ':thumbdown:', '<img src="#CONTEXT#/images/smilies/e78feac27fa924c4d0ad6cf5819f3554.gif" />', 'e78feac27fa924c4d0ad6cf5819f3554.gif'),
(32, ':|', '<img src="#CONTEXT#/images/smilies/1cfd6e2a9a2c0cf8e74b49b35e2e46c7.gif" />', '1cfd6e2a9a2c0cf8e74b49b35e2e46c7.gif');

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_themes`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_themes` (
  `themes_id` int(11) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(30) NOT NULL DEFAULT '',
  `style_name` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`themes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_topics`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_topics` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_id` int(11) NOT NULL DEFAULT '0',
  `topic_title` varchar(100) NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `topic_time` datetime DEFAULT NULL,
  `topic_views` int(11) DEFAULT '1',
  `topic_replies` int(11) DEFAULT '0',
  `topic_status` tinyint(3) DEFAULT '0',
  `topic_vote_id` int(11) NOT NULL DEFAULT '0',
  `topic_type` tinyint(3) DEFAULT '0',
  `topic_first_post_id` int(11) DEFAULT '0',
  `topic_last_post_id` int(11) NOT NULL DEFAULT '0',
  `topic_moved_id` int(11) DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`topic_id`),
  KEY `forum_id` (`forum_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_first_post_id` (`topic_first_post_id`),
  KEY `topic_last_post_id` (`topic_last_post_id`),
  KEY `topic_moved_id` (`topic_moved_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dump dei dati per la tabella `jforum_topics`
--

INSERT INTO `jforum_topics` (`topic_id`, `forum_id`, `topic_title`, `user_id`, `topic_time`, `topic_views`, `topic_replies`, `topic_status`, `topic_vote_id`, `topic_type`, `topic_first_post_id`, `topic_last_post_id`, `topic_moved_id`, `moderated`) VALUES
(1, 3, 'Welcome to JForum', 2, '2005-01-04 16:59:54', 59, 1, 0, 0, 0, 1, 3, 1, 0),
(2, 1, 'Support JForum - Please read', 2, '2012-11-28 10:29:12', 177, 0, 0, 0, 2, 2, 2, 0, 0),
(3, 1, 'ghhj', 2, '2012-12-06 08:20:15', 33, 3, 0, 0, 0, 5, 8, 0, 0),
(4, 1, 'x', 2, '2012-12-09 17:34:23', 4, 0, 0, 0, 0, 9, 9, 0, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_topics_watch`
--
-- Creazione: Nov 28, 2012 alle 09:25
--

CREATE TABLE IF NOT EXISTS `jforum_topics_watch` (
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_read` tinyint(1) DEFAULT '1',
  KEY `idx_topic` (`topic_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `jforum_topics_watch`
--

INSERT INTO `jforum_topics_watch` (`topic_id`, `user_id`, `is_read`) VALUES
(1, 3, 1),
(3, 2, 1),
(3, 3, 1),
(4, 2, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_users`
--
-- Creazione: Nov 28, 2012 alle 09:24
--

CREATE TABLE IF NOT EXISTS `jforum_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_active` tinyint(1) DEFAULT NULL,
  `username` varchar(50) NOT NULL DEFAULT '',
  `user_password` varchar(32) NOT NULL DEFAULT '',
  `user_session_time` bigint(20) DEFAULT '0',
  `user_session_page` int(11) NOT NULL DEFAULT '0',
  `user_lastvisit` datetime DEFAULT NULL,
  `user_regdate` datetime DEFAULT NULL,
  `user_level` tinyint(4) DEFAULT NULL,
  `user_posts` int(11) NOT NULL DEFAULT '0',
  `user_timezone` varchar(5) NOT NULL DEFAULT '',
  `user_style` tinyint(4) DEFAULT NULL,
  `user_lang` varchar(255) NOT NULL DEFAULT '',
  `user_dateformat` varchar(20) NOT NULL DEFAULT '%d/%M/%Y %H:%i',
  `user_new_privmsg` int(11) NOT NULL DEFAULT '0',
  `user_unread_privmsg` int(11) NOT NULL DEFAULT '0',
  `user_last_privmsg` datetime DEFAULT NULL,
  `user_emailtime` datetime DEFAULT NULL,
  `user_viewemail` tinyint(1) DEFAULT '0',
  `user_attachsig` tinyint(1) DEFAULT '1',
  `user_allowhtml` tinyint(1) DEFAULT '0',
  `user_allowbbcode` tinyint(1) DEFAULT '1',
  `user_allowsmilies` tinyint(1) DEFAULT '1',
  `user_allowavatar` tinyint(1) DEFAULT '1',
  `user_allow_pm` tinyint(1) DEFAULT '1',
  `user_allow_viewonline` tinyint(1) DEFAULT '1',
  `user_notify` tinyint(1) DEFAULT '1',
  `user_notify_always` tinyint(1) DEFAULT '0',
  `user_notify_text` tinyint(1) DEFAULT '0',
  `user_notify_pm` tinyint(1) DEFAULT '1',
  `user_popup_pm` tinyint(1) DEFAULT '1',
  `rank_id` int(11) DEFAULT '0',
  `user_avatar` varchar(100) DEFAULT NULL,
  `user_avatar_type` tinyint(4) NOT NULL DEFAULT '0',
  `user_email` varchar(255) NOT NULL DEFAULT '',
  `user_icq` varchar(15) DEFAULT NULL,
  `user_website` varchar(255) DEFAULT NULL,
  `user_from` varchar(100) DEFAULT NULL,
  `user_sig` text,
  `user_sig_bbcode_uid` varchar(10) DEFAULT NULL,
  `user_aim` varchar(255) DEFAULT NULL,
  `user_yim` varchar(255) DEFAULT NULL,
  `user_msnm` varchar(255) DEFAULT NULL,
  `user_occ` varchar(100) DEFAULT NULL,
  `user_interests` varchar(255) DEFAULT NULL,
  `user_biography` text,
  `user_actkey` varchar(32) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `themes_id` int(11) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `user_viewonline` tinyint(1) DEFAULT '1',
  `security_hash` varchar(32) DEFAULT NULL,
  `user_karma` double DEFAULT NULL,
  `user_authhash` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `jforum_users`
--

INSERT INTO `jforum_users` (`user_id`, `user_active`, `username`, `user_password`, `user_session_time`, `user_session_page`, `user_lastvisit`, `user_regdate`, `user_level`, `user_posts`, `user_timezone`, `user_style`, `user_lang`, `user_dateformat`, `user_new_privmsg`, `user_unread_privmsg`, `user_last_privmsg`, `user_emailtime`, `user_viewemail`, `user_attachsig`, `user_allowhtml`, `user_allowbbcode`, `user_allowsmilies`, `user_allowavatar`, `user_allow_pm`, `user_allow_viewonline`, `user_notify`, `user_notify_always`, `user_notify_text`, `user_notify_pm`, `user_popup_pm`, `rank_id`, `user_avatar`, `user_avatar_type`, `user_email`, `user_icq`, `user_website`, `user_from`, `user_sig`, `user_sig_bbcode_uid`, `user_aim`, `user_yim`, `user_msnm`, `user_occ`, `user_interests`, `user_biography`, `user_actkey`, `gender`, `themes_id`, `deleted`, `user_viewonline`, `security_hash`, `user_karma`, `user_authhash`) VALUES
(1, NULL, 'Anonymous', 'nopass', 0, 0, NULL, '2012-11-28 10:29:08', NULL, 0, '', NULL, '', '%d/%M/%Y %H:%i', 0, 0, NULL, NULL, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, NULL, 0, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, 0, NULL),
(2, NULL, 'Admin', '21232f297a57a5a743894a0e4a801fc3', 0, 0, '2012-12-02 16:24:22', '2012-11-28 10:29:08', NULL, 4, '', NULL, '', '%d/%M/%Y %H:%i', 0, 0, NULL, NULL, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, NULL, 0, 'admin', '', '', '', '', NULL, '', '', '', '', '', '', NULL, NULL, 0, NULL, 1, 'b93d30af4943a0f8aa83a0b71f3311dc', 5, '701751b26e0e7e51ee8e7461bd3becc2'),
(3, 1, 'prova', '189bbbb00c5f1fb7fba9ad9285f193d1', 0, 0, '2012-12-08 12:33:48', '2012-12-03 08:45:30', NULL, 3, '', NULL, '', '%d/%M/%Y %H:%i', 0, 0, NULL, NULL, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 'eccbc87e4b5ce2fe28308fd9f2a7baf3.png', 0, 'prova@prova.it', 'x', 'http://www.prova.it', '3', '78', NULL, 'xx', '1', 'xxx', '4', '5', '6', NULL, NULL, 0, NULL, 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_user_groups`
--
-- Creazione: Nov 28, 2012 alle 09:26
--

CREATE TABLE IF NOT EXISTS `jforum_user_groups` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `idx_group` (`group_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `jforum_user_groups`
--

INSERT INTO `jforum_user_groups` (`group_id`, `user_id`) VALUES
(1, 1),
(2, 2),
(1, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_vote_desc`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_vote_desc` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `vote_text` varchar(255) NOT NULL DEFAULT '',
  `vote_start` datetime NOT NULL,
  `vote_length` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vote_id`),
  KEY `topic_id` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_vote_results`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_vote_results` (
  `vote_id` int(11) NOT NULL DEFAULT '0',
  `vote_option_id` tinyint(4) NOT NULL DEFAULT '0',
  `vote_option_text` varchar(255) NOT NULL DEFAULT '',
  `vote_result` int(11) NOT NULL DEFAULT '0',
  KEY `vote_id` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_vote_voters`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_vote_voters` (
  `vote_id` int(11) NOT NULL DEFAULT '0',
  `vote_user_id` int(11) NOT NULL DEFAULT '0',
  `vote_user_ip` varchar(15) NOT NULL DEFAULT '',
  KEY `vote_id` (`vote_id`),
  KEY `vote_user_id` (`vote_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `jforum_words`
--
-- Creazione: Nov 28, 2012 alle 09:23
--

CREATE TABLE IF NOT EXISTS `jforum_words` (
  `word_id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(100) NOT NULL DEFAULT '',
  `replacement` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
