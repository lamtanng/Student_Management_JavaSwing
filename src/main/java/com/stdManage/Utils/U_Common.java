/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Utils;

import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class U_Common {

    public static final String RESOURCE = "src/main/resources/";
    public static final String IMAGE_FOLDER = "images/";
    public static final String PROFILE_FOLDER = "ProfileImage/";

    public static final ImageIcon createImageIcon(String imgName, String folder) {
        ImageIcon icon = new ImageIcon(RESOURCE.concat(folder).concat(imgName));
        return icon;
    }

    public class ActionTable {

        public static final int EDIT_DELETE = 1;
        public static final int ADD = 2;
        public static final int DELETE = 3;
    }

    public class ROLE {

        public static final String ADMIN = "admin";
        public static final String TEACHER = "teacher";
        public static final String STUDENT = "student";
    }

}
