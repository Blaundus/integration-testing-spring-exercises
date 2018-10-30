create table rates
(
   currency varchar(4) not null,
   rate decimal(1000,3) not null,
   primary key(currency)
);