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
	zip_code int,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE profile (
        profile_id serial PRIMARY KEY,
        username varchar (50),
        first_name varchar (50) NOT NULL,
        last_name varchar (50) NOT NULL,
        zipcode int NOT NULL,
        FOREIGN KEY (username) REFERENCES users(username));

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
CREATE TABLE playdate_status(
    status_id serial PRIMARY KEY,
    status_type varchar(50) UNIQUE
);
CREATE TABLE playdate (
    playdate_id serial PRIMARY KEY,
    username varchar(50),
    address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    zip_code int NOT NULL,
    playdate_time time NOT NULL,
    playdate_date date NOT NULL,
    playdate_description text,
    status_type varchar (50),
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (status_type) REFERENCES playdate_status(status_type)
    );
CREATE TABLE playdate_pets(
    playdate_id int,
    pet_id int,
    FOREIGN KEY (playdate_id) REFERENCES playdate(playdate_id),
    FOREIGN KEY (pet_id) REFERENCES pet(pet_id)
    );
CREATE TABLE forum(
    forum_id serial PRIMARY KEY,
    username varchar(50),
    pet_species varchar(50) NOT NULL,
    title varchar(50) NOT NULL,
    post_date_time timestamp,
    forum_post text NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
    ); 
INSERT INTO playdate_status (status_type) VALUES ('Accepted');
INSERT INTO playdate_status (status_type) VALUES ('Pending');
INSERT INTO playdate_status (status_type) VALUES ('Declined');
COMMIT TRANSACTION;
