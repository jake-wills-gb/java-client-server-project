# Projet de synthèse en SI
### _ROBI et Client-Serveur ROBI_

Ce projet de synthèse a été développé et rédigé par les étudiants suivants :

- Benjamin Callac
- Sasha Coulm
- James Pittman
- Jake Wills


### Partie ROBI

Pour la partie ROBI, les exercices 1 à 6 ont été développés et rendus.
Tous les exercices sont fonctionnels, cependant certains présentent des perspectives d'améliorations

Les explications nécessaires sont indiquées en commentaire de code.


### Partie Client-Serveur ROBI

Pour la Partie Client-Serveur ROBI, les parties 1 et 2 ont été développées et fonctionnent.
En ce qui concerne les options, nous n'avons développé que celle permettant au client d’interrompre un programme ROBI.

##### Description du client:
- On a une interface contenant un bouton Start, un bouton Stop, une zone de saisie de texte à gauche, et une zone d’affichage de texte à droite.
- Le bouton Start permet d’envoyer au serveur le script contenu dans la zone de saisie de texte à gauche.
- Le bouton Stop permet d’interrompre le programme ROBI en cours.
- La zone de saisie de texte à gauche sert à saisir le script.
- La zone d’affichage du texte à droite permet l’affichage du log.

##### Description du serveur:
- La boucle principale du serveur se déroule de la manière suivante:
- Le serveur ouvre une ServerSocket et se met en attente d’un client
- Lorsque le serveur reçoit le programme d’un client, il lance deux thread. L’une exécute le le programme Robi et l’autre se met en attente de clients.
- Lorsque la thread d’exécution est terminée, on met également fin à la thread d'accueil en fermant le ServerSocket.

