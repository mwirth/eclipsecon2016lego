package at.bestsolution.lego.ui;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.fx.core.di.ContextValue;
import org.eclipse.fx.ui.controls.sceneviewer.Viewer3d;

import at.bestsolution.lego.ui.components.LegoAssembly;
import at.bestsolution.lego.ui.components.LegoElement;
import javafx.beans.property.Property;
import javafx.scene.layout.BorderPane;

public class ModelViewer  {
	private Viewer3d viewer;

	@Inject
	public ModelViewer(BorderPane parent, @ContextValue("legoElement") Property<LegoElement> element) {
		viewer = new Viewer3d();
		viewer.setOnOpenItem( e -> {
			element.setValue((LegoElement)e.getItemNode());
		});
		parent.setCenter(viewer);
	}

	@Inject
	public void setModel(@Optional @Named("legoAssembly") LegoAssembly element) {
		viewer.setContent(element);
	}
}
