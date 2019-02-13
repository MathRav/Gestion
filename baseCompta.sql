
create table entreprise(
id integer not null auto_increment,
nom varchar(50),
adresse varchar(10),
code integer(3),
devise varchar(10),
allocCodes integer,
PRIMARY KEY(id)
);
insert into entreprise values(1,'SOBA','1234',110,'MGA',5);

create table devise(
id integer not null auto_increment,
identreprise integer,
intitule varchar(10),
valeur double,
PRIMARY KEY (id)
);
alter table devise add constraint unique(identreprise,intitule);

insert into devise values(1,1,'MGA',1);

create table journal(
id integer not null auto_increment,
idEntreprise integer not null,
code varchar(10),
intitule varchar(50),
PRIMARY KEY (id)
);

insert into journal values(1,1,'AC','ACHAT');

create table plancomptable(
id integer not null auto_increment,
idEntreprise integer not null,
code varchar(10),
intitule varchar(50),
PRIMARY KEY (id)
);
insert into plancomptable values(1,1,'40100','CLIENT LOCAL');

create table plantiers(
id integer not null auto_increment,
idEntreprise integer not null,
numero varchar(50),
intitule varchar(50),
type varchar(50),
comptecollectif varchar(10),
PRIMARY KEY (id)
);

insert into plantiers values(1,1,'ANDRY','CLT:ANDRY','CLIENT','40100');


create table types(
id integer not null auto_increment,
nom varchar(50),
PRIMARY KEY(id)
);

insert into types values(1,'CLIENT');

create table mvt(
id integer not null auto_increment,
Id_Journal integer,
date_Mvt date,
reference varchar(10),
id_compte integer,
id_tiers integer,
id_exercice integer,
libelle varchar(50),
echeance date,
debit double,
credit double,
PRIMARY KEY(id)
);

insert into mvt(1,1,'2019-02-13','FA0001','1','','FACTURE CLIENT 1',null,100000,0);

create view MvtJournal as
select mvt.id,mvt.codeJournal as codeJournal,journal.intitule as intituleJournal,mvt.numerocompte as numerocompte,mvt.numerotiers as numerotiers,date_Mvt,reference,libelle,echeance,debit,credit
from mvt
join journal on mvt.codeJournal=journal.id

create view MvtJournalPlanComptable as
select mvt.id,mvt.codeJournal as codeJournal,journal.intitule as intituleJournal,mvt.numerocompte as numerocompte,plancomptable.intitule as intitulePlanComptable,mvt.numerotiers as numerotiers,date_Mvt,reference,libelle,echeance,debit,credit
from mvt
join journal on mvt.codeJournal=journal.id
join plancomptable on mvt.numerocompte=plancomptable.code

create view mvtotal as
select mvt.id,mvt.id_exercice as idExercice,journal.code as codeJournal,journal.intitule as intituleJournal,mvt.id_compte as idCompte,plancomptable.intitule as intitulePlanComptable,plantiers.numero as numeroTiers,plantiers.intitule as intitulePlantiers,date_Mvt as dateMvt,reference,libelle,echeance,debit,credit
from mvt
join journal on mvt.id_Journal=journal.id
join plancomptable on mvt.id_compte=plancomptable.id
left join plantiers on mvt.id_tiers=plantiers.id;

select id,idExercice,codeJournal,intituleJournal,idCompte,intitulePlanComptable,numeroTiers,intitulePlantiers,dateMvt,reference,libelle,echeance,debit,credit from mvtotal  where idExercice=1 and month(dateMvt)=2 order by idCompte asc , dateMvt asc;



create table exercice(
id integer not null auto_increment,
identreprise integer not null,
annee integer not null,
isClotured boolean,PRIMARY KEY(id),
FOREIGN KEY (identreprise) REFERENCES entreprise(id)
);

insert into exercice values(1,1,2019,false);
