import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLayeredPane;

class MyCanvas extends JLayeredPane{
	private static final long serialVersionUID = 1L;	
	private static MyCanvas canvas = null;
	private ActionMode currentMode = new ActionMode();
	
	private Vector<LineObject> vectorAllLineObj = new Vector<LineObject>();
	private Vector<BasicObject> vectorAllObj = new Vector<BasicObject>();
	private Vector<BasicObject> selected_objs = new Vector<BasicObject>();
	
	private Rectangle rect = null;
	public LineObject line = null;
	
	private MyCanvas() {
		setSize(100,100);
		setBackground(Color.WHITE);
		addMouseListener(new CanvasActionListener());
		addMouseMotionListener(new CanvasActionListener());
		System.out.println("Create MyCanvas");
	}
	
	// get methods
	public static MyCanvas getInstance() { 
		if (canvas==null) {
			canvas = new MyCanvas();
		}
		return canvas;
	}
	
	protected int getSelectedSize() { return this.selected_objs.size(); }
	protected Vector<BasicObject> getSelectedObjects() { 
		Vector<BasicObject> tmp = new Vector<BasicObject>();		
		for (BasicObject o : this.selected_objs) {
			o.setSelect(false);
			tmp.add(o);
		}
		System.out.println("this.selected_objs.size" + this.selected_objs.size());
		return tmp; 
	}
	
	// add methods
	protected void addLine(LineObject o) { 
		vectorAllLineObj.add(o); 
		this.line = null;
	}
	protected void addObject(BasicObject o) { vectorAllObj.add(o); }
	protected void addSelectedObject(BasicObject o) { this.selected_objs.add(o); }
	
	// set methods
	protected void setActionMode(ActionMode mode) { this.currentMode = mode; }
	protected void setRect(Rectangle r) { this.rect = r; }
	protected void setLine(LineObject l) { this.line = l; }
	
	// remove
	protected void removeObject(BasicObject o) { vectorAllObj.remove(o); }
	
	
	protected void update_canvas_line() {
		for(LineObject l : vectorAllLineObj) {
			l.update_line();
	    }
	}
	
	protected void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		for (LineObject o : vectorAllLineObj) {
			o.draw(g2d);
		}
		
		for (BasicObject o : vectorAllObj) {
			o.draw(g2d);
		}
		
		if (rect != null && selected_objs.size()==0) {
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.draw(rect);
		}
		
		if (this.line!=null) {
			this.line.draw(g2d);
		}
	}
	
	protected BasicObject whichObject(Point p) {
		int _x = (int)p.getX();
		int _y = (int)p.getY();
		
		for(int i = vectorAllObj.size()-1; i >=0 ; i--)	
		{	
			// Mouse location is in a object
			if (vectorAllObj.get(i).contains(_x, _y)) {
				System.out.println("Find it!" + vectorAllObj.size());
				
				BasicObject obj = vectorAllObj.get(i);
				return obj;
			}
	    }
		
		// Mouse location isn't in a object
		return null;
	}
	
	protected void changeDepth(BasicObject o) {
//		vectorAllObj.remove(o);
//		vectorAllObj.add(o);
	}
	
	protected int selectObjects(Rectangle rect) {	
		for(BasicObject o : this.vectorAllObj) {
			Rectangle r = new Rectangle(o.x, o.y, o.w, o.h);
			
			// BasicObject o is in Rectangle
			if (rect.contains(r)) {
				System.out.println(o.name);
				o.setSelect(true);
				this.selected_objs.add(o);
				System.out.println("Selected" + this.selected_objs.size());
			}
	    }
		
		return this.selected_objs.size();
	}
	
	protected void clearSelected() {	
		for(BasicObject o : vectorAllObj) {
			o.setSelect(false);
	    }
		this.selected_objs.clear();
		
		System.out.println("Clear Selected" + selected_objs.size());
	}
	
	
	class CanvasActionListener extends MouseAdapter{
		/* Perform canvas action for different button mode*/
		
		public void mouseClicked(MouseEvent e) {
	    	currentMode.mouseClicked(e);
			canvas.repaint();
	    }  
	    
		public void mousePressed(MouseEvent e) {
			currentMode.mousePressed(e);
			canvas.repaint();
		}

		public void mouseReleased(MouseEvent e) {
			currentMode.mouseReleased(e);
			canvas.repaint();
		}
		
		public void mouseDragged(MouseEvent e) {
			currentMode.mouseDragged(e);
			canvas.repaint();
		}
	}
}