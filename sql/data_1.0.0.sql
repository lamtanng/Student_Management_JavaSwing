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
    (uf_AutoGenerateID("teacher"), 'Nguyễn Văn A', '1990-01-01', 'Nam', '123 ABC Street, XYZ City', '123456789', 'nguyenvana@example.com', 'teacher1'),
    (uf_AutoGenerateID("teacher"), 'Trần Thị B', '1985-05-10', 'Nữ', '456 DEF Street, XYZ City', '987654321', 'tranthib@example.com', 'teacher2'),
    (uf_AutoGenerateID("teacher"), 'Nguyễn Thị E', '1995-07-05', 'Nữ', '654 MNO Street, XYZ City', '777777777', 'nguyenthie@example.com', 'teacher3');

Select * from course;
insert into course values
	("JAVA", "Lập trình Java"),
	("CPP", "Lập trình C++"),
	("PYTHON", "Lập trình Python");

select * from class;
insert into class values
	("JAVA_CB", "JAVA", "Java cơ bản", 1, 500000),
	("JAVA_NC", "JAVA", "Class nâng cao", 2, 350000),
	("CPP_CB", "CPP", "C++ cơ bản", 1, 750000),
	("PYTHON_CB", "PYTHON", "Python cơ bản", 2, 500000);

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
INSERT INTO class_group (_id, class_id, teacher_id, classroom_id, shift_id, students_min, students_max, start_date, end_date, register_status, day_of_week)
VALUES
    ( "",'JAVA_CB', 'TEA001', 'ROOM001', "SHIFT001", 1, 3, '2024-04-01', '2024-06-30', 1, '2'),
    ('2', 'JAVA_NC', 'TEA002', 'ROOM002',"SHIFT002", 1, 3, '2024-04-01', '2024-06-30', 0, '3'),
    ('3', 'CPP_CB', 'TEA003', 'ROOM003', "SHIFT003", 1, 2, '2024-04-01', '2024-06-30', 0, '4'),
    ('4', 'PYTHON_CB', 'TEA001', 'ROOM001', "SHIFT004", 1, 3, '2024-04-01', '2024-06-30', 1, '6'),
    ('5', 'PYTHON_CB', 'TEA003', 'ROOM003', "SHIFT001", 1, 3, '2024-04-01', '2024-06-30', 1, '5');
Select * from class_group where _id = "CPP_CB01";
SELECT student._id, student.full_name 
FROM student JOIN grade_detail 
ON student._id = grade_detail.student_id 
WHERE grade_detail.group_id <> "CPP_CB01"
GROUP BY student._id, student.full_name ;


insert into student values
    (uf_AutoGenerateID("student"), 'Nguyễn Tấn Lâm', '1990-01-01', 'Nam', '123 ABC Street, XYZ City', '123456789', 'student1'),
    (uf_AutoGenerateID("student"), 'Nguyễn Vũ Trương Giang', '1985-05-10', 'Nam', '456 DEF Street, XYZ City', '987654321','student2'),
    (uf_AutoGenerateID("student"), 'Lê Duy Khiêm', '1995-07-05', 'Nam', '654 MNO Street, XYZ City', '777777777', 'student3');


Select * from notification;
insert into notification values
    (uf_AutoGenerateID("notification"), "JAVA_CB01", 'TEA001', "Thông báo 1", "Nội dung thông báo 1" ),
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