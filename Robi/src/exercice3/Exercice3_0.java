package exercice3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import stree.parser.SParser;
import tools.Tools;

public class Exercice3_0 {
	GSpace space = new GSpace("Exercice 3", new Dimension(200, 100));
	GRect robi = new GRect();
	String script = "" +
	"   (space setColor black) " +
	"   (robi setColor yellow)" +
	"   (space sleep 1000)" +
	"   (space setColor white)\n" + 
	"   (space sleep 1000)" +
	"	(robi setColor red) \n" + 
	"   (space sleep 1000)" +
	"	(robi translate 100 0)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate 0 50)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate -100 0)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate 0 -40)";

	public Exercice3_0() {
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
		Command cmd = getCommandFromExpr(expr);
		if (cmd == null)
			throw new Error("unable to get command for: " + expr);
		cmd.run();
	}

	Command getCommandFromExpr(SNode expr) {

		//Ajout de tous les strings individuels de l'expression dans une liste d'arguments
		List<String> args = new ArrayList<String>();
		String target = expr.get(0).contents();
		String cmd = expr.get(1).contents();
		for(int i=2;i<expr.size();i++) {
			args.add(expr.get(i).contents());
		}

		switch (target) {
			case "space":
				if(cmd.equalsIgnoreCase("setcolor")) {
					Color color = Tools.getColorByName(args.get(0));
					return new SpaceChangeColor(color);
				}
				else if (cmd.equalsIgnoreCase("sleep")) {
					return new SpaceSleep(Integer.parseInt(args.get(0)));
				}
				else {
					throw new Error("Invalid space command");
				}
				
		case "robi":
				if(cmd.equalsIgnoreCase("setcolor")) {
					Color color = Tools.getColorByName(args.get(0));
					return new RobiChangeColor(color);
				}
				else if (cmd.equalsIgnoreCase("translate")) {
					int x = Integer.parseInt(args.get(0));
					int y = Integer.parseInt(args.get(1));
					return new RobiTranslate(new Point(x,y));
				}
				break;
		}

		return null;
	}

	public static void main(String[] args) {
		new Exercice3_0();
	}

	public interface Command {
		abstract public void run();
	}

	public class SpaceChangeColor implements Command {
		Color newColor;

		public SpaceChangeColor(Color newColor) {
			this.newColor = newColor;
		}

		@Override
		public void run() {
			space.setColor(newColor);
		}

	}
	
	public class SpaceSleep implements Command {
		int value;
		
		public SpaceSleep(int value) {
			this.value = value;
		}
		
		public void run() {
			try
			{
			    Thread.sleep(value);

			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}
	}
	
	public class RobiTranslate implements Command {
		Point gap;
		
		public RobiTranslate(Point gap) {
			this.gap = gap;
		}

		@Override
		public void run() {
			robi.translate(gap);
		}
	}
	
	public class RobiChangeColor implements Command {
		Color newColor;
		
		public RobiChangeColor(Color newColor) {
			this.newColor = newColor;
		}

		@Override
		public void run() {
			robi.setColor(newColor);
		}
	}
}