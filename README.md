# MON ÉCOLE

## Plateforme scolaire officielle

> Un enfant qu'on enseigne est un homme qu'on gagne.

Mon École est une plateforme scolaire destinée à connecter :

- établissements
- directions
- services de scolarité
- enseignants
- parents
- élèves

## Applications

### Mon École
Application destinée aux parents.

### Mon École Admin
Application destinée à l'administration.

## Fonctionnalités

- gestion des élèves
- classes
- enseignants
- parents
- années scolaires
- résultats
- moyennes
- absences
- retards
- conduite
- devoirs
- emplois du temps
- documents
- notifications
- communication
- appareils autorisés
- historique
- permissions
- IA

## Architecture

Android + API Node.js + PostgreSQL.

Les APK ne contiennent aucune base de données scolaire.

## Données

La production démarre avec une base vide.

Aucune donnée fictive n'est utilisée comme donnée officielle.

## IA

L'intelligence artificielle assiste les administrateurs :

photo/texte
→ extraction
→ proposition
→ vérification
→ validation humaine
→ enregistrement.

## Production

Les secrets de production doivent être configurés dans l'environnement serveur.

Ne jamais placer :

- mot de passe
- JWT secret
- clé API
- clé privée
- données personnelles d'élèves

dans le dépôt.
