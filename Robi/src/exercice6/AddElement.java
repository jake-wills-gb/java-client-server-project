package exercice6;


import graphicLayer.GContainer;
import graphicLayer.GElement;
import stree.parser.SNode;

public class AddElement implements Command {
	
	Environment env;

	public AddElement(Environment environment) {
		this.env = environment;
	}

	@Override
	public Reference run(Reference reference, SNode method) {

		GContainer container = (GContainer) reference.getReceiver();
		Reference elem = null;
		
		Reference target = env.getReferenceByName(method.get(3).get(0).contents().toLowerCase() + ".class");
		elem = target.run(method.get(3));
		
		container.addElement((GElement) elem.getReceiver());
		env.addReference(method.get(0).contents() + "." + method.get(2).contents(), elem);

		
		container.repaint();
		
		return null;
	}

}
