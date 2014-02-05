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
    SELECT 1, TRUNC(RANDOM() * 98 + 1), (NOW() - TRUNC(RANDOM() * 24) * '1 hour'::INTERVAL), TRUNC(RANDOM() * 98 + 1)
        FROM generate_series(1, 150);

-------------------------------------------------
--- Forte hausse de variation pour le marché n°1
-------------------------------------------------
INSERT INTO lwm_variations_market (market_id, price, instant, quantity)
    SELECT 1, 99, (NOW() - INTERVAL '1 hour'), 1000
        FROM generate_series(1, 50);