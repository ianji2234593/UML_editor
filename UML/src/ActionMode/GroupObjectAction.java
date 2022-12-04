import java.awt.event.MouseEvent;
import java.util.Vector;

public class GroupObjectAction extends ActionMode{
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Group Object menu selected");
		
		MyCanvas canvas = MyCanvas.getInstance();
		Vector<BasicObject> selected_objs = canvas.getSelectedObjects();
		
		if (selected_objs.size()>1) {
			for (BasicObject obj : selected_objs) {
				canvas.removeObject(obj);
			}
			
			BasicObject composite = new CompositeObject(selected_objs);
			
			canvas.addObject(composite);
		}
		canvas.repaint();
	}
}
