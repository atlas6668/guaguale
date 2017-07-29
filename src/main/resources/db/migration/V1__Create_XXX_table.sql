/* ********  ******** 
	过年七天乐 刮刮乐
e.g.	
DROP TABLE IF EXISTS tablename; 
CREATE TABLE tablename(
	id int(11) not null auto_increment,
	
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;
********  ******** */
/* ******** user ******** */
DROP TABLE IF EXISTS user; 
CREATE TABLE user(
	id int(11) not null auto_increment,
	mobile varchar(20) not null,
	nick varchar(100) not null,
	akey varchar(100),
	onedaymaxtimes tinyint,
	onewholetimes int,
	createtime datetime,
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

/* ******** prize ******** */
DROP TABLE IF EXISTS prize; 
CREATE TABLE prize(
	id int(11) not null auto_increment,
	xmk varchar(32) not null,
	name varchar(200) not null,
	ptype varchar(100),
	image varchar(200),
	unick varchar(100),
	umobile varchar(20),
	createtime datetime,
	status tinyint(1) not null default 0,
	duijiangtime datetime,
	activity int(11),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

/* ******** pond ******** */
DROP TABLE IF EXISTS pond; 
CREATE TABLE pond(
	id int(11) not null auto_increment,
	name varchar(100),
	createtime datetime,
	usetime datetime,
	activity int(11),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

/* ******** pond_prize ******** */
DROP TABLE IF EXISTS pond_prize; 
CREATE TABLE pond_prize(
	id int(11) not null auto_increment,
	pond_id int(11) not null,
	prize_id int(11) not null,
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

/* ******** activity ******** */
DROP TABLE IF EXISTS activity; 
CREATE TABLE activity(
	id int(11) not null auto_increment,
	akey varchar(100),
	name varchar(200),
	intro varchar(500),
	starttime datetime,
	stoptime datetime,
	status tinyint(1),
	onedaymaxtimes tinyint,
	onedayoutremind varchar(200),
	onewholetimes int,
	onewholeoutremind varchar(200),
	zhongremind varchar(200),
	nozhongremind varchar(200),
	winningrate tinyint,
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;


