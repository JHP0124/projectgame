dbeaver는 신뢰할 수 없어서 
cmd 창에서 작업함.

mysql -u root -p

--show databases;

USE playdata;

DROP TABLE IF EXISTS Olduser;

CREATE TABLE Olduser (
int unsigned auto_increment  primary key, 
id VARCHAR(40) primary key,  
pw VARCHAR(40),
mail VARCHAR(40),
name VARCHAR(40),
nickname VARCHAR(40)
);


-- User 테이블
DROP TABLE IF EXISTS User;
-- 아래처럼 처리된 이유는 tab칸 하나로 금요일밤에 3시간동안 씨름하다가 원인이 tab키가 원인인 걸
-- 발견함..... 처음부터 다시 침... 알아두면 좋을 듯..
CREATE TABLE User(
id VARCHAR(40) primary key,  
pw VARCHAR(40),
mail VARCHAR(40),
name VARCHAR(40),
nickname VARCHAR(40)
);




-- Total 테이블
DROP TABLE IF EXISTS Total;

CREATE TABLE Total(
nickname VARCHAR(40) primary key,   
score1 int,
score2 int,
score3 int
);


-- cgame1 테이블
DROP TABLE IF EXISTS cgame1;

 --num int unsigned auto_increment  primary key, 
 -- 이 게임을 몇번 했는지와 예를 들어 100만번째에 게임을 한 사람을 찾을 때 넣을까 말까하다가 뺌.

create table cgame1(
num int unsigned auto_increment  primary key,  
nickname VARCHAR(40),
score1 int unsigned
);



DROP TABLE IF EXISTS cgame2;

CREATE TABLE cgame2(
 num int unsigned auto_increment  primary key, 
 nickname VARCHAR(40),
 score2 int unsigned
);

DROP TABLE IF EXISTS cgame3;

CREATE TABLE cgame3(
 num int unsigned auto_increment  primary key, 
 nickname VARCHAR(40),  
 score3 int unsigned
);




--mysql auto commit
SELECT @@AUTOCOMMIT;   // AUTOCOMMIT 여부 확인
SET AUTOCOMMIT = TRUE;   // AUTOCOMMIT 설정
SET AUTOCOMMIT = FALSE;   // AUTOCOMMIT 해제


--기존에 배웠던 방식대로 DELIMITER $$ 없이 입력하면 오류가 발생함.
--계속 아래의 오류가 발생해서 원인을 찾는데 os업데이트와 문법 체크한다고 몇번이고 수작업으로 재입력하고
-- mysql 재설치 등 토요일 오후부터  4시간동안 개고생을 다함... delimiter는 왜 안알려준걸까?
--

/* 
mysql> CREATE TRIGGER user_total
    -> AFTER INSERT
    -> ON user
    -> FOR EACH ROW
    -> BEGIN
    -> insert into Total(nickname, score1, score2, score3) values (new.nickname,0,0,0);
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that 
corresponds to your MySQL server version for the right syntax to use near '' at line 6
*/
-- 그래서 앞뒤로  DELIMITER $$    ~  $$ 입력하고 면 됨. 
-- 마무리하려면 DELIMITER ; 를 입력해야  -> 커맨드 모드에서 나옴.
-- DELIMITER ; 가 먹힐 때도 있고  안 먹힐 때도 있는데 안 먹히면  sql 치고 나서  $$ 입력하고 엔터쳐야함.
-- DELIMITER  사용할 때 부터 발생할 수 있는 상황임.

--https://hogu-programmer.tistory.com/23 참고
--https://m.blog.naver.com/alcmskfl17/221858522028 참고 
/*프로시저(Procedure) 안에는 세미콜론(;) 이 여러개 등장한다. 
그렇다면 프로시저 자체를 한  명령문으로 보는 것이 아닌, 
프로시저 중간 중간에 있는 세미콜론(;) 단위로 명령문을 쪼개서 읽어버리기 때문에 
인식을 못하는 것이다.*/

drop trigger IF EXISTS user_total;


-- 회원가입시 user 테이블의 닉네임을 가지고 total 테이블에 넣고 각 점수칸에 0을 넣는 트리거
-- 트리거 user_total
/*
DELIMITER $$
CREATE TRIGGER user_total
AFTER INSERT
ON user
FOR EACH ROW
BEGIN 
insert into Total(nickname, score1, score2, score3) values (new.nickname,0,0,0);
END$$        
DELIMITER ;
*/

DELIMITER $$
CREATE TRIGGER user_total
AFTER INSERT
ON user
FOR EACH ROW
BEGIN 
DECLARE nick VARCHAR(40);
set nick = new.nickname ;
insert into Total(nickname, score1, score2, score3) values (nick,0,0,0);
END$$        
DELIMITER ;



--insert into Total(nickname, score1, score2, score3) values (NICK1,0,0,0);
--insert into Total(nickname, score1, score2, score3) values (new.nickname,0,0,0);
--declare nick varchart(40);
--set nick = new.nickname;

아래의 트리커에도 delimiter 적용하기


--트리거 autoscore1
drop trigger  IF EXISTS autoscore1;

DELIMITER $$
CREATE TRIGGER autoscore1
AFTER INSERT
ON cgame1 FOR EACH ROW
BEGIN
DECLARE score int unsigned;
DECLARE nick VARCHAR(40);
SET score=NEW.score1;  
SET nick=NEW.nickname;
update Total set score1=score where nickname=nick; 
END$$
DELIMITER ;

--ERROR 1193 (HY000): Unknown system variable 'new_score' 자꾸 이 에러 발생. 
--해결책 : 1. DECLARE new_score => score로 변경 / 2. set score1 => score / 3. update문 한 줄로 변경. 


--트리거 autoscore2
drop trigger IF EXISTS autoscore2;
$$

DELIMITER $$
CREATE TRIGGER autoscore2
AFTER INSERT
ON cgame2 FOR EACH ROW
BEGIN
DECLARE score int unsigned;
DECLARE nick  VARCHAR(40);
SET score=NEW.score2;  
SET nick=NEW.nickname;
update Total set score2=score where nickname= nick; 
END$$
DELIMITER ;




--트리거 autoscore3
drop trigger IF EXISTS autoscore3;
$$

DELIMITER $$
CREATE TRIGGER autoscore3
AFTER INSERT
ON cgame3 FOR EACH ROW
BEGIN
DECLARE score int unsigned;
DECLARE nick  VARCHAR(40);
SET score=NEW.score3 ;  
SET nick=NEW.nickname;
update Total set score3=score where nickname=nick; 
END$$
DELIMITER ;




insert into User values('n1','n1','n1','n1','n1');
$$
insert into User values('n2','n2','n2','n2','n2');
$$
insert into User values('n3','n3','n3','n3','n3');
$$
insert into User values('n4','n4','n4','n4','n4');
$$
insert into User values('n5','n5','n5','n5','n5');
$$
insert into User values('n6','n6','n6','n6','n6');
$$
insert into User values('n7','n7','n7','n7','n7');

insert into User values('n8','n8','n8','n8','n8');

insert into cgame1(nickname,score1) values('n1',90);
insert into cgame1(nickname,score1) values('n7',98);
insert into cgame1(nickname,score1) values('n8',99);
$$

select * from cgame1;
$$

select * from total;
$$

insert into cgame2(nickname,score2) values('n7',90);
insert into cgame2(nickname,score2) values('n8',80);
$$

select * from cgame2;
$$

select * from total;
$$

insert into cgame3(nickname,score3) values('n7',90);
insert into cgame3(nickname,score3) values('n8',70);
$$

select * from cgame3;
$$

select * from total;
$$

--delimiter 참고 링크
https://doorbw.tistory.com/23
참고 버전업이 되어서인지 
맨 마지막에 DELIMITER ; 를 안하고 
$$쳐도됨  문제는  DELIMITER $$ ~ $$ 치고나서 가끔씩 DELIMITER ;를 해도 SQL문이 적용 안되는 문제가 있음.  
insert into cgame3 values('n1',90);
$$ 
해야 SQL문이 적용되는 경우가 있음.
내 mysql이 꼬인 건지 내 컴이 문제인 건지.. 내가 오타를 낸 건지는 몰라도 
알아서 상황에 맞게 적용해보시길

다행히도 테이블 트리거 다 생성되고 데이터 삽입도 되고 삽입으로 인한 트리거 발동도 잘 적용됨. 

끗. 이제 구조도... 아 이걸로 하루 다날려먹네...


-- 닉네임 변경시 total,cgame1~3 테이블의 닉네임 변경하는 트리거

drop trigger IF EXISTS user_nick_update;

DELIMITER $$
CREATE TRIGGER user_nick_update
AFTER UPDATE 
ON user
FOR EACH ROW
BEGIN 
DECLARE nick VARCHAR(40);
DECLARE name VARCHAR(40);
set nick = new.nickname ;
SET name = OLD.nickname ;

IF nick != name THEN
UPDATE total SET nickname=nick WHERE nickname = name;
UPDATE CGAME1 SET nickname=nick WHERE nickname = name;
UPDATE CGAME2 SET nickname=nick WHERE nickname = name;
UPDATE CGAME3 SET nickname=nick WHERE nickname = name;
END IF;
END$$        
DELIMITER ;


-- user table에서 삭제시 모든 테이블에서 해당 닉네임의 정보가 삭제되고 
-- old 테이블로 user 정보가 넘어가는 트리거 만들기


drop trigger IF EXISTS user_drop_old;

DELIMITER $$
CREATE TRIGGER user_drop_old
before delete
on user
for each row
begin
DECLARE nick VARCHAR(40);
set nick = old.nickname;
insert into olduser values (null, old.id, old.pw, old.mail, old.name, old.nickname);  
delete from total where nickname = nick;
delete from cgame1 where nickname = nick;
delete from cgame2 where nickname = nick;
delete from cgame3 where nickname = nick;
END$$        
DELIMITER ;















