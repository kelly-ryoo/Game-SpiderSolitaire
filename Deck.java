
/*
 * Kelly Ryoo. P5. 12/2/2018. This lab took me about an hour and a half.
 * I think this lab could have took me far less time, but the directions were
 * confusing at first, and I was kind of lost. But after reading the instructions and
 * starting to code, it was easier to understand what I needed to do. The card class
 * was a good review that reminded me of the usage of interfaces.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.NullPointerException;

public class Deck {
	// attributes
	int size;
	private ArrayList<Card> deck;

	// constructors
	public Deck() {
		deck = new ArrayList<Card>();
	}

	public Deck(String format) {
		deck = new ArrayList<Card>();
		Scanner scan = new Scanner(format);

		while (scan.hasNext()) {

			String symbol = "";
			int value = 0;
			String suit = "";
			try {
				symbol = scan.next();
			} catch (InputMismatchException input) {
				System.out.println(input.getMessage());
			} catch (NullPointerException nulls) {
				System.out.println(nulls.getMessage());
			}
			try {
				value = scan.nextInt();
			} catch (InputMismatchException input) {
				System.out.println(input.getMessage());
			} catch (NullPointerException nulls) {
				System.out.println(nulls.getMessage());
			}
			try {
				suit = scan.next();
			} catch (InputMismatchException input) {
				System.out.println(input.getMessage());
			} catch (NullPointerException nulls) {
				System.out.println(nulls.getMessage());
			}

			Card card = new Card(symbol, value, suit);

			boolean faceUp = false;
			if (scan.next().equals("true")) {
				faceUp = true;
			}
			card.setFaceUp(faceUp);

			deck.add(card);

		}
	}

	// methods
	public void add(Card card) {
		deck.add(card);
	}

	public void add(Card card, int index) {
		deck.add(index, card);
	}

	public void shuffle() {
		int size = deck.size();
		for (int i = 0; i < size; i++) {
			Card card = deck.get(i);

			int random = (int) (Math.random() * (deck.size() - 1));
			Card card2 = deck.get(random);

			deck.set(i, card2);

			deck.set(random, card);
		}
	}

	public Card get(int index) {
		return deck.get(index);
	}

	public Deck drawList(int size) {
		Deck otherDeck = new Deck();
		int deckSize = size / 2;

		for (int i = 0; i < deckSize; i++) {
			int random = (int) (Math.random() * this.size);
			otherDeck.add(this.get(random));
			this.remove(random);
		}

		return otherDeck;
	}

	public boolean contains(Deck deck, int h) {
		for (int i = 0; i < deck.getSize(); i++) {
			for (int k = 0; k < this.getSize(); k++) {
				if (this.get(h) == deck.get(i)) {
					return true;
				}
			}
		}

		return false;
	}

	public Card draw() {
		return this.get(0);
	}

	public void setSize(int sizes) {
		size = sizes;
	}

	public int getSize() {
		return deck.size();
	}

	public String getSuit(int index) {
		return deck.get(index).getSuit();
	}

	public void remove(int i) {
		deck.remove(i);
	}

	public String getDeck() {
		String str = "";

		for (int i = 0; i < this.getSize(); i++) {
			str += this.get(i).getSymbol() + " " + this.get(i).getValue() + " " + this.get(i).getSuit() + " "
					+ this.get(i).isFaceUp() + "\n";
		}

		return str;
	}

	@Override
	public String toString() {
		String str = "[";
		for (int i = 0; i < deck.size(); i++) {
			str += deck.get(i).toString() + " ";
		}
		str += "]";
		return str;
	}

	public void sort() {

		for (int i = this.getSize() - 1; i > 0; i--) {
			int max = i;
			for (int n = i; n >= 0; n--) {
				if (this.get(max).compareTo(this.get(n)) < 0) {
					max = n;
				}
			}

			swap(max, i);
		}

	}

	public void swap(int max, int i) {
		Card first = this.get(max);
		Card sec = this.get(i);
		this.add(first, i);
		this.remove(i + 1);
		this.add(sec, max);
		this.remove(max + 1);
	}

}