package at.bestsolution.lego.ui;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.fx.core.di.ContextValue;
import org.eclipse.fx.ui.controls.sceneviewer.Viewer3d;

import at.bestsolution.lego.ui.components.LegoAssembly;
import at.bestsolution.lego.ui.components.LegoElement;
import javafx.beans.property.Property;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class ModelViewer  {
	private Viewer3d viewer;
	private LegoElement currentElement;

	@Inject
	public ModelViewer(BorderPane parent,
			@ContextValue(Constants.CURRENT_LEGO_BRICK) Property<LegoElement> element) {
		viewer = new Viewer3d();
		viewer.setOnOpenItem( e -> {
			element.setValue((LegoElement)e.getItemNode());
		});
		viewer.setMinWidth(400);
		viewer.setMinHeight(400);

		parent.setCenter(viewer);
	}

	@Inject
	public void setModel(@Optional @Named(Constants.CURRENT_LEGO_ASSEMBLY) LegoElement element) {
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
