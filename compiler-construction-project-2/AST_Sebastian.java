import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;

class faux // collection of non-OO auxiliary functions (currently just errOut)
{
    public static void error(String msg)
	{
		System.err.println("Interpreter error: "+msg);
		System.exit(-1);
    }
}

class Environment 
{
    private HashMap<String,String> variableValues = new HashMap<String,String>();
    public Environment() 
	{	
	}
    public void setVariable(String name, String value) 
	{
		variableValues.put(name, value);
    }
    public String getVariable(String name)
	{	
		String value = variableValues.get(name);
		if (value == null) faux.error("Variable not defined: "+name);
			return value;
    }
	// Kommer vi til at bruge i mult og i add for eksempel. Steder hvor der er lokale variable, så de skal slettes efter.
	public void removeVariable(String key) 
	{
		variableValues.remove(key);
	}
	
	public String currentDataType;
	
	public String checkVar(String name)
	{
		String str = variableValues.get(name);
		if(str == null)
			return "false";
		return "true";
    }	
}

abstract class AST
{
	String typeCheck(Environment env, ArrayList<String> tokData, ArrayList<String> argumentList)
	{
		return "";
	}
	
	String compile(Environment env)
	{
		return "";
	}
}

class Start extends AST
{
    public List<TokenDef> tokendefs;
    public List<DataTypeDef> datatypedefs;
    Start(List<TokenDef> tokendefs, List<DataTypeDef> datatypedefs)
	{
		this.tokendefs=tokendefs;
		this.datatypedefs=datatypedefs;
    }
	
	@Override String typeCheck(Environment env, ArrayList<String> tokData, ArrayList<String> argumentList)
	{
		String errOut = "";
		for(int i = 0; i < tokendefs.size(); i++)
		{
			errOut += tokendefs.get(i).typeCheck(env, tokData, argumentList);
			env.setVariable(tokendefs.get(i).tokenname, "TokenName");
		}
		
		for(int i = 0; i < datatypedefs.size(); i++)
		{
			errOut += datatypedefs.get(i).typeCheck(env, tokData, argumentList);
			env.setVariable(datatypedefs.get(i).dataTypeName, "dataTypeName");
		}
		if(!errOut.equals(""))
			faux.error(errOut);
		// Skal fjernes
		for(int i = 0; i < argumentList.size(); i++)
		{
//			System.out.println(argumentList.get(i));
		}
		// Fjern slut
		return errOut;
	}
	
	@Override String compile(Environment env)
	{
//		this.typeCheck(env);
		String inp = "";
		for(TokenDef td : tokendefs)
			inp += td.compile(env);
		for (DataTypeDef dtd: datatypedefs) 
			inp += dtd.compile(env);
		return inp + "\n";
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

	@Override String typeCheck(Environment env, ArrayList<String> tokData, ArrayList<String> argumentList)
	{
		String errOut = "";
		tokData.add(tokenname);
		if(env.checkVar(tokenname).equals("true"))					// is the tokenname already taken previously?
			errOut += "\nToken name already taken " + tokenname;
		return errOut;
	}
	
	@Override String compile(Environment env)
	{
		env.setVariable(tokenname, "token");
		String inp = "";
		return inp;
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

	@Override String typeCheck(Environment env, ArrayList<String> tokData, ArrayList<String> argumentList)
	{
		String errOut = "";
		tokData.add(dataTypeName);
		if(env.checkVar(dataTypeName).equals("true"))
			errOut += "\nDataType name already taken " + dataTypeName;
		for(int i = 0; i < alternatives.size(); i++)
		{
			errOut += alternatives.get(i).typeCheck(env, tokData, argumentList);
			env.setVariable(alternatives.get(i).constructor, "Constructor");
		}
		return errOut;
	}
	
	@Override String compile(Environment env)
	{
		String inp = "";
		String exp_out = "";
		env.currentDataType = dataTypeName;
		env.setVariable(dataTypeName, "dataType");
		for(Alternative at : alternatives)
			inp += at.compile(env) + "\n";
		if (inp == null)
			faux.error("Variable not defined: " + dataTypeName);
		exp_out = "abstract class " + dataTypeName + " { };\n" + inp;
		return exp_out;
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
	
	@Override String typeCheck(Environment env, ArrayList<String> tokData, ArrayList<String> argumentList)
	{
		String errOut = "";
		if(env.checkVar(constructor).equals("true"))
			errOut += "\nConstructor already taken " + constructor;
		
		for(int i = 0; i < arguments.size(); i++)
		{
			errOut += arguments.get(i).typeCheck(env, tokData, argumentList);
			env.setVariable(arguments.get(i).type, "Type");
			env.setVariable(arguments.get(i).name, "Name");
		}
		for(int i = 0; i < arguments.size(); i++)
			env.removeVariable(arguments.get(i).name);
		
		for(int i = 0; i < tokens.size(); i++)
		{
			errOut += tokens.get(i).typeCheck(env, tokData, argumentList);
//			env.setVariable(tokens.get(i).name, "Name");
		}
//		for(int i = 0; i < tokens.size(); i++)
//			env.removeVariable(tokens.get(i).name);
		
		return errOut;
	}
	@Override String compile(Environment env)
	{
		env.setVariable(constructor, "constructor");
		String argInp = "";
		String argVar = "";
		String tokInp = "";
		String exp_out = "";
		
		for(Argument ag : arguments)
		{
			argInp += ag.compile(env) + ", ";
			argVar += "\npublic " + ag.type + " " + ag.name + ";";
		}
		argInp = argInp.substring(0, argInp.length()-2);
		for(Token tk : tokens)
			argInp += tk.compile(env);
		
		exp_out = "class " + constructor + " extends " + env.currentDataType + "\n{" + argVar + "\n" + "\t" + constructor + "(" + argInp + ")\n\t{\n";
		
		for(Argument ag : arguments)
			exp_out += "\t\tthis." + ag.name + " = " + ag.name + ";\n"; 
		
		exp_out += "\t}\n" + "\tpublic String toString()\n\t{\n\t\treturn \"\"";

		for(Token tk : tokens)
			exp_out += "+" + tk.toString();
		
		exp_out += ";\n\t}\n}";
		return exp_out;
	}
}

class Argument extends AST
{
    public String type; // expr before e1 and e2.
    public String name; // e1, e2, v and name within ( ) BEFORE ":"
    Argument(String type, String name)
	{
		this.type=type; this.name=name;
	}
	
	@Override String typeCheck(Environment env, ArrayList<String> tokData, ArrayList<String> argumentList)
	{
		
		String errOut = "";
		// Checking if the datatype or tokenname you are using exists (e.g. expr, NUM, ID)
		int x = 0;
		for(int i = 0; i < tokData.size(); i++)
		{
			if(tokData.get(i).equals(type))
				x++;
		}
		if(x == 0)
			errOut += "\nArgument type does not exist: " + type;
		
		// Checking if you are using the same argument twice
		if(env.checkVar(name).equals("true"))
			errOut += "\nArgument name already taken " + name;
		
		// Checking if you are using a tokenname This will make sure that you are not using a datatype or a tokenname as an argument
		int y = 0;
		for(int i = 0; i < tokData.size(); i++)
		{
			if(tokData.get(i).equals(name))
				y++;
		}
		if(y != 0)
			errOut += "\nYou are using a datatype or tokenname as an argument: " + name;

		
		argumentList.add(name);

		return errOut;
	}
	
	@Override String compile(Environment env)
	{
		String inp = "";
		String exp_out = "";
		if (inp == null)
			faux.error("Variable not defined: " + type);
		if (inp == null)
			faux.error("Variable not defined: " + name);
		if (env.getVariable(type).equals("token"))
			type = "String";
		
		exp_out = type + " " + name;
		return exp_out;
	}
}

abstract class Token extends AST
{
	
}

class Nonterminal extends Token
{
    public String name; // e1, e2, v and name within ( ) AFTER ":"
    Nonterminal(String name)
	{
		this.name=name;
	}
	public String toString()
	{
		return "" + name + "";
	}
	
	@Override String typeCheck(Environment env, ArrayList<String> tokData, ArrayList<String> argumentList)
	{
		String errOut = "";
		for(int i = 0; i < argumentList.size(); i++)
		{
			if(!argumentList.get(i).equals(name))
				errOut += "\nNonterminal tokenname either does not exist as an argument:" + name + ", or one of the arguments are unused";
			argumentList.remove(i);
		}
		
		return errOut;
	}
	
	@Override String compile(Environment env)
	{
		String inp = "";
		return inp;
	}
}

class Terminal extends Token
{
    public String token; // (, ), ', *, +
    Terminal(String token)
	{
		this.token=token;
	}
	
	public String toString()
	{
		return "\"" + token.replaceAll("'","") + "\"";	// ' was automatically added. This function removes them
	}
	
	@Override String typeCheck(Environment env, ArrayList<String> tokData, ArrayList<String> argumentList)
	{
		String errOut = "";
		if(env.checkVar(token).equals("true"))
			errOut += "\nTerminal token already taken " + token;
		
		return errOut;
	}
	
	@Override String compile(Environment env)
	{
		String inp = "";
		if (inp == null)
			faux.error("Variable not defined: " + token);
		return inp;
	}
}