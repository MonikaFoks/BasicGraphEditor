package Lab4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

/* 
 *  Program: Prosty edytor grafu
 *     Plik: Edge.java
 *           
 *  Klasa Edge reprezentuje krawêdzie grafu na p³aszczyŸnie. 
 *  Klasa mo¿e byæ klas¹ bazow¹ dla klas reprezentuj¹cych 
 *  krawêdzie grafów modeluj¹ch  wybrane zagadnienia np.: 
 *     - schemat komunikacji miejskiej,
 *     - drzewo genealogiczne,
 *     - schemat obwodu elektronicznego typu RLC,
 *     - schemat topologi sieci komputerowej
 *            
 *    Autor: Monika Foks
 *     Data:  listopad 2018 r.
 */

public class Edge implements Serializable {
  private static final long serialVersionUID = 1L;
  protected Node nodeFrom;
  protected Node nodeTo;
  private Color color;

  public Color getColor() {
		return color;
	}

  public void setColor(Color color) {
		this.color = color;
	}
	
  public Edge(Node nodeFrom, Node nodeTo)
  {
    this.nodeFrom = nodeFrom;
    this.nodeTo = nodeTo;
    this.color = Color.BLUE;
  }
  
  public Edge(Node nodeFrom, Node nodeTo, Color color)
  {
    this.nodeFrom = nodeFrom;
    this.nodeTo = nodeTo;
    this.color = color;
  }

  public Node getNodeFrom() {
    return nodeFrom;
  }
  
  public Node getNodeTo() {
    return nodeTo;
  }
  
  void draw(Graphics g)
  {
    g.setColor(color);
    
    ((Graphics2D)g).setStroke(new BasicStroke(3.0F));
    g.drawLine(nodeFrom.getX(), nodeFrom.getY(), nodeTo.getX(), nodeTo.getY());
    
    ((Graphics2D)g).setStroke(new BasicStroke(1.0F));
    
    g.setColor(Color.BLACK);
  }
  
  public boolean isMouseOver(int x,int y)  
	{
		float a;
		float b;
		float y1=nodeFrom.getY();
		float x1=nodeFrom.getX();
		float y2=nodeTo.getY();
		float x2=nodeTo.getX();
		
		a=((y1-y2)/(x1-x2));
		b=y2-a*x2;
		
		if((y+5>=x*a+b)&&(y-5<=x*a+b))
			return true;
		else
			return false;
	}

  
  public String toString()
  {
    return "[" + nodeFrom + "==>" + nodeTo + "]";
  }
}
