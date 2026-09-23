CREATE TABLE ocs_procedimento (
    ocs_id BIGINT,
    proc_codigo_dgp BIGINT,
    PRIMARY KEY (ocs_id, proc_id),
    FOREIGN KEY (ocs_id) REFERENCES ocs(ocs_id),
    FOREIGN KEY (proc_codigo_dgp) REFERENCES procedimento(proc_codigo_dgp)
);