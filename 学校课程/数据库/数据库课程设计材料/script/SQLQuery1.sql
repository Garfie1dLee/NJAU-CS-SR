/*建数据库*/

create database restaurant
/*建表*/
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
		check(empsex in('男','女')),
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
	  check ((sex)in('男','女')),
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


/*添加数据*/

insert into tableinfo(tablenum,seatamount,hasoccupy)
values (1,2,'no'),(2,4,'no'),(3,6,'yes'),(4,2,'no'),(5,4,'no'),(6,6,'yes'),
(7,2,'no'),(8,4,'no'),(9,6,'yes'),(10,2,'no'),(11,4,'no'),(12,6,'yes')

insert into empinfo(empnum,empname,empsex,empage,empsalary,empposition)
values (1,'张三','女',22,6000,'服务员'),(2,'李斯','男',59,20000,'厨师'),(3,'王武','男',35,5000,'服务员'),
(4,'赵柳','女',40,7000,'收银'),(5,'钱六','男',55,8000,'会计')

insert into manu(dishnum,dishname,dishprice)
values(1,'招牌蛋炒饭',10),(2,'鱼香肉丝',15),(3,'大碗牛肉面',9),(4,'可乐鸡翅',15),
(5,'宫保鸡丁',16),(6,'北京烤鸭',50),(7,'可乐',3),(8,'酸辣土豆丝',8),(9,'清水白菜',500),
(10,'双层鸡腿堡',20),(11,'土豆泥',15)
