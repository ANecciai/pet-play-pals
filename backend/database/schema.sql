BEGIN TRANSACTION;
DROP TABLE IF EXISTS forum;
DROP TABLE IF EXISTS playdate_pets;
DROP TABLE IF EXISTS playdate;
DROP TABLE IF EXISTS playdate_status;
DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
CREATE SEQUENCE seq_user_id
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1;
CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username, password_hash, role) VALUES ('Sara','$2a$10$rVKA6zvhKw2zIN8rr/JkH.1kl4jTlhIefs7wY01ljYuZZmQzjC6xC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('John', '$2a$10$rVKA6zvhKw2zIN8rr/JkH.1kl4jTlhIefs7wY01ljYuZZmQzjC6xC', 'ROLE_USER');

CREATE TABLE profile (
    profile_id serial PRIMARY KEY,
    username varchar (50) NOT NULL,
    first_name varchar (50) NOT NULL,
    last_name varchar (50) NOT NULL,
    zipcode int NOT NULL,
    about_me text,
    FOREIGN KEY (username) REFERENCES users(username));

INSERT INTO profile (username, first_name, last_name, zipcode, about_me) VALUES ('Sara', 'Sara', 'Jane', '15316', 'Hi, my name is Sara! Feel free to make a playdate!');
INSERT INTO profile (username, first_name, last_name, zipcode, about_me) VALUES ('John', 'John', 'Doe', '15222', 'Help me my house is a zoo');

CREATE TABLE pet (
  pet_id serial PRIMARY KEY,
  username varchar(50),
  pet_name varchar(50) NOT NULL,
  species varchar(50) NOT NULL,
  breed varchar(50),
  activity_level varchar(50),
  gender varchar(50),
  age int,
  description varchar(200),
  CONSTRAINT FK_pet FOREIGN KEY (username) REFERENCES users(username),
  CONSTRAINT CHK_activity_level CHECK (activity_level IN ('High', 'Medium', 'Low'))
  );

INSERT INTO pet (username, pet_name, species, breed, activity_level, gender, age, description) VALUES ('Sara', 'Spot', 'dog', 'Dalmatian', 'High', 'Male', '2', 'This is Spot.');
INSERT INTO pet (username, pet_name, species, breed, activity_level, gender, age, description) VALUES ('Sara', 'Fluffy', 'cat', 'Maine Coon', 'Medium', 'Female', '5', 'Fluffy lives up to her name! She sleeps hard and plays harder!');
INSERT INTO pet (username, pet_name, species, breed, activity_level, gender, age, description) VALUES ('John', 'Tweety', 'bird', 'Conure', 'High', 'Female', '1', 'No cats please!');
INSERT INTO pet (username, pet_name, species, breed, activity_level, gender, age, description) VALUES ('John', 'Bentley', 'dog', 'Mix', 'Medium', 'Male', '2', 'Lab/Beagle Mix');
INSERT INTO pet (username, pet_name, species, breed, activity_level, gender, age, description) VALUES ('John', 'Peanut', 'dog', 'Mastiff', 'Low', 'Male', '3', 'Gentle giant');
INSERT INTO pet (username, pet_name, species, breed, activity_level, gender, age, description) VALUES ('John', 'Whiskers', 'cat', 'Orange', 'Low', 'Male', '2', 'He is orange.');



CREATE TABLE playdate (
  playdate_id serial PRIMARY KEY,
  address varchar(50) NOT NULL,
  city varchar(50) NOT NULL,
  state varchar(25) NOT NULL,
  zip_code int NOT NULL,
  playdate_time varchar(10) NOT NULL,
  playdate_date date NOT NULL,
  playdate_description text,
  status_type varchar (50),
  host_username varchar(50) NOT NULL,
  invited_username varchar(50) NOT NULL,
  CONSTRAINT CHK_status_type CHECK (status_type IN ('Accepted','Declined','Canceled','Pending')),
  FOREIGN KEY (host_username) REFERENCES users(username),
  FOREIGN KEY (invited_username) REFERENCES users(username));

  INSERT INTO playdate (address, city, state, zip_code, playdate_time, playdate_date, playdate_description, status_type, host_username, invited_username)
  VALUES ('123 Pitt Ave', 'Pittsburgh', 'PA', '15222', '10:00', '10-12-2021', 'I will be the one on the corner with the giant dog!', 'Pending', 'John', 'Sara');


CREATE TABLE forum(
  forum_id serial PRIMARY KEY,
  username varchar(50),
  pet_species varchar(50) NOT NULL,
  title varchar(50) NOT NULL,
  post_date_time timestamp,
  forum_post text NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
  ); 

COMMIT TRANSACTION;
