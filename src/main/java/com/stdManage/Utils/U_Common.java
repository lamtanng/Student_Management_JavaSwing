/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stdManage.Utils;

import com.stdManage.Views.Components.TextField;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import javax.swing.ImageIcon;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author ADMIN
 */
public class U_Common {

    //variables
    public static final String RESOURCE = "src/main/resources/";
    public static final String IMAGE_FOLDER = "images/";
    public static final String PROFILE_FOLDER = "ProfileImage/";
    public static final String[] GENDER = {"Nam", "Nữ"};

    //functions
    public static final ImageIcon createImageIcon(String imgName, String folder) {
        ImageIcon icon = new ImageIcon(RESOURCE.concat(folder).concat(imgName));
        return icon;
    }

    public static final LocalDate toLocalDate(JDatePickerImpl datePicker) {
        java.util.Date getDate = (java.util.Date) datePicker.getModel().getValue();
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(getDate);
        return LocalDate.parse(dateFormat);
    }

    public static final JDatePickerImpl createDatePicker(Date selectedDate) {
        UtilDateModel model = new UtilDateModel();
        model.setValue(selectedDate);
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        return new JDatePickerImpl(datePanel, new DateComponentFormatter());

    }

    public static final TextField createTextField(String lableText, String text) {
        TextField txt = new TextField();
        txt.setLabelText(lableText);
        txt.setText(text);
        return txt;
    }
    
    
    //class
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
