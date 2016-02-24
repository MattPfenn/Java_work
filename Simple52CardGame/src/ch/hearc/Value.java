package ch.hearc;

public class Value{
	
	private final static char ACE = 'A';
	private final static char JACK = 'J';
	private final static char QUEEN = 'Q';
	private final static char KING = 'K';
	private final char value;
	
	Value(char value){
		this.value=value;
	}
	
	Value(int value){
		this.value= (char)(value+48); 
	}

	public static char getAce() {
		return ACE;
	}

	public static char getJack() {
		return JACK;
	}

	public static char getQueen() {
		return QUEEN;
	}

	public static char getKing() {
		return KING;
	}

	public char getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return value+"";
	}
	
}
