package exercice1;

import java.awt.Color;
import java.awt.Dimension;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import tools.Tools;

import java.awt.Point;
import java.util.Random;

public class Exercice1_0 {
	GSpace space = new GSpace("Exercice 1", new Dimension(200, 150));
	GRect robi = new GRect();

	public Exercice1_0() {
		space.addElement(robi);
		space.open();
		
		int r_size = robi.getWidth(); 
		int width = space.getWidth();
		int height = space.getHeight();
		int step = 1;
		Random rand = new Random();
		
		System.out.print(space.getWidth() + "\n");
		System.out.print(space.getHeight());
		
		// Produit une animation qui se répète
		while(true)
		{
			Tools.sleep(1000);
			switch(step) {
			   	case 1:
			  		robi.setPosition(new Point(width-r_size,0));
			   		step = 2;
				break;
			    	
			   	case 2:
			   		robi.setPosition(new Point(width-r_size,height-r_size));
			   		step = 3;
				break;
			    		
			   	case 3: 
			   		robi.setPosition(new Point(0,height-r_size));
			   		step = 4;
				break;
			    		
			   	case 4:
			   		robi.setPosition(new Point(0,0));
			   		robi.setColor(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
			   		step = 1;
				break;  	
			}
		}
	}

	public static void main(String[] args) {
		new Exercice1_0();
	}

}