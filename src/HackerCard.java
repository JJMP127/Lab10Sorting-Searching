import java.util.Comparator;

public class HackerCard {
	
	//Enumerated type for a suit
	public enum Suit {HACKER(1), PC(2), INTERNET(3), PROGRAM(4);
		private final int level;
		Suit(int dominanceLevel) {
			level = dominanceLevel;
		}
		int dominanceLevel() {
			return level;
		}
	};
	
	//Enumerated type for a rank
	public enum Rank {ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
		SIX(6), SEVEN(7), EIGHT(8), NINE(9), A(10), B(11), C(12),
		D(13), E(14), F(15); 
		private final int level;
		Rank(int dominanceLevel) {
			level = dominanceLevel;
		}
		int dominanceLevel() {
			return level;
		}
	}
		
	private Suit suit; //Suit of a card, it goes between Hacker, PC, Internet, Program
	private Rank rank; // Rank of a card, it goes between 0 till F (In order of 0, 1, 2, ... , E, F) 
	
	/**
	 * A constructor that requires a suit and rank input to initialize a Hacker Card
	 * @param suit - A suit between Hacker, PC, Internet, Program
	 * @param rank - 
	 */
	public HackerCard(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	//getters
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	//To string to print the object
	public String toString() {
		return "Card:[" + suit.name() + ", " + rank.name() + "]";
	}
	
	//Equals method for comparing if another object is the same as this instance
	public boolean equals(Object obj) {
		if(!(obj instanceof HackerCard))
			return false;
		HackerCard other = (HackerCard) obj;
		return this.suit == other.getSuit() && this.rank == other.getRank();
	}
	
	/**
	 * A method to return a copy of the given array
	 * @param deck - Original Array
	 * @return - Copy of shelve
	 */
	public static HackerCard[] copyOf(HackerCard[] deck) {
		HackerCard[] copy = new HackerCard[deck.length];
		for(int i = 0; i < deck.length; i++) {
			copy[i] = deck[i];
		}
		return copy;
	}
	
	
	
	/**
	 * Recursive Quick Sort for an array
	 * @param shelve - Array to be sorted
	 * @param comp - comparator object to compare cards
	 */
	public static void quickSort(Comparator<HackerCard> comp, HackerCard[] deck) {
		quickHelper(comp, deck, 0, deck.length-1);
	}
	
	/**
	 * A helper method for the quick sort's recursive algorithm
	 * @param shelve - Array to be sorted
	 * @param tailIndex - Index of the right part of the array that is yet to be sorted.
	 */
	private static void quickHelper(Comparator<HackerCard> comp, HackerCard[] deck, int left, int right) {
		if(left < right) {
			int parIndex = partition(comp, deck, left, right);
			quickHelper(comp, deck, left, parIndex - 1);
			quickHelper(comp, deck, parIndex + 1, right);
			
		}
	}
	
	/**
	 * A helper method that distributes the items with a pivot and returns the index of where it stopped
	 * @param card - array to be sorted
	 * @param left - left index of the section of to be sorted
	 * @param right - right index of the section to be sorted
	 * @return the index of the partition
	 */
	private static int partition(Comparator<HackerCard> comp, HackerCard[] deck, int left, int right) {
		
		HackerCard pivot = deck[right];
		
		int storeIndex = left;
		
		for(int i = left; i < right; i++){
			
			if(comp.compare(deck[i], pivot) <= 0){
				
				swap(deck, i, storeIndex);
				
				storeIndex++;
								
			}
			
		}
		
		swap(deck, right, storeIndex);
		
		return storeIndex;
	}

	/**
	 * Helper method to swap the position of two objects in a given array
	 * @param deck - array to work in
	 * @param a - index of the first object to swap
	 * @param b - index of the second object to swap
	 */
	private static void swap(HackerCard[] deck, int a, int b) {
		HackerCard temp = deck[a];
		deck[a] = deck[b];
		deck[b] = temp;
	}
	
	/**
	 * Comparator for class type card
	 */
	public static class CardComparator implements Comparator<HackerCard> {
		
		public CardComparator() {
			
		}
		
		@Override
		public int compare(HackerCard first, HackerCard second) {
		
			if(first.getSuit().dominanceLevel() == second.getSuit().dominanceLevel()) return first.getRank().compareTo(second.getRank());
			
			return first.getSuit().compareTo(second.getSuit());
			

			
		}	
	}
}