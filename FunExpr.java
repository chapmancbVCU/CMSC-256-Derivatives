import java.util.Scanner;
/**
 * This class has the derivativeInstance method and other 
 * methods used for computing derivatives
 * @author Chad Chapman
 */
public class FunExpr 
{
	/**
	 * empty constructor
	 */
	FunExpr()
	{
		
	}
	
	/**
	 * One args constructor
	 * @param s input to the constructor
	 */
	FunExpr(String s)
	{
		this.s = s;
	}
	
	/**
	 * this is a recursive method for computing the derivative
	 * from a string that it recieves as input
	 * @return the string printed to the screen show as the 
	 * derivative of the function
	 */
	String derivativeInstance()
	{
		int count = 0;
		int j;
		int x = 0;
		if(s.charAt(0) == '(' && s.charAt(s.length()-1) == ')')
		{
			for(j=0; j<s.length(); j++)
			{
				if(s.charAt(j)=='(')count++;
				else if (s.charAt(j) == ')')count --;
				if(count == 0) {x=j; break;}
			}//end for
			if(x == s.length()-1)
			{
				s = s.substring(1, s.length()-1);
			}//end if
		}//end if
		if(s.indexOf('+')==-1)
		{
			for(int i = 0; i<s.length(); i++)
			{
				if(s.charAt(i)=='+' && (!s.contains("*") && !s.contains("/")))
				{
					return addDerivative(s);
				}//end if
				if(s.charAt(i)=='*')
				{
					return multDerivative(s);
				}//end if
				if(s.charAt(i)=='/')
				{
					return divideDerivative(s);
				}//end if
				if(s.charAt(i)=='-' && (!s.contains("*") &&! s.contains("/")))
				{
					return subtractDerivative(s);
				}//end if
				if(!s.contains("*") && !s.contains("/") && 
						!s.contains("-") && !s.contains("+"))
				{
					 return setConstant(s);
				}//end if
			}//end for
			Scanner scan = new Scanner(s);
			return scan.next();
		}//end if
		if(s.contains("+") && ((s.charAt(0)!='(')&&s.length()!=')'))
		{
			return addDerivative(s);
		}//end if
		else
		{
			for(j=0; j<s.length(); j++)
			{
				if(s.charAt(j)=='(')count++;
				else if (s.charAt(j) == ')')count --;
				else if((s.indexOf('+')==-1) || (s.indexOf('-')==-1) || 
						(s.indexOf('*')==-1) || (s.indexOf('/')==-1))
				{
					if(count == 0) {x=j; break;}
				}//end else if 
			}//end for
			FunExpr left, right;
			left = new FunExpr(s.substring(0,x));
			right = new FunExpr(s.substring(x+1, s.length()));
			return "(" + left.derivativeInstance() + "+" + 
					right.derivativeInstance() + ")";
		}//end else
	}//end valueIs
	
	/**
	 * computes derivatives of the sum of 2 numbers
	 * @param add values to be diferentiated
	 * @return the derivative 
	 */
	public String addDerivative(String add)
	{
		String mess = add;
		char first = mess.charAt(0);
		char last = mess.charAt(2);
		mess = "("+first+"` + "+last+"`)";
		addexpression = mess;
		return addexpression;
	}
	
	/**
	 * computes derivatives of the difference of 2 numbers
	 * @param sub values to be diferentiated
	 * @return the derivative 
	 */
	public String subtractDerivative(String sub)
	{
		String mess = sub;
		char first = mess.charAt(0);
		char last = mess.charAt(2);
		mess = "(" + first + "` - " + last + "`)";
		subexpression = mess;
		return subexpression;
	}
	
	/**
	 * computes derivatives of the product of 2 numbers
	 * @param mess values to be diferentiated
	 * @return the derivative 
	 */
	public String multDerivative(String a)
	{
		String mess = a;
		char first = mess.charAt(0);
		char last = mess.charAt(2);
		mess = "(("+first+"`*"+last+")+("+first+"*"+last+"`))";
		return mess;
	}
	
	/**
	 * computes derivatives of the quotient of 2 numbers
	 * @param mess values to be diferentiated
	 * @return the derivative 
	 */
	public String divideDerivative(String a)
	{
		String mess = a;
		char first = mess.charAt(0);
		char last = mess.charAt(2);
		mess = "(((" + last + "`*" + first + ")-(" + first + "*" + last + 
				"`))/(" +last +"*" + last + "))";
		return mess;
	}
	
	/**
	 * derivative ofa  constant
	 * @param c constant
	 * @return contant differentaited
	 */
	public String setConstant(String c)
	{
		String temp;
		temp= c;
		this.constant = temp+"`"; 
		return constant;
	}
	
	private String s;
	private String constant;
	private String addexpression;
	private String subexpression;
}//end class FunExpr
