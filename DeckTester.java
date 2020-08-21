import java.util.ArrayList;
public class DeckTester
{
    public static void main(String[] args) {
        //create symbol array
        ArrayList<String> symbols = new ArrayList<String>();
        symbols.add("A");
        symbols.add("2");
        symbols.add("3");
        symbols.add("4");
        symbols.add("5");
        symbols.add("6");
        symbols.add("7");
        symbols.add("8");
        symbols.add("9");
        symbols.add("10");
        symbols.add("J");
        symbols.add("Q");
        symbols.add("K");

        //create array of symbol values
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(8);
        values.add(9);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);

        //create array of suits
        ArrayList<String> suits = new ArrayList<String>();
        suits.add("diamond");
        suits.add("club");
        suits.add("heart");
        suits.add("spade");

        //add to new deck object
        int deckSize = 13;
        Deck decks = new Deck();
        for(int i = 0; i < deckSize; i++){
            decks.add(new Card(symbols.get(i), values.get(i),
                    suits.get((int)(Math.random() * 4))));
            decks.get(i).setFaceUp(true);
        }
        
        System.out.println(decks);
        decks.shuffle();
        System.out.println("shuffled: " + decks);
        decks.sort();
        System.out.println("sorted: " + decks);
        
        /*
        Deck deck = new Deck(decks.getDeck());
        System.out.println(deck.toString());

        
        //print outs
        System.out.println("before: ");
        System.out.println(decks.toString());
        decks.shuffle();
        System.out.println("after shuffling: ");
        System.out.println(decks.toString());
        System.out.println("draw random card: ");
        System.out.println(decks.drawRandomCard());

        System.out.println("draw a list: ");

        System.out.println("other list:");
        System.out.println(decks.drawList(13));

        System.out.println("draw the list: ");
        System.out.println(decks);
        */

    }
}
