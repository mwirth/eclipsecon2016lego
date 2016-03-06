package at.bestsolution.lego.ui;

import java.net.URL;

import org.eclipse.fx.ui.services.theme.Stylesheet;
import org.eclipse.fx.ui.services.theme.Theme;

/* TODO 8 contribute the css */
public class LegoStylesheet implements Stylesheet {

	@Override
	public boolean appliesToTheme(Theme t) {
		return true;
	}

	@Override
	public URL getURL(Theme t) {
		return getClass().getResource("default.css");
	}

}
