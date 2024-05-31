create table t_account ()
	id				varchar(255),
	pass_word		varchar(255),
	owner_id		char(4),
	nick			varchar(255),
	intro			varchar(255),
	alive			tinyint,
	reg_dt			timestamp default current_timestamp(),
	upt_dt			timestamp default current_timestamp() on update current_timestamp()
)