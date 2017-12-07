package edu.mum.eventmanagement;

import javafx.scene.control.TextField;

public final class WindowUtils {
	public static void validateDate(TextField textField) {

		String text = textField.getText();
		if (text == null || text.length() < 10) {
			return;
		}
		
		text = text.substring(0, 10);

		String month = text.substring(0, 2);
		String day = text.substring(3, 5);
		String year = text.substring(6, 10);

		for (char c : month.toCharArray()) {
			if (!Character.isDigit(c)) {
				textField.setText("");
				return;
			}
		}

		for (char c : day.toCharArray()) {
			if (!Character.isDigit(c)) {
				textField.setText("");
				return;
			}
		}

		for (char c : year.toCharArray()) {
			if (!Character.isDigit(c)) {
				textField.setText("");
				return;
			}
		}

		int iMonth = Integer.parseInt(month);
		int iDay = Integer.parseInt(day);
		int iYear = Integer.parseInt(year);
		
		if (iMonth < 1 || iMonth > 12) {
			textField.setText("");
			return;
		}
		
		if(iDay < 1 || iDay > 31) {
			textField.setText("");
			return;
		}
		
		if(iYear < 2000 || iYear > 2099) {
			textField.setText("");
			return;
		}
		
		textField.setText(text);
	}
}