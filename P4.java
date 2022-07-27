import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
/**
 * @author Chad Chapman
 * 
 * Input file f.dat: (f*g)+(f/g)
 *					 (f*g)+h
 *					 (t*s)+(s*t)
 * 					 (a+b)
 *					 (a-b)
 *
 * Output: My name is Chad Chapman
 *		   derivative (static method) for (f*g)+(f/g) is:
 *		   (((f`*g)+(f*g`))+(((g`*f)-(f*g`))/(g*g)))
 *			
 * 		   derivative (instance method) for (f*g)+(f/g) is:
 *		   (((f`*g)+(f*g`))+(((g`*f)-(f*g`))/(g*g)))
 *			
 *		   derivative (static method) for (f*g)+h is:
 *		   (((f`*g)+(f*g`))+h`)
 *			
 *		   derivative (instance method) for (f*g)+h is:
 *		   (((f`*g)+(f*g`))+h`)
 *			
 *		   derivative (static method) for (t*s)+(s*t) is:
 *		   (((t`*s)+(t*s`))+((s`*t)+(s*t`)))
 *			
 *		   derivative (instance method) for (t*s)+(s*t) is:
 *		   (((t`*s)+(t*s`))+((s`*t)+(s*t`)))
 *		
 *		   derivative (static method) for (a+b) is:
 *		   (a` + b`)
 *		 
 *	   	   derivative (instance method) for (a+b) is:
 *		   (a` + b`)
 *			
 *		   derivative (static method) for (a-b) is:
 *		   (a` - b`)
 *			
 *		   derivative (instance method) for (a-b) is:
 *		   (a` - b`)
 */
public class P4 
{
	public static void main(String[] args)throws IOException
	{
		System.out.println("My name is Chad Chapman");
		String mess;
		FunExpr x;
		BufferedReader reader = new BufferedReader(new FileReader("f.dat"));
		mess = reader.readLine();
		while(mess != null)
		{
			System.out.println("derivative (static method) for "+mess+" is:");
			System.out.println(DerivativeStatic(mess)+"\n");
			x = new FunExpr(mess);
			System.out.println("derivative (instance method) for "+mess+" is:");
			System.out.println(x.derivativeInstance()+"\n");
			mess = reader.readLine();
		}//end while
		reader.close();
	}//end main
	
	public static String DerivativeStatic(String mess)
	{
		//mess is a fully parenthesized legal expression involving
		//'+' and integers, and spaces.
		int count = 0;
		int j;
		int x=0;
		String add, mult, sub, div, constant;
		String exp = mess;;
		FunExpr s = new FunExpr();
		//if mess looks like (...) remove the outer parenthesis
		if(exp.charAt(0)== '(' && exp.charAt(exp.length()-1) == ')')
		{
			for(j=0; j<exp.length(); j++)
			{
				if(exp.charAt(j) == '(')count++;
				else if (exp.charAt(j) == ')')count--;
				if(count == 0)
				{
					x = j; break;
				}//end if
			}//end for
			if(x==exp.length()-1)
			{
				exp = exp.substring(1, exp.length()-1);
			}
		}//end if
		if(exp.indexOf('+')==-1)
		{
			for(int i = 0; i<exp.length(); i++)
			{
				if(exp.charAt(i)=='*')
				{
					mult = s.multDerivative(exp);
					return mult;
				}//end if
				if(exp.charAt(i)=='/')
				{
					div = s.divideDerivative(exp);
					return div;
				}//end if
				if(exp.charAt(i)=='-' && (!exp.contains("*") && 
						!exp.contains("/")))
				{
					sub = s.subtractDerivative(exp);
					return sub;
				}//end if
				if(!exp.contains("*") && !exp.contains("/") && 
						!exp.contains("-") && !exp.contains("+"))
				{
					 constant = s.setConstant(exp);
					 return constant;
				}//end if
			}//end for
			Scanner scan = new Scanner(exp);
			return scan.next();
		}//end if
		if(exp.contains("+") && ((exp.charAt(0)!='(')&&exp.length()!=')'))
		{
			add = s.addDerivative(exp);
			return add;
		}//end if
		else
		{
			for(j=0; j<exp.length(); j++)
			{
				if(exp.charAt(j) == '(')count++;
				else if (exp.charAt(j) == ')')count--;
				else if((exp.indexOf('+')==-1) || (exp.indexOf('-')==-1) || 
						(exp.indexOf('*')==-1) || (exp.indexOf('/')==-1))
				{
					if(count == 0)
					{
						x = j; break;
					}//end if
				}//end else if
			}//end for
			return "(" + DerivativeStatic(exp.substring(0, x)) + "+" + 
				DerivativeStatic(exp.substring(x+1, exp.length())) + ")";
		}//end else
	}//end DerivativeStatic
}//end class P4
