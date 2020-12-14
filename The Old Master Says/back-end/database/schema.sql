DROP TABLE IF EXISTS translation CASCADE;
DROP TABLE IF EXISTS chapter_text CASCADE;

DROP SEQUENCE IF EXISTS translation_text_id_seq;

CREATE SEQUENCE translation_text_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

CREATE TABLE translation (
text_id serial,
author varchar(50) NOT NULL,                    --name of translator(s)
publish_year integer NOT NULL,                  --publication year
CONSTRAINT pk_translation_text_id PRIMARY KEY (text_id)
);

CREATE TABLE chapter_text (
text_id integer NOT NULL,
chapter integer NOT NULL,
chapter_text text NOT NULL,
chapter_text_tokens TSVECTOR,
CONSTRAINT fk_chapter_text_text_id FOREIGN KEY (text_id) REFERENCES translation (text_id)
);