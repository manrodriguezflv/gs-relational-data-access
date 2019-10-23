CREATE TABLE IF NOT EXISTS customers(
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  PRIMARY KEY (id)
);

INSERT INTO customers (first_name, last_name) values ('John', 'Woo');
INSERT INTO customers (first_name, last_name) values ('Jeff', 'Dean');
INSERT INTO customers (first_name, last_name) values ('Josh', 'Bloch');
INSERT INTO customers (first_name, last_name) values ('Josh', 'Long');