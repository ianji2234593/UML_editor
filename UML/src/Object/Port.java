import java.awt.Point;

public class Port {
	protected BasicObject parent_obj = null;
	protected Point point = null;
	
	Port(BasicObject obj, Point p) {
		this.parent_obj = obj;
		this.point = p;
	}
	
	void update(Point p) {
		this.point = p;
	}
	
	int getX() { return point.x; }
	int getY() { return point.y; }
}
