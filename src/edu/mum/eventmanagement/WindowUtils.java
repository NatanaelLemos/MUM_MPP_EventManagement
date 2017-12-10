package edu.mum.eventmanagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import edu.mum.eventmanagement.models.Location;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setDateColumn(TableColumn column) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		column.setCellFactory(c -> {
		    TableCell<Object, Date>  cell = new TableCell<Object, Date>() {
		        @Override
		        protected void updateItem(Date item, boolean empty) {
		            super.updateItem(item, empty);
		            if(empty) {
		                setText(null);
		            }
		            else {
		                setText(dateFormat.format(item));
		            }
		        }
		    };

		    return cell;
		});
	}

	
	public static <TEntity> void loadCombobox(ComboBox<TEntity> comboBox, List<TEntity> dataSource, final Function<TEntity, ?> keyExtractor) {
		comboBox.getItems().clear();
		comboBox.setConverter(new StringConverter<TEntity>() {
			@Override public String toString(TEntity object) {
				return keyExtractor.apply(object).toString();
			}

			@Override public TEntity fromString(String string) {
				return comboBox.getItems().stream().filter(i -> keyExtractor.apply(i).equals(string)).findFirst().orElse(null);
			}
		});
		
		comboBox.getItems().setAll(dataSource);
	}
}