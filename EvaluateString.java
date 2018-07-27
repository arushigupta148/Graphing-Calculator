

import java.text.DecimalFormat;
import java.util.Stack;

public class EvaluateString
{
	int flag;
	
	public EvaluateString() {
		
	}

    public Double evaluate(String expression, EvaluateString es1,String xVal)
    {
    		//SET THE VARIABLES
    		flag=1;
    		expression=purify(expression);
        char[] tokens = expression.toCharArray();
        DecimalFormat df = new DecimalFormat("#.######################");
        String temp="";
        Stack<Double> values = new Stack<Double>();
        Stack<Character> ops = new Stack<Character>();
        
        
        //DECODE THE EXPRESSION
        for (int i = 0; i < tokens.length; i++)
        {	
            if (tokens[i] == ' ')
                continue;
            
            if (tokens[i] == 'v')
            {	es1.flag=-1;
    				return 0.0;
            }

            
            //NUMBERS
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {	
                StringBuffer s1 = new StringBuffer();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.'))   
                		s1.append(tokens[i++]);
                values.push(Double.parseDouble(s1.toString()));
            }
         
            else if(tokens[i]=='e' ||tokens[i]=='π' || tokens[i]=='x') {
            		StringBuffer s1 = new StringBuffer();
	            	if(tokens[i]=='π') {
	        			s1.append(Math.PI);
	        		}
	        		else if(tokens[i]=='e') {
	        			s1.append(Math.E);
	        		}
	        		else if(tokens[i]=='x') {
	        			s1.append(xVal);
	        		}
	            	values.push(Double.parseDouble(s1.toString()));
            }
            
            //OPEN BRACKET
            else if (tokens[i] == '(') {
            		if(temp.length()>0) {
            			switch(temp) {
            			case "sin": ops.push('s');
            				 break;
            			case "cos": ops.push('c');
            				break;
            			case "tan": ops.push('t');
        					break;
            			case "ln": ops.push('l');
        					break;
        				default: 
        					es1.flag=-1;
        	        			return 0.0;
            			}
            		}  
                ops.push(tokens[i]);
            }

            //VALIDATE BRACKETS
            else if (tokens[i] == ')')
            {

            		if(ops.empty())
            			es1.flag=-1;
            		else {
	                while (ops.peek() != '(') {
	                		try {
	                			values.push(applyOperation(ops.pop(), values.pop(), values.pop(),es1));
	                		}catch(Exception e) {
	                			flag=-1;
	                			return 0.0;
	                		}
	                }
	                ops.pop();
            		}
            }
 
            //PUSH OPERATORS INTO STACK AND CHECK PRECEDENCE
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/' || tokens[i]=='^'||tokens[i]=='√')
            {
                while (!ops.empty() && hasLowerPrecedence(tokens[i], ops.peek()))
                {
                		if(ops.peek()=='√'|| ops.peek()=='s'|| ops.peek()=='c'|| ops.peek()=='t'|| ops.peek()=='l') {
                			values.push(applyOperation(ops.pop(), values.pop(), 0.0,es1));
                		}
                		else{
                			values.push(applyOperation(ops.pop(), values.pop(), values.pop(),es1));
                		}
                }

                ops.push(tokens[i]);
            }
            else if(Character.isAlphabetic(tokens[i])) {
            		temp+=tokens[i];
            }
        }

        //EVALUATE EXPRESSION
        while (!ops.empty()) {
        		if(ops.peek()=='('||ops.peek()==')') {
        			es1.flag=-1;
        			ops.pop();
        		}
        		else {
        			if(ops.peek()=='s'||ops.peek()=='c'||ops.peek()=='t'||ops.peek()=='l'|| ops.peek()=='√') {
        				values.push(applyOperation(ops.pop(), values.pop(), 0.0,es1));
        			}
        			else { 
        				if(values.peek()!=null)
        				{	
        					double t=values.pop();
        					if(!values.empty()) {
        						values.push(t);
        						values.push(applyOperation(ops.pop(), values.pop(), values.pop(),es1));
        					}else{
        						es1.flag=-1;
        						ops.pop();
        					}
        				}
        				else {
        					es1.flag=-1;
        					ops.pop();
        				}
        			}
        		}
        }
       
        if(!values.empty()) {
        		double t=values.pop();
        		if(values.empty()) {
        			values.push(t);
        			return Double.parseDouble(df.format(Double.parseDouble(String.valueOf(values.pop()))));
        		}
        		else {
        			es1.flag=-1;
            		return 0.0;
        		}	
        }
        else{
        		es1.flag=-1;
        		return 0.0;
        }
   }

    //PURIFY THE EXPRESSION
    private String purify(String expression) {
    		expression=expression.replace("*"," * ");
    		expression=expression.replace("+"," + ");
    		expression=expression.replace("-"," - ");
    		expression=expression.replace("/"," / ");
    		expression=expression.replace("^"," ^ ");
    		expression=expression.replace("e"," e ");
    		expression=expression.replace("("," ( ");
    		expression=expression.replace(")"," ) ");
    		expression=expression.replace("sin"," sin ");
    		expression=expression.replace("cos"," cos ");
    		expression=expression.replace("tan"," tan ");
    		expression=expression.replace("x","  x ");
    		expression=expression.replace("ln"," ln ");//pi and under root not included
    		return expression;
	}

    //CHECK PRECEDENCE OF CHARACTERS
	public static boolean hasLowerPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if (((op1 == '*' || op1 == '/') || (op1 == '+' || op1 == '-'))&& (op2=='^'||op2=='√'))
            return true;
        else if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
        		return false;
        else if((op1=='^'||op1=='√') && (op2 == '*' || op2 == '/' || op2 == '+' || op2 == '-'))
        		return false;
        else
            return true;
    }

	//EVALUATE THE EXPRESSION
    public static double applyOperation(char op, Double double1, Double double2, EvaluateString es1)
    {
        switch (op)
        {
        case '+':
            return double2 + double1;
        case '-':
            return double2 - double1;
        case '^':
        		return (double) Math.pow(double2, double1);
        case '*':
            return double2 * double1;
        case '/':
            if (double1 == 0)
            {	es1.flag=-1;
            		return 0.0;
            }else
            			return double2 / double1;
        case '√':
        		return Math.sqrt(double1);
        case 's':
        		return Math.sin(double1);
        case 'c':
        		System.out.println(double1);
    			return Math.cos(double1);
        case 't':
        		double finalValue = Math.round( Math.cos(double1) * 100.0 ) / 100.0;
        		if(finalValue!=0)
        		{	
    				return Math.tan(double1);
    			
    			}
        		es1.flag=-1;
        		return 0.0;
        case 'l':
    			return Math.log(double1);
        }
        return 0;
    }
}