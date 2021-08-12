package com.luanpaiva.jogodavelha.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class Alerts {

	public static void showAlerts(ImageView graphic, String title, String headerText, String contentText, AlertType type) {
		Alert alert = new Alert(type);
		alert.setGraphic(graphic);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.show();
	}

}
