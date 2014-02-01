-------------------------------------------------
---                                           ---
---              LilleWebMarket               ---
---                                           ---
-------------------------------------------------
--- Données de tests - lwm_user_stock
---------------------------------------
--- Auteur : Alexandre Demode
--- Date de création : 15 janvier 2014
-------------------------------------------------





-------------------------------------------------
--- Crée des titres de test
-------------------------------------------------
INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('demodea', 1, 7, 0, 5, TRUE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('test', 1, 75, 23, 43, TRUE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('titi', 1, 75, 23, 43, FALSE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('admin', 1, 75, 0, 43, FALSE, NOW(), 'BUY');



INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('demodea', 2, 7, 0, 5, TRUE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('test', 2, 75, 23, 43, TRUE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('titi', 2, 75, 23, 43, TRUE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('admin', 2, 75, 0, 43, TRUE, NOW(), 'BUY');



INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('demodea', 3, 7, 0, 5, FALSE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('test', 3, 75, 23, 43, FALSE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('titi', 3, 75, 23, 43, FALSE, NOW(), 'BUY');

INSERT INTO lwm_user_stock (login, market_id, nb_stock, nb_sold, price, assertion, date_buy, buy_or_sell)
    VALUES ('admin', 3, 75, 0, 43, FALSE, NOW(), 'BUY');