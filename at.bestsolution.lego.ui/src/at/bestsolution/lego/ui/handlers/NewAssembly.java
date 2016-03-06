package at.bestsolution.lego.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.fx.ui.services.dialog.LightWeightDialogService;
import org.eclipse.fx.ui.services.dialog.LightWeightDialogService.ModalityScope;

public class NewAssembly {
	@Execute
	public void createNewAssembly(LightWeightDialogService dialogService) {
		dialogService.openDialog(NewAssemblyDialog.class, ModalityScope.WINDOW);
	}
}
