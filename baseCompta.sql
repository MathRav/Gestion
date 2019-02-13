
create table entreprise(
id integer not null auto_increment,
nom varchar(50),
adresse varchar(10),
code integer(3),
devise varchar(10),
allocCodes integer,
PRIMARY KEY(id)
);

create table devise(
id integer not null auto_increment,
identreprise integer,
foreign key(identreprise) references entreprise(id),
intitule varchar(10),
valeur double,
PRIMARY KEY (id)
);
alter table devise add constraint unique(identreprise,intitule);
create table journal(
id integer not null auto_increment,
idEntreprise integer not null,
code varchar(10),
intitule varchar(50),
PRIMARY KEY (id),
FOREIGN KEY (idEntreprise) REFERENCES entreprise(id)
);

create table plancomptable(
id integer not null auto_increment,
idEntreprise integer not null,
code varchar(10),
intitule varchar(50),
PRIMARY KEY (id),
FOREIGN KEY (idEntreprise) REFERENCES entreprise(id)
);

create table plantiers(
id integer not null auto_increment,
idEntreprise integer not null,
numero varchar(50),
intitule varchar(50),
type varchar(50),
comptecollectif varchar(10),
PRIMARY KEY (id),
FOREIGN KEY (idEntreprise) REFERENCES entreprise(id)
);

create table types(
id integer not null auto_increment,
nom varchar(50),
PRIMARY KEY(id)
);

create table mvt(
id integer not null auto_increment,
id_Journal integer,
date_Mvt date,
reference varchar(10),
id_compte varchar(10),
id_tiers varchar(10),
id_exercice varchar(50),
libelle varchar(50),
echeance date,
debit double,
credit double,
PRIMARY KEY(id)
);

create view MvtJournalPlanComptable as
select mvt.id as id,mvt.codeJournal as codeJOurnal,journal.intitule as intituleJournal,mvt.numerocompte as numerocompte,plancomptable.intitule as intitulePlanComptable,mvt.numerotiers as numerotiers,plantiers.intitule as intitulePlantiers,date_Mvt,reference,libelle,echeance,debit,credit
from mvt 
join journal on mvt.codeJournal=journal.id
join plancomptable on mvt.numerocompte=plancomptable.id
join plantiers on mvt.numerotiers=plantiers.numero

create table exercice(
id integer not null auto_increment,
identreprise integer not null,
annee integer not null,
isClotured boolean,PRIMARY KEY(id),
FOREIGN KEY (identreprise) REFERENCES entreprise(id)
);