package my.alg.parser;

class Value implements Token {
	char value;

	public Value(char value) {
		super();
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}