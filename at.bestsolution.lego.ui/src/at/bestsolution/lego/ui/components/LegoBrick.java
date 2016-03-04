package at.bestsolution.lego.ui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javafx.beans.Observable;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Translate;

public class LegoBrick extends LegoRasterElement {
	private IntegerProperty xUnits = new SimpleIntegerProperty(this, "xUnits", 1);
	private IntegerProperty yUnits = new SimpleIntegerProperty(this, "yUnits", 1);
	private IntegerProperty zUnits = new SimpleIntegerProperty(this, "zUnits", 1);

	private static final double off = 0.2;
	private static final double r = 4.8;
	private static final double rh = 3.4;

	public final IntegerProperty xUnitsProperty() {
		return this.xUnits;
	}

	public final int getXUnits() {
		return this.xUnitsProperty().get();
	}

	public final void setXUnits(final int xUnits) {
		this.xUnitsProperty().set(xUnits);
	}

	public final IntegerProperty yUnitsProperty() {
		return this.yUnits;
	}

	public final int getYUnits() {
		return this.yUnitsProperty().get();
	}

	public final void setYUnits(final int yUnits) {
		this.yUnitsProperty().set(yUnits);
	}

	public final IntegerProperty zUnitsProperty() {
		return this.zUnits;
	}

	public final int getZUnits() {
		return this.zUnitsProperty().get();
	}

	public final void setZUnits(final int zUnits) {
		this.zUnitsProperty().set(zUnits);
	}

	private DoubleBinding width() {
		return this.xUnits.multiply(P).subtract(2*off);
	}

	private DoubleBinding height() {
		return this.yUnits.multiply(H);
	}

	private DoubleBinding depth() {
		return this.zUnits.multiply(P).subtract(2*off);
	}

	public static LegoBrick createSimpleBrick(int xUnits, int yUnits, int zUnits) {
		LegoBrick b = new LegoBrick();
		b.setXUnits(xUnits);
		b.setYUnits(yUnits);
		b.setZUnits(zUnits);
		return b;
	}

	public static LegoBrick createSimpleBrickWithLocation(int xUnits, int yUnits, int zUnits, int xLoc, int yLoc, int zLoc) {
		LegoBrick b = createSimpleBrick(xUnits, yUnits, zUnits);
		b.setXLocUnits(xLoc);
		b.setYLocUnits(yLoc);
		b.setZLocUnits(zLoc);
		return b;
	}

	public LegoBrick() {
		getStyleClass().add("component");
		setId(UUID.randomUUID().toString());
		Box box = new Box();
		box.getStyleClass().add("shape");
		box.widthProperty().bind(width());
		box.heightProperty().bind(height());
		box.depthProperty().bind(depth());
		box.materialProperty().bind(materialProperty());
		getChildren().add(box);

		Translate edgeTranslation = new Translate();
		edgeTranslation.xProperty().bind(width().divide(2.0));
		edgeTranslation.yProperty().bind(height().divide(-2.0));
		edgeTranslation.zProperty().bind(depth().divide(2.0));

		Translate originTranslation = new Translate(-(P/2.0-off),0,-(P/2.0-off));

		box.getTransforms().addAll(edgeTranslation, originTranslation);

		xUnits.addListener(this::handleUnitChange);
		zUnits.addListener(this::handleUnitChange);
		handleUnitChange(null);
	}

	private void handleUnitChange(Observable o) {
		List<Node> list = new ArrayList<>();
		list.add(getChildren().get(0));

		for( int iX = 1; iX <= xUnits.get(); iX++ ) {
			for( int iZ = 1; iZ <= zUnits.get(); iZ++ ) {
				Cylinder c = new Cylinder(r, rh);
				c.materialProperty().bind(materialProperty());
				Translate t = new Translate();
				t.xProperty().set(P/2.0 + (iX-1) * P - off);
				t.yProperty().bind(height().multiply(-1).subtract(rh/2));
				t.zProperty().set(P/2.0 + (iZ-1) * 16 - off);
				c.getTransforms().add(t);
				c.getTransforms().add(new Translate(-(P/2.0-off), 0, -(P/2.0-off)));
				list.add(c);
			}
		}

		List<Node> copy = getChildren()
				.stream()
				.filter( n -> n instanceof Cylinder)
				.collect( Collectors.toList());
		getChildren().setAll(list);

		// Clean up bindings
		copy
			.stream()
			.flatMap( n -> n.getTransforms().stream())
			.map( t -> (Translate)t)
			.forEach( t -> t.yProperty().unbind());
	}
}
