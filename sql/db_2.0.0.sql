create database schedule_management;
use schedule_management;

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
	class_group_id VARCHAR(20),
    teacher_id VARCHAR(20),
    title NVARCHAR(50),
    description NVARCHAR(255) NOT NULL,
    CONSTRAINT fk_noti_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(_id) ON DELETE SET NULL ON UPDATE CASCADE,
	CONSTRAINT fk_noti_classgroup FOREIGN KEY (class_group_id) REFERENCES class_group(_id) ON DELETE SET NULL ON UPDATE CASCADE
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


use schedule_management;
SET GLOBAL log_bin_trust_function_creators = 1;


DELIMITER //
CREATE FUNCTION uf_AutoGenerateIDByParameters (
  lastID VARCHAR(20),
  prefix VARCHAR(255),
  size INT
)
RETURNS VARCHAR(20)
BEGIN
  DECLARE nextID VARCHAR(20);
  DECLARE num_nextID INT;
  DECLARE new_size INT;
  
  IF lastID IS NULL OR lastID = '' THEN
    SET lastID = CONCAT(prefix, LPAD('', size - LENGTH(prefix), '0'));
  END IF;
  
  SET lastID = TRIM(lastID);
  SET num_nextID = CAST(REPLACE(lastID, prefix, '') AS UNSIGNED) + 1;
  SET new_size = size - LENGTH(prefix);
  
  SET nextID = CONCAT(prefix, LPAD(num_nextID, new_size, '0'));
  
  RETURN nextID;
END//
DELIMITER ;


DELIMITER $$
CREATE FUNCTION uf_AutoGenerateID(table_name VARCHAR(255)) 
RETURNS VARCHAR(20)
BEGIN
  DECLARE lastID VARCHAR(20);
  DECLARE prefix VARCHAR(255);
  DECLARE num INT;
  DECLARE nextID VARCHAR(20);

  CASE table_name
    WHEN 'teacher' THEN
      SELECT _id INTO lastID FROM teacher ORDER BY _id DESC LIMIT 1;
      SET prefix = 'TEA';
    WHEN 'classroom' THEN
      SELECT _id INTO lastID FROM classroom ORDER BY _id DESC LIMIT 1;
      SET prefix = 'ROOM';
    WHEN 'shift' THEN
      SELECT _id INTO lastID FROM shift ORDER BY _id DESC LIMIT 1;
      SET prefix = 'SHIFT';
    WHEN 'student' THEN
      SELECT _id INTO lastID FROM student ORDER BY _id DESC LIMIT 1;
      SET prefix = 'STU';
	WHEN 'notification' THEN
      SELECT _id INTO lastID FROM notification ORDER BY _id DESC LIMIT 1;
      SET prefix = 'NOTI';
	WHEN 'grade_detail' THEN
      SELECT _id INTO lastID FROM grade_detail ORDER BY _id DESC LIMIT 1;
      SET prefix = 'GRADE';
	WHEN 'attendance_record' THEN
      SELECT _id INTO lastID FROM attendance_record ORDER BY _id DESC LIMIT 1;
      SET prefix = 'REC';
  END CASE;
  
  SET num = 3 + LENGTH(TRIM(prefix));
  
  SET nextID = uf_AutoGenerateIDByParameters(lastID, prefix, num);
  
  RETURN nextID;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER TG_AutoIncrID 
BEFORE INSERT ON class_group
FOR EACH ROW
BEGIN
	DECLARE groupId VARCHAR(20);
	DECLARE classId VARCHAR(20);
	DECLARE sizeOfNum INT DEFAULT 2; -- (+2: 2 digits after the prefix character)
	SELECT NEW.class_id INTO classId;
    SELECT NEW._id INTO groupId;
    
	BEGIN
		DECLARE lastID VARCHAR(20);
		DECLARE prefix VARCHAR(20);
        DECLARE size INT;
        DECLARE nextID VARCHAR(20);
        
        SET prefix = classId; -- Assign the prefix as the character of maLop
        SET size  = LENGTH(TRIM(prefix)) + sizeOfNum; -- Get the length of the prefix
		
		-- Retrieve the most recent NHOMHOC.MaNhomHoc for the given maLop, excluding the newly inserted one
		SELECT _id INTO lastID FROM class_group
		WHERE class_id = classId AND _id != groupId
		ORDER BY _id DESC
		LIMIT 1;
		
		-- Call the function to generate the new ID for NHOMHOC
		SET nextID  = uf_AutoGenerateIDByParameters(lastID, prefix, size);
		
		-- Update the new ID for NHOMHOC
		SET NEW._id = nextID;
	END;
END $$
DELIMITER ;


use schedule_management;

insert into account values
	("admin", "123456", "admin"),
	("teacher1", "123456", "teacher"),
	("teacher2", "123456", "teacher"),	
	("teacher3", "123456", "teacher"),
    ("student1", "123456", "student"),
	("student2", "123456", "student"),
	("student3", "123456", "student");

select * from teacher;
insert into teacher values
    (uf_AutoGenerateID("teacher"), 'Nguyễn Văn A', '1990-01-01', 'Nam', '123 ABC Street, XYZ City', '123456789', 'nguyenvana@example.com', 'teacher1',''),
    (uf_AutoGenerateID("teacher"), 'Trần Thị B', '1985-05-10', 'Nữ', '456 DEF Street, XYZ City', '987654321', 'tranthib@example.com', 'teacher2',''),
    (uf_AutoGenerateID("teacher"), 'Nguyễn Thị E', '1995-07-05', 'Nữ', '654 MNO Street, XYZ City', '777777777', 'nguyenthie@example.com', 'teacher3', '');

Select * from course;
insert into course values
	("JAVA", "Lập trình Java", true),
	("CPP", "Lập trình C++", true),
	("PYTHON", "Lập trình Python", true);

select * from class;
insert into class values
	("JAVA_CB", "JAVA", "Java cơ bản", 1, 500000, true),
	("JAVA_NC", "JAVA", "Class nâng cao", 2, 350000, true),
	("CPP_CB", "CPP", "C++ cơ bản", 1, 750000, true),
	("PYTHON_CB", "PYTHON", "Python cơ bản", 2, 500000, true);

select * from classroom;
insert into classroom values
	(uf_AutoGenerateID("classroom"), 3),
	(uf_AutoGenerateID("classroom"), 5),
	(uf_AutoGenerateID("classroom"), 2);
    
select * from shift;
insert into shift values
	(uf_AutoGenerateID("shift"), "7", "9"),
    (uf_AutoGenerateID("shift"), "9", "11"),
    (uf_AutoGenerateID("shift"), "15", "17"),
    (uf_AutoGenerateID("shift"), "17", "19");
    
select * from class_group;
INSERT INTO class_group (_id, class_id, teacher_id, classroom_id, shift_id, students_min, students_max, start_date, end_date, register_status, day_of_week, is_open)
VALUES
    ( '1','JAVA_CB', 'TEA001', 'ROOM001', "SHIFT001", 1, 3, '2024-04-01', '2024-06-30', 1, '2' , false),
    ('2', 'JAVA_NC', 'TEA002', 'ROOM002',"SHIFT002", 1, 3, '2024-04-01', '2024-06-30', 0, '3', false),
    ('3', 'CPP_CB', 'TEA003', 'ROOM003', "SHIFT003", 1, 2, '2024-04-01', '2024-06-30', 0, '4', false),
    ('4', 'PYTHON_CB', 'TEA001', 'ROOM001', "SHIFT004", 1, 3, '2024-04-01', '2024-06-30', 1, '6', false),
    ('5', 'PYTHON_CB', 'TEA003', 'ROOM003', "SHIFT001", 1, 3, '2024-04-01', '2024-06-30', 1, '5', false);
Select * from class_group where _id = "CPP_CB01";
SELECT student._id, student.full_name 
FROM student JOIN grade_detail 
ON student._id = grade_detail.student_id 
WHERE grade_detail.group_id <> "CPP_CB01"
GROUP BY student._id, student.full_name ;


insert into student values
    (uf_AutoGenerateID("student"), 'Nguyễn Tấn Lâm', '1990-01-01', 'Nam', '123 ABC Street, XYZ City', '123456789', 'student1',''),
    (uf_AutoGenerateID("student"), 'Nguyễn Vũ Trương Giang', '1985-05-10', 'Nam', '456 DEF Street, XYZ City', '987654321','student2',''),
    (uf_AutoGenerateID("student"), 'Lê Duy Khiêm', '1995-07-05', 'Nam', '654 MNO Street, XYZ City', '777777777', 'student3','');


Select * from notification;
insert into notification values
    (uf_AutoGenerateID("notification"), "JAVA_CB01", 'TEA001', "Thông báo 1", "Nội dung thông báo 1"),
    (uf_AutoGenerateID("notification"),"JAVA_NC01" ,'TEA002', "Thông báo 1", "Nội dung thông báo 1");
 

INSERT INTO grade_detail (_id, group_id, student_id, theory_mark, practice_mark, pay_status, certificate_status)
VALUES
    (uf_AutoGenerateID("grade_detail"), 'CPP_CB01', 'STU001', 8.5, 7.2, 1, 0),
    (uf_AutoGenerateID("grade_detail"), 'JAVA_CB01', 'STU002', 7.8, 6.5, 1, 0),
    (uf_AutoGenerateID("grade_detail"), 'JAVA_NC01', 'STU003', 9.2, 8.9, 1, 1),
    (uf_AutoGenerateID("grade_detail"), 'PYTHON_CB01', 'STU002', 6.7, 7.3, 0, 0),
    (uf_AutoGenerateID("grade_detail"), 'PYTHON_CB01', 'STU001', 8.9, 9.1, 1, 1);


select * from attendance_record;
INSERT INTO attendance_record (_id, check_date, class_group_id, student_id, is_present)
VALUES
    (uf_AutoGenerateID("attendance_record"), '2024-04-01', 'CPP_CB01', 'STU001', 1),
    (uf_AutoGenerateID("attendance_record"), '2024-04-02', 'JAVA_CB01', 'STU002', 0),
    (uf_AutoGenerateID("attendance_record"), '2024-04-03', 'JAVA_NC01', 'STU003', 1);
