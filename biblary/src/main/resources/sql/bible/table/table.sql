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

create table t_verse(
	bible		varchar(64),
	book		varchar(2),
	chapter		tinyint,
	verse		int,
	contents	text,
	primary key(bible, book, chapter, verse)
);

create table t_verse_strong(
	book		varchar(2),
	chapter		tinyint,
	verse		int,
	pos			int,
	code_num	int,
	code_type	char(1),
	primary key(book, chapter, verse)
)




