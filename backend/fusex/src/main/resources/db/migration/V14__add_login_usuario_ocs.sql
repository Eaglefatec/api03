ALTER TABLE ocs ADD COLUMN login_usuario VARCHAR(50) NULL;
ALTER TABLE ocs ADD CONSTRAINT uk_ocs_login_usuario UNIQUE (login_usuario);

UPDATE ocs SET login_usuario = 'ocs'
WHERE ocs_id = (SELECT min_id FROM (SELECT MIN(ocs_id) AS min_id FROM ocs) AS t);
