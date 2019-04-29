CREATE DATABASE document_store;

USE document_store

CREATE TABLE document
(id INT NOT NULL AUTO_INCREMENT primary key,
ref_no VARCHAR(20),
full_name VARCHAR(200),
address VARCHAR(200),
nic VARCHAR(20),
status VARCHAR(20),
created_date DATE,
last_updated_date DATE,
UNIQUE KEY unique_ref (ref_no));
