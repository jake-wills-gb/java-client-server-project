package examples;

import exercice5.Exercice5;

public class Example1 {
	
	/* 
	 * Exemple exercice 5
	 * 
	 * Dessine un premier carré rouge dans un carré blanc, comme dans le sujet de l'exercicee 5
	 * 
	 * Dessine ensuite un carré, change sa couleur en vert et change sa taille à 50 pixels et le déplace
	 * Ajoute ensuite dans ce carré vert un cercle jaune et le déplace en bas à droite du carré
	 * Le texte "Hello" est ajouté au carré puis le carré et ses enfants sont déplacés à gauche
	 * 
	 */
	String script = "(space add robi (Rect new))\n"
			+ "(space.robi setDim 50 50)\n"
			+ "(space.robi add robi (Rect new))\n"
			+ "(space.robi.robi setColor red)\n"
			+ "(space.robi setColor white)\n"
			+ "(space sleep 1000)"
			+ "(space add robi2 (Rect new))"
			+ "(space.robi2 setColor green)"
			+ "(space.robi2 setDim 50 50)"
			+ "(space.robi2 translate 100 100)"
			+ "(space.robi2 add momo (Oval new))"
			+ "(space.robi2.momo setColor yellow)"
			+ "(space sleep 1000)"
			+ "(space.robi2.momo translate 40 40)"
			+ "(space.robi2 add text (label new \"Hello\"))"
			+ "(space sleep 1000)"
			+ "(space.robi2 translate -80 0 )"
			+ "";
	
	
	public  void launch() {
		Exercice5 exo = new Exercice5();
		exo.oneShot(script);
		exo.mainLoop();
	}
	
	public static void main(String[] args) {
		new Example1().launch();
	}
}
  