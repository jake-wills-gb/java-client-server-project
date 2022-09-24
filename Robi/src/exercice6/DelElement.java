package exercice6;

import graphicLayer.GContainer;
import graphicLayer.GElement;
import stree.parser.SNode;

public class DelElement implements Command {
	
	Environment env;

	public DelElement(Environment environment) {
		this.env = environment;
	}

	@Override
	public Reference run(Reference receiver, SNode method) {
		
		GContainer container = (GContainer) receiver.getReceiver();
		
		Reference elem = env.getReferenceByName(method.get(2).contents());
		
		// Supprime l'élément de son conteneur et de l'environnement
		container.removeElement((GElement) elem.receiver);
		env.removeReference(method.get(2).contents());
		
		container.repaint();
		
		return null;
	}

}
