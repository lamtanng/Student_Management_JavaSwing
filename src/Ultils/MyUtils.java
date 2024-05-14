package Ultils;
import javax.swing.JOptionPane;

public class MyUtils {
	public static void showErrorMessage(String title, String errorMsg) {
		JOptionPane.showMessageDialog(null, errorMsg, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showInfoMessage(String title, String errorMsg) {
		JOptionPane.showMessageDialog(null, errorMsg, title, JOptionPane.INFORMATION_MESSAGE);
	}
}
