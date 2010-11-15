-- --------------------------------------------------------------------
-- vim: set expandtab ts=4 sw=4 syntax=sql:
--
-- rest-server.sql
--
-- Sample database for the rest-server template
--
-- This script creates a two table for customers and addresses.
-- Each customer can have zero or more addresses.
--
-- Author: Frank Schroeder <frank.schroeder@go-left.com>
--


-- --------------------------------------------------------------------
-- Create tables
--

-- create a table for customers
select now(), 'creating table customer...';
drop table if exists customer;
create table customer (
    customerId int not null auto increment,
    firstName varchar(255) null default '',
    lastName varchar(255) null default '',
    email varchar(255) null default '',

    primary key (customerId),
    unique key (email)
) engine=innodb default charset=utf8;
select now(), concat('rows inserted/updated: ', row_count());

-- create a table for addresses
select now(), 'creating table address...';
drop table if exists address;
create table address (
    addressId int not null auto increment,
    customerId int not null,
    street varchar(255) null default '',
    city varchar(255) null default '',
    zip varchar(255) null default '',

    primary key (addressId,customerId),
    constraint fk_address_customer
        foreign key (customerId)
        references customer (customerId)
        on delete cascade
        on update cascade
) engine=innodb default charset=utf8;
select now(), concat('rows inserted/updated: ', row_count());

-- --------------------------------------------------------------------
-- Add sample data
--
insert into customer (customerId, firstName, lastName, email)
  values (1, "Frank", "Schroeder", "frank.schroeder@go-left.com");

insert into customer (customerId, firstName, lastName, email)
  values (2, "John", "Doe", "john.doe@go-left.com");

insert into address (customerId, street, city, zip)
  values (1, "Bond Street", "London", "N70AA");

insert into address (customerId, street, city, zip)
  values (1, "Lindenstrasse", "Frankfurt", "60596");

select now(), 'finished ...';

