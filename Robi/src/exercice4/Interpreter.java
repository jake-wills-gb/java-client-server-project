package exercice4;

import stree.parser.SNode;

public class Interpreter {
	
	public void compute(Environment environment, SNode expr) {
		
		Reference target = null;
		target = environment.getReferenceByName(expr.get(0).contents());

		target.run(expr);
		
	}
}
