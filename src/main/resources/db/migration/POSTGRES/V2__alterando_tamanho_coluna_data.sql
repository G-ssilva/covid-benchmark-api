ALTER TABLE IF EXISTS covid_uf
    ALTER COLUMN data TYPE VARCHAR(10);

ALTER TABLE IF EXISTS benchmark
    ALTER COLUMN data TYPE VARCHAR(10);