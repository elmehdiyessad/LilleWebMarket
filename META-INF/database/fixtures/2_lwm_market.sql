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
INSERT INTO lwm_market (title, title_rev, term, maker)
    VALUES (
        'M. Beaufils sera à l''heure à la remise des diplômes',
        'M. Beaufils ne sera pas à l''heure pour la remise des diplômes',
        '2014-02-06',
        'admin'
    );

INSERT INTO lwm_market (title, title_rev, term, maker)
    VALUES (
        'Apple va sortir un iPhone 6 cette année',
        'Apple ne fera pas un iPhone 6 cette année',
        '2014-12-31',
        'demodea'
    );

INSERT INTO lwm_market (title, title_rev, term, maker)
    VALUES (
        'Microsoft va sortir une Xbox One pour jouer uniquement aux jeux dématérialisés',
        'Microsoft ne sortira pas de Xbox One pour jouer uniquement aux jeux dématérialisés',
        '2014-12-31',
        'admin'
    );

INSERT INTO lwm_market (title, title_rev, term, maker)
    VALUES (
        'Ce marché sera réellement mis en production avec le LDAP de l''université',
        'Ce marché ne sera pas mis en production avec le LDAP de l''université',
        '2014-03-31',
        'admin'
    );