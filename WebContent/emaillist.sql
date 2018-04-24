CREATE TABLE emaillist(
no number,
lastname varchar2(10),
firstname varchar2(10),
email varchar(10)
)

CREATE SEQUENCE seq_emaillist_no
INCREMENT BY 1
START WITH 1
NOCACHE;

select * from emaillist;