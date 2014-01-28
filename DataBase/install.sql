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
DROP TABLE lwm_variations_marche;
DROP TABLE lwm_utilisateur_titre;
DROP TABLE lwm_utilisateur_achat;
DROP TABLE lwm_marche;
DROP TABLE lwm_utilisateur_infos;
DROP TABLE lwm_utilisateur;



-------------------------------------------------
--- Crée la table des utilisateurs
-------------------------------------------------
CREATE TABLE lwm_utilisateur (
    login       VARCHAR(30) PRIMARY KEY,
    mdp         VARCHAR(32) NOT NULL,
    nom         VARCHAR(75) NOT NULL,
    prenom      VARCHAR(75) NOT NULL
);



-------------------------------------------------
--- Crée la table des informations sur les utilisateurs
-------------------------------------------------
CREATE TABLE lwm_utilisateur_infos (
    login       VARCHAR(30) PRIMARY KEY
                            REFERENCES  lwm_utilisateur(login)   ON DELETE CASCADE   ON UPDATE CASCADE,
    role        VARCHAR(30) NOT NULL,
    cash        INTEGER     NOT NULL    DEFAULT 10000
);



-------------------------------------------------
--- Crée la table des marchés
-------------------------------------------------
CREATE TABLE lwm_marche (
    id          SERIAL      PRIMARY KEY,
    titre       VARCHAR(255)NOT NULL,
    titre_inv   VARCHAR(255)NOT NULL,
    echeance    TIMESTAMP   NOT NULL
);



-------------------------------------------------
--- Crée la table des achats en cours des utilisateurs
-------------------------------------------------
CREATE TABLE lwm_utilisateur_achat (
    login       VARCHAR(30) REFERENCES  lwm_utilisateur(login)   ON DELETE CASCADE   ON UPDATE CASCADE,
    id_marche   INTEGER     REFERENCES  lwm_marche(id)           ON DELETE CASCADE   ON UPDATE CASCADE,
    nb_titres   INTEGER     NOT NULL    CHECK (nb_titres > 0),
    prix        INTEGER     NOT NULL,
    affirmation BOOLEAN     NOT NULL    DEFAULT TRUE,
    dateachat   TIMESTAMP   NOT NULL    DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (login, id_marche, dateachat)
);



-------------------------------------------------
--- Crée la table des titres des utilisateurs
-------------------------------------------------
CREATE TABLE lwm_utilisateur_titre (
    id          SERIAL      PRIMARY KEY,
    login       VARCHAR(30) REFERENCES  lwm_utilisateur(login)   ON DELETE CASCADE   ON UPDATE CASCADE,
    id_marche   INTEGER     REFERENCES  lwm_marche(id)           ON DELETE CASCADE   ON UPDATE CASCADE,
    nb_titres   INTEGER     NOT NULL    CHECK (nb_titres > 0),
    prix        INTEGER     NOT NULL
);



-------------------------------------------------
--- Crée la table des variations d'un marché
-------------------------------------------------
CREATE TABLE lwm_variations_marche (
    id_marche   INTEGER     REFERENCES  lwm_marche(id)           ON DELETE CASCADE   ON UPDATE CASCADE,
    instant     TIMESTAMP   NOT NULL,
    valeur      INTEGER     NOT NULL,

    PRIMARY KEY (id_marche, instant)
);