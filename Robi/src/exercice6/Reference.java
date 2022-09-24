package exercice6;

import java.util.HashMap;
import java.util.Map;

import stree.parser.SDefaultNode;
import stree.parser.SNode;

public class Reference {
	
	// Classe r�f�rence, compos�e d'un objet, et de deux HashMap
	// primitives : Associe le nom d'une commande (String) � une commande
	// scripts : Associe le nom d'un script (String) � un script (SNode)
	
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
		// Ajoute un SNode � la HashMap avec str en tant que cl�
		scripts.put(str, script);
	}
	
	public SNode getScriptByName(String str) {
		return scripts.get(str);
	}
	
	public SNode cloneScript(SNode node) {
		
		// Retourne une copie du SNode pass� en param�tre
		// Appelle r�cursivement sur tous les enfants du SNode pour en copier l'int�gralit�
		
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
		
		// Lance soit une commande, soit un script qui est une suite de commandes enregistr�es
		
		String word = null;
		Command cmd = null;
		
		// Le mot d�signant le script ou commande est le 2�me de l'expression
		// Si aucun script associ� au mot n'est trouv� dans la map des scripts alors on recherche
		// dans la map des commandes
		// Si un script est trouv� la commande "runScript" est lanc�e avec la reference et l'expression
		// en param�tres
		// Si une commande est trouv�e elle est ex�cut�e de la m�me mani�re
		// Si une commande n'est pas trouv�e alors la m�thode retourne null
		
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
