import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ChangeNameAction extends ActionMode{
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Menu ChangeName menu selected");
		
		MyCanvas canvas = MyCanvas.getInstance();
		Vector<BasicObject> selected_objs = canvas.getSelectedObjects();
		
		if (selected_objs.size()==1) {
			System.out.println("Change object name");
	        String getMessage = JOptionPane.showInputDialog(canvas, "Enter your class name");
	        if (getMessage!=null) {
	        	selected_objs.get(0).setTxt(getMessage);
		        canvas.repaint();
	        }
		}
	}
}
