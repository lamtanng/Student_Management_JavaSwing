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
import com.stdManage.Views.Swing.Button;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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
    private static boolean isButton(Component component) {
        if (component instanceof JButton || component instanceof Button) {
            return true;
        }
        return false;
    }

    public static void setFocusable(Container c) {
        for (Component component : getAllComponents(c)) {
            if (isButton(component)) {
                ((JButton) component).setFocusable(false);
            }
        }
    }

    private static List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
        }
        return compList;
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
