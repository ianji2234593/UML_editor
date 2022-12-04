import java.awt.event.MouseEvent;

public class CreateUseclassAction extends ActionMode{
	@Override
	public void mouseClicked(MouseEvent e) {
		BasicObject obj = new UsecaseObject(e.getX(), e.getY());
		MyCanvas.getInstance().addObject(obj);		
	}
}
