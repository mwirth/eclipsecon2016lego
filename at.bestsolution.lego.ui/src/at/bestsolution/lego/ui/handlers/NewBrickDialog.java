package at.bestsolution.lego.ui.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.fx.ui.controls.dialog.TitleAreaDialog;

import at.bestsolution.lego.ui.LegoElementPropertyEditor;
import at.bestsolution.lego.ui.LegoPropertyEditorController;
import at.bestsolution.lego.ui.ModelViewer;
import at.bestsolution.lego.ui.components.LegoAssembly;
import at.bestsolution.lego.ui.components.LegoBrick;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

@SuppressWarnings("restriction")
public class NewBrickDialog extends TitleAreaDialog {
	private final LegoBrick brick;
	private final LegoAssembly assembly;

	@Inject
	public NewBrickDialog(@Named("legoAssembly") LegoAssembly assembly) {
		super("New Brick", "New Brick", "Create a new brick");
		this.assembly = assembly;
		this.brick = LegoBrick.createSimpleBrick(1, 1, 1);
		this.brick.setMaterial(new PhongMaterial(Color.BLACK));
		setClientArea(createClientArea());
		addDefaultButtons();
		setPrefWidth(600);
	}

	private Node createClientArea() {


		BorderPane root = new BorderPane();

		{
			BorderPane viewerContainer = new BorderPane();

			ModelViewer v = new ModelViewer(viewerContainer,new SimpleObjectProperty<>());
			v.setModel(brick);
			root.setCenter(viewerContainer);
		}

		{
			BorderPane viewerContainer = new BorderPane();
			LegoElementPropertyEditor editor = new LegoElementPropertyEditor();
			FXMLLoader loader = new FXMLLoader();
			WritableValue v = new WritableValue();
			loader.setControllerFactory( c -> new LegoPropertyEditorController(v));
			editor.init(viewerContainer, loader);
			v.setValue(brick);
			root.setRight(viewerContainer);
		}

		return root;
	}

	@Override
	protected void handleOk() {
		assembly.add(brick);
		super.handleOk();
	}
}
