#  TP 20 - Application Number Book avec Android, Contacts et API distante via Retrofit

##  Fonctionnalités Principales

- **Demande de permissions** : Gère l'autorisation d'accéder aux contacts (READ_CONTACTS).
- **Lecture des contacts locaux** : Récupère les noms et numéros stockés dans le téléphone via le `ContentResolver`.
- **Interface Esthétique (UI)** :
  - Thème personnalisé Premium (Tons Violets/Roses).
  - Listes modernes et esthétiques avec `RecyclerView` et `MaterialCardView`.
- **Synchronisation Cloud** : Envoie les données vers une base distante via une API REST.
- **Recherche Distante** : Permet d'interroger la base de données MySQL distante par nom ou numéro et de mettre à jour l'affichage en temps réel.

##  Technologies Utilisées

### Application Android (`IbtissamBook/`)
- **Langage** : Java
- **UI** : XML, Material Components, ConstraintLayout
- **Réseau** : Retrofit2 (Appels HTTP) & Gson (Sérialisation JSON)

### Backend (`ibtissam-api/`)
- **Serveur** : PHP (Architecture structurée MVC)
- **Base de données** : MySQL (`PDO` & requêtes préparées pour la sécurité)

##  Installation & Lancement

### 1. Configuration du Serveur (Backend)
1. Installez un serveur local comme **XAMPP** ou **WAMP**.
2. Allez dans phpMyAdmin et importez le fichier `ibtissam-api/database.sql` pour créer la base `ibtissam_db`.
3. Déplacez le dossier `ibtissam-api` dans votre répertoire web (ex: `C:\xampp\htdocs\`).

### 2. Configuration de l'Application Android
1. Ouvrez **Android Studio**.
2. Cliquez sur **File > Open** et sélectionnez le dossier `IbtissamBook`.
3. Laissez Gradle synchroniser le projet.
4. **Important** : Si vous testez avec un téléphone physique, ouvrez `IbtissamRetrofitClient.java` et remplacez `10.0.2.2` par l'adresse IP de votre PC (ex: `192.168.1.XX`). Si vous utilisez l'émulateur, laissez `10.0.2.2`.
5. Lancez l'application (Run) ! 

##  Aperçu de l'Interface
L'application propose des boutons dégradés aux bords ronds, un champ de recherche stylisé et des cartes d'informations pour chaque contact avec des couleurs douces et modernes (teintes `ibtissam_primary` et `ibtissam_accent`).

---
*Projet réalisé par Ibtissam EL BEKKALI.*
