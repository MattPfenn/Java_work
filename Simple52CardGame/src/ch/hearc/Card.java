package ch.hearc;

public class Card {
	
	private final Suit suit; 
	private final Value value;
	
	Card(Suit suit, Value value){
		this.suit = suit; 
		this.value = value; 
	}

	public String toString(){
		return "["+this.getValue()+this.getSuit()+"]";
	}
	
	public Value getValue(){
		return this.value; 
	}
	
	public Suit getSuit(){
		return this.suit; 
	}
}
