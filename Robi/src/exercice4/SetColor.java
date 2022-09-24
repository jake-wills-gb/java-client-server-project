package exercice4;

import java.awt.Color;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;
import tools.Tools;

public class SetColor implements Command {
	
	
	public SetColor() {
	}
	
	public Reference run(Reference receiver, SNode method) {
		
		GElement a = null;
		GSpace b = null;
		
		if(receiver.getReceiver() instanceof GElement) {
			a = (GElement) receiver.receiver;
		}
		else if (receiver.getReceiver() instanceof GSpace) {
			b = (GSpace) receiver.receiver;
		}
		
		Color color = Tools.getColorByName(method.get(2).contents());
		if(receiver.getReceiver() instanceof GElement) {
			a.setColor(color);
		}
		else if (receiver.getReceiver() instanceof GSpace) {
			b.setColor(color);
		}
		
		return receiver;
	}
}