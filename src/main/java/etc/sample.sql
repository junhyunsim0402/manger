DROP DATABASE IF EXISTS CompanyDB;
CREATE DATABASE CompanyDB;
USE CompanyDB;

-- 1. 부서 테이블
CREATE TABLE Department (
    dept_key INT PRIMARY KEY,
    dept_name VARCHAR(50) NOT NULL
);

-- 2. 사원 테이블
CREATE TABLE Employee (
    emp_code INT PRIMARY KEY,
    emp_name VARCHAR(50) NOT NULL,
    dept_key INT,
    position VARCHAR(50),
    emp_photo VARCHAR(100),
    FOREIGN KEY (dept_key) REFERENCES Department(dept_key)
);

-- 3. 사원 휴가 테이블
CREATE TABLE Employee_Leave (
    leave_code INT PRIMARY KEY,
    emp_code INT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    leave_reason VARCHAR(255),
    FOREIGN KEY (emp_code) REFERENCES Employee(emp_code)
);

INSERT INTO Department VALUES (1, '개발팀');
INSERT INTO Department VALUES (2, '디자인팀');
INSERT INTO Department VALUES (3, '기획팀');

INSERT INTO Employee VALUES (100, '김민준', 1, '선임 개발자', 'img-1');
INSERT INTO Employee VALUES (101, '이서연', 2, '수석 디자이너', 'img-2');
INSERT INTO Employee VALUES (102, '박도윤', 3, '팀장', 'img-3');
INSERT INTO Employee VALUES (103, '유재석', 1, '대리', 'img-4');

INSERT INTO Employee_Leave VALUES (200, 100, '2025-08-04', '2025-08-04', '병원 진료');
INSERT INTO Employee_Leave VALUES (201, 101, '2025-07-21', '2025-07-25', '여름 휴가');