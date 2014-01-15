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
--- Crée la table des utilisateurs
-------------------------------------------------
CREATE TABLE lwm_utilisateurs (
	login		VARCHAR(30) PRIMARY KEY,
	mdp			VARCHAR(30) NOT NULL,
	nom			VARCHAR(75) NOT NULL,
	prenom		VARCHAR(75) NOT NULL,
);



-------------------------------------------------
--- Crée la table des informations sur les utilisateurs
-------------------------------------------------
CREATE TABLE lwm_infos_utilisateur (
	login		VARCHAR(30) PRIMARY KEY,
	role		VARCHAR(30) NOT NULL,
	cash		INTEGER 	NOT NULL	DEFAULT 10000,

	FOREIGN KEY login
		REFERENCES lwm_utilisateurs(login)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);



-------------------------------------------------
--- Crée la table des achats des utilisateurs
-------------------------------------------------
CREATE TABLE lwm_achats_utilisateur (
	login		INTEGER NOT NULL,
	id_marche	INTEGER NOT NULL,
	nb_titres	INTEGER NOT NULL CHECK (nb_titres > 0),
	prix		INTEGER NOT NULL,

	PRIMARY KEY (login, id_marche, prix),

	FOREIGN KEY login
		REFERENCES lwm_utilisateurs(login)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);



-------------------------------------------------
--- Crée la table des marchés
-------------------------------------------------
CREATE TABLE lwm_marche (
	id			INTEGER			AUTO_INCREMENT PRIMARY KEY,
	id_inverse	INTEGER,
	titre		VARCHAR(255)	NOT NULL,
	echeance	TIMESPTAMP		NOT NULL,

	FOREIGN KEY id_inverse
		REFERENCES lwm_marche(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);