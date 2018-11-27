package Lab4;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/* 
 *  Program: Prosty edytor grafu
 *     Plik: GraphEditor.java
 *           
 *  Klasa GraphEditor implementuje okno g³ówne
 *  dla prostego graficznego edytora grafu.  
 *            
 *     Autor: Monika Foks
 *     Data:  listopad 2018 r.
 */

public class GraphEditor extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private static final String APP_AUTHOR = "Autor: Monika Foks\n  Data: listopad 2018";
	private static final String APP_TITLE = "Prosty edytor grafów";
	
	private static final String APP_INSTRUCTION =
			"                  O P I S   P R O G R A M U \n\n" + 
	        "Aktywna klawisze:\n" +
			"   strza³ki ==> przesuwanie wszystkich kó³\n" +
			"   SHIFT + strza³ki ==> szybkie przesuwanie wszystkich kó³\n\n" +
			"ponadto gdy kursor wskazuje ko³o:\n" +
			"   DEL   ==> kasowanie ko³a\n" +
			"   +, -   ==> powiêkszanie, pomniejszanie ko³a\n" +
			"   r,g,b ==> zmiana koloru ko³a\n\n" +
			"Operacje myszka:\n" +
			"   przeci¹ganie ==> przesuwanie wszystkich kó³\n" +
			"   PPM ==> tworzenie nowego ko³a w niejscu kursora\n" +
	        "ponadto gdy kursor wskazuje ko³o:\n" +
	        "   przeci¹ganie ==> przesuwanie ko³a\n" +
			"   PPM ==> zmiana koloru ko³a\n" +
	        "                   lub usuwanie ko³a\n"+
			"\nAby stworzyc krawedz:"+
	        "	PPM na pierwsze kolo ==> new edge ==>\n LPM drugie kolo ==> PPM"+
			"==>new edge";
	
	
	public static void main(String[] args) {
		new GraphEditor();
	}
	// private GraphBase graph;
      private JTextField field;
	  private JMenuBar menuBar = new JMenuBar();
	  private JMenu menuFile = new JMenu("File");
	  private JMenu menuGraph = new JMenu("Graph");
	  private JMenu menuHelp = new JMenu("Help");
	  private JMenuItem menuNew = new JMenuItem("New", 78);
	  private JMenuItem menuOpenFile = new JMenuItem("Open File", 79);
	  private JMenuItem menuSave = new JMenuItem("Save to File", 83);
	  private JMenuItem menuShowExample = new JMenuItem("Example", 88);
	  private JMenuItem menuExit = new JMenuItem("Exit", 69);
	  private JMenuItem menuListOfNodes = new JMenuItem("List of Nodes", 78);
	  private JMenuItem menuListOfEdges = new JMenuItem("List of Edges", 69);
	  private JMenuItem menuAuthor = new JMenuItem("Author", 65);
	  private JMenuItem menuInstruction = new JMenuItem("Instruction", 73);
	
	private GraphPanel panel = new GraphPanel();
	
	public GraphEditor() {
		super(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		setContentPane(panel);
		createMenu();
		showBuildinExample();
		setVisible(true);
		field = new JTextField();
	}

	private void showListOfNodes(Graph graph) {
		Node array[] = graph.getNodes();
		int i = 0;
		StringBuilder message = new StringBuilder("Liczba wêz³ów: " + array.length + "\n");
		for (Node node : array) {
			message.append(node + "    ");
			if (++i % 5 == 0)
				message.append("\n");
		}
		JOptionPane.showMessageDialog(this, message, APP_TITLE + " - Lista wêz³ów", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void showListOfEdges(Graph graph) {
		Edge array[] = graph.getEdges();
		int i = 0;
		StringBuilder message = new StringBuilder("Liczba krawêdzi: " + array.length + "\n");
		for (Edge edge : array) {
			message.append(edge + "    ");
			if (++i % 5 == 0)
				message.append("\n");
		}
		JOptionPane.showMessageDialog(this, message, APP_TITLE + " - Lista krawêdzi", JOptionPane.PLAIN_MESSAGE);
	}

	private void createMenu() {
		menuNew.addActionListener(this);
	    menuOpenFile.addActionListener(this);
	    menuSave.addActionListener(this);
	    menuShowExample.addActionListener(this);
	    menuExit.addActionListener(this);
	    menuListOfNodes.addActionListener(this);
	    menuListOfEdges.addActionListener(this);
	    menuAuthor.addActionListener(this);
	    menuInstruction.addActionListener(this);
	    
	    menuFile.setMnemonic(70);
	    menuFile.add(menuNew);
	    menuFile.add(menuOpenFile);
	    menuFile.add(menuSave);
	    menuFile.addSeparator();
	    menuFile.add(menuShowExample);
	    menuFile.addSeparator();
	    menuFile.add(menuExit);
	    
	    menuGraph.setMnemonic(71);
	    menuGraph.add(menuListOfNodes);
	    menuGraph.add(menuListOfEdges);
	    
	    menuHelp.setMnemonic(72);
	    menuHelp.add(menuInstruction);
	    menuHelp.add(menuAuthor);
	    
	    menuBar.add(menuFile);
	    menuBar.add(menuGraph);
	    menuBar.add(menuHelp);
	    setJMenuBar(menuBar);
	    
	    setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
	    if (source == menuNew) {
	      panel.setGraph(new Graph());
	    }
	    if (source == menuOpenFile) {
	    	String fileName = JOptionPane.showInputDialog("Podaj nazwe pliku");
		     if ((fileName == null) || (fileName.equals(""))) return;
	    	try 
			{
				panel.setGraph((Graph.loadGraph(fileName)));
			} 
			catch (ClassNotFoundException | IOException e) 
			{				
				JOptionPane.showMessageDialog(this, e.getMessage(), "BLAD", 0);
			}
			panel.repaint();
	    }
	    if (source == menuSave) {
	    	String fileName = JOptionPane.showInputDialog("Podaj nazwe pliku");
		     if ((fileName == null) || (fileName.equals(""))) return;
	    	try 
			{
				panel.getGraph().saveGraph(fileName);
			} 
			catch (IOException e) 
			{	
				e.printStackTrace();
			}
	    	field.setText("");
	    }
	    if (source == menuShowExample) {
	      showBuildinExample();
	    }
	    if (source == menuExit) {
	      System.exit(0);
	    }
	    if (source == menuListOfNodes) {
	      showListOfNodes(panel.getGraph());
	    }
	    if (source == menuListOfEdges) {
	      showListOfEdges(panel.getGraph());
	    }
	    if (source == menuAuthor) {
	      JOptionPane.showMessageDialog(this, APP_AUTHOR, "Author", 1);
	    }
	    if (source == menuInstruction) {
	      JOptionPane.showMessageDialog(this, APP_INSTRUCTION, "Instruction", -1);
	    }
	    System.out.println(((JMenuItem)source).getText());
	}

	private void showBuildinExample() {
		Graph graph = new Graph();
	    
		Node n1 = new Node(130, 70, "A", Color.CYAN);
	    Node n2 = new Node(220, 220, "B", Color.GREEN);
	    Node n3 = new Node(100, 300, "C", Color.RED);
	    Node n7 = new Node(300, 70, "C", Color.BLUE);
	    
	    graph.addNode(n1);
	    graph.addNode(n2);
	    graph.addNode(n3);
	    graph.addNode(n7);
	    

	    graph.addEdge(new Edge(n1, n2, Color.YELLOW));
	    graph.addEdge(new Edge(n2, n3, Color.BLUE));
	    graph.addEdge(new Edge(n1, n3, Color.CYAN));
	    panel.setGraph(graph);
	    repaint();
	  }
}
