CREATE SEQUENCE t_person_seq MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 NOORDER NOCYCLE;

create table t_person
(
    id        number(19),
    name      VARCHAR2(255 char),
    code      VARCHAR2(255 char),
    type      VARCHAR2(255 char),
    birth_date VARCHAR2(255 char)
);