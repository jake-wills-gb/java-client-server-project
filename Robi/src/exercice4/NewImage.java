package exercice4;

import stree.parser.SNode;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphicLayer.GImage;

public class NewImage implements Command {

	@Override
	public Reference run(Reference reference, SNode method) {
		
		File path = new File(method.get(2).contents());
		BufferedImage rawImage = null;
		try {
			rawImage = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GImage image = new GImage (rawImage);
		image.setPosition(new Point(5,5));
		
		Reference ref = new Reference(image);
		ref.addCommand("translate", new Translate());
		
		return ref;

	}

}
