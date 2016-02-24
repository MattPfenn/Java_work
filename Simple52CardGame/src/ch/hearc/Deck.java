package ch.hearc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Deck extends ArrayList<Card>{	
	 
	private static int top; 

	public static void main(String[] args){
		
		Deck deck = new Deck(); 
		System.out.println("Display deck");
		for(int i=0; i<52; i++){
			System.out.println(deck.get(i));
		}
				
		System.out.println("test pioche :");
		System.out.println("1 :"+draw(deck));
		System.out.println("2 :"+draw(deck));
		
		System.out.println("Display deck");
		for(int i=0; i<50; i++){
			System.out.println(deck.get(i));
		}
		
		//int numPlayers = 2; 	   
		//int numCards = 5; 
	
		//distribute(numPlayers,numCards);
		//shuffle(); 
	}
	
	Deck() { 
		super(); 
		System.out.println("creation deck");
		for(int suit=0; suit<4; suit++){
			for(int value=0; value<13; value++){
				switch(suit){
					case 0 : if(value==1){
								 this.add((value+suit),new Card(new Suit(Suit.getSpades()),new Value(Value.getAce()))); 
							 }
							 else if(value==10){
								 this.add((value+suit),new Card(new Suit(Suit.getSpades()),new Value(Value.getJack())));	 
							 }
							 else if(value==11){
								 this.add((value+suit),new Card(new Suit(Suit.getSpades()),new Value(Value.getQueen())));
							 }
							 else if(value==12){
								 this.add((value+suit),new Card(new Suit(Suit.getSpades()),new Value(Value.getKing())));
							 }
							 else{
								 this.add((value+suit),new Card(new Suit(Suit.getSpades()),new Value(value)));
							 }
							 break; 
							 
					case 1 : if(value==1){
								 this.add((value+suit),new Card(new Suit(Suit.getDiamonds()),new Value(Value.getAce())));
					 		 }
							 else if(value==10){
								 this.add((value+suit),new Card(new Suit(Suit.getDiamonds()),new Value(Value.getJack())));
							 }
							 else if(value==11){
								 this.add((value+suit),new Card(new Suit(Suit.getDiamonds()),new Value(Value.getQueen())));
							 }
							 else if(value==12){
								 this.add((value+suit),new Card(new Suit(Suit.getDiamonds()),new Value(Value.getKing())));
							 }
							 else{
								 this.add((value+suit),new Card(new Suit(Suit.getDiamonds()),new Value(value)));
							 }
							 break; 
					
					case 2 : if(value==1){
								this.add((value+suit),new Card(new Suit(Suit.getHearts()),new Value(Value.getAce())));
			 		 		 }
					 		 else if(value==10){
					 			this.add((value+suit),new Card(new Suit(Suit.getHearts()),new Value(Value.getJack())));
					 		 }
					 		 else if(value==11){
					 			this.add((value+suit),new Card(new Suit(Suit.getHearts()),new Value(Value.getQueen())));
					 		 }
					 		 else if(value==12){
					 			this.add((value+suit),new Card(new Suit(Suit.getHearts()),new Value(Value.getKing())));
					 		 }
					 		 else{
					 			this.add((value+suit),new Card(new Suit(Suit.getHearts()),new Value(value)));
					 		 }
							 break; 
					
					case 3 : if(value==1){
								this.add((value+suit),new Card(new Suit(Suit.getClubs()),new Value(Value.getAce())));
							 }
			 		 		 else if(value==10){
			 		 			this.add((value+suit),new Card(new Suit(Suit.getClubs()),new Value(Value.getJack())));
			 		 		 }
			 		 		 else if(value==11){
			 		 			this.add((value+suit),new Card(new Suit(Suit.getClubs()),new Value(Value.getQueen())));
			 		 		 }
			 		 		 else if(value==12){
			 		 			this.add((value+suit),new Card(new Suit(Suit.getClubs()),new Value(Value.getKing())));
			 		 		 }
			 		 		 else{
			 		 			this.add((value+suit),new Card(new Suit(Suit.getClubs()),new Value(value)));
			 		 		 }
							 break; 
				}
			}
		}
		this.top = this.size()-1;
	} 
	
	
	public static void shuffle(Deck d){
		
	}
	
	public static void dealCards(int numPlayers, int numCards){
		
		System.out.println("Distribution des cartes :");
		
		
	}
	
	public static Card draw(Deck d){
		if(d.isEmpty()){
			System.out.println("Deck vide");
		}
		Card cardToDraw = d.get(top); 
		d.remove(top);
		top--;
		return cardToDraw;
	} 
	
	public static void discard(Card c){
		
	}
}


