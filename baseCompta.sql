create table entreprise(
id_entreprise integer not null auto_increment,
nom_entreprise varchar(50),
adresse varchar(10),
code_postal integer(3),
id_devise varchar(10),
allocCodes integer,
PRIMARY KEY(id)
);
alter table devise add FOREIGN KEY (devise) REFERENCES devise(intitule);

create table journal(
idEntreprise integer not null,
code varchar(10),
intitule varchar(50),
PRIMARY KEY (idEntreprise,code),
FOREIGN KEY (idEntreprise) REFERENCES entreprise(id_entreprise)
);

create table plan_comptable(
idEntreprise integer not null,
code varchar(10),
intitule varchar(50),
PRIMARY KEY (idEntreprise,code),
FOREIGN KEY (idEntreprise) REFERENCES entreprise(id_entreprise)
);

create table plan_tiers(
idEntreprise integer not null,
numero varchar(50),
intitule varchar(50),
type_plan varchar(50),
compte_collectif varchar(10),
PRIMARY KEY (numero,idEntreprise),
FOREIGN KEY (idEntreprise) REFERENCES entreprise(id_entreprise),
FOREIGN KEY (compte_collectif) REFERENCES plan_comptable(code)
);

create table types(
id integer not null auto_increment,
nom varchar(50),
PRIMARY KEY(id)
);

create table mvt(
idMvt integer not null auto_increment,
codeJournal integer,
date_Mvt date,
reference varchar(10),
numero_compte varchar(10),
numero_tiers varchar(10),
libelle varchar(50),
echeance date,
debit double,
credit double,
PRIMARY KEY(idMvt),
FOREIGN KEY (numero_compte) REFERENCES plan_comptable(code),
FOREIGN KEY (numero_tiers) REFERENCES plan_tiers(numero)
);

create table devise(
id_devise integer not null auto_increment,
intitule_devise varchar(10),
valeur double,
PRIMARY KEY ()
);

create table exercice(
id_entreprise integer not null,
annee integer not null,
isClotured boolean,
FOREIGN KEY (id_entreprise) REFERENCES entreprise(id_entreprise),
);