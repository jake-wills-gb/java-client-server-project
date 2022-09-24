package exercice4;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

// La commande AddElement lance une commande de création d'objet graphique
// et ajoute cet élément au space et sa référence à l'environnement

public class AddElement implements Command {
	
	Environment env;
	
	public AddElement(Environment env) {
		this.env = env;
	}
	
	@Override
	public Reference run(Reference reference, SNode method) {
		GSpace space = (GSpace) reference.getReceiver();
		
		Reference target = env.getReferenceByName(method.get(3).get(0).contents().toLowerCase() +".class");
		
		Reference elem = target.run(method.get(3));		
		
		space.addElement((GElement) elem.receiver);
		env.addReference(method.get(2).contents(), elem);
		
		space.repaint();
		
		return null;
	}

}
