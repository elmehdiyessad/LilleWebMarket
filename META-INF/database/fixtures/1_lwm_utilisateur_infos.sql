-------------------------------------------------
---                                           ---
---              LilleWebMarket               ---
---                                           ---
-------------------------------------------------
--- Données de tests - lwm_user_infos
--------------------------------------------
--- Auteur : Alexandre Demode
--- Date de création : 15 janvier 2014
-------------------------------------------------





-------------------------------------------------
--- Crée les infos des utilisateurs de test
-------------------------------------------------
INSERT INTO lwm_user_infos (login, role, cash)
	VALUES ('demodea', 'user', 20000);

INSERT INTO lwm_user_infos (login, role, cash)
	VALUES ('test', 'user', 7568);

INSERT INTO lwm_user_infos (login, role, cash)
	VALUES ('toto', 'maker', 9965);

INSERT INTO lwm_user_infos (login, role, cash)
	VALUES ('admin', 'admin', 200000);