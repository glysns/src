-- JAVA e MYSQL

SELECT CONCAT('private ',
	case when DATA_TYPE ='int' then 'Integer' else 'String' end,
	' ',
	COLUMN_NAME,
	' ;')
  FROM INFORMATION_SCHEMA.COLUMNS
  WHERE TABLE_SCHEMA = 'seletivo_2024' AND TABLE_NAME = 'cadastros';

-- ANOTAÇÃO

SELECT CONCAT('@Column (name="',COLUMN_NAME, '")', 'private ',
	case when DATA_TYPE ='int' then 'Integer' else 'String' end,
	' ',
	COLUMN_NAME,
	' ;')
  FROM INFORMATION_SCHEMA.COLUMNS
  WHERE TABLE_SCHEMA = 'seletivo_2024' AND TABLE_NAME = 'cadastros';




DELETE from proseletivodb.dbo.ps_cadastros_temp;