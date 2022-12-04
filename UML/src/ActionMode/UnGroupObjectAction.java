import java.awt.event.MouseEvent;
import java.util.Vector;

public class UnGroupObjectAction extends ActionMode{
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("UnGroup Object menu selected");
		
		MyCanvas canvas = MyCanvas.getInstance();
		Vector<BasicObject> selected_objs = canvas.getSelectedObjects();
		
		if (selected_objs.size()==1) {
			BasicObject obj = selected_objs.get(0);
			
			Vector<BasicObject> childs = obj.getChild();
			if (childs!=null) {
				for (BasicObject o : childs) {
					canvas.addObject(o);
				}
				canvas.removeObject(obj);
			}
			canvas.repaint();
		}
	}
}
