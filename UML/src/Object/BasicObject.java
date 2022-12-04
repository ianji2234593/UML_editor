import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

public abstract class BasicObject {
	protected int x = 0;
	protected int y = 0;
	protected int w = 0;
	protected int h = 0;
	protected String name = "";
	protected boolean select_state = false;
	protected Port[] ports = new Port[4];

	// get methods
	protected int getX() { return this.x; }   
    protected int getY() { return this.y; }   
    protected int getWidth() { return this.w; }   
    protected int getHeight() { return this.h; } 
    protected abstract Vector<BasicObject> getChild();
    
    // set methods
    protected void setSelect(boolean b) { 
    	/* Set object selectState as isSelected or notSelected */
    	
    	this.select_state = b; 
    }
    protected void setTxt(String s) { 
    	/* Set object's name */
    	
    	this.name = s; 
    }
    protected void setLocationOffset(int _x, int _y) {
    	/* Move object to new location */
    	
		this.x += _x;
		this.y += _y;
		update_port();
	}
	
    // draw methods
	protected void draw(Graphics2D g2D) {}
	protected void drawPort(Graphics2D g2D) {
        double size = 10.0;
        double bias = 3;
        
        g2D.setColor(Color.black);
        
        // top
        g2D.fill(new Rectangle.Double(this.ports[0].point.x - bias, this.ports[0].point.y - bias, size, size));
        // left
        g2D.fill(new Rectangle.Double(this.ports[1].point.x - bias, this.ports[1].point.y - bias, size, size));
        // bottom
        g2D.fill(new Rectangle.Double(this.ports[2].point.x - bias, this.ports[2].point.y - bias*2, size, size));
        // right
        g2D.fill(new Rectangle.Double(this.ports[3].point.x - bias*2, this.ports[3].point.y - bias, size, size));
    }
    
	protected boolean contains(int _x, int _y) {
		/* Check point is in this object? */
		
		// In this object
    	if(_x >= x && _x <= x + w && _y >= y && _y <= y+h) {
    		return true;
    	}
    	// Not in this object
    	else {
    		return false;
    	}
    }
	
	
	/*
	 * Port functions
	 */
	
	private double cal_distance(Point p1, Point p2) {
		/* Calculate 2 points distance */
		
		double d = Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2);
		d = Math.sqrt(d);
		
		return d;
	}
	
	protected Port select_port(Point mouse_p) {
		/* Select nearest port on some object */
		
		double min = 100;
		double d;
		Port selected_port = null;
		
		for(int i = 0; i < 4; i++) {
			Port port = this.ports[i];
			d = cal_distance(mouse_p, port.point);
			if (d<min) {
				min = d;
				selected_port = port;
			}
		}
		return selected_port;
	}
	
	protected void create_port() {
		/* Create Object's ports */
		
		Port[] ports = new Port[4];
		
		// top
		ports[0] = new Port(this, new Point((int)(this.x + this.w*0.5), this.y));
		// left
		ports[1] = new Port(this, new Point(this.x, (int)(this.y + this.h*0.5)));
		// bottom
		ports[2] = new Port(this, new Point((int)(this.x + this.w*0.5), this.y + this.h));
		// right
		ports[3] = new Port(this, new Point((int)(this.x + this.w), (int)(this.y + this.h*0.5)));
		
		for(int i = 0; i < 4; i++) {
			this.ports[i] = ports[i];
		}
	}
	
	protected void update_port() {
		/* Update object's ports when object move */
		
		// top
		this.ports[0].update(new Point((int)(this.x + this.w*0.5), this.y));
		// left
		this.ports[1].update(new Point(this.x, (int)(this.y + this.h*0.5)));
		// bottom
		this.ports[2].update(new Point((int)(this.x + this.w*0.5), this.y + this.h));
		// right
		this.ports[3].update(new Point((int)(this.x + this.w), (int)(this.y + this.h*0.5)));
	}
}