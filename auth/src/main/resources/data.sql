DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `description` varchar(255) NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `account_non_expired` bit(1) DEFAULT NULL,
                        `account_non_locked` bit(1) DEFAULT NULL,
                        `credentials_non_expired` bit(1) DEFAULT NULL,
                        `enable` bit(1) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `user_name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission` (
                                   `id_user` bigint NOT NULL,
                                   `id_permissions` bigint NOT NULL,
                                   KEY `FKfyk736flpmax2419j4gl6ua12` (`id_permissions`),
                                   KEY `FKpskbfr0wjeo3vcytgr8y2fhqx` (`id_user`),
                                   CONSTRAINT `FKfyk736flpmax2419j4gl6ua12` FOREIGN KEY (`id_permissions`) REFERENCES `permission` (`id`),
                                   CONSTRAINT `FKpskbfr0wjeo3vcytgr8y2fhqx` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO auth.permission
(id, description)
VALUES(1, 'Admin');

INSERT INTO auth.`user`
(id, account_non_expired, account_non_locked, credentials_non_expired, enable, password, user_name)
VALUES(1, 1, 1, 1, 1, '$2a$10$BZ6KJ5xFxSAPrB8XrMeBvuKhRn2ztyxClZfHpZygCMN73KrZbBB5W', 'administrador');

INSERT INTO auth.user_permission
(id_user, id_permissions)
VALUES(1, 1);
