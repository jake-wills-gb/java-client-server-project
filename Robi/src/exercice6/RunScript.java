package exercice6;

import java.util.HashMap;
import java.util.Map;

import stree.parser.SNode;

public class RunScript implements Command {
	
	Environment env;
	Map<String, String> var;
	
	public RunScript(Environment env) {
		this.env = env;
	}
	
	public SNode replaceVarInSNodes(SNode script) {
		
		// M�thode prenant en param�tre le SNode copi� dans la m�thode run
		// Elle remplace toutes les occurences d'un nom de variable (cl� de la HashMap var)
		// par les valeurs correspondantes (valeur de la HashMap var)
		
		// Dans le cas o� un string contient un point, le string est divis�, les valeurs remplac�es
		// puis le string reconstruit et mis dans le script 
		
		// ex :
		// HashMap : {self=space, name=robi, width=100, color=yellow}
		// SNode en entr�e : ( self add name (Rect new))
		// La fonction retourne un SNode : ( space add robi (Rect new))
		
		for(int i=0;i<script.size();i++) {
			for(int j=0;j<script.get(i).size();j++) {
				
				
				String str = script.get(i).get(j).contents();
				
				if (str == null) { continue; }
				
				if(var.get(str) != null) {
					script.get(i).get(j).setContents(var.get(str));
				}
				
				// Gestion des noms de variable en notation point�e
				if(str.contains(".")) {
					String strArray[] = str.split("\\.");
					StringBuilder sb = new StringBuilder();
					String prefix = "";
					
					for(int k=0;k<strArray.length;k++) {
						if(var.get(strArray[k]) != null) {
							sb.append(prefix);
							prefix = ".";
							sb.append(var.get(strArray[k]));
						}
						else {
							sb.append(prefix);
							prefix = ".";
							sb.append(strArray[k]);
						}
					}
					String newStr = sb.toString();
					script.get(i).get(j).setContents(newStr);
					
				}
				 str = script.get(i).get(j).contents();
				
			}
			
		}
		return script;
	}

	@Override
	public Reference run(Reference receiver, SNode method) {
		
		// M�thode traitant le lancement d'un script enregistr� dans une r�f�rence
		// La m�thode fait une copie (script) du script enregistr� (savedScript) et 
		// modifie cette copie en appelant this.replaceVarInSNodes
		// Le script modifi� est envoy� � l'interpr�teur pour �tre trait� normalement
		
		// La HashMap var associe un nom de variable du script enregistr� � une valeur
		// �crite dans l'expression pour lancer le script
		// ex : 
		// ( space addScript addRect (
		//		(self name width color )
		// 		( self add name (Rect new))
		//   ... ))
		// ( space addRect robi 100 yellow )
		//
		// La HashMap sera telle que :
		// {self=space, name=robi, width=100, color=yellow}
				
		if(var != null) {var.clear();}
		var = new HashMap<String, String>();
		
		
		SNode savedScript = receiver.getScriptByName(method.get(1).contents());
		SNode script = receiver.cloneScript(savedScript);
		
		// Construction des associations variables/valeurs dans la HashMap
		for(int i=0;i<method.size()-1;i++) {
			var.put(script.get(0).get(i).contents(), method.get((i<1?i:i+1)).contents());
		}
		
		
		this.replaceVarInSNodes(script);

		
		for(int i=1;i<script.size();i++)
		{
			new Interpreter().compute(env, script.get(i));
		}
		
		
		
		
		
		return receiver;
	}

}
