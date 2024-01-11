/*�����ݿ�*/

create database restaurant
/*����*/
create table 	UserInfos
(
   userId int ,
   userPwd char(50) ,
   userName char(50)
)
insert into UserInfos(userId,userPwd,username) values(1,'123','123')
create table manu
(
      dishnum smallint primary key,
	  dishname char(20)not null,
	  dishprice float not null
)



create table tableinfo
( 
      tablenum smallint primary key,
	  seatamount smallint ,
	  check (seatamount between 0 and 20),
	  hasoccupy char(3),
	  check (hasoccupy in ('yes','no'))
)
create table empinfo
(
        empnum smallint primary key,
		empname char(20) not null,
		empsex char(2)
		check(empsex in('��','Ů')),
		empage smallint
		check(empage between 20 and 60),
		empsalary smallint
		check(empsalary between 5000 and 100000),
		empposition char(20) not null
)
create table manuinfo
( 
       billnum char(50), 
       dishnum smallint,
	   primary key(billnum,dishnum),
	   dishname char(20),
	   dishamount smallint,
	   dishprice int,
       sumprice int
	   
	   foreign key(dishnum)references manu(dishnum)
)
create table customer
(
      customerTle varchar(15) primary key,
	  customerName char(20)not null,
	  sex char(2),
	  check ((sex)in('��','Ů')),
	  consumAmount smallint
)
create table billinfo
(
      billnum varchar(50) primary key,
	 
	  sumprice float,
	  paytime varchar(50),
	  discount float,
	  actualpay float,
      customerTle varchar(15),
      tablenum smallint,
	  empnum smallint

	

)
create table oder
(
    billnum smallint primary key,
	customerTle varchar(15) ,
	time_ datetime,
	tablenum smallint,
	empnum smallint
)


/*�������*/

insert into tableinfo(tablenum,seatamount,hasoccupy)
values (1,2,'no'),(2,4,'no'),(3,6,'yes'),(4,2,'no'),(5,4,'no'),(6,6,'yes'),
(7,2,'no'),(8,4,'no'),(9,6,'yes'),(10,2,'no'),(11,4,'no'),(12,6,'yes')

insert into empinfo(empnum,empname,empsex,empage,empsalary,empposition)
values (1,'����','Ů',22,6000,'����Ա'),(2,'��˹','��',59,20000,'��ʦ'),(3,'����','��',35,5000,'����Ա'),
(4,'����','Ů',40,7000,'����'),(5,'Ǯ��','��',55,8000,'���')

insert into manu(dishnum,dishname,dishprice)
values(1,'���Ƶ�����',10),(2,'������˿',15),(3,'����ţ����',9),(4,'���ּ���',15),
(5,'��������',16),(6,'������Ѽ',50),(7,'����',3),(8,'��������˿',8),(9,'��ˮ�ײ�',500),
(10,'˫�㼦�ȱ�',20),(11,'������',15)
