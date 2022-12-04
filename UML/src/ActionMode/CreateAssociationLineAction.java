import java.awt.event.MouseEvent;

public class CreateAssociationLineAction extends ActionMode{
	MyCanvas canvas = MyCanvas.getInstance();
	private LineObject line_obj = null;
	
	private BasicObject selected_obj = null;
	private BasicObject connected_obj = null;

	private Port start_port = null;
	private Port end_port = null;
	
	CreateAssociationLineAction() {}
	
	private LineObject createLine() {
		return new AssociationLineObject();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		canvas.clearSelected();
		selected_obj = canvas.whichObject(e.getPoint());	
		
		// is in object.
		if (selected_obj!=null && selected_obj.getChild()==null) {
			selected_obj.setSelect(true);
			start_port = selected_obj.select_port(e.getPoint());
			
			line_obj = this.createLine();
			line_obj.setStartPort(start_port);
			line_obj.setEndPort(start_port);

			line_obj.cal_arrowInfo();

			canvas.setLine(line_obj);
		}
		canvas.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// start at object, valid
		if (line_obj!=null) {
			line_obj.setEndPoint(e.getPoint());
			line_obj.cal_arrowInfo();
			canvas.repaint();
		}
		
	}
	
	@Override
    public void mouseReleased(MouseEvent e) {
		connected_obj = canvas.whichObject(e.getPoint());
		
		if (line_obj!=null) {
			// line is valid, can create line
			if (connected_obj!=null && selected_obj!=connected_obj && connected_obj.getChild()==null){
				end_port = connected_obj.select_port(e.getPoint());
				line_obj.setEndPort(end_port);
				line_obj.cal_arrowInfo();
				canvas.addLine(line_obj);
			}
			// end_point is invalid
			else if (selected_obj!=null){
				canvas.setLine(null);
			}
		}
		line_obj = null;
		canvas.repaint();
    }
}

