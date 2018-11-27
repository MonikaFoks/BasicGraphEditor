package Lab4;

import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/* 
 *  Program: Prosty edytor grafu
 *     Plik: Graph.java
 *           
 *  Klasa Graph reprezentuje graf na p³aszczyŸnie. 
 *  Klasa mo¿e byæ klas¹ bazow¹ dla klas reprezentuj¹cych 
 *  grafy modeluj¹ce wybrane zagadnienia np.: 
 *     - schemat komunikacji miejskiej,
 *     - drzewo genealogiczne,
 *     - schemat obwodu elektronicznego typu RLC,
 *     - schemat topologi sieci komputerowej
 *            
 *    Autor: Monika Foks
 *     Data:  listopad 2018 r.
 */

public class Graph implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Listy wêz³ów i krawêdzi grafu
	private List<Node> nodes;
	private List<Edge> edges;
	
	public Graph() {
		this.nodes = new ArrayList<Node>();	
		this.edges = new ArrayList<Edge>();
	}
	
	public void addNode(Node node){
		nodes.add(node);
	}
	
	public void removeNode(Node node){
		nodes.remove(node);
	}
	
	public Node[] getNodes(){
		Node [] array = new Node[0];
		return nodes.toArray(array);
	}
	
	public void addEdge(Edge edge){
		edges.add(edge);
	}
	
	public void removeEdge(Edge edge){
		edges.remove(edge);
	}
	
	public Edge[] getEdges(){
		Edge [] array = new Edge[0];
		return edges.toArray(array);
	}
	
	
	public void draw(Graphics g){
		for(Edge edge : edges){
			edge.draw(g);
		}
		for(Node node : nodes){
			node.draw(g);
		}
	}
	
	public void saveGraph(String fileName) throws IOException
	{
		FileOutputStream stream = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(stream);
		out.writeObject(this);
		out.close();
	}
	
	public static Graph loadGraph(String fileName) throws IOException, ClassNotFoundException
	{
		FileInputStream stream = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(stream);
		Object readed = in.readObject();
		Graph graph = (Graph)readed;				   
		in.close();
		return graph;
	}
}
