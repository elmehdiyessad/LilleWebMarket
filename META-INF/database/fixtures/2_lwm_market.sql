-------------------------------------------------
---                                           ---
---              LilleWebMarket               ---
---                                           ---
-------------------------------------------------
--- Données de tests - lwm_market
---------------------------------------
--- Auteur : Alexandre Demode
--- Date de création : 31 janvier 2014
-------------------------------------------------





-------------------------------------------------
--- Crée des marchés de test
-------------------------------------------------
INSERT INTO lwm_market (id, title, title_rev, term)
    VALUES (
        1,
        'M. Beaufils sera à l''heure à la remise des diplômes',
        'M. Beaufils ne sera pas à l''heure pour la remise des diplômes',
        '2014-12-31'
    );

INSERT INTO lwm_market (id, title, title_rev, term)
    VALUES (
        2,
        'Apple va sortir un iPhone 6 cette année',
        'Apple ne fera pas un iPhone 6 cette année',
        '2014-12-31'
    );

INSERT INTO lwm_market (id, title, title_rev, term)
    VALUES (
        3,
        'Microsoft va sortir une Xbox One pour jouer uniquement aux jeux dématérialisés',
        'Microsoft ne sortira pas de Xbox One pour jouer uniquement aux jeux dématérialisés',
        '2014-12-31'
    );

INSERT INTO lwm_market (id, title, title_rev, term)
    VALUES (
        4,
        'Ce marché sera réellement mis en production avec le LDAP de l''université',
        'Ce marché ne sera pas mis en production avec le LDAP de l''université',
        '2014-12-31'
    );