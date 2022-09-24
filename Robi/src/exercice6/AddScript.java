package exercice6;

import stree.parser.SNode;

public class AddScript implements Command {

	@Override
	public Reference run(Reference reference, SNode method) {
		
		reference.addScript(method.get(2).contents(), method.get(3));
		
		System.out.println(reference.scripts);
		
		return null;
	}

}
