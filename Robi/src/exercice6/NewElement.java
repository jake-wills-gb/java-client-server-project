package exercice6;

import java.lang.reflect.InvocationTargetException;

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
			ref.addCommand("sleep", new Sleep());
			ref.addCommand("add", new AddElement(env));
			ref.addCommand("addScript", new AddScript());
			ref.addCommand("runScript", new RunScript(env));
			return ref;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		System.out.print("return NewElement\n");
		return null;
	}
}