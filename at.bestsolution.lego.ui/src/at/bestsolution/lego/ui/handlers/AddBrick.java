package at.bestsolution.lego.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Optional;

import at.bestsolution.lego.ui.components.LegoAssembly;

public class AddBrick {
	@CanExecute
	public boolean canAdd(@Optional @Named("legoAssembly") LegoAssembly assembly) {
		return assembly != null;
	}

	public void addBrick() {
		/* TODO 3 Show the New Brick Dialog */
	}
}
