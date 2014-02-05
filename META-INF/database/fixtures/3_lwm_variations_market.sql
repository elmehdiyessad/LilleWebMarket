-------------------------------------------------
---                                           ---
---              LilleWebMarket               ---
---                                           ---
-------------------------------------------------
--- Données de tests - lwm_variations_market
--------------------------------------------
--- Auteur : Alexandre Demode
--- Date de création : 15 janvier 2014
-------------------------------------------------





-------------------------------------------------
--- Crée des variations pour le marché n°1
-------------------------------------------------
INSERT INTO lwm_variations_market (market_id, price, instant, quantity)
    SELECT 1, TRUNC(RANDOM() * 98 + 1), (NOW() - TRUNC(RANDOM() * 72) * '1 hour'::INTERVAL), TRUNC(RANDOM() * 98 + 1)
        FROM generate_series(1, 200);


INSERT INTO lwm_variations_market (market_id, price, instant, quantity)
    SELECT 2, TRUNC(RANDOM() * 98 + 1), (NOW() - TRUNC(RANDOM() * 72) * '1 hour'::INTERVAL), TRUNC(RANDOM() * 98 + 1)
        FROM generate_series(1, 200);

INSERT INTO lwm_variations_market (market_id, price, instant, quantity)
    SELECT 3, TRUNC(RANDOM() * 98 + 1), (NOW() - TRUNC(RANDOM() * 72) * '1 hour'::INTERVAL), TRUNC(RANDOM() * 98 + 1)
        FROM generate_series(1, 200);

INSERT INTO lwm_variations_market (market_id, price, instant, quantity)
    SELECT 4, TRUNC(RANDOM() * 98 + 1), (NOW() - TRUNC(RANDOM() * 48 - 24) * '1 hour'::INTERVAL), TRUNC(RANDOM() * 98 + 1)
        FROM generate_series(1, 100);
INSERT INTO lwm_variations_market (market_id, price, instant, quantity)
    SELECT 4, TRUNC(RANDOM() * 50 + 1), (NOW() - TRUNC(RANDOM() * 24) * '1 hour'::INTERVAL), TRUNC(RANDOM() * 98 + 1)
        FROM generate_series(1, 100);


-------------------------------------------------
--- Forte hausse de variation pour le marché n°1
-------------------------------------------------
INSERT INTO lwm_variations_market (market_id, price, instant, quantity)
    SELECT 1, 99, (NOW() - INTERVAL '1 hour'), 1000
        FROM generate_series(1, 50);


-------------------------------------------------
--- Forte baisse de variation pour le marché n°2
-------------------------------------------------
INSERT INTO lwm_variations_market (market_id, price, instant, quantity)
    SELECT 2, 1, (NOW() - INTERVAL '3 hour'), 1000
        FROM generate_series(1, 50);