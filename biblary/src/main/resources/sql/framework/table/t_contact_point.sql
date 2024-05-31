create table t_contact_point(
	owner_id	char(4),
	cp_code		varchar(255),
	cp_val		varchar(255),
	primary key(owner_id, cp_code)
);