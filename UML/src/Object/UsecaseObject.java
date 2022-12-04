import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

public class UsecaseObject extends BasicObject{
	UsecaseObject(int _x, int _y){
		x = _x;
		y = _y;	
		w = 110;
		h = 80;
		name = "UseClass";
		this.create_port();
		this.update_port();
		System.out.println("Create UsecaseObject with xy, x=" + x + ", y=" + y);
	}

	@Override
	protected void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillOval(x, y, w, h);
		g.setColor(Color.WHITE);
		g.fillOval(x+5,y+5,100,70);
		
		g.setColor(Color.BLACK);
		g.drawString(this.name, this.x+30, this.y+25);
		

		if(select_state) {
			drawPort(g);
		}
    }

	@Override
	protected Vector<BasicObject> getChild() {
		// TODO Auto-generated method stub
		return null;
	}
}
