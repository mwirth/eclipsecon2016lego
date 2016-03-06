package at.bestsolution.lego.ui;

import org.eclipse.fx.core.event.Topic;

import at.bestsolution.lego.ui.components.LegoAssembly;

public class Constants {
	public static final String CURRENT_LEGO_BRICK = "legoElement";
	public static final String CURRENT_LEGO_ASSEMBLY = "legoAssembly";
	public static final Topic<LegoAssembly> TOPIC_NEW_ASSEMBLY = new Topic<>("at/bestsolution/lego/ui/assembly/new");
}
