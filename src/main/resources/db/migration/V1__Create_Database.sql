CREATE TABLE app_users
 (
    id                   bigint       NOT NULL AUTO_INCREMENT,
created_at date,
email VARCHAR(255) NOT NULL,
name VARCHAR(255) NOT NULL, 
password VARCHAR(255),
PRIMARY KEY (id)
) ENGINE = MyISAM
DEFAULT CHARSET = utf8;

CREATE TABLE contacts 
(
    id                   bigint       NOT NULL AUTO_INCREMENT,
description VARCHAR(255), 
email VARCHAR(255), 
contact_name VARCHAR(255) NOT NULL, 
phone_number VARCHAR(255) NOT NULL, 
type VARCHAR(255) NOT NULL, 
user_id binary(255), 
PRIMARY KEY (id)
) ENGINE = MyISAM
DEFAULT CHARSET = utf8;

CREATE TABLE medicines 
(
    id                   bigint       NOT NULL AUTO_INCREMENT,
dosage VARCHAR(255), 
importancy VARCHAR(255), 
medicine_name VARCHAR(255) NOT NULL, 
pet_id binary(255), 
PRIMARY KEY (id)
) ENGINE = MyISAM
DEFAULT CHARSET = utf8;

CREATE TABLE nutrition_details 
(
    id                   bigint       NOT NULL AUTO_INCREMENT,
brand VARCHAR(255), 
description VARCHAR(255), 
food_name VARCHAR(255) NOT NULL, 
pet_id binary(255), 
PRIMARY KEY (id)
) ENGINE = MyISAM
DEFAULT CHARSET = utf8;

CREATE TABLE pedigrees 
(
    id                   bigint       NOT NULL AUTO_INCREMENT,
breeder VARCHAR(255), 
father_name VARCHAR(255), 
mother_name VARCHAR(255), 
pedigree_number VARCHAR(255) NOT NULL, 
pet_id binary(255), 
PRIMARY KEY (id)
) ENGINE = MyISAM
DEFAULT CHARSET = utf8;

CREATE TABLE pets 
(
    id                   bigint       NOT NULL AUTO_INCREMENT,
birth_name date, 
chip_number VARCHAR(255), 
coat_color VARCHAR(255), 
created_at datetime, 
dead BIT, 
death_date date, 
gender VARCHAR(255), 
avatar_url VARCHAR(255), 
pet_name VARCHAR(255) NOT NULL, 
race VARCHAR(255), 
type VARCHAR(255) NOT NULL, 
owner_id binary(255), 
pedigree_id binary(255), 
PRIMARY KEY (id)
) ENGINE = MyISAM
DEFAULT CHARSET = utf8;

CREATE TABLE treatments 
(
    id                   bigint       NOT NULL AUTO_INCREMENT,
description VARCHAR(255), 
treatment_end_date datetime, 
treatment_start_date datetime NOT NULL, 
treatment_type VARCHAR(255) NOT NULL, 
pet_id binary(255), 
PRIMARY KEY (id)
) ENGINE = MyISAM
DEFAULT CHARSET = utf8;
 
 ALTER TABLE contacts ADD FOREIGN KEY (user_id) REFERENCES app_users (id);
 
 ALTER TABLE medicines ADD FOREIGN KEY (pet_id) REFERENCES pets (id);
 
 ALTER TABLE nutrition_details ADD FOREIGN KEY (pet_id) REFERENCES pets (id);
 
 ALTER TABLE pedigrees ADD FOREIGN KEY (pet_id) REFERENCES pets (id);
 
 ALTER TABLE pets ADD FOREIGN KEY (owner_id) REFERENCES app_users (id);
 
 ALTER TABLE pets ADD FOREIGN KEY (pedigree_id) REFERENCES pedigrees (id);
 
 ALTER TABLE treatments ADD FOREIGN KEY (pet_id) REFERENCES pets (id);
