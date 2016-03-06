package at.bestsolution.lego.ui;

import javax.inject.Inject;

import org.eclipse.fx.core.di.ContextValue;
import org.eclipse.fx.ui.controls.image.FontIconView;
import org.eclipse.fx.ui.controls.sceneviewer.Viewer3d;

import at.bestsolution.lego.ui.components.LegoAssembly;
import at.bestsolution.lego.ui.components.LegoElement;
import javafx.beans.property.Property;
import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class ModelViewer  {
	private Viewer3d viewer;
	private LegoElement currentElement;

	@Inject
	public ModelViewer(BorderPane parent,
			@ContextValue(Constants.CURRENT_LEGO_BRICK) Property<LegoElement> element) {
		parent.getStyleClass().add("model-viewer");
		viewer = new Viewer3d();
		viewer.setOnOpenItem( e -> {
			element.setValue((LegoElement)e.getItemNode());
		});
		viewer.setMinWidth(400);
		viewer.setMinHeight(400);

		ToolBar tb = new ToolBar();
		tb.setOrientation(Orientation.VERTICAL);

		{
			Button b = new Button();
			b.setGraphic(new FontIconView());
			b.getStyleClass().add("zoom-in");
			b.setOnAction( e -> viewer.zoomIn(0.1));
			tb.getItems().add(b);
		}

		{
			Button b = new Button();
			b.setGraphic(new FontIconView());
			b.getStyleClass().add("zoom-out");
			b.setOnAction( e -> viewer.zoomOut(0.1));
			tb.getItems().add(b);
		}

		{
			ToggleButton b = new ToggleButton();
			b.setGraphic(new FontIconView());
			b.getStyleClass().add("rotate");
			viewer.contentRotateProperty().bindBidirectional(b.selectedProperty());
			tb.getItems().add(b);
		}

		parent.setLeft(tb);
		parent.setCenter(viewer);
	}

	/* TODO 2 Retrieve the current selection assembly  */
	public void setModel(LegoElement element) {
		if( currentElement != null && currentElement instanceof LegoAssembly ) {
			element.getChildren().removeListener( this::handleChange);
		}
		if( element instanceof LegoAssembly ) {
			element.getChildren().addListener( this::handleChange);
		}
		viewer.setContent(element);
	}

	private void handleChange(Change<? extends Node> c) {
		viewer.reevaluate();
	}
}
