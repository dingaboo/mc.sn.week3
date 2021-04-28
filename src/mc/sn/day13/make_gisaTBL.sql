--테이블 생성
--기사시험에 나오는 데이터를 입력하는 테이블인데,
--속성의 이름을 StudentDTO의 필드이름으로 만들어 보세요
--int형은 NUMBER 크기가 없는 것으로
--String형은 VARCHAR2로 크기가 50인 것으로 만드세요
--stdNO는 p.k로 선정하고 모든 컬럼(속성은) not null임.
--table이름 gisaTBL입니다.

CREATE TABLE gisaTBL(
	stdNo NUMBER PRIMARY KEY,
	email VARCHAR(50) NOT NULL,
	kor NUMBER NOT NULL,
	eng NUMBER NOT NULL,
	math NUMBER NOT NULL,
	sci NUMBER NOT NULL,
	hist NUMBER NOT NULL,
	total NUMBER NOT NULL,
	MgrCode VARCHAR(50) NOT NULL,
	accPoint VARCHAR(50) NOT NULL,
	localCode VARCHAR(50) NOT NULL
);

drop table gisaTBL;

SELECT * FROM gisaTBL;
select count(*) from gisaTBL;