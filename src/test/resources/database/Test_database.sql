DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS app_users;
DROP TABLE IF EXISTS medicines;
DROP TABLE IF EXISTS nutrition_details;
DROP TABLE IF EXISTS pedigrees;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS treatments;

CREATE TABLE app_users
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    created_at DATE,
    email      VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    password   VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
CREATE TABLE contacts
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    description  VARCHAR(255),
    email        VARCHAR(255),
    contact_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    type         VARCHAR(255) NOT NULL,
    user_id      BIGINT,
    PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
CREATE TABLE medicines
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    dosage        VARCHAR(255),
    importancy    VARCHAR(255),
    medicine_name VARCHAR(255),
    pet_id        BIGINT,
    PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
CREATE TABLE nutrition_details
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    brand       VARCHAR(255),
    description VARCHAR(255),
    food_name   VARCHAR(255),
    pet_id      BIGINT,
    PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
CREATE TABLE pedigrees
(
    id              BIGINT NOT NULL AUTO_INCREMENT,
    breeder         VARCHAR(255),
    father_name     VARCHAR(255),
    mother_name     VARCHAR(255),
    pedigree_number VARCHAR(255),
    pet_id          BIGINT,
    PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
CREATE TABLE pets
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    birth_name  DATE,
    chip_number VARCHAR(255),
    coat_color  VARCHAR(255),
    created_at  DATETIME,
    dead        BIT,
    death_DATE  DATE,
    gender      VARCHAR(255),
    avatar_url  VARCHAR(255),
    pet_name    VARCHAR(255),
    race        VARCHAR(255),
    type        VARCHAR(255),
    owner_id    BIGINT,
    pedigree_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
CREATE TABLE treatments
(
    id                   BIGINT NOT NULL AUTO_INCREMENT,
    description          VARCHAR(255),
    treatment_end_DATE   DATE,
    treatment_start_DATE DATE,
    treatment_type       VARCHAR(255),
    pet_id               BIGINT,
    PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
ALTER TABLE contacts
    ADD FOREIGN KEY (user_id) REFERENCES app_users (id);
ALTER TABLE medicines
    ADD FOREIGN KEY (pet_id) REFERENCES pets (id);
ALTER TABLE nutrition_details
    ADD FOREIGN KEY (pet_id) REFERENCES pets (id);
ALTER TABLE pedigrees
    ADD FOREIGN KEY (pet_id) REFERENCES pets (id);
ALTER TABLE pets
    ADD FOREIGN KEY (owner_id) REFERENCES app_users (id);
ALTER TABLE pets
    ADD FOREIGN KEY (pedigree_id) REFERENCES pedigrees (id);
ALTER TABLE treatments
    ADD FOREIGN KEY (pet_id) REFERENCES pets (id);


# INSERT INTO app_users
# VALUES (1, LOCALTIMESTAMP, 'John', 'johndoe@email.com', 'passworD123'),
#        (2, LOCALTIMESTAMP, 'Paul', 'paul.we@email.com', 'passworD123'),
#        (3, LOCALTIMESTAMP, 'Romain', 'romainthecat@email.com', 'passworD123');
#
# INSERT INTO pets
# VALUES (1, LOCALTIMESTAMP, NULL, NULL, 'white', FALSE, NULL, 'FEMALE', NULL, 'Maple', 'Maine Coon', 'CAT', 1, NULL);
