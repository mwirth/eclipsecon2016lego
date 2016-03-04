package at.bestsolution.lego.ui.components;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class LegoAssembly extends LegoRasterElement {
	public LegoAssembly() {

	}

	public void add(LegoElement element) {
		LegoRasterElement rElement = (LegoRasterElement) element;

		Group g = new Group();
		g.getChildren().add(element);

		Translate t = new Translate();
		t.xProperty().bind(rElement.xLocUnitsProperty().multiply(P));
		t.yProperty().bind(rElement.yLocUnitsProperty().multiply(H));
		t.zProperty().bind(rElement.zLocUnitsProperty().multiply(P));
		g.getTransforms().add(t);

		Rotate r = new Rotate();
		r.setAxis(new Point3D(0, 1, 0));
		ObjectBinding<Integer> angle = Bindings.createObjectBinding( () -> {
			switch (rElement.rotationProperty().get()) {
			case ROTATE0:
				return 0;
			case ROTATE90:
				return 90;
			case ROTATE180:
				return 180;
			default:
				return 270;
			}
		}, rElement.rotationProperty());
		r.angleProperty().bind(angle);
		g.getTransforms().add(r);

		getChildren().add(g);
	}

	public void remove(LegoElement element) {
		getChildren().removeIf( n -> ((Group)n).getChildren().get(0) == element);
	}
}
