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
--- Crée de variations pour le marché n°1
-------------------------------------------------
INSERT INTO lwm_variations_market (market_id, price, instant)
    SELECT 1, TRUNC(RANDOM() * 98 + 1), (NOW() - TRUNC(RANDOM() * 24) * '1 hour'::INTERVAL)
        FROM generate_series(1, 150);