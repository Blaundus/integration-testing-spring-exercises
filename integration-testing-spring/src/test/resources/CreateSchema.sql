CREATE TABLE RATES
(
    currency varchar(4) not null,
   rateValue decimal(1000,3) not null,
   primary key(currency)
);