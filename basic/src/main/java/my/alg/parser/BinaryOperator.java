package my.alg.parser;

class BinaryOperator implements Token {
	char operator;
	Token lhs;
	Token rhs;

	public BinaryOperator(char operator, Token lhs, Token rhs) {
		super();
		this.operator = operator;
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public String toString() {
		return "(" + lhs + " " + operator + " " + rhs + ")";
	}
}