-- Titre :             Création base cabinet recrutement version élèves.sql
-- Version :           1.0
-- Date création :     28 juin 2011
-- Date modification : 30 août 2011
-- Auteur :            Philippe Tanguy
-- Description :       Script de création de la base de données pour le SI "gestion de cabinet de
--                     recrutement"
--                     Note : script pour PostgreSQL 8.4
--                     Ebauche du script : ne contient pour le moment que la table "entreprise".

-- +----------------------------------------------------------------------------------------------+
-- | Suppression des tables                                                                       |
-- +----------------------------------------------------------------------------------------------+

drop table if exists "entreprise";

-- +----------------------------------------------------------------------------------------------+
-- | Création des tables                                                                          |
-- +----------------------------------------------------------------------------------------------+

create table "entreprise"
(
  id              serial primary key,
  nom             varchar(50) not null,
  descriptif      text,
  adresse_postale varchar(30) -- Pour simplifier, adresse_postale = ville.
);

-- +----------------------------------------------------------------------------------------------+
-- | Insertion de quelques données de pour les tests                                              |
-- +----------------------------------------------------------------------------------------------+

-- Insertion des secteurs d'activité

insert into "entreprise" values (nextval('entreprise_id_seq'),'Télécom Bretagne','Télécom Bretagne est une grande école pionnière en formation, en recherche et en entrepreneuriat.','Plouzané');
insert into "entreprise" values (nextval('entreprise_id_seq'),'ENIB','Une école d''ingénieur juste à côté...','Plouzané');
