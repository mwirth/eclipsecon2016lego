package at.bestsolution.lego.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.fx.core.event.EventBus;

import at.bestsolution.lego.ui.components.LegoAssembly;
import at.bestsolution.lego.ui.components.LegoBrick;
import at.bestsolution.lego.ui.components.LegoRasterElement.Rotation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class AssemeblyListView {

	static class AssemblyCell extends ListCell<LegoAssembly> {
		@Override
		protected void updateItem(LegoAssembly item, boolean empty) {
			super.updateItem(item, empty);
			if( item != null && ! empty ) {
				setText(item.getName());
			} else {
				setText("");
			}
		}
	}

	private final EventBus eventBus;

	@Inject
	public AssemeblyListView(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@PostConstruct
	void init(BorderPane parent) {
		parent.getStyleClass().add("assembly-list-form");
		ListView<LegoAssembly> view = new ListView<>();
		view.setCellFactory( v -> new AssemblyCell());

		/*  TODO 1 Publish the current selected item from the list as Constants.CURRENT_LEGO_ASSEMBLY*/

		ObservableList<LegoAssembly> list = FXCollections.observableArrayList(createSampleAssembly());
		view.setItems(list);

		/* TODO 5 Subscribe to the  Constants.TOPIC_NEW_ASSEMBLY and update the list of items*/

		parent.setCenter(view);
	}

	private LegoAssembly createSampleAssembly() {
		LegoAssembly assembly = new LegoAssembly();
		assembly.setName("Sample 1");

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
		return assembly;
	}
}
