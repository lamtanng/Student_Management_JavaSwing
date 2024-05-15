package Ultils;

import java.util.ArrayList;
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

}
