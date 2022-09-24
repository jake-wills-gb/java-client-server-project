package exercice4;

import java.awt.Dimension;

import graphicLayer.GBounded;
import stree.parser.SNode;

public class SetDim implements Command {

	@Override
	public Reference run(Reference receiver, SNode method) {
		
	    GBounded elem = (GBounded) receiver.getReceiver();
	    
	    int x = Integer.parseInt(method.get(2).contents());
	    int y = Integer.parseInt(method.get(3).contents());
	    
	    elem.setDimension(new Dimension(x,y));
		
		
		return null;
	}
	
	
}
