package edu.mum.eventmanagement;

import javafx.scene.control.TextField;

public final class WindowUtils {
	public static void validateDate(TextField textField) {

		String text = textField.getText();
		if (text == null)
			return;

		if (text.length() > 2 && text.charAt(2) != '/') {
			text = text.substring(0, 2) + "/" + text.substring(2);
		}
		
		if(text.length() > 5 && text.charAt(5) != '/') {
			text = text.substring(0, 5) + "/" + text.substring(5);
		}

		if (text.length() > 9) {
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

			if (iDay < 1 || iDay > 31) {
				textField.setText("");
				return;
			}

			if (iYear < 2000 || iYear > 2099) {
				textField.setText("");
				return;
			}
		}

		textField.setText(text);
		textField.positionCaret(text.length());
	}
}