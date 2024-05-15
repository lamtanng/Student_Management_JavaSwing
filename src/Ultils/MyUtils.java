package Ultils;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

public class MyUtils {

    static ArrayList<String> errorList = new ArrayList<String>();

    public static void showErrorMessage(String title, String errorMsg) {
        JOptionPane.showMessageDialog(null, errorMsg, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String title, String errorMsg) {
        JOptionPane.showMessageDialog(null, errorMsg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void addErrorMessage(String title) {
        errorList.add(title);
    }

    public static void showErrorList() {
        String errorStr = "";
        for (String string : errorList) {
            errorStr = errorStr.concat(string + "\n");
        }
        JOptionPane.showMessageDialog(null, errorStr, "Error", JOptionPane.ERROR_MESSAGE);
        clearErrorList();
    }

    public static void clearErrorList() {
        errorList = new ArrayList<String>();
    }

    public static boolean isErrorListEmpty() {
        return errorList.isEmpty();
    }

    /*
    * value: string to validate
    * type: name of format (phone, mail,...)
     */
    public static boolean isValid(String value, String type) {

        value = value.trim();
        type = type.trim();
        
        if (value == null || value.isBlank() || type == null || type.isBlank()) {
            return false; // Handle null or empty values
        }

        switch (type) {
            case Constant.PHONE_TYPE:
                // Simple phone number validation (US format)
                return value.matches(Regex.PHONE);
            case Constant.EMAIL_TYPE:
                // Improved email validation with stricter checks
                return value.matches(Regex.EMAIL);
            case Constant.TEXT_TYPE:    
                // Improved text validation with stricter checks
                return value.matches(Regex.TEXT);
            default:
                return false;
//                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    public static int calculateAge(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        LocalDate today = LocalDate.now();
        LocalDate birthday = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return today.getYear() - birthday.getYear() - ((today.getMonthValue() > birthday.getMonthValue()
                || (today.getMonthValue() == birthday.getMonthValue() && today.getDayOfMonth() >= birthday.getDayOfMonth())) ? 0 : 1);
    }
}
