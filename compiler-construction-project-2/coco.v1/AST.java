import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
// collection of non-OO auxiliary functions (currently just error) 
class faux {
    public static void error(String msg) {
		System.err.println("Interpreter error: "+msg);
		System.exit(-1);
    }
}

abstract class AST {
	abstract String compile(Environment env);
	abstract void typecheck(Environment env);
}

class Environment {
    private HashMap<String,String> variableValues = new HashMap<String,String>();
	public ArrayList<String> tokenList = new ArrayList<String>();
	public ArrayList<String> argumentList = new ArrayList<String>();
    public Environment() {}
    public void setVariable(String name, String value) {
		variableValues.put(name, value);
    }
    public String getVariable(String name) {	
		String value = variableValues.get(name);
		if (value == null) faux.error("Variable not defined: "+name);
		return value;
    }
	public void addToken(String token) {
		if(tokenList.contains(token))
			faux.error(token + " already defined");
		else
			tokenList.add(token);
	}
	
	public void addArgument(String argument) {
		if(argumentList.contains(argument))
			faux.error(argument + " already defined");
		else
			argumentList.add(argument);
	}
	
	public void removeVariable(String name) {
		variableValues.remove(name);
	}
	
	
	
	public String currentDataType;
	public String currentDataType2;
}



class Start extends AST {
    public List<TokenDef> tokendefs;
    public List<DataTypeDef> datatypedefs;
    Start(List<TokenDef> tokendefs, List<DataTypeDef> datatypedefs) {
		this.tokendefs=tokendefs;
		this.datatypedefs=datatypedefs;
    }
	
	void typecheck(Environment env) {
		for(TokenDef td : tokendefs) {
			td.typecheck(env);
		}
		for(DataTypeDef dtd : datatypedefs) {
			dtd.typecheck(env);
		}
	}
	
	String compile(Environment env) {
		String output = "";
		for(TokenDef td : tokendefs)
			output += td.compile(env);
		for (DataTypeDef dtd: datatypedefs) 
			output += dtd.compile(env);
		return output + "\n";
	}
	
}

class TokenDef extends AST	// Used to print antlr code. Not needed at first.
{
    public String tokenname; // NUM, ID
    public String ANTLRCODE; // Anything in between # #
    TokenDef(String tokenname, String ANTLRCODE)
	{
		this.tokenname=tokenname;
		this.ANTLRCODE=ANTLRCODE;
    }
	String compile(Environment env)
	{
		env.setVariable(tokenname, "token");
		return "";
	}
	void typecheck(Environment env) {
		env.addToken(tokenname);
	}
}


class DataTypeDef extends AST
{	
    public String dataTypeName; // Expr before constant. (The one the class will extend)
    public List<Alternative> alternatives;
    DataTypeDef(String dataTypeName, List<Alternative> alternatives)
	{
		this.dataTypeName=dataTypeName;
		this.alternatives=alternatives;
	}
	String compile(Environment env)
	{
		String output = "";
		env.currentDataType = dataTypeName;
		env.setVariable(dataTypeName, "dataType");
		for(Alternative at : alternatives)
			output += at.compile(env) + "\n";
		if (output == null)
			faux.error("Variable not defined: " + dataTypeName);
		output = "\nabstract class " + dataTypeName + " {};\n" + output;
		return output;
	}
	void typecheck(Environment env) {
		env.addToken(dataTypeName);
		for(Alternative ag : alternatives) {
			ag.typecheck(env);
			env.setVariable(ag.constructor, "Constructor");
		}
	}
}

class Alternative extends AST
{
    public String constructor;	// Constant, Variable, Mult & Add
    public List<Argument> arguments;
    public List<Token> tokens;
	
    Alternative(String constructor, List<Argument> arguments,  List<Token> tokens)
	{
		this.constructor=constructor;
		this.arguments=arguments;
		this.tokens=tokens;
    }
	String compile(Environment env)
	{
		String output = "";
		env.setVariable(constructor, "constructor");
		output += "class " + constructor + " extends " + env.currentDataType + " {\n";
		for(Argument ag : arguments) {
			output += "\tpublic " + ag.compile(env) + ";\n";
		}
		output += "\t" + constructor + "(";
		for(Argument ag : arguments) {
			output += ag.compile(env) + ", ";
		}
		output = output.substring(0, output.length()-2); 
		output += ") {\n";
		
		for(Argument ag: arguments) {
			String x = ag.compile(env);
			output += "\t\tthis." + env.currentDataType2 + " = " + env.currentDataType2 + ";\n";
		}
		output += "\t}\n";
		output += "\n" + "\tpublic String toString() {\n\t\treturn \"\"+";
		for(Token tk : tokens) {
			output += tk.compile(env) + "+";
		}
		output = output.substring(0, output.length()-1); 
		output += ";\n\t}\n}";
		return output;
	}
	void typecheck(Environment env) {
		if(env.tokenList.contains(constructor))
			faux.error(constructor + " already defined");
		for(Argument ag : arguments) {
			ag.typecheck(env);
			env.setVariable(ag.type, "Type");
			env.setVariable(ag.name, "Name");
		}
		for(Argument ag : arguments)
			env.removeVariable(ag.name);
		for(Token tk : tokens) {
			tk.typecheck(env);
		}
			
			
	}
}

class Argument extends AST
{
    public String type; // expr before e1 and e2.
    public String name; // e1, e2, v, name etc
    Argument(String type, String name){
		this.type=type; this.name=name;
	}
	String compile(Environment env) {
		String temptype = type;
		env.currentDataType2 = name;
		if (env.getVariable(type).equals("token"))
		{
			temptype = "String";
		}
		return temptype + " " + name;
	}
	int x, y = 0;
	void typecheck(Environment env) {
		for(String tk : env.tokenList) {
			if(tk.equals(type))
				x++;
				
		}
		if(x == 0)
			faux.error("Argument type does not exist");
		
		if(env.tokenList.contains(name))
			faux.error("Argument name already exists");
		for(String tk : env.tokenList) {
			if(tk.equals(name))
				y++;
				
		}
		if(y != 0)
			faux.error("\nYou are using a datatype or tokenname as an argument: " + name);
		env.addArgument(name);
	}
}

abstract class Token extends AST {
}

class Nonterminal extends Token
{
    public String name; // e1, e2 within ( )
    Nonterminal(String name)
	{
		this.name=name;
	}
	String compile(Environment env)
	{
		if (name == null)
			faux.error("Variable not defined: " + name);
		return name;
	}
	void typecheck(Environment env) {
		if(env.argumentList.contains(name))
			env.argumentList.remove(name);
		else
			faux.error("Variable not deined: " + name + " or Variable used twice or Variable not used");
	}
}

class Terminal extends Token
{
    public String token; // (, ), ', *, +
    Terminal(String token)
	{
		this.token=token;
	}
	String compile(Environment env)
	{
		String output = "";
		if (output == null)
			faux.error("Variable not defined: " + token);
		
		return token.replaceAll("'", "\"");
	}
	void typecheck(Environment env) {
 		if(env.argumentList.contains(token))
			faux.error("\nTerminal token already taken " + token);
	}
}

