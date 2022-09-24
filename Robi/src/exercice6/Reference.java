package exercice6;

import java.util.HashMap;
import java.util.Map;

import stree.parser.SDefaultNode;
import stree.parser.SNode;

public class Reference {
	
	// Classe référence, composée d'un objet, et de deux HashMap
	// primitives : Associe le nom d'une commande (String) à une commande
	// scripts : Associe le nom d'un script (String) à un script (SNode)
	
	Object receiver;
	Map<String, Command> primitives;
	Map<String, SNode> scripts;
	
	public Reference(Object receiver) {
		this.receiver = receiver;
		primitives = new HashMap<String, Command>();
		scripts = new HashMap<String, SNode>();
	}
	
	public void addCommand(String str, Command primitive) {
		primitives.put(str, primitive);
	}
	
	public Command getCommandByName(String str) {
		return primitives.get(str);
	}
	
	public void addScript(String str, SNode script) {
		// Ajoute un SNode à la HashMap avec str en tant que clé
		scripts.put(str, script);
	}
	
	public SNode getScriptByName(String str) {
		return scripts.get(str);
	}
	
	public SNode cloneScript(SNode node) {
		
		// Retourne une copie du SNode passé en paramètre
		// Appelle récursivement sur tous les enfants du SNode pour en copier l'intégralité
		
		SNode newNode = new SDefaultNode();
		newNode.setContents(node.contents());
		
		if(node.hasChildren()) {
			for(int i=0;i<node.size();i++) {
				SNode newChild = this.cloneScript(node.get(i));
				newChild.setParent(newNode);
				newNode.addChild(newChild);
			}
		}
		
		return newNode;
	}

	public Reference run(SNode expr) {
		
		// Lance soit une commande, soit un script qui est une suite de commandes enregistrées
		
		String word = null;
		Command cmd = null;
		
		// Le mot désignant le script ou commande est le 2ème de l'expression
		// Si aucun script associé au mot n'est trouvé dans la map des scripts alors on recherche
		// dans la map des commandes
		// Si un script est trouvé la commande "runScript" est lancée avec la reference et l'expression
		// en paramètres
		// Si une commande est trouvée elle est exécutée de la même manière
		// Si une commande n'est pas trouvée alors la méthode retourne null
		
		if(expr.size()>1) {
			word = expr.get(1).contents();
		}
		
		if(this.getScriptByName(word) != null) {
			
			cmd = this.getCommandByName("runScript");
			return cmd.run(this, expr);
		}
		else if ((cmd = this.getCommandByName(expr.get(1).contents())) != null)
		{
			return cmd.run(this, expr);
		}
		System.out.println("Command not found");
		return null;
		
	}
	
	public Object getReceiver() {
		return receiver;
	}
}
