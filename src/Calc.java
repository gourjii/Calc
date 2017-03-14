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
	
	private boolean validateExpression(String s)
	{
		return s.matches(Calc.exp_pattern);
	}
	
	private int findActionPos (String s)
	{
		for(int i=0; i<s.length(); i++)
		{
			for (int j = 0; j < Calc.actions.length; j++)
			{
				if (s.charAt(i) == Calc.actions[j])
				{
					//this.actionPos = i;
					return i; //returns position of an action
				}
			}
		}
		return ' ';
	}
	
	private void cutNumbers (String s)
	{
		String first_string = s.substring(0, this.actionPos);
		String second_string = s.substring(this.actionPos + 1);
		this.firstNum = Integer.parseInt(first_string);
		this.secondNum = Integer.parseInt(second_string);
	}

	private int applyAction (int first, char act, int second)
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
	
	public void Calculate(String s)
	{
		if (this.validateExpression(s) == true)
		{
			this.actionPos = this.findActionPos(s);
			this.action = s.charAt(this.actionPos);//set value for actionPos
			this.cutNumbers(s); //set first and second num
			int res = this.applyAction(this.firstNum, this.action, this.secondNum); //returns result
			System.out.println(res);
		}
		else {System.out.println("incorrect input: num+action+num, e.g. 2+2");}
	}
	
	public static void main(String [] args)
	{ 
		Calc c = new Calc();
		c.inputStr = c.getInput(); 
		c.Calculate(c.inputStr);
	}
}
