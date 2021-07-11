CREATE TABLE umasio.ImportError (
	ENTITY_ID varchar(255) NOT NULL,
	jobExecutionId BIGINT(20) NOT NULL,
	description varchar(1024) NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1;
CREATE INDEX ix_import_error_id_job_execution USING BTREE ON umasio.ImportError (jobExecutionId);