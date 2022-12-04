import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class UMLButton {
	protected JButton btn;
	protected ActionMode mode;
	
	UMLButton(ActionMode mode) {
		this.btn = new JButton("");
		this.btn.setBackground(Color.WHITE);
		this.btn.addActionListener(new ButtonClickListener());
		this.mode = mode;
	}
	
	public JButton getButton() {
		return this.btn;
	}
	
	protected class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			UMLButtonFactory.resetButtons();
			btn.setBackground(Color.BLACK);
			MyCanvas.getInstance().clearSelected();
			MyCanvas.getInstance().setActionMode(mode);
		}
	}
}



class SelectButton extends UMLButton {
	SelectButton(ActionMode mode) {
		super(mode);
		btn.setIcon(new ImageIcon("./icon/select.PNG"));
	}
}

class CreateAssociationLineButton extends UMLButton {
	CreateAssociationLineButton(ActionMode mode) {
		super(mode);
		btn.setIcon(new ImageIcon("./icon/association.PNG"));
	}
}

class CreateGeneralizationLineButton extends UMLButton {
	CreateGeneralizationLineButton(ActionMode mode) {
		super(mode);
		btn.setIcon(new ImageIcon("./icon/generalizationLine.PNG"));
	}
}

class CreateCompositionLineButton extends UMLButton {
	CreateCompositionLineButton(ActionMode mode) {
		super(mode);
		btn.setIcon(new ImageIcon("./icon/compositionLine.PNG"));	
	}
}

class CreateClassButton extends UMLButton {
	CreateClassButton(ActionMode mode) {
		super(mode);
		btn.setIcon(new ImageIcon("./icon/class.PNG"));
	}
}

class CreateUseclassButton extends UMLButton {
	CreateUseclassButton(ActionMode mode) {
		super(mode);
		btn.setIcon(new ImageIcon("./icon/usecase.PNG"));
	}
}