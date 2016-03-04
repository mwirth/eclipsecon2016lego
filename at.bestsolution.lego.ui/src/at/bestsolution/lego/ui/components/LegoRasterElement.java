package at.bestsolution.lego.ui.components;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;

public class LegoRasterElement extends LegoElement {
	private IntegerProperty xLocUnits = new SimpleIntegerProperty(this, "xUnits");
	private IntegerProperty yLocUnits = new SimpleIntegerProperty(this, "yUnits");
	private IntegerProperty zLocUnits = new SimpleIntegerProperty(this, "zUnits");
	private ObjectProperty<Rotation> rotation = new SimpleObjectProperty<>(this, "rotation",Rotation.ROTATE0);

	private ObjectProperty<Material> material = new SimpleObjectProperty<>(this, "material", new PhongMaterial());

	static final double P = 16;
	static final double H = 9.6;


	public enum Rotation {
		ROTATE0, ROTATE90, ROTATE180, ROTATE270,
	}

	public final IntegerProperty xLocUnitsProperty() {
		return this.xLocUnits;
	}

	public final int getXLocUnits() {
		return this.xLocUnitsProperty().get();
	}

	public final void setXLocUnits(final int xLocUnits) {
		this.xLocUnitsProperty().set(xLocUnits);
	}

	public final IntegerProperty yLocUnitsProperty() {
		return this.yLocUnits;
	}

	public final int getYLocUnits() {
		return this.yLocUnitsProperty().get();
	}

	public final void setYLocUnits(final int yLocUnits) {
		this.yLocUnitsProperty().set(yLocUnits);
	}

	public final IntegerProperty zLocUnitsProperty() {
		return this.zLocUnits;
	}

	public final int getZLocUnits() {
		return this.zLocUnitsProperty().get();
	}

	public final void setZLocUnits(final int zLocUnits) {
		this.zLocUnitsProperty().set(zLocUnits);
	}

	public final ObjectProperty<Rotation> rotationProperty() {
		return this.rotation;
	}

	public final Rotation getRotation() {
		return this.rotationProperty().get();
	}

	public final void setRotation(final Rotation rotation) {
		this.rotationProperty().set(rotation);
	}

	public final ObjectProperty<Material> materialProperty() {
		return this.material;
	}

	public final Material getMaterial() {
		return this.materialProperty().get();
	}

	public final void setMaterial(final Material material) {
		this.materialProperty().set(material);
	}

}
