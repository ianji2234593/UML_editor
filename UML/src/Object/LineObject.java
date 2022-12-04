import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

public class LineObject {
    protected Point start_point = null;
    protected Point end_point = null;
    
	protected Port start_port = null;
	protected Port end_port = null;
    
	// for draw arrow
	protected int arrowHeight = 9;		// change as seen fit
	protected int halfArrowWidth = 5;	// this too
	protected double angle;
	protected Point2D end1;
	protected Point2D end2;
	protected Point2D aroBase;
	protected Point2D aroBase2;
    
    protected void draw(Graphics graphics) {}
    
    protected void setEndPoint(Point p) { end_point = p; }
    protected void setStartPort(Port p) { 
    	start_port = p;
    	start_point = p.point;
    }
    protected void setEndPort(Port p) {
    	end_port = p;
    	end_point = p.point;
	}
    
    
    protected void cal_arrowInfo() {
    	/* Calculate arrow information for draw the arrow */
    	
    	//find angle of line
    	angle = Math.atan2(end_point.getY()-start_point.getY(), end_point.getX()-start_point.getX());
    	
    	//determine the location of middle of
    	aroBase = new Point2D.Double(
    			end_point.getX() - arrowHeight*Math.cos(angle),
    			end_point.getY() - arrowHeight*Math.sin(angle)
      		  ); 
    	
    	aroBase2 = new Point2D.Double(
    			end_point.getX() - arrowHeight*Math.cos(angle)*2,
    			end_point.getY() - arrowHeight*Math.sin(angle)*2
      		  ); 
    	
    	//distance back towards the starting point
    	end1 = new Point2D.Double(
    				aroBase.getX()-halfArrowWidth*Math.cos(angle-Math.PI/2),
    				aroBase.getY()-halfArrowWidth*Math.sin(angle-Math.PI/2)
    			);
    	
    	end2 = new Point2D.Double(
    				aroBase.getX()+halfArrowWidth*Math.cos(angle-Math.PI/2),
    				aroBase.getY()+halfArrowWidth*Math.sin(angle-Math.PI/2)
    			);
    }
    
    protected void update_line() {
    	/* Update line when object move */
    	
		this.start_point = this.start_port.point;
		this.end_point = this.end_port.point;
		this.cal_arrowInfo();
    }
}