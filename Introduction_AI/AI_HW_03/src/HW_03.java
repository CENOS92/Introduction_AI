import java.awt.Container;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class HW_03 {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
	    frame.setTitle("DrawPoly");
	    frame.setSize(350, 250);
	    frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	    Container contentPane = frame.getContentPane();
	    contentPane.add(new DrawPolyPanel());

	    frame.show();
	}
}

class DrawPolyPanel extends JPanel {
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    
	    Polygon p = new Polygon();
	    for (int i = 0; i < 6; i++)
	      p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 6)),
	          (int) (100 + 50 * Math.sin(i * 2 * Math.PI / 6)));

	    g.drawPolygon(p);

	  }
}

class Node{
	int[][] state = new int[6][6];
	int heuristic;
	int alpha;
	int beta;
	
	void printState(){
		for(int i=0 ; i<6 ; i++){
			for(int j=0 ; j<6 ; j++){
				System.out.print(state[i][j]);
			}
			System.out.println();
		}
	}
}

class Minmax{
	Node head;
	
	
}
//}