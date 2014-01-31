-------------------------------------------------
---                                           ---
---              LilleWebMarket               ---
---                                           ---
-------------------------------------------------
--- Données de tests - lwm_user
---------------------------------------
--- Auteur : Alexandre Demode
--- Date de création : 15 janvier 2014
-------------------------------------------------





-------------------------------------------------
--- Crée des utilisateurs de test
-------------------------------------------------
INSERT INTO lwm_user (login, password, last_name, first_name)
	VALUES ('demodea', MD5('moi'), 'Demode', 'Alexandre');

INSERT INTO lwm_user (login, password, last_name, first_name)
	VALUES ('test', MD5('test'), 'Test', 'Test');

INSERT INTO lwm_user (login, password, last_name, first_name)
	VALUES ('titi', MD5('titi'), 'Titi', 'Titi');

INSERT INTO lwm_user (login, password, last_name, first_name)
	VALUES ('admin', MD5('admin'), 'Admin', 'Admin');