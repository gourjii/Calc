import java.util.Scanner;


public class Calc {

	public int first_num = 0;
	public int second_num = 0;
	public int action_pos = 0;
	public char action = ' ';
	public String input_str; 
	final public static char[] actions = {'+','-','/','*','%'};
	final public static String exp_pattern = "[0-9]+[/+-/*//%][0-9]+";
	
	public String getInput()
	{
		Scanner user_input = new Scanner(System.in); // (System.in) 
		String input_str = user_input.nextLine();
		user_input.close();
		return input_str; 
	}
	
	public boolean validateExpression(String s)
	{
		return s.matches(Calc.exp_pattern);
	}
	
	public void findAction (String s)
	{
		for(int i=0; i<s.length(); i++)
		{
			if (this.action == ' ')
			{
				for (int j = 0; j < Calc.actions.length; j++)
				{
					if (s.charAt(i) == Calc.actions[j])
					{
						this.action = s.charAt(i);
						this.action_pos = i;
						break;
					}
				}
			}
			else {break;}
		}
	}
	
	public void cutNumbers (String s)
	{
		String first_string = s.substring(0, this.action_pos);
		String second_string = s.substring(this.action_pos + 1);
		this.first_num = Integer.parseInt(first_string);
		this.second_num = Integer.parseInt(second_string);
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
		c.input_str = c.getInput(); 
		
		if (c.validateExpression(c.input_str) == true)
		{
			c.findAction(c.input_str); //set values for action and action_pos
			c.cutNumbers(c.input_str); //set first and second num
			int res = c.applyAction(c.first_num, c.action, c.second_num); //returns result
			System.out.println(res);
		}
		else {System.out.println("incorrect input: num+action+num, e.g. 2+2");}
		//System.out.println(action);		
	}
}
