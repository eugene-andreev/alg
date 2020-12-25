package my.alg.parser;

import java.util.HashMap;
import java.util.Map;

/*
 * GRAMMAR
 * expr = mul + expr
 * expr = mul
 * mul 	= id * mul
 * mul 	= id
 * id 	= IDENTIFIER
 */
public class SimpleParser {
	char[] expr;
	int curPos = 0;
	char aheadToken;
	char EOL = '\n';

	public static void main(String arg[]) {
		SimpleParser p = new SimpleParser();
		Token tree = p.parse("a+b*c+d*e".toCharArray());
		System.out.println("\t" + tree + ":");

		Map<Character, Integer> values = new HashMap<>();
		values.put('a', 1);
		values.put('b', 2);
		values.put('c', 3);
		values.put('d', 4);
		values.put('e', 5);

		p.calculate(tree, values);
	}

	Token parse(char[] expr) {
		this.expr = expr;
		aheadToken = expr[1];
		return expr();
	}

	void next() {
		curPos++;
		if (curPos >= expr.length - 1) {
			aheadToken = EOL;
		} else {
			aheadToken = expr[curPos + 1];
		}
	}

	Token expr() {
		Token mul = multDiv();
		if (cur() == '+') {
			next();
			return new BinaryOperator('+', mul, expr());
		}
		return mul;
	}

	Token multDiv() {
		Token id = id();
		if (cur() == '*') {
			next();
			return new BinaryOperator('*', id, multDiv());
		}
		return id;
	}

	Token id() {
		Value v = new Value(expr[curPos]);
		next();
		return v;
	}

	char cur() {
		if (curPos >= expr.length - 1) {
			return EOL;
		} else {
			return expr[curPos];
		}
	}

	int calculate(Token tree, Map<Character, Integer> values) {
		if (tree instanceof BinaryOperator) {
			Token lhs = ((BinaryOperator) tree).lhs;
			Token rhs = ((BinaryOperator) tree).rhs;
			char operator = ((BinaryOperator) tree).operator;
			if (operator == '+') {
				int sum = calculate(lhs, values) + calculate(rhs, values);
				System.out.println(lhs + " + " + rhs + " = " + sum);
				return sum;
			}
			int mul = calculate(lhs, values) * calculate(rhs, values);
			System.out.println(lhs + " * " + rhs + " = " + mul);
			return mul;
		}
		if (tree instanceof Value) {
			return values.get(((Value) tree).value);
		}
		return 0;
	}

}
