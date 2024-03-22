CREATE TABLE IF NOT EXISTS public.dados_comparados
(
    id                                             SERIAL PRIMARY KEY,
    data                                           character varying(10),
    diferenca_confirmacoes                         bigint,
    diferenca_confirmados_sobre_cem_mil_habitantes numeric(38, 2),
    diferenca_mortes                               bigint,
    diferenca_mortes_sobre_confirmados             numeric(38, 2),
    primeiro_estado                                character varying(2),
    segundo_estado                                 character varying(2),
    CONSTRAINT ukp5w07ercjt48wtgwyxku02bum UNIQUE (data, primeiro_estado, segundo_estado)
);
