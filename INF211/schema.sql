-- INF210 / Version 1.1
-- Yann Feunteun, Maxime Mouchet
--
-- 1.0 : Version initiale
-- 1.1 : Correction des donnees de test

--
-- Creation des tables
--

DROP TABLE IF EXISTS entreprise CASCADE;
CREATE TABLE entreprise (
  id              serial       PRIMARY KEY,
  nom             varchar(255) NOT NULL,
  descriptif      text         NOT NULL,
  adresse_postale text         NOT NULL
);

DROP TABLE IF EXISTS niveau_qualification CASCADE;
CREATE TABLE niveau_qualification (
  id        serial       PRIMARY KEY,
  intitule  varchar(255) NOT NULL
);

DROP TABLE IF EXISTS secteur_activite CASCADE;
CREATE TABLE secteur_activite (
  id       serial       PRIMARY KEY,
  intitule varchar(255) NOT NULL
);

DROP TABLE IF EXISTS offre_emploi CASCADE;
CREATE TABLE offre_emploi (
  id                   serial       PRIMARY KEY,
  titre                varchar(255) NOT NULL,
  description_mission  text         NOT NULL,
  profil_recherche     text         NOT NULL,
  date_depot           date         NOT NULL,
  entreprise           int          REFERENCES entreprise           ON UPDATE CASCADE ON DELETE CASCADE,
  niveau_qualification int          REFERENCES niveau_qualification ON UPDATE CASCADE ON DELETE RESTRICT
);

DROP TABLE IF EXISTS candidature CASCADE;
CREATE TABLE candidature (
  id                   serial PRIMARY KEY,
  nom                  varchar(255) NOT NULL,
  prenom               varchar(255) NOT NULL,
  date_naissance       date         NOT NULL,
  adresse_postale      text         NOT NULL,
  adresse_email        varchar(255) NOT NULL,
  cv                   text   NOT NULL,
  date_depot           date   NOT NULL,
  niveau_qualification int    REFERENCES niveau_qualification ON UPDATE CASCADE ON DELETE RESTRICT
);

DROP TABLE IF EXISTS message_offre_emploi CASCADE;
CREATE TABLE message_offre_emploi (
  id            serial PRIMARY KEY,
  date_envoi    date   NOT NULL,
  corps_message text   NOT NULL,
  offre_emploi  int    REFERENCES offre_emploi ON UPDATE CASCADE ON DELETE CASCADE,
  candidature   int    REFERENCES candidature  ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS message_candidature CASCADE;
CREATE TABLE message_candidature (
  id            serial PRIMARY KEY,
  date_envoi    date   NOT NULL,
  corps_message text   NOT NULL,
  offre_emploi  int    REFERENCES offre_emploi ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS secteur_activite_offre_emploi CASCADE;
CREATE TABLE secteur_activite_offre_emploi (
  secteur_activite int REFERENCES secteur_activite ON UPDATE CASCADE ON DELETE RESTRICT,
  offre_emploi     int REFERENCES offre_emploi     ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS secteur_activite_candidature CASCADE;
CREATE TABLE secteur_activite_candidature (
  secteur_activite int REFERENCES secteur_activite ON UPDATE CASCADE ON DELETE RESTRICT,
  candidature      int REFERENCES candidature      ON UPDATE CASCADE ON DELETE CASCADE
);

--
-- Donnees de test
--

INSERT INTO entreprise (nom, descriptif, adresse_postale) VALUES ('Boston Dynamics', 'Robotique à usage militaire.', '78 Fourth Avenue. Waltham, MA
02451-7507');
INSERT INTO entreprise (nom, descriptif, adresse_postale) VALUES ('Tesla Motors', 'Constructeur automobile de voitures électriques.', '3500 Deer Creek Road. Palo Alto, CA 94304');
INSERT INTO entreprise (nom, descriptif, adresse_postale) VALUES ('Theranos', 'Analyse sanguine automatisée.', '1701 Page Mill Road. Palo Alto, CA 94304');

INSERT INTO niveau_qualification (intitule) VALUES ('CAP/BEP');
INSERT INTO niveau_qualification (intitule) VALUES ('Bac');
INSERT INTO niveau_qualification (intitule) VALUES ('Bac+3');
INSERT INTO niveau_qualification (intitule) VALUES ('Bac+5');
INSERT INTO niveau_qualification (intitule) VALUES ('Doctorat');

INSERT INTO secteur_activite (intitule) VALUES ('Marketing');
INSERT INTO secteur_activite (intitule) VALUES ('Informatique');
INSERT INTO secteur_activite (intitule) VALUES ('Telecoms/Reseaux');

INSERT INTO offre_emploi (titre, description_mission, profil_recherche, date_depot, entreprise, niveau_qualification) VALUES ('Expert systemes embarqués', 'blablabla', 'blublublu', '2015-09-01', 2, 2);
