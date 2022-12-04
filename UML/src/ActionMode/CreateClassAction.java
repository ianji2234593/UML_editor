import java.awt.event.MouseEvent;

public class CreateClassAction extends ActionMode{
	@Override
	public void mouseClicked(MouseEvent e) {
		BasicObject obj = new ClassObject(e.getX(), e.getY());
		MyCanvas.getInstance().addObject(obj);		
	}
}
