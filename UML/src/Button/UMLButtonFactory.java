import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

public class UMLButtonFactory {
	private static ArrayList<JButton> functionButtons = new ArrayList<JButton>();
	
	UMLButtonFactory() {
		loadButtons();
	}

	private void loadButtons() {
		// TODO Auto-generated method stub
		functionButtons.add((new SelectButton(new SelectAction())).getButton());
		functionButtons.add((new CreateAssociationLineButton(new CreateAssociationLineAction())).getButton());
		functionButtons.add((new CreateGeneralizationLineButton(new CreateGeneralizationLineAction())).getButton());
		functionButtons.add((new CreateCompositionLineButton(new CreateCompositionLineAction())).getButton());
		functionButtons.add((new CreateClassButton(new CreateClassAction())).getButton());
		functionButtons.add((new CreateUseclassButton(new CreateUseclassAction())).getButton());
	}
	
	public static void resetButtons() {
        for (JButton functionButton : functionButtons) {
            functionButton.setBackground(Color.WHITE);
        }
	}
	
	public int getSize() {
		return functionButtons.size();
	}
	
	public JButton getButton(int idx) {
		return functionButtons.get(idx);
	}
}
