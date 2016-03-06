package at.bestsolution.lego.ui.handlers;

import javax.inject.Inject;

import org.eclipse.fx.ui.controls.dialog.TitleAreaDialog;

import at.bestsolution.lego.ui.components.LegoAssembly;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

@SuppressWarnings({ "restriction" })
public class NewAssemblyDialog extends TitleAreaDialog {
	private TextField field;

	@Inject
	public NewAssemblyDialog() {
		super("Create assembly", "Create assembly", "Create a new assembly");
		setClientArea(createClientArea());
		addDefaultButtons();
		setMinWidth(300);
	}

	private Node createClientArea() {
		HBox box = new HBox();
		box.getStyleClass().add("new-assembly-form");
		box.setAlignment(Pos.CENTER_LEFT);
		Label l = new Label("Name");
		field = new TextField();
		HBox.setHgrow(field, Priority.ALWAYS);
		box.getChildren().addAll(l, field);

		return box;
	}

	@Override
	protected void handleOk() {
		LegoAssembly a = new LegoAssembly();
		a.setName(field.getText());
		/* TODO 4 Publish the new assembly created on topic Constants.TOPIC_NEW_ASSEMBLY*/
		super.handleOk();
	}
}
