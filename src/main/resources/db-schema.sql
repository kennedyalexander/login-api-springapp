CREATE TABLE USERS
(USER_USERNAME VARCHAR(25) NOT NULL,
USER_EMAIL VARCHAR(50),
USER_PASSWORD VARCHAR(50) NOT NULL,
USER_ACTIVE INT NOT NULL,
PRIMARY KEY (USER_USERNAME));