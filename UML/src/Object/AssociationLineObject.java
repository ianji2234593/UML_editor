import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class AssociationLineObject extends LineObject {
	AssociationLineObject(){}

	@Override
	protected void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        
		g.setColor(Color.BLACK);
		g.drawLine((int)start_point.getX(), (int)start_point.getY(), (int)end_point.getX(), (int)end_point.getY());
		
		g.drawLine((int)this.end_point.getX(), (int)this.end_point.getY(), (int)this.end1.getX(), (int)this.end1.getY());
		g.drawLine((int)this.end_point.getX(), (int)this.end_point.getY(), (int)this.end2.getX(), (int)this.end2.getY());
    }
}
