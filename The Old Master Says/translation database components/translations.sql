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



--Translator table
INSERT INTO translation (author, publish_year)
VALUES ('Cheng Lin', 1949), ('Raymond B Blakney', 1955), ('Archie J Bahm', 1958), ('Wing-Tsit Chan', 1963), ('Paul J Lin', 1977), 
('Gia Fu Feng', 1989), ('Stephen Addiss', 1993), ('John Marby', 1994), ('Bart Marshall', 2006);

--Text table

--Queries
UPDATE full_text AS t1
SET full_text_tokens = to_tsvector(t1.full_text);

SELECT text_id, full_text FROM full_text
WHERE full_text_tokens @@ to_tsquery('truth');


SELECT * FROM full_text;
SELECT * FROM translation;
SELECT * FROM chapter_text;

SELECT * FROM ts_stat('SELECT full_text_tokens FROM text') --write SQL params here for which text, author, etc.
WHERE ts_stat.word = 'truth'; --only does lowercase

SELECT trans.author, t.full_text
FROM full_text AS t JOIN translation AS trans ON t.translation_id = trans.translation_id
WHERE full_text_tokens @@ to_tsquery('name <9> name'); -- to test whether phrases exist, do two words and how many words they are to be apart

SELECT trans.author, t.full_text
FROM full_text AS t JOIN translation AS trans ON t.translation_id = trans.translation_id
WHERE full_text_tokens @@ to_tsquery('everlasting <8> name'); -- lines 68-74 are contradicting each other


SELECT text_id, full_text FROM full_text
WHERE full_text_tokens @@ to_tsquery('these <9> origin');


SELECT ct.chapter, t.author, t.translation_id, ct.chapter_text
FROM chapter_text AS ct
JOIN full_text AS ft ON ct.text_id = ft.text_id
JOIN translation AS t ON ft.translation_id = t.translation_id
WHERE ct.chapter = 1;