package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {
	
	private LinkedStack<String> operators = new LinkedStack<String>();
	private LinkedStack<Integer> operands  = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<String> getOperatorStack() { return operators; }
	public LinkedStack<Integer> getOperandStack()  { return operands;  }
	
	
	/** This method performs one step of evaluation of a infix expression.
	 *  The input is a token. Follow the infix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */	
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			int tok = Integer.parseInt(token);
			operands.push(tok);
			
			// TODO: What do we do if the token is an operand?
		} else {
			if(token.equals("(")) {
				operators.push(token);
				return;
			}
			if(operators.isEmpty() || precedence(token) > precedence(operators.top())) {
				operators.push(token);
				return;
			}
			if(token.equals(")")){
				while(!(operators.top().equals("("))) {
					operands.push(process_operator(operators.pop()));
					if(operators.isEmpty()) {
						throw new Exception("missing (");
					}
					
				}
				operators.pop();
				return;
			}
			
				while((!operators.isEmpty()) && !(precedence(token)>precedence(operators.top()))  ) {
					operands.push(process_operator(operators.pop()));
				}
			operators.push(token);
			
			
		}	
	}
	/** This method evaluates an infix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception object
	 *  if the infix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		while(!operators.isEmpty()) {
			operands.push(process_operator(operators.pop()));
		}
		/* TODO: what do we do after all tokens have been processed? */
		
		// The operand stack should have exactly one operand after the evaluation is done
		if(operands.size()>1)
			throw new Exception("too many operands");
		else if(operands.size()<1)
			throw new Exception("too few operands");
		
		return operands.pop();
	}
	
	public Integer process_operator(String token) throws Exception{
		Integer result;
		
		if(token.equals("!")) {
			Integer num = operands.pop();
			if(num==null) {
				throw new Exception("too few operands");
			}
			return -num;
			
		}
		
		Integer val2 = operands.pop() ;
		Integer val1 = operands.pop();
		
		
		if (val1 == null || val2 == null) {
			throw new Exception("too few operands");
		}
		
		if(token.equals("+")) {
			result = val1 + val2;
		}
		else if(token.equals("-")) {
			result = val1 - val2;
		}
		else if(token.equals("/")) {
			if(val2.equals(0) ) {
				throw new Exception("division by zero");
			}
			else {
			result = val1/val2 ;
			}
		}
		else if(token.equals("*")) {
			result = val2 * val1 ;
		}
		else {
			throw new Exception("invalid operator");
		}
		
		
		return result;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
	}
}
