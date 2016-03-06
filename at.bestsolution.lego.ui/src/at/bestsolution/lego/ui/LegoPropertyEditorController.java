package at.bestsolution.lego.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.fx.core.databinding.JFXBeanProperties;
import org.eclipse.fx.core.di.ContextValue;
import org.eclipse.fx.ui.databinding.JFXUIProperties;

import at.bestsolution.lego.ui.components.LegoRasterElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LegoPropertyEditorController implements Initializable {

	@FXML TextField xDim;
	@FXML TextField yDim;
	@FXML TextField zDim;
	@FXML TextField xCoord;
	@FXML TextField yCoord;
	@FXML TextField zCoord;

	@FXML ColorPicker colorPicker;
	@FXML TextField name;
	@FXML ComboBox<LegoRasterElement.Rotation> rotation;

	IObservableValue legoBrick;

	@Inject
	public LegoPropertyEditorController(@ContextValue(Constants.CURRENT_LEGO_BRICK) IObservableValue legoBrick) {
		this.legoBrick = legoBrick;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DataBindingContext dbc = new DataBindingContext();

		{
			IObservableValue mObs = JFXBeanProperties.value("name").observeDetail(legoBrick);
			IObservableValue uiObs = JFXUIProperties.text().observe(name);
			dbc.bindValue(uiObs, mObs);
		}

		{
			IObservableValue mObs = JFXBeanProperties.value("XUnits").observeDetail(legoBrick);
			IObservableValue uiObs = JFXUIProperties.text().observe(xDim);
			dbc.bindValue(uiObs, mObs, stringToInt(), intToString());
		}

		{
			IObservableValue mObs = JFXBeanProperties.value("YUnits").observeDetail(legoBrick);
			IObservableValue uiObs = JFXUIProperties.text().observe(yDim);
			dbc.bindValue(uiObs, mObs, stringToInt(), intToString());
		}

		{
			IObservableValue mObs = JFXBeanProperties.value("ZUnits").observeDetail(legoBrick);
			IObservableValue uiObs = JFXUIProperties.text().observe(zDim);
			dbc.bindValue(uiObs, mObs, stringToInt(), intToString());
		}

		{
			IObservableValue mObs = JFXBeanProperties.value("XLocUnits").observeDetail(legoBrick);
			IObservableValue uiObs = JFXUIProperties.text().observe(xCoord);
			dbc.bindValue(uiObs, mObs, stringToInt(), intToString());
		}

		{
			IObservableValue mObs = JFXBeanProperties.value("YLocUnits").observeDetail(legoBrick);
			IObservableValue uiObs = JFXUIProperties.text().observe(yCoord);
			dbc.bindValue(uiObs, mObs, stringToInt(), intToString());
		}

		{
			IObservableValue mObs = JFXBeanProperties.value("ZLocUnits").observeDetail(legoBrick);
			IObservableValue uiObs = JFXUIProperties.text().observe(zCoord);
			dbc.bindValue(uiObs, mObs, stringToInt(), intToString());
		}

		{
			IObservableValue mObs = JFXBeanProperties.value("material.diffuseColor").observeDetail(legoBrick);
			IObservableValue uiObs = JFXBeanProperties.value("value").observe(colorPicker);
			dbc.bindValue(uiObs, mObs);
		}

		{
			IObservableValue mObs = JFXBeanProperties.value("rotation").observeDetail(legoBrick);
			IObservableValue uiObs = JFXBeanProperties.value("value").observe(rotation);
			dbc.bindValue(uiObs, mObs);
		}

	}

	private UpdateValueStrategy intToString() {
		UpdateValueStrategy v = new UpdateValueStrategy();
		v.setConverter(NumberToStringConverter.fromInteger(false));
		return v;
	}

	private UpdateValueStrategy stringToInt() {
		UpdateValueStrategy v = new UpdateValueStrategy();
		v.setConverter(StringToNumberConverter.toInteger(false));
		return v;
	}


//	@Inject
//	public void setValue(@Named("legoElement") LegoBrick value) {
////		unbindAll();
////		this.value = value;
////		bindAll();
//	}
}
