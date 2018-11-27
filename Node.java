package Lab4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/* 
 *  Program: Prosty edytor grafu
 *     Plik: Node.java
 *           
 *  Klasa Node reprezentuje wêz³y grafu na p³aszczyŸnie. 
 *  Klasa mo¿e byæ klas¹ bazow¹ dla klas reprezentuj¹cych 
 *  wêz³y grafów modeluj¹ch  wybrane zagadnienia np.: 
 *     - schemat komunikacji miejskiej,
 *     - drzewo genealogiczne,
 *     - schemat obwodu elektronicznego typu RLC,
 *     - schemat topologi sieci komputerowej
 *            
 *    Autor: Monika Foks
 *     Data:  listopad 2018 r.
 */

public class Node implements Serializable {
	private static final long serialVersionUID = 1L;
	// po³o¿enie ko³a
	protected int x;
	protected int y;
	protected int r;
	
	private static final int RADIUS = 20;
    private static final Font font = new Font("SansSerif", 1, 16);
    private String name;
    private Color color;
	
	
	public Node(int x, int y, String name, Color color) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.r = RADIUS;
		this.color = color;
	}
	
	public String getName() {
    return name;
	}
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Color getColor() {
    return color;
  }
  
  public void setColor(Color color) {
    this.color = color;
  }
  
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
	
	public boolean isMouseOver(int mx, int my){
		return (x-mx)*(x-mx)+(y-my)*(y-my)<=r*r;
	}

	void draw(Graphics g)
	  {
	    g.setColor(color);
	    g.fillOval(x - 20, y - 20, 40, 40);
	    g.setColor(Color.BLACK);
	    g.drawOval(x - 20, y - 20, 40, 40);
	    g.setFont(font);
	    FontRenderContext frc = ((Graphics2D)g).getFontRenderContext();
	    Rectangle2D bounds = font.getStringBounds(name, frc);
	    g.drawString(name, x - (int)bounds.getWidth() / 2, y + (int)bounds.getHeight() / 4);
	  }
	
	@Override
	public String toString(){
		return ("(" + x +", " + y + ", " + r + ")");
	}
	
}
