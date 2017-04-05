package parser;

public class Parser
{

	private final String expression;
	private final double value;
	
	public Parser()
	{
		this.expression = null;
		this.value = 0;
	}
	
	public Parser(final String s)
	{
		this.expression = new String(s);
		Calculate c = new Calculate(this.expression);
		this.value = c.getVal();
	}
	
	@Override
	public String toString()
	{
		return value + "";
	}

}
