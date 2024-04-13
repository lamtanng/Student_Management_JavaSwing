/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Utils;

/**
 *
 * @author ADMIN Columns name of JTable Type: String[]
 */
public class U_ColumnTitles {

    public static final String ACTION_TITLE = "Action";
    public static final String ORDER_TITLE = "Order";

    public class ACCOUNT {

        public static final String USERNAME = "Username";
        public static final String PASSWORD = "Password";
        public static final String ROLE = "Role";
        public static final String[] COLUMNS_TITLE = {ACCOUNT.USERNAME, ACCOUNT.PASSWORD, ACCOUNT.ROLE};

    }

    public static class TEACHER {

        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String BIRTH_DAY = "Birth date";
        public static final String GENDER = "Gender";
        public static final String ADDRESS = "Address";
        public static final String PHONE = "Phone";
        public static final String EMAIL = "Email";
        public static final String USERNAME = "Username";
        public static final String[] COLUMNS_TITLE = {TEACHER.ID, TEACHER.NAME, TEACHER.BIRTH_DAY,
            TEACHER.GENDER, TEACHER.ADDRESS, TEACHER.PHONE,
            TEACHER.EMAIL, TEACHER.USERNAME};
    }

    public static class COURSE {

        public static final String ID = "ID";
        public static final String TITLE = "Title";
        public static final String[] COLUMNS_TITLE = {COURSE.ID, COURSE.TITLE};
    }

    public static class CLASS {

        public static final String ID = "ID";
        public static final String TITLE = "Title";
        public static final String COURSE = "Course";
        public static final String PERIOD_TOTAL = "Period total";
        public static final String FEE = "Fee";
        public static final String[] COLUMNS_TITLE = {U_ColumnTitles.CLASS.ID, U_ColumnTitles.CLASS.TITLE,
            U_ColumnTitles.CLASS.COURSE, U_ColumnTitles.CLASS.PERIOD_TOTAL, U_ColumnTitles.CLASS.FEE};

    }

    public static class ROOM {

        public static final String ID = "ID";
        public static final String SEAT = "Seat";
        public static final String[] COLUMNS_TITLE = {ROOM.ID, ROOM.SEAT};

    }

    public static class SHIFT {

        public static final String ID = "ID";
        public static final String START_TIME = "Start";
        public static final String END_TIME = "End";
        public static final String[] COLUMNS_TITLE = {SHIFT.ID, SHIFT.START_TIME, SHIFT.END_TIME};

    }

    public static class CLASS_GROUP {

        public static final String ID = "ID";
        public static final String CLASS = "Class";
        public static final String TEACHER = "Teacher";
        public static final String CLASSROOM = "Room";
        public static final String SHIFT = "Shift";
        public static final String STUDENT_MIN = "Student min";
        public static final String STUDENT_MAX = "Student max";
        public static final String START_DATE = "Start";
        public static final String PERIOD_CHECKED = "Checked";
        public static final String REGISTER_STATUS = "Register";
        public static final String DAY_OF_WEEK = "Day of week";
        public static final String IS_OPEN = "OPENING";

        public static final String[] COLUMNS_TITLE = {CLASS_GROUP.ID, CLASS_GROUP.CLASS, CLASS_GROUP.TEACHER,
            CLASS_GROUP.CLASSROOM, CLASS_GROUP.SHIFT,
            CLASS_GROUP.STUDENT_MIN, CLASS_GROUP.STUDENT_MAX,
            CLASS_GROUP.START_DATE, CLASS_GROUP.PERIOD_CHECKED,
            CLASS_GROUP.REGISTER_STATUS, CLASS_GROUP.DAY_OF_WEEK, CLASS_GROUP.IS_OPEN};

    }

    public static class STUDENT {

        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String BIRTH_DAY = "Birth date";
        public static final String GENDER = "Gender";
        public static final String ADDRESS = "Address";
        public static final String PHONE = "Phone";
        public static final String USERNAME = "Username";
        public static final String IMAGE = "Image";
        public static final String[] COLUMNS_TITLE = {STUDENT.ID, STUDENT.NAME, STUDENT.BIRTH_DAY,
            STUDENT.GENDER, STUDENT.ADDRESS, STUDENT.PHONE, STUDENT.USERNAME, STUDENT.IMAGE};

    }

    public static class NOTIFICATION {

        public static final String ID = "ID";
        public static final String TEACHER = "Teacher";
        public static final String TITLE = "Title";
        public static final String DESCRIPTION = "Description";
        public static final String CLASS_GROUP = "Group";
        public static final String[] COLUMNS_TITLE = {
            NOTIFICATION.ID, NOTIFICATION.TEACHER, NOTIFICATION.TITLE,
            NOTIFICATION.DESCRIPTION, NOTIFICATION.CLASS_GROUP};
    }

    public class GRADE_DETAIL {

        public static final String ID = "ID";
        public static final String GROUP = "Group";
        public static final String STUDENT = "Student";
        public static final String THEORY_MARK = "Theory Mark";
        public static final String PRACTICE_MARK = "Practice Mark";
        public static final String PAY_STATUS = "Pay Status";
        public static final String CERTIFICATE = "Certificate";
        public static final String[] COLUMNS_TITLE = {GRADE_DETAIL.ID, GRADE_DETAIL.GROUP, GRADE_DETAIL.STUDENT,
            GRADE_DETAIL.THEORY_MARK, GRADE_DETAIL.PRACTICE_MARK,
            GRADE_DETAIL.PAY_STATUS, GRADE_DETAIL.CERTIFICATE};
    }

    public static class ATTENDANCE_RECORD {

        public static final String ID = "ID";
        public static final String CHECK_DATE = "Date";
        public static final String CLASS_GROUP = "Group";
        public static final String STUDENT = "Student";
        public static final String IS_PRESENT = "Present";
        public static final String[] COLUMNS_TITLE = {
            ATTENDANCE_RECORD.ID, ATTENDANCE_RECORD.CHECK_DATE, ATTENDANCE_RECORD.CLASS_GROUP,
            ATTENDANCE_RECORD.STUDENT, ATTENDANCE_RECORD.IS_PRESENT};
    }
}
