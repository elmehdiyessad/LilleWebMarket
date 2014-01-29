-------------------------------------------------
---                                           ---
---              LilleWebMarket               ---
---                                           ---
-------------------------------------------------
--- Données de tests - lwm_utilisateurs
---------------------------------------
--- Auteur : Alexandre Demode
--- Date de création : 15 janvier 2014
-------------------------------------------------





-------------------------------------------------
--- Crée des utilisateurs de test
-------------------------------------------------
INSERT INTO lwm_utilisateur (login, mdp, nom, prenom)
	VALUES ('demodea', MD5('moi'), 'Demode', 'Alexandre');

INSERT INTO lwm_utilisateur (login, mdp, nom, prenom)
	VALUES ('test', MD5('test'), 'Test', 'Test');

INSERT INTO lwm_utilisateur (login, mdp, nom, prenom)
	VALUES ('titi', MD5('titi'), 'Titi', 'Titi');

INSERT INTO lwm_utilisateur (login, mdp, nom, prenom)
	VALUES ('admin', MD5('admin'), 'Admin', 'Admin');