package examples;

import exercice6.Exercice6;

public class Example2 {
	
	
	/* Exemple exercice 6
	 * 
	 *  3 scripts différents :
	 * On peut changer lequel est lancé en modifiant le paramètre de "exo.oneShot()" dans la méthode launch()
	 * 
	 * script : crée un script addRect qui permet de créer un carré avec les dimensions précisées et une couleur
	 * 
	 * script2 : Une simple animation qui s'appelle elle-même la faisant boucler
	 * 
	 * script3 : crée le script addRect précédent et s'en sert pour créer un script draw qui permet de dessiner
	 * 			 un dessin. A ce dessin est associé un script changeColor qui permet d'en changer la couleur

	 */
	
	String script = "( space add robi (Rect new ) ) ( space.robi setColor red ) ( space.robi setDim 50 50 )"
				  + "( space.robi addScript addRect ("
				  	+ "( self name w c )"
				  	+ "( self add name ( Rect new ) ) "
				  	+ "( self.name setColor c )"
				  	+ "( self.name setDim w w ) ) )"
				  + "( space.robi addRect mySquare 30 yellow )";
	
	String script2 = "( space add robin (Rect new ) ) ( space.robin setColor green ) ( space.robin setDim 40 40 )"
					+ "( space.robin addScript animLoop ("
					+ "(self)"
					+ "(self translate 0 40 ) "
					+ "(self sleep 1000) "
					+ "(self translate 40 0)"
					+ "(self sleep 1000) "
					+ "(self translate 0 -40 )"
					+ "(self sleep 1000)"
					+ "(self translate -40 0 )"
					+ "(self sleep 1000)"
					+ "(self animLoop))) ( space.robin animLoop )";
	
	String script3 = "( space addScript addRect ("
						+ "( self name w c )"
					  	+ "( self add name ( Rect new ) ) "
					  	+ "( self.name setColor c )"
					  	+ "( self.name setDim w w ) ) )"
					+ "(space addScript draw ("
						+ "(self name c x y )"
						+ "(self addRect name 350 white)"
						+ "(self.name translate x y )"
						+ "( self.name addScript addRect2 ("
							+ "( self name w c )"
							+ "( self add name ( Rect new ) )"
							+ "( self.name setColor c )"
							+ "( self.name setDim w w )))"
						+ "(self.name addRect2 body 300 c)"
						+ "(self.name.body translate 50 0)"
						+ "(self.name addRect2 leftLeg 160 c)"
						+ "(self.name.leftLeg translate 50 300)"
						+ "(self.name addRect2 rightLeg 160 c)"
						+ "(self.name.rightLeg translate 210 300)"
						+ "(self.name addRect2 back 200 c)"
						+ "(self.name.back setDim 50 200)"
						+ "(self.name.back translate 0 75)"
						+ "(self.name addRect2 eyes 200 white)"
						+ "(self.name.eyes setDim 200 100)"
						+ "(self.name.eyes translate 150 60)"
						+ "(self.name addScript changeColor("
							+ "(self c)"
							+ "(self.body setColor c)"
							+ "(self.leftLeg setColor c)"
							+ "(self.rightLeg setColor c)"
							+ "(self.back setColor c)))))"
					+ "(space draw crewmate red 0 0 )";
				
	
	public  void launch() {
		Exercice6 exo = new Exercice6();
		exo.oneShot(script2);
		exo.mainLoop();
	}
	
	public static void main(String[] args) {
		new Example2().launch();
	}
}
  