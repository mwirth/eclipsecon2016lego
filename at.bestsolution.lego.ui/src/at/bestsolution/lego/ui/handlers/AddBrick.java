package at.bestsolution.lego.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.fx.ui.services.dialog.LightWeightDialogService;
import org.eclipse.fx.ui.services.dialog.LightWeightDialogService.ModalityScope;

import at.bestsolution.lego.ui.components.LegoAssembly;

public class AddBrick {
	@CanExecute
	public boolean canAdd(@Optional @Named("legoAssembly") LegoAssembly assembly) {
		return assembly != null;
	}

	@Execute
	public void addBrick(LightWeightDialogService service) {
		service.openDialog(NewBrickDialog.class, ModalityScope.WINDOW);
	}
}
