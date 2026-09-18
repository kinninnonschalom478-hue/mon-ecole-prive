# MON ÉCOLE — 3 APK séparés

- `apps/parent` : application Parents
- `apps/teacher` : application Enseignant
- `apps/admin` : application Administration

Les trois applications utilisent le serveur MON ÉCOLE et ne contiennent aucun secret PostgreSQL/JWT.
Le serveur reste l'autorité pour l'authentification, les rôles, l'isolation école et les permissions.

## Serveur local / émulateur
URL par défaut : `http://10.0.2.2:3000`.
Pour un téléphone réel, remplacer par l'URL HTTPS du serveur.

## Compilation
`./BUILD-ANDROID-3.sh`
