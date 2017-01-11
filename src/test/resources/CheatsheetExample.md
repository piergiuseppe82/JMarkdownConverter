#Headers

# H1
## H2
### H3
#### H4
##### H5
###### H6

Alternatively, for H1 and H2, an underline-ish style:

Alt-H1
======

Alt-H2
------


#Emphasis
Emphasis, aka italics, with *asterisks* or _underscores_.

Strong emphasis, aka bold, with **asterisks** or __underscores__.

Combined emphasis with **asterisks and _underscores_**.

Strikethrough uses two tildes. ~~Scratch this.~~



#Lists
1. First ordered list item
2. Another item
...* Unordered sub-list. 
1. Actual numbers don't matter, just that it's a number
...1. Ordered sub-list
4. And another item.

OR:

* Unordered list can use asterisks
- Or minuses
+ Or pluses



#Links
[I'm an inline-style link](https://www.google.com)

[I'm an inline-style link with title](https://www.google.com "Google's Homepage")

[I'm a reference-style link][Arbitrary case-insensitive reference text]

[I'm a relative reference to a repository file](../blob/master/LICENSE)

[You can use numbers for reference-style link definitions][1]

Or leave it empty and use the [link text itself].

URLs and URLs in angle brackets will automatically get turned into links. 
http://www.example.com or <http://www.example.com> and sometimes 
example.com (but not on Github, for example).

Some text to show that the reference links can follow later.

[arbitrary case-insensitive reference text]: https://www.mozilla.org
[1]: http://slashdot.org
[link text itself]: http://www.reddit.com




#Images
Here's our logo (hover to see the title text):

Inline-style: 
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")

Reference-style: 
![alt text][logo]

[logo]: https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 2"



#Tables
Colons can be used to align columns.

| Tables        | Are           | Cool  |
| :------------ |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |



#Blockquotes
> Blockquotes are very handy in email to emulate reply text.
> This line is part of the same quote.

Quote break.

> This is a very long line that will still be quoted properly when it wraps. Oh boy let's keep writing to make sure this is long enough to actually wrap for everyone. Oh, you can *put* **Markdown** into a blockquote. 

#Inline HTML
<dl>
  <dt>Definition list</dt>
  <dd>Is something people use sometimes.</dd>

  <dt>Markdown in HTML</dt>
  <dd>Does *not* work **very** well. Use HTML <em>tags</em>.</dd>
</dl>

Three or more...



#Horizontal Rule
---

Hyphens

***

Asterisks

___

Underscores





#Line Breaks
Here's a line for us to start with.

This line is separated from the one above by two newlines, so it will be a *separate paragraph*.

This line is also a separate paragraph, but...
This line is only separated by a single newline, so it's a separate line in the *same paragraph*.


#Youtube videos
[![IMAGE ALT TEXT HERE](http://img.youtube.com/vi/y3mqRZMB89c/0.jpg)](https://www.youtube.com/watch?v=y3mqRZMB89c)



#Code and Syntax Highlighting
Inline `code` has `back-ticks around` it.

Properties:

``` properties
# --- ConsoleHandler ---
# Override of global logging level


java.util.logging.ConsoleHandler.level=FINE
java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter
```

SQL:

``` sql

--SQL COMMENT
# other comment
CREATE TABLE SCHEMA_A.TIP_BTC_EXT 
(
  COD_TIP_BTC VARCHAR2(150 BYTE) NOT NULL 
, EXT_BIN_VRS VARCHAR2(150 BYTE) NULL 
, EXT_CONFIG_VRS VARCHAR2(150 BYTE) NULL 
, EXT_NGEST_VER VARCHAR2(150 BYTE) NULL
, EXT_LIBS_VRS VARCHAR2(150 BYTE) NULL 
, COD_UTE_INS VARCHAR2(8 BYTE)  NOT NULL 
, TMST_INS_RIG TIMESTAMP NOT NULL
, COD_UTE_AGR VARCHAR2(8 BYTE)  NOT NULL 
, TMST_AGR_RIG TIMESTAMP NOT NULL
, CONSTRAINT TIP_BTC_EXT_PK PRIMARY KEY (COD_TIP_BTC) 
); 
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.COD_TIP_BTC IS 'CODICE TIPO BATCH';
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.EXT_BIN_VRS IS 'SCRIPT  DI AVVIO SPECIFICO DEL BATCH';
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.EXT_CONFIG_VRS IS 'CONFIGURAZIONE SPECIFICA DEL BATCH';
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.EXT_NGEST_VER IS 'VERSIONE NGEST.jar SPECIFICA DEL BATCH';
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.EXT_LIBS_VRS IS 'LIBRERIE JAVA SPECIFICHE PER IL BATCH';
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.COD_UTE_INS IS 'CODICE UTENTE INSERIMENTO';
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.TMST_INS_RIG IS 'TIMESTAMP INSERIMENTO RIGA';
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.COD_UTE_AGR IS 'CODICE UTENTE AGGIORNAMENTO';
COMMENT ON COLUMN SCHEMA_A.TIP_BTC_EXT.TMST_AGR_RIG IS 'TIMESTAMP AGGIORNAMENTO RIGA';

      select 1 from dual; -- CON UN PO DI SPAZI 

/*
select 1 from dual;
*/

select * from t1;

update t1 set c1 = 1 where c2 is not null; -- INLINE COMMENT
update t1 set c1 =1 where c2 is not null;#inline comment
update t1 set c1 ='OTHER CHARS > ssss||| 11111!!!!!{}{§°°°sds@@@@$$$€€€€€@@@@a<<<<<<@@@@¸@ò@@@@@> > 4' where c2 > c3;#inline comment
insert into t1 (c1,c2,c3) values (65.6,'PIPPO','123',65);

```
