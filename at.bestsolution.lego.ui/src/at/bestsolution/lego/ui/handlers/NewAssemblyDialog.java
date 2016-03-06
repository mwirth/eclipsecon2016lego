package at.bestsolution.lego.ui.handlers;

import javax.inject.Inject;

import org.eclipse.fx.core.event.EventBus;
import org.eclipse.fx.ui.controls.dialog.TitleAreaDialog;

import at.bestsolution.lego.ui.Constants;
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

	private final EventBus eventBus;

	@Inject
	public NewAssemblyDialog(EventBus eventBus) {
		super("Create assembly", "Create assembly", "Create a new assembly");
		this.eventBus = eventBus;
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
		box.getChildren().addAll(l,field);

		return box;
	}

	@Override
	protected void handleOk() {
		LegoAssembly a = new LegoAssembly();
		a.setName(field.getText());
		eventBus.publish(Constants.TOPIC_NEW_ASSEMBLY, a, true);
		super.handleOk();
	}
}
