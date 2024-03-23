/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Utils;

/**
 *
 * @author ADMIN
 */
public class U_ModelFields {
    public static class ACCOUNT {
        public static final String USERNAME = "userName";
        public static final String PASSWORD = "passWord";
        public static final String ROLE = "role";
    }
    
    public static class TEACHER {
        public static final String ID = "_id";
        public static final String NAME = "full_name";
        public static final String BIRTH_DAY = "birth_date";
        public static final String GENDER = "gender";
        public static final String ADDRESS = "address";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String USERNAME = "username";                                                                                       
    }
    
    public static class COURSE {
        public static final String ID = "_id";
        public static final String NAME = "name";
    }
    
    public static class CLASS {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String COURSE_ID = "course_id";
        public static final String PERIOD_TOTAL = "period_total";
        public static final String FEE = "fee";
    }
    
    public static class CLASS_ROOM {
        public static final String ID = "_id";
        public static final String SEAT = "seat";
    }
    
    public static class SHIFT {
        public static final String ID = "_id";
        public static final String START_TIME = "start_time";
        public static final String END_TIME = "end_time";
    }
    
    public static class CLASS_GROUP {
        public static final String ID = "_id";
        public static final String CLASS_ID = "class_id";
        public static final String TEACHER_ID = "teacher_id";
        public static final String CLASSROOM_ID = "classroom_id";
        public static final String SHIFT_ID = "shift_id";
        public static final String STUDENT_MIN = "students_min";
        public static final String STUDENT_MAX = "students_max";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String REGISTER_STATUS = "register_status";
        public static final String DAY_OF_WEEK = "day_of_week";                                                                                         
    }
    
    public static class STUDENT {
        public static final String ID = "_id";
        public static final String NAME = "full_name";
        public static final String BIRTH_DAY = "birth_date";
        public static final String GENDER = "gender";
        public static final String ADDRESS = "address";
        public static final String PHONE = "phone";
        public static final String USERNAME = "username";                                                                                       
    }
    
    public static class NOTIFICATION {
        public static final String ID = "_id";
        public static final String TEACHER_ID = "teacher_id";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String CLASS_GROUP_ID = "class_group_id";                                                                                    
    }
    
    public static class GRADE_DETAIL {
        public static final String ID = "_id";
        public static final String GROUP_ID = "group_id";
        public static final String STUDENT_ID = "student_id";
        public static final String THEORY_MARK = "theory_mark";
        public static final String PRACTICE_MARK = "practice_mark";
        public static final String PAY_STATUS = "pay_status";
        public static final String CERT_STATUS = "certificate_status";                                                                              
    }
    
    public static class ATTENDANCE_RECORD {
        public static final String ID = "_id";
        public static final String CHECK_DATE = "check_date";
        public static final String CLASS_GROUP_ID = "class_group_id";
        public static final String STUDENT_ID = "student_id";
        public static final String IS_PRESENT = "is_present";                                                                         
    }
}
