# Application de Gestion de Concours / Admissions

## Description:

Cette application desktop développée en Java Swing & JDBC permet de gérer l’ensemble du processus d’admission aux concours.
Elle propose deux types d’utilisateurs : Administrateur et Candidat, avec des fonctionnalités adaptées à chaque rôle.

### L’objectif principal est de faciliter :

-la gestion des candidats et des concours,

-la saisie et la génération automatique des décisions,

-la consultation des résultats,

-l’analyse statistique des admissions.

### Types d’utilisateurs:

#### Candidat:

Le candidat crée un compte sur la plateforme.

Après connexion, il accède à la liste des concours disponibles.

Il choisit un concours via une ComboBox et s’y inscrit.

Il est ensuite redirigé vers la fenêtre Consultation des résultats (lecture seule).

Le candidat ne peut effectuer aucune modification.

#### Administrateur:

Après authentification, l’administrateur accède à trois modules principaux :

##### 1️ Gestion des Candidats:

###### Fonctionnalités :

- Ajouter un candidat

- Modifier un candidat

- Supprimer un candidat

- Filtrer par ville

#### Pourquoi l’admin peut ajouter des candidats ?
Cette option permet d’intégrer :

des candidats inscrits hors plateforme (inscriptions papier ou import),

des corrections ou régularisations administratives,

la création de comptes tests ou internes.

#### 2️ Gestion des Concours:

##### Fonctionnalités :

- Créer un concours

- Modifier un concours

-Supprimer un concours

- Vider les champs

- Filtrer par filière

#### 3️ Gestion des Résultats:

##### Fonctionnalités :

Saisie des notes finales

Génération automatique de la décision :

- Admis

- Liste d’attente

- Rejeté

* Filtrage par décision

* Recherche (nom, note, etc.)

#### Statistiques (diagramme circulaire):

L’application génère un diagramme circulaire affichant :

-le pourcentage d’admis

-le pourcentage en attente

-le pourcentage de rejetés

### Fonctionnalités principales:

✔ CRUD complet (Candidat, Concours, Résultat)

✔ Authentification sécurisée avec hashage des mots de passe

✔ Récupération du mot de passe via JavaMail

✔ Filtrage et recherche dynamiques

✔ Génération automatique des décisions

✔ Statistiques avec graphique

✔ Empêche l’inscription multiple au même concours

### Sécurité & Authentification:
-Mots de passe stockés avec hashage (SHA-256)

-Vérification lors du login

-Aucun stockage en clair

 -Fonction “Mot de passe oublié”

-L’application intègre JavaMail API :

-L’utilisateur clique sur Mot de passe oublié

-Il saisit son adresse email

-Le mot de passe est envoyé automatiquement par email

-L’utilisateur peut se reconnecter et le modifier

##### Cette fonctionnalité améliore :

-l’autonomie des utilisateurs

-la sécurité du système

-l’expérience utilisateur


### Architecture du projet:

Voici l'architecture de mon projet, visible directement dans NetBeans. J'ai organisé mon code en couches : les entités dans model, l'accès aux données dans dao avec leurs tests, l'interface utilisateur dans ui, et les outils transversaux dans util.


<img width="588" height="702" alt="image" src="https://github.com/user-attachments/assets/16f431c5-d423-4613-937b-2e86cf4a4a08" />




### Démonstration de l’application de gestion des concours et admissions:


Cette vidéo présente le fonctionnement de l’application desktop développée en Java Swing & JDBC pour la gestion des concours. 
Elle montre les différents rôles (Admin et Candidat), le processus d’inscription, la gestion des candidats et des concours, la saisie des notes,
la génération automatique des décisions ainsi que l’affichage des statistiques.



https://github.com/user-attachments/assets/cf292b0f-645f-47ef-b097-f7b47bea328a



### Diagramme de classes:

Le diagramme de classes décrit la structure du système de gestion des concours en présentant les principales entités et leurs relations.
La classe Utilisateur est la super-classe qui regroupe les informations communes et est spécialisée en Admin et Candidat afin de différencier les rôles. 
L’admin possède les fonctionnalités de gestion des candidats, concours et résultats, tandis que le candidat peut s’inscrire à un concours et consulter ses résultats.
Les classes Concours et Resultat représentent respectivement les concours organisés et les performances des candidats. La classe Resultat relie un candidat à un concours,
montrant qu’un candidat peut participer à plusieurs concours et qu’un concours peut contenir plusieurs résultats.


![WhatsApp Image 2026-02-28 at 15 03 58](https://github.com/user-attachments/assets/bd23cf6b-6558-4336-8581-00fa691c0a8a)


### Diagramme de cas d’utilisation:

Le diagramme de cas d’utilisation présente les interactions entre les acteurs et le système. Il met en évidence deux acteurs principaux :
l’Administrateur, qui gère les candidats, les concours et les résultats, et le Candidat, qui peut s’inscrire à un concours et consulter ses résultats.
Le cas d’utilisation central est S’authentifier, car toutes les fonctionnalités nécessitent une connexion préalable. Le cas Générer décision fait partie du
processus de gestion des résultats. Ce diagramme montre ainsi une séparation claire des rôles et un fonctionnement cohérent du système.


![WhatsApp Image 2026-02-28 at 15 32 44](https://github.com/user-attachments/assets/2bf60561-a0f7-4bcd-a84a-fb4f05349688)



Conclusion

À travers ce projet, j’ai pu concevoir et développer une application complète de gestion des concours et admissions en utilisant Java Swing et JDBC.
Ce travail m’a permis de mettre en pratique les concepts de la programmation orientée objet, la modélisation UML et l’architecture en couches, 
tout en intégrant des fonctionnalités réelles comme l’authentification sécurisée, la génération automatique des décisions et les statistiques.

Ce projet m’a également aidé à mieux comprendre l’importance de l’organisation du code, de l’ergonomie de l’interface et de la sécurité dans le développement 
d’une application. Il représente pour moi une expérience enrichissante et constitue une base solide que je pourrais améliorer à l’avenir en ajoutant de nouvelles
fonctionnalités.



