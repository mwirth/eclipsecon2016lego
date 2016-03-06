package at.bestsolution.lego.ui;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.eclipse.fx.core.di.LocalInstance;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class LegoElementPropertyEditor {
	@PostConstruct
	public void init(BorderPane parent, @LocalInstance FXMLLoader loader) {
		loader.setLocation(getClass().getResource("LegoPropertyEditor.fxml"));
		try {
			parent.setCenter(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
