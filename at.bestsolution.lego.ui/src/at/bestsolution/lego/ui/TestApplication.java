package at.bestsolution.lego.ui;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.fx.core.databinding.JFXRealm;

import at.bestsolution.lego.ui.components.LegoAssembly;
import at.bestsolution.lego.ui.components.LegoBrick;
import at.bestsolution.lego.ui.components.LegoElement;
import at.bestsolution.lego.ui.components.LegoRasterElement.Rotation;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;

public class TestApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane parent = new BorderPane();
		ObjectProperty<LegoElement> element = new SimpleObjectProperty<LegoElement>();
		ModelViewer viewer = new ModelViewer(parent, element);

		JFXRealm.createDefault();

		LegoElementPropertyEditor editor = new LegoElementPropertyEditor();
		BorderPane editorContainer = new BorderPane();
		parent.setRight(editorContainer);


		WritableValue legoBrick = new WritableValue();
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory( cl -> new LegoPropertyEditorController(legoBrick));

		editor.init(editorContainer, loader);
		element.addListener( o -> {
			legoBrick.setValue(element.get());
		});

		LegoAssembly assembly = new LegoAssembly();

		LegoBrick brick = LegoBrick.createSimpleBrick(8, 1, 2);
		brick.setMaterial(new PhongMaterial(Color.GREEN));
		assembly.add(brick);

		brick = LegoBrick.createSimpleBrickWithLocation(2, 2, 2, 4, -1, 0);
		brick.setMaterial(new PhongMaterial(Color.LIGHTGREEN));
		assembly.add(brick);

		brick = LegoBrick.createSimpleBrickWithLocation(2, 4, 1, 6, -1, 1);
		brick.setMaterial(new PhongMaterial(Color.LIGHTBLUE));
		assembly.add(brick);

		brick = LegoBrick.createSimpleBrickWithLocation(2, 2, 2, 4, -3, 0);
		brick.setMaterial(new PhongMaterial(Color.ORANGE));
		assembly.add(brick);

		brick = LegoBrick.createSimpleBrickWithLocation(2, 2, 2, 4, -5, 0);
		brick.setMaterial(new PhongMaterial(Color.RED));
		assembly.add(brick);

		brick = LegoBrick.createSimpleBrickWithLocation(3, 2, 2, 2, -7, 1);
		brick.setRotation(Rotation.ROTATE180);
		brick.setMaterial(new PhongMaterial(Color.RED));
		assembly.add(brick);

		brick = LegoBrick.createSimpleBrickWithLocation(3, 2, 2, 3, -7, 0);
		brick.setMaterial(new PhongMaterial(Color.RED));
		assembly.add(brick);

		brick = LegoBrick.createSimpleBrickWithLocation(4, 6, 2, 0, -1, 0);
		brick.setMaterial(new PhongMaterial(Color.YELLOW));
		assembly.add(brick);

		viewer.setModel(assembly);

		primaryStage.setScene(new Scene(parent,800,600,true));
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
