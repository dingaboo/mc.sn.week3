--���̺� ����
--�����迡 ������ �����͸� �Է��ϴ� ���̺��ε�,
--�Ӽ��� �̸��� StudentDTO�� �ʵ��̸����� ����� ������
--int���� NUMBER ũ�Ⱑ ���� ������
--String���� VARCHAR2�� ũ�Ⱑ 50�� ������ ���弼��
--stdNO�� p.k�� �����ϰ� ��� �÷�(�Ӽ���) not null��.
--table�̸� gisaTBL�Դϴ�.

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