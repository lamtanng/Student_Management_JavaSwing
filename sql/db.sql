create database schedule_management;
use schedule_management1;

CREATE TABLE account(
    username VARCHAR(20) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE teacher (
    _id VARCHAR(20) PRIMARY KEY,
    full_name NVARCHAR(50) NOT NULL,
    birth_date DATE,
    gender NVARCHAR(20) NOT NULL,
    address NVARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(50) NOT NULL,
    username VARCHAR(20),
    CONSTRAINT fk_teacher_account FOREIGN KEY (username) REFERENCES account(username) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE course (
    _id VARCHAR(20) PRIMARY KEY,
    name NVARCHAR(50) NOT NULL
);

CREATE TABLE class (
    _id VARCHAR(20) PRIMARY KEY,
    course_id VARCHAR(20),
    name NVARCHAR(50) NOT NULL,
    period_total INT,
    fee DECIMAL(10, 2),
    CONSTRAINT fk_class_course FOREIGN KEY (course_id) REFERENCES course(_id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE classroom (
    _id VARCHAR(20) PRIMARY KEY,
    seat INT
);


CREATE TABLE shift (
    _id VARCHAR(20) PRIMARY KEY,
    start_time VARCHAR(10) NOT NULL,
    end_time VARCHAR(10) NOT NULL
);


CREATE TABLE class_group (
    _id VARCHAR(20) PRIMARY KEY,
    class_id VARCHAR(20),
    teacher_id VARCHAR(20),
    classroom_id VARCHAR(20),
    shift_id VARCHAR(20),
    students_min INT,
    students_max INT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    register_status BIT NOT NULL DEFAULT 0,
    day_of_week VARCHAR(20),
    CONSTRAINT fk_classgroup_class FOREIGN KEY (class_id) REFERENCES class(_id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_classgroup_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(_id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_classgroup_classroom FOREIGN KEY (classroom_id) REFERENCES classroom(_id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_classgroup_shift FOREIGN KEY (shift_id) REFERENCES shift(_id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE student (
    _id VARCHAR(20) PRIMARY KEY,
    full_name NVARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    gender NVARCHAR(10) NOT NULL,
    address NVARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    username VARCHAR(20),
    CONSTRAINT fk_student_account FOREIGN KEY (username) REFERENCES account(username) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE notification (
    _id VARCHAR(20) PRIMARY KEY,
    teacher_id VARCHAR(20),
    title NVARCHAR(50),
    description NVARCHAR(255) NOT NULL,
    CONSTRAINT fk_noti_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(_id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE grade_detail (
    _id VARCHAR(20) PRIMARY KEY,
    group_id VARCHAR(20),
    student_id VARCHAR(20),
    theory_mark REAL,
    practice_mark REAL,
    pay_status BIT NOT NULL DEFAULT 0,
    certificate_status BIT NOT NULL DEFAULT 0,
    CONSTRAINT fk_gradedetail_classgroup FOREIGN KEY (group_id) REFERENCES class_group(_id) ON DELETE CASCADE,
    CONSTRAINT fk_noti_classgroup FOREIGN KEY (class_group_id) REFERENCES class_group(_id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_gradedetail_student FOREIGN KEY (student_id) REFERENCES student(_id) ON DELETE CASCADE
);

CREATE TABLE attendance_record (
	_id VARCHAR(20) PRIMARY KEY,
    check_date DATE,
    class_group_id VARCHAR(20),
    student_id VARCHAR(20),
    is_present BIT NOT NULL DEFAULT 0,
    CONSTRAINT fk_attendancerecord_classgroup FOREIGN KEY (class_group_id) REFERENCES class_group(_id) ON DELETE CASCADE,
    CONSTRAINT fk_attendancerecord_student FOREIGN KEY (student_id) REFERENCES student(_id) ON DELETE CASCADE
);

SET SQL_SAFE_UPDATES = 0;
ALTER TABLE student
ADD COLUMN image VARCHAR(255) DEFAULT "";

ALTER TABLE teacher
ADD COLUMN image VARCHAR(255) DEFAULT "";
Update teacher set image = '';

ALTER TABLE course
ADD COLUMN status boolean default true;

ALTER TABLE class
ADD COLUMN status boolean default true;

ALTER TABLE class_group
ADD COLUMN status boolean default true;

ALTER TABLE class_group
ADD COLUMN is_open boolean default false;

ALTER TABLE grade_detail
ADD COLUMN status boolean default true;

ALTER TABLE class
DROP CONSTRAINT fk_class_course;

ALTER TABLE class
ADD CONSTRAINT fk_class_course FOREIGN KEY (course_id)
REFERENCES course(_id)
ON UPDATE CASCADE;

ALTER TABLE class_group
DROP CONSTRAINT fk_classgroup_class;

ALTER TABLE class_group
ADD CONSTRAINT fk_classgroup_class FOREIGN KEY (class_id) REFERENCES class(_id) ON UPDATE CASCADE;

ALTER TABLE grade_detail
DROP CONSTRAINT fk_gradedetail_classgroup;	

ALTER TABLE notification
ADD CONSTRAINT fk_classgroup_class FOREIGN KEY (class_id) REFERENCES class(_id) ON UPDATE CASCADE;

--
ALTER TABLE class_group
RENAME COLUMN end_date TO period_checked;
ALTER TABLE class_group
MODIFY COLUMN period_checked int default 0 ;
ALTER TABLE class_group
MODIFY COLUMN students_min int default 0 ;
ALTER TABLE class_group
MODIFY COLUMN students_max int default 0 ;
ALTER TABLE class_group
ADD CONSTRAINT chk_students_classgroup CHECK (students_min >= 0 and students_min <= students_max);
