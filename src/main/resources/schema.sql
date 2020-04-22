CREATE TABLE users (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INT UNSIGNED,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE boards (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    owner_id INT UNSIGNED NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE task_groups (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title TEXT NOT NULL,
    board_id INT UNSIGNED NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (board_id) REFERENCES boards(id)
);

CREATE TABLE tasks (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    group_id INT UNSIGNED NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (group_id) REFERENCES task_groups(id)
);