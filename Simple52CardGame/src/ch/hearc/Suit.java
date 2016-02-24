package ch.hearc;

public class Suit {
	
	private final char suit; 
	private final static char SPADES = '\u2660';
	private final static char HEARTS = '\u2665';
	private final static char DIAMONDS ='\u2666';
	private final static char CLUBS = '\u2663';
	
	Suit(char s){
		this.suit=s; 
	}
	
	public static char getSpades() {
		return SPADES;
	}

	public static char getHearts() {
		return HEARTS;
	}

	public static char getDiamonds() {
		return DIAMONDS;
	}

	public static char getClubs() {
		return CLUBS;
	}

	public char getSuit() {
		return suit;
	}
	
	@Override
	public String toString() {
		return ""+suit;
	}
}
