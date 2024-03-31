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

insert into course values
	("1", "Course 01"),
	("2", "Course 02"),
	("3", "Course 03");

insert into class values
	("1", "1", "Class 01", 1, 500000),
	("2", "1", "Class 02", 2, 350000),
	("3", "2", "Class 03", 1, 750000),
	("4", "3", "Class 04", 2, 500000);
Select * from class where course_id = "1";

insert into classroom values
	("Room1", 3),
	("Room2", 5),
	("Room3", 2);
    
insert into shift values
	(1, "7", "9"),
    (2, "9", "11"),
    (3, "15", "17"),
    (4, "17", "19");
    
INSERT INTO class_group (_id, class_id, teacher_id, classroom_id, shift_id, students_min, students_max, start_date, end_date, register_status, day_of_week)
VALUES
    ('1', '1', 'GV1', 'Room1', 1, 1, 3, '2024-04-01', '2024-06-30', 1, '2'),
    ('2', '2', 'GV2', 'Room2', 2, 1, 3, '2024-04-01', '2024-06-30', 0, '3'),
    ('3', '3', 'GV3', 'Room3', 3, 1, 2, '2024-04-01', '2024-06-30', 0, '4'),
    ('4', '2', 'GV2', 'Room3', 4, 1, 3, '2024-04-01', '2024-06-30', 1, '6'),
    ('5', '4', 'GV3', 'Room1', 1, 1, 3, '2024-04-01', '2024-06-30', 1, '5');
Select * from class_group where _id = 1;

insert into student values
    ('ST1', 'Nguyễn Tấn Lâm', '1990-01-01', 'Nam', '123 ABC Street, XYZ City', '123456789', 'student1'),
    ('ST2', 'Nguyễn Vũ Trương Giang', '1985-05-10', 'Nam', '456 DEF Street, XYZ City', '987654321','student2'),
    ('ST3', 'Lê Duy Khiêm', '1995-07-05', 'Nam', '654 MNO Street, XYZ City', '777777777', 'student3');
Select * from student;
    
insert into notification values
    ('NOTI1', 'GV1', "Thông báo 1", "Nội dung thông báo 1", "1"),
    ('NOTI2', 'GV2', "Thông báo 1", "Nội dung thông báo 1", "2");
    
INSERT INTO grade_detail (_id, group_id, student_id, theory_mark, practice_mark, pay_status, certificate_status)
VALUES
    ('1', '1', 'ST1', 8.5, 7.2, 1, 0),
    ('2', '1', 'ST2', 7.8, 6.5, 1, 0),
    ('3', '2', 'ST3', 9.2, 8.9, 1, 1),
    ('4', '2', 'ST2', 6.7, 7.3, 0, 0),
    ('5', '3', 'ST1', 8.9, 9.1, 1, 1);
   
select * from grade_detail;
Update grade_detail SET group_id = 3 where student_id = "ST1" and _id = "5";
SELECT student._id, student.full_name 
FROM student JOIN grade_detail ON student._id = grade_detail.student_id 
WHERE grade_detail.group_id <> 2
Group by student._id, student.full_name;


INSERT INTO attendance_record (_id, check_date, class_group_id, student_id, is_present)
VALUES
    ('1', '2024-04-01', '1', 'ST1', 1),
    ('2', '2024-04-02', '1', 'ST2', 0),
    ('3', '2024-04-03', '1', 'ST3', 1);