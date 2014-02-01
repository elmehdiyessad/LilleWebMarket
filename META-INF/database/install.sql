-------------------------------------------------
---                                           ---
---              LilleWebMarket               ---
---                                           ---
-------------------------------------------------
--- Script de création de la base
---------------------------------
--- Auteur : Alexandre Demode
--- Date de création : 15 janvier 2014
-------------------------------------------------



-------------------------------------------------
--- Purge la base de données
-------------------------------------------------
DROP TABLE lwm_variations_market;
DROP TABLE lwm_user_stock;
DROP TABLE lwm_market;
DROP TABLE lwm_user_infos;
DROP TABLE lwm_user;
DROP TYPE  ETAT;



-------------------------------------------------
--- Crée la table des utilisateurs
-------------------------------------------------
CREATE TABLE lwm_user (
    login       TEXT        PRIMARY KEY,
    password    TEXT        NOT NULL,
    first_name  TEXT        NOT NULL,
    last_name   TEXT        NOT NULL
);



-------------------------------------------------
--- Crée la table des informations sur les utilisateurs
-------------------------------------------------
CREATE TABLE lwm_user_infos (
    login       TEXT        PRIMARY KEY
                            REFERENCES  lwm_user(login) ON DELETE CASCADE  ON UPDATE CASCADE,
    role        TEXT        NOT NULL,
    cash        INTEGER     NOT NULL    DEFAULT 10000   CHECK (cash > 0)
);



-------------------------------------------------
--- Crée la table des marchés
-------------------------------------------------
CREATE TABLE lwm_market (
    id          SERIAL      PRIMARY KEY,
    title       TEXT        NOT NULL,
    title_rev   TEXT        NOT NULL,
    term        TIMESTAMP   NOT NULL
);



-------------------------------------------------
--- Crée la table des achats en cours des utilisateurs
-------------------------------------------------
CREATE TYPE ETAT AS ENUM ('BUY', 'SELL');
CREATE TABLE lwm_user_stock (
    login       TEXT        REFERENCES  lwm_user(login) ON DELETE CASCADE  ON UPDATE CASCADE,
    market_id   INTEGER     REFERENCES  lwm_market(id)  ON DELETE CASCADE  ON UPDATE CASCADE,
    nb_stock    INTEGER     NOT NULL    CHECK (nb_stock > 0),
    nb_sold     INTEGER     NOT NULL    CHECK (nb_sold >= 0),
    price       INTEGER     NOT NULL    CHECK (price > 0),
    assertion   BOOLEAN     NOT NULL    DEFAULT TRUE,
    date_buy    TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,
    buy_or_sell ETAT        NOT NULL,

    PRIMARY KEY (login, id_market, date_buy)
);



-------------------------------------------------
--- Crée la table des variations d'un marché
-------------------------------------------------
CREATE TABLE lwm_variations_market (
    market_id   INTEGER     REFERENCES  lwm_market(id)   ON DELETE CASCADE  ON UPDATE CASCADE,
    instant     TIMESTAMP   NOT NULL,
    value       INTEGER     NOT NULL,

    PRIMARY KEY (id_market, instant)
);