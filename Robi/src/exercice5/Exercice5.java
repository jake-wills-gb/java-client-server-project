package exercice5;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import exercice4.DelElement;
import exercice4.Environment;
import exercice4.Interpreter;
import exercice4.NewImage;
import exercice4.NewString;
import exercice4.Reference;
import exercice4.SetColor;
import exercice4.Sleep;
import graphicLayer.GImage;
import graphicLayer.GOval;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import graphicLayer.GString;
import stree.parser.SNode;
import stree.parser.SParser;
import tools.Tools;

public class Exercice5 {

	Environment environment = new Environment();
	
	public Exercice5() {
		GSpace space = new GSpace("Exercice 5", new Dimension(400, 400));
		space.open();

		Reference spaceRef = new Reference(space);
		Reference rectClassRef = new Reference(GRect.class);
		Reference ovalClassRef = new Reference(GOval.class);
		Reference imageClassRef = new Reference(GImage.class);
		Reference stringClassRef = new Reference(GString.class);

		spaceRef.addCommand("setColor", new SetColor());
		spaceRef.addCommand("sleep", new Sleep());

		spaceRef.addCommand("add", new AddElement(environment));
		spaceRef.addCommand("del", new DelElement(environment));
		
		rectClassRef.addCommand("new", new NewElement(environment));
		ovalClassRef.addCommand("new", new NewElement(environment));
		imageClassRef.addCommand("new", new NewImage());
		stringClassRef.addCommand("new", new NewString());

		environment.addReference("space", spaceRef);
		environment.addReference("rect.class", rectClassRef);
		environment.addReference("oval.class", ovalClassRef);
		environment.addReference("image.class", imageClassRef);
		environment.addReference("label.class", stringClassRef);
	}
	
	public void oneShot(String str) {
		SParser<SNode> parser = new SParser<>();
		// compilation
		List<SNode> compiled = null;
		try {
			compiled = parser.parse(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// execution des s-expressions compilees
		Iterator<SNode> itor = compiled.iterator();
		while (itor.hasNext()) {
			new Interpreter().compute(environment, itor.next());
		}
	}
	
	public void mainLoop() {
		while (true) {
			// prompt
			System.out.print("> ");
			// lecture d'une serie de s-expressions au clavier (return = fin de la serie)
			String input = Tools.readKeyboard();
			// creation du parser
			SParser<SNode> parser = new SParser<>();
			// compilation
			List<SNode> compiled = null;
			try {
				compiled = parser.parse(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// execution des s-expressions compilees
			Iterator<SNode> itor = compiled.iterator();
			while (itor.hasNext()) {
				new Interpreter().compute(environment, itor.next());
			}
		}
	}
	
}
