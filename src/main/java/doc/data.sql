CREATE  DATABASE IF NOT EXISTS paymybuddy;
USE paymybuddy;

DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS user_connections;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    balance DOUBLE NOT NULL DEFAULT 0.0
);

CREATE TABLE user_connections (
    user_id  BIGINT NOT NULL,
    connection_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, connection_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_connection FOREIGN KEY (connection_id) REFERENCES users(id)
);

CREATE TABLE transaction (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description VARCHAR(255),
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    CONSTRAINT fk_sender FOREIGN KEY (sender_id) REFERENCES users(id),
    CONSTRAINT fk_receiver FOREIGN KEY (receiver_id) REFERENCES users(id)
);