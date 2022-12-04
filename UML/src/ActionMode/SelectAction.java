import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class SelectAction extends ActionMode{
	MyCanvas canvas = MyCanvas.getInstance();
	
	private BasicObject selected_obj;
	private Point mouse_start = new Point();
	private Rectangle rect = null;
	 
    private Rectangle createRect(Point p1, Point p2) {
        int x = Math.min(mouse_start.x, p2.x);
        int y = Math.min(mouse_start.y, p2.y);
        int width = Math.abs(mouse_start.x - p2.x);
        int height = Math.abs(mouse_start.y - p2.y);
        Rectangle rect2 = new Rectangle(x, y, width, height);
        return rect2;
     }
    
	@Override
	public void mousePressed(MouseEvent e) {
		mouse_start = e.getPoint();
		canvas.clearSelected();
		selected_obj = canvas.whichObject(mouse_start);
		
		// press on canvas
		if (selected_obj==null) {
			canvas.clearSelected();
		}
		// press on object, and no selected object
		else if (selected_obj!=null && canvas.getSelectedSize()==0) {
			selected_obj.setSelect(true);	
			canvas.addSelectedObject(selected_obj);
			canvas.changeDepth(selected_obj);
		}
		// press on object, and have selected objects
		else if (selected_obj!=null && canvas.getSelectedSize()!=0) {
			canvas.clearSelected();
			
			selected_obj.setSelect(true);
			canvas.addSelectedObject(selected_obj);
		}
		canvas.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// have selected object, move object
		if(selected_obj!=null) {
			int offsetx = e.getX() - mouse_start.x;
			int offsety = e.getY() - mouse_start.y;
			
			selected_obj.setLocationOffset(offsetx, offsety);
			canvas.update_canvas_line();
			
			mouse_start.x = e.getX();
			mouse_start.y = e.getY();
		}
		// mouse create rectangle to select object
		else {
			rect = createRect(mouse_start, e.getPoint());
			canvas.setRect(rect);
		}
		canvas.repaint();
	}
	
	@Override
    public void mouseReleased ( MouseEvent e )
    {	
		int selected_num = canvas.getSelectedSize();
		
		// mouse select objects
		if (rect!=null) {
			System.out.println("Rect!=null");
			selected_num = canvas.selectObjects(rect);
			
			// no object in rectangle
			if (selected_num == 0) {
				canvas.clearSelected();
			}
		}

		System.out.println("Selected" + selected_num);
		
		selected_obj = null;
		rect = null;
		canvas.setRect(rect);
		canvas.repaint();
    }
}