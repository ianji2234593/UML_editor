import java.awt.Graphics2D;
import java.util.Vector;

public class CompositeObject extends BasicObject{
	protected Vector<BasicObject> objs_vector = new Vector<BasicObject>();
	
	CompositeObject(Vector<BasicObject> v){
		objs_vector = v;
		cal_xywh();
		this.create_port();
		update_port();
	}

	@Override
    protected void draw(Graphics2D g) {
        
        for (BasicObject o : this.objs_vector) {
        	o.draw(g);
        }

		if(this.select_state) {
			drawPort(g);
		}
    }
	
	@Override
	protected Vector<BasicObject> getChild() {
		// TODO Auto-generated method stub
		return this.objs_vector;
	}
	
	protected void cal_xywh() {
		int minx = Integer.MAX_VALUE;
		int miny = Integer.MAX_VALUE;
		int maxx = Integer.MIN_VALUE;
		int maxy = Integer.MIN_VALUE;
		
		for (BasicObject o : this.objs_vector) {
			if (o.getX()<minx) { minx=o.getX(); }
			if (o.getY()<miny) { miny=o.getY(); }
			if (o.getX()+o.getWidth()>maxx) { maxx=o.getX()+o.getWidth(); }
			if (o.getY()+o.getHeight()>maxy) { maxy=o.getY()+o.getHeight(); }
		}
		
		this.x = minx;
		this.y = miny;
		this.w = maxx-minx;
		this.h = maxy-miny;
		System.out.printf("Create composite, x=%d, y=%d, w=%d, h=%d\n", this.x, this.y, this.w, this.h);
	}
	
    protected void setLocationOffset(int _x, int _y) {
		this.x += _x;
		this.y += _y;
		update_port();
		
		// ²¾°ÊComposite¤ºªºobject
		Vector<BasicObject> childs = this.getChild();
		if (childs!=null) {
			for (BasicObject o : childs) {
				o.setLocationOffset(_x, _y);
			}
		}
	}
}
