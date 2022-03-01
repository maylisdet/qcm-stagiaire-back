# ai13qcm

Projet AI13 de QCM des stagiaires

## Membres du projet

- MELIN Alexandre
- DE TALHOUËT Maylis
- RADJAVELOU Aroun
- BOTTY Clément
- TERRADE Nathan

## Installation de mysql via docker

### Prérequis

- Installer docker : <https://www.docker.com/get-started>

### Commande d'installation mysql et configuration pour le projet

L'ensemble des commandes sont à entrer au sein de votre terminal, pour windows, vous pourrez utiliser votre wsl ou bien le powershell, pour Mac et linux votre terminal suffira amplement.

- Créer l'image docker
  
```bash
docker run --detach --name=ai13qcm -p 8889:3306  --env="MYSQL_ROOT_PASSWORD=root" mysql
```

Si vous êtes sur un Mac M1 : 
```bash
docker run --detach --name=ai13qcm -p 8889:3306  --env="MYSQL_ROOT_PASSWORD=root" --platform linux/x86_64 mysql
```

- Se connecter au cli de l'image docker

```bash
docker exec -it ai13qcm bash
```

- Se connecter en tant que root pour ajouter un nouvel utilisateur

```bash
mysql -u root -proot
```

- Créer le nouvel utilisateur et lui donner les privilèges au sein de mysql puis créer la database
  
```mysql
CREATE USER 'ai13qcm' IDENTIFIED BY 'ai13qcm';
GRANT ALL PRIVILEGES ON * . * TO 'ai13qcm';
FLUSH PRIVILEGES;
CREATE DATABASE ai13qcm;
```

- Pour les prochaines connexion, vous pourrez utiliser directement le nouvel utilisateur:
  
```bash
mysql -u ai13qcm -pai13qcm
```

- Il faut faire la commande suivante pour préciser la database que l'on veut utiliser

```mysql
USE ai13qcm;
```
