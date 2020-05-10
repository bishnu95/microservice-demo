DROP TABLE IF EXISTS user;
 
CREATE TABLE user (
  id INT PRIMARY KEY,
  userName VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  active BOOLEAN,
  roles VARCHAR(250)
);
 
INSERT INTO user VALUES (1, 'Bishnu', 'password', true, 'USER_ROLE');