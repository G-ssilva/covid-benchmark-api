ALTER TABLE IF EXISTS covid_uf
    ALTER COLUMN uf TYPE VARCHAR(2);

ALTER TABLE IF EXISTS benchmark
    ALTER COLUMN primeiro_estado TYPE VARCHAR(2);

ALTER TABLE IF EXISTS benchmark
    ALTER COLUMN segundo_estado TYPE VARCHAR(2);