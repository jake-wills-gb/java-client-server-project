package exercice5;

import java.lang.reflect.InvocationTargetException;

import exercice4.Command;
import exercice4.Environment;
import exercice4.Reference;
import exercice4.SetColor;
import exercice4.SetDim;
import exercice4.Translate;
import graphicLayer.GElement;
import stree.parser.SNode;

public class NewElement implements Command {
	Environment env;
	
	public NewElement(Environment env) {
		this.env = env;
	}
	
	public Reference run(Reference reference, SNode method) {
		try {
			@SuppressWarnings("unchecked")
			GElement e = ((Class<GElement>) reference.getReceiver()).getDeclaredConstructor().newInstance();
			Reference ref = new Reference(e);
			ref.addCommand("setColor", new SetColor());
			ref.addCommand("translate", new Translate());
			ref.addCommand("setDim", new SetDim());
			ref.addCommand("add", new AddElement(env));
			return ref;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		System.out.print("return NewElement\n");
		return null;
	}
}