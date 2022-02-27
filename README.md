# Yuka - Léo DELABRE - Nicolas MURILLON

Ce readme a pour but de présenter les points que nous jugeons important de présenter pour ce projet (choix et justifications)

## Architecture 

Le SDK java que nous avons utilisé pour notre application est ***corretto-11***.

### Source

Notre projet se décompose en plusieurs parties :

- Controlleurs
- Services
- Repositories
- Models
- Resources

La majorité de la logique métier se trouve dans la partie service (calcul du nutriscore, appel à l'api externe...). Le dossier Ressources contient le fichier `data.sql` pour les règles concernant le calcul du nutriscore, et également le fichier `application.properties` contenant un ensemble de variables utiles pour le contexte de l'application spring (exemple : lien de l'api externe).

Le dossier Repositories contient les repositories (avec les requêtes sql) permettant d'accéder à notre base de données (cf fichier `data.sql`)

### Test

Tous nos composants ont été testés grâce aux annotations springboot (utilisation de Mockito, webmvc...)

## Calcul du nutriscore

Nous avons fait le choix de calculer le nutriscore grâce aux règles fournies dans le fichier `data.sql`. Le calcul est effectué dans le service `NutriScoreService` où l'on calcule le score de chaque composante (score positif pour les composantes négatives, et négatif pour les composantes positives). Dans l'éventualité où une nouvelle composante serait à prendre en compte dans le calcul du nutriScore, seulement 4 fichiers devraient être modifiés :

 - ProductRestult (ajout d'un nouvel attribut pour stocker la valeur pour 100g du nutriment)
 - data.sql (rajout de la règle correspondant à la composante)
 - NutriScoreService : ajout du nom du nutriment à la liste suivante

```java
/* List of all nutrients that are taken into account for nutriscore calculation */
nutrientsList = new ArrayList<>(Arrays.asList(
                "energy_100g",
                "saturated-fat_100g",
                "sugars_100g",
                "salt_100g",
                "fiber_100g",
                "proteins_100g"
        ));
```

et ajout du getter correspondant à cette liste :

```java
/* List the values of all nutrients that are taken into account for nutriscore calculation */
ArrayList<Double> nutrientsValues = new ArrayList<>(Arrays.asList(
        product.getEnergy_100g(),
        product.getSaturatedFat_100g(),
        product.getSugars_100g(),
        product.getSalt_100g(),
        product.getFiber_100g(),
        product.getProteins_100g()
));
```

## Panier

Pour le panier, il n'y a qu'une seule route de disponible. L'utilisateur envoie alors les informations suivantes par requête POST: 

```json
{
	"email": "test@test.fr",
	"products": ["7622210449283","7622210449283", "737628064502"]
}
```

- `email` : email de l'utilisateur (utile pour une éventuelle amélioration de notre api, avec rajout d'une authentification par exemple)
- `products` : La liste des codes bar des produits contenus dans le panier (le code bar apparaît autant de fois que le produit est commandé)

Pour améliorer ce système, on pourrait rajouter un système de cache, pour éviter de calculer plusieurs fois les mêmes informations pour un produit donné.
