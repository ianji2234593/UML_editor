import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

public class ClassObject extends BasicObject{
	ClassObject(int _x, int _y){
		x = _x;
		y = _y;	
		w = 80;
		h = 110;
		name = "Class";
		this.create_port();
		this.update_port();
		System.out.println("Create ClassObject with xy, x=" + x + ", y=" + y);
	}
	
	@Override
    protected void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, w, h);
		g.setColor(Color.WHITE);
		g.fillRect(x+5,y+5,70,30);
		g.fillRect(x+5,y+40,70,30);
		g.fillRect(x+5,y+75,70,30);
		g.setColor(Color.BLACK);
		g.drawString(this.name, this.x+10, this.y+20);
		
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
