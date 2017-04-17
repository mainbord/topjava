DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE,
  calories_per_day INTEGER DEFAULT 2000 NOT NULL
);

CREATE TABLE meals
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  datetime        TIMESTAMP NOT NULL,
  description     VARCHAR NOT NULL,
  calories        INTEGER NOT NULL,
  user_id         INTEGER references users(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER
SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES
  ('2015-05-30 10:00', 'Завтрак', 500, 100000),
  ('2015-05-30 13:00', 'Обед', 1000, 100000),
  ('2015-05-30 20:00', 'Ужин', 500, 100000),
  ('2015-05-31 10:00', 'Завтрак', 1000, 100000),
  ('2015-05-31 13:00', 'Обед', 500, 100000),
  ('2015-05-31 13:00', 'Обед', 500, 100001),
  ('2015-05-31 20:00', 'Ужин', 510, 100000);
