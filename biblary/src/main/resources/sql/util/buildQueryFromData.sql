-- data로부터 insert문 역추출
SELECT CONCAT(
	'insert into {table_name}()\n',
	'values ', GROUP_CONCAT(
		CONCAT('(', CONCAT_WS(', ', 
			CONCAT("'", {abbr}.{char_column_name}, "'"),
			CONCAT('"', {abbr}.{text_column_name}, '"'),
			{abbr}.{other_column_name},
		), ')')
		SEPARATOR ',\n' 
	)
)
  FROM {table_name} {abbr}