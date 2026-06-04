CREATE DATABASE IF NOT EXISTS ibtissam_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE ibtissam_db;

CREATE TABLE ibtissam_contacts (
    ibtissam_id INT AUTO_INCREMENT PRIMARY KEY,
    ibtissam_name VARCHAR(150) NOT NULL,
    ibtissam_phone VARCHAR(50) NOT NULL,
    ibtissam_source VARCHAR(50) DEFAULT 'mobile',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
