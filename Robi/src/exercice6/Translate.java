package exercice6;

import java.awt.Point;

import graphicLayer.GElement;
import stree.parser.SNode;

public class Translate implements Command {
	
	// D�place l'objet pass� en r�f�rence
	
	@Override
	public Reference run(Reference receiver, SNode method) {
		
		GElement obj = (GElement) receiver.getReceiver();
		int x = Integer.parseInt(method.get(2).contents());
		int y = Integer.parseInt(method.get(3).contents());
		
		obj.translate(new Point(x,y));
		
		return receiver;
	}
	

}
