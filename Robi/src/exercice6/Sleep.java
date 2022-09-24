package exercice6;

import stree.parser.SNode;
import tools.Tools;

public class Sleep implements Command {

	public Sleep() {
	}
	
	@Override
	public Reference run(Reference receiver, SNode method) {

		Tools.sleep(Integer.parseInt(method.get(2).contents()));

		return receiver;
	}

}
