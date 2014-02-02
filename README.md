LilleWebMarket
==============

Site web de bourse d'informations



Installation
------------

> Note : les commandes sont à lancer depuis la racine du contexte LilleWebMarket (tomcat/webapps/LilleWebMarket/)

### Compilation des classes

```
./compile
```

### Installation de la base de données

- Configurer l'accès à la BDD dans ```META-INF/context.xml```
- Executer le script ```META-INF/database/install.sql```
- Executez, au besoin les différentes fixtures ```META-INF/database/fixtures/*.sql```

Exemple pour tout installer en une seule commande :

```
cat META-INF/database/install.sql META-INF/database/fixtures/*.sql | psql -f -
```