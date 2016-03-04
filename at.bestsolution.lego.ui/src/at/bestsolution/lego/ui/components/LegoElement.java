package at.bestsolution.lego.ui.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;

public class LegoElement extends Group {
	private StringProperty name = new SimpleStringProperty(this, "name");

	public final StringProperty nameProperty() {
		return this.name;
	}


	public final String getName() {
		return this.nameProperty().get();
	}


	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

}
