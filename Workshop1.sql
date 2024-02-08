create database MobileStore

use MobileStore

create table [User]
(
	[userID] varchar(20) not null primary key,
	[password] varchar(20) not null,
	[fullName] varchar(50) not null,
	[role] int --(0: user, 1: manager, 2: staff)
)

create table [Mobile]
(
	[mobileID] varchar(20) not null primary key,
	[name] varchar(20) not null,
	[price] float,
	[quantity] int,
	[yearOfProduction] int,
	[description] varchar(250),
	[notSale] bit --(0: sale)
)

create table [Cart]
(
	[cartID] varchar(20) not null primary key,
	[totalQuantity] int,
	[totalPrice] float,
	[userID] varchar(20) 
	constraint fr_cu foreign key(userID) references [User](userID)
)

create table [CartDetails]
(
	[cartID] varchar(20) not null,
	[mobileID] varchar(20) not null,
	[totalQuantity] int,
	[totalPrice] float,
	constraint fr_c foreign key(cartID) references [Cart](cartID),
	constraint fr_m foreign key(mobileID) references [Mobile](mobileID),
	constraint pr_mc primary key(mobileID, cartID)
)

insert into [User](userID, password, fullName, role)  values ('datu', '123', 'Nguyen Van Dat', 0)
insert into [User](userID, password, fullName, role)  values ('datm', '123', 'Nguyen Van Dat', 1)
insert into [User](userID, password, fullName, role)  values ('dats', '123', 'Nguyen Van Dat', 2)

insert into [Mobile](mobileID, name, price, quantity, yearOfProduction, notSale) values ('M1', 'iPhone 15 Pro Max', 1000, 100, 2023, 0)
insert into [Mobile](mobileID, name, price, quantity, yearOfProduction, notSale) values ('M2', 'iPhone 14 Pro Max', 1000, 100, 2022, 0)
insert into [Mobile](mobileID, name, price, quantity, yearOfProduction, notSale) values ('M3', 'iPhone 13 Pro Max', 1000, 100, 2021, 0)
insert into [Mobile](mobileID, name, price, quantity, yearOfProduction, notSale) values ('M4', 'iPhone 12 Pro Max', 1000, 100, 2020, 0)
insert into [Mobile](mobileID, name, price, quantity, yearOfProduction, notSale) values ('M5', 'iPhone 11 Pro Max', 1000, 100, 2019, 0)

insert into [Cart](cartID, userID, totalQuantity, totalPrice) values ('datucart', 'datu', 0, 0)
insert into [Cart](cartID, userID, totalQuantity, totalPrice) values ('datmcart', 'datm', 0, 0)
insert into [Cart](cartID, userID, totalQuantity, totalPrice) values ('datscart', 'dats', 0, 0)

insert into [CartDetails](cartID, mobileID, totalQuantity, totalPrice) values ('datucart', 'M1', 1, 0);
insert into [CartDetails](cartID, mobileID, totalQuantity, totalPrice) values ('datucart', 'M2', 1, 0);
insert into [CartDetails](cartID, mobileID, totalQuantity, totalPrice) values ('datucart', 'M3', 1, 0);

delete from CartDetails where cartID = 'datucart' and mobileID = 'M1'

select * from CartDetails
select * from Cart

update CartDetails
set totalQuantity = totalQuantity - 1
where cartID = 'datcart' and mobileID = 'M5' and totalQuantity > 0

-- add to cart trigger
go
create trigger utg_addCartDetails on CartDetails after insert, update as 
begin

	update CartDetails
	set totalPrice = i.totalQuantity * m.price
	from inserted i, Mobile m
	where CartDetails.cartID = i.cartID and CartDetails.mobileID = i.mobileID and i.mobileID = m.mobileID

	update Cart
	set totalPrice = (select ISNULL(sum(cd.totalPrice), 0)
					  from CartDetails cd
					  where cd.cartID = c.cartID),
		totalQuantity = (select ISNULL(sum(cd.totalQuantity), 0)
					  from CartDetails cd
					  where cd.cartID = c.cartID)
	from Cart c
	where c.cartID IN (SELECT cartID from inserted)
end

-- delete from cart trigger
go
create trigger utg_deleteCartDetails on CartDetails after delete as
begin
	update Cart
	set totalPrice = (select ISNULL(sum(cd.totalPrice), 0)
					  from CartDetails cd
					  where cd.cartID = c.cartID),
		totalQuantity = (select ISNULL(sum(cd.totalQuantity), 0)
						from CartDetails cd
						where cd.cartID = c.cartID)
	from Cart c
	where c.cartID IN (SELECT cartID from deleted)
end