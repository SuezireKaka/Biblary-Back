create table t_bible(
	name		varchar(64) primary key,
	language	varchar(16)
);

create table t_sys_book(
	pos			tinyint	primary key,
	short_name	varchar(2) not null,
	full_name	varchar(16),
	chapter_num tinyint unsigned,
	new_test	tinyint
);
create index abbr_search on t_sys_book(short_name);