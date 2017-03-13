import java.util.Scanner;


public class Calc {

	public int firstNum = 0;
	public int secondNum = 0;
	public int actionPos = 0;
	public char action = ' ';
	public String inputStr; 
	final public static char[] actions = {'+','-','/','*','%'};
	final public static String exp_pattern = "[0-9]+[/+-/*//%][0-9]+";
	
	public String getInput()
	{
		Scanner userInput = new Scanner(System.in); // (System.in) 
		String inputStr = userInput.nextLine();
		userInput.close();
		return inputStr; 
	}
	
	public boolean validateExpression(String s)
	{
		return s.matches(Calc.exp_pattern);
	}
	
	public char findAction (String s)
	{
		for(int i=0; i<s.length(); i++)
		{
			for (int j = 0; j < Calc.actions.length; j++)
			{
				if (s.charAt(i) == Calc.actions[j])
				{
					this.actionPos = i;
					return s.charAt(i);
				}
			}
		}
		return ' ';
	}
	
	public void cutNumbers (String s)
	{
		String first_string = s.substring(0, this.actionPos);
		String second_string = s.substring(this.actionPos + 1);
		this.firstNum = Integer.parseInt(first_string);
		this.secondNum = Integer.parseInt(second_string);
	}

	public int applyAction (int first, char act, int second)
	{
		int res = 0;
		switch (act)
		{
		case '+': res = first + second; break;
		case '-': res = first - second; break;
		case '*': res = first * second; break;
		case '/': 
			if (second == 0) {System.out.println("cannot divide by 0");break;}
			res = first / second; 
			break;
		case '%': res = first % second; break;
		}
		return res;
	}
	
	public static void main(String [] args)
	{ 
		Calc c = new Calc();
		c.inputStr = c.getInput(); 
		
		if (c.validateExpression(c.inputStr) == true)
		{
			c.action = c.findAction(c.inputStr); //set values for action and actionPos
			c.cutNumbers(c.inputStr); //set first and second num
			int res = c.applyAction(c.firstNum, c.action, c.secondNum); //returns result
			System.out.println(res);
		}
		else {System.out.println("incorrect input: num+action+num, e.g. 2+2");}
		//System.out.println(action);		
	}
}
