package exercice2;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import stree.parser.SParser;


public class Exercice2_1_0 {
	GSpace space = new GSpace("Exercice 2_1", new Dimension(200, 100));
	GRect robi = new GRect();
	String script = "(space color white)\n"
			+ "(robi color red)\n"
			+ "(robi translate 10 0)\n"
			+ "(space sleep 100)\n"
			+ "(robi translate 0 10)\n"
			+ "(space sleep 100)\n"
			+ "(robi translate -10 0)\n"
			+ "(space sleep 100)\n"
			+ "(robi translate 0 -10)\n"
			+ "";

	public Exercice2_1_0() {
		space.addElement(robi);
		space.open();
		this.runScript();
	}

	private void runScript() {
		SParser<SNode> parser = new SParser<>();
		List<SNode> rootNodes = null;
		try {
			rootNodes = parser.parse(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<SNode> itor = rootNodes.iterator();
		while (itor.hasNext()) {
			this.run(itor.next());
		}
	}
	
	private void run(SNode expr) {
		SNode arg = expr.get(0);
		System.out.print(arg.contents()+" ");
		System.out.print(expr.get(1).contents() + " ");
		System.out.print(expr.get(2).contents() + "\n");
		Color color;
		try {
		    java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(expr.get(2).contents());
		    color = (Color)field.get(null);
		} catch (Exception e) {
		    color = null; // Not defined
		}

		switch(arg.contents())
		{
			case "robi":
				arg = expr.get(1);
				switch (arg.contents()) {
					case "setColor":
					case "color":
						robi.setColor(color);
						break;
						
					case "translate":
						int x = Integer.parseInt(expr.get(2).contents());
						int y = Integer.parseInt(expr.get(3).contents());
						Point gap = new Point(x,y);
						robi.translate(gap);
						break;
				}
				break;
			case "space":
				arg = expr.get(1);
				switch(arg.contents()) {
					case "setColor":
						space.setColor(color);
						break;
						
					case "sleep":
						try
						{
						    Thread.sleep(1000);

						}
						catch(InterruptedException ex)
						{
						    Thread.currentThread().interrupt();
						}
						break;
				}
				break;
		}

	}

	public static void main(String[] args) {
		new Exercice2_1_0();
	}

}