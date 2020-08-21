/*
 * Kelly Ryoo. 12/4/2018. P5. This lab took me 3 hours.
 * I mostly struggled at first with the constructor, because of stupid mistakes
 * that led me to have problems with boundaries or filling the decks.
 * After I finished the constructor, I started working on the board methods themselves.
 * Many of them are not currently working, and I need to check which ones
 * are actually working. Then I will go back and debug them carefully, as I 
 * know I have boundary issues.
 * 
 */
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Board
{   
    // Attributes
    ArrayList<Deck> list;
    Deck deck = new Deck();
    Deck stack = new Deck();
    Deck drawPile = new Deck();
    int numCards;
    int numSuit;
    int numberOfStacks;
    int decksPerStack;

    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  The number of Cards in a Deck
     *  depends on the number of suits. Here are examples:
     *  
     *  # suits     # numDecks      #cards in overall Deck
     *      1            1          13 (all same suit)
     *      1            2          26 (all same suit)
     *      2            1          26 (one of each suit)
     *      2            2          52 (two of each suit)
     *      4            2          104 (two of each suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.  If you'd like to specify
     *  more than one suit, feel free to add to the parameter list.
     */    
    public Board(int numStacks, int numDecks, boolean heart, boolean club, 
    boolean spade, boolean diamond){

        list = new ArrayList<Deck>(numStacks);
        numberOfStacks = numStacks;

        //find number of suits
        int suitNumber = 0;
        if(diamond){
            suitNumber++;
        }
        if(club){
            suitNumber++;
        }
        if(heart){
            suitNumber++;
        }
        if(diamond){
            suitNumber++;
        }

        //find number of cards
        numCards = suitNumber * numDecks * 13;

        //create array of symbols
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
        if(diamond){
            suits.add("diamond");
        }
        if(club){
            suits.add("club");
        }
        if(heart){
            suits.add("heart");
        }
        if(diamond){
            suits.add("spade");
        }

        for(int i = 0; i < suits.size(); i++){
            for(int k = 0; k < numDecks; k++){
                for(int h = 0; h < 13; h++){
                    deck.add(new Card(symbols.get(h), values.get(h), suits.get(i)));
                }
            }
        }

        //shuffle
        deck.shuffle();

        //set deck equal to drawPile
        drawPile = deck;
        //System.out.println("shuffled original pile: " + drawPile.toString());

        //set array list to numStacks amount of decks 
        for(int i = 0; i < numStacks; i++){
            list.add(new Deck());
        }

        //distribute half of the cards from drawPile into each deck of the list
        ArrayList<Card> half = new ArrayList<Card>();
        for(int i = 0; i < numCards / 2; i++){
            half.add(drawPile.get(i));
            //half.get(i).setFaceUp(true);
        }
        for(int i = numCards / 2 - 1; i >= 0; i--){
            drawPile.remove(i);
            //half.get(i).setFaceUp(true);
        }

        //System.out.println("split drawPile into half:");
        //System.out.println("remaining half of drawPile: " + drawPile.toString());
        //System.out.println("half to be distributed into stacks:" + half.toString());

        //distribute into stacks
        for(int i = 0; i < list.size(); i++){
            //System.out.println("i = " + i);
            int halfsize = half.size() / (numStacks - i);
            for(int k = 0; k < halfsize; k++){
                // System.out.println(half.size() / numStacks);
                //System.out.println("k = " + k);
                //System.out.println("half.get(k) = " + half.get(k));
                list.get(i).add(half.get(k));
            }
            //System.out.println(list.get(i));
            for(int k = halfsize - 1; k >= 0; k--){
                //System.out.println("removing: " + half.get(k));
                half.remove(k);
            }
        }         
        //System.out.println("after distributing to stacks: " + list.toString());

    }

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.  Change the parameter list to match
     *  your implementation of Card if you need to.
     */
    public void makeMove(String symbol, int src, int dest) {
        //SEARCH FOR SYMBOL
        Deck deckSrc = list.get(src);
        Deck deckDest = list.get(dest);
        int index = 0;
        boolean symbolExists = false;
        for(int i = deckSrc.getSize() - 1; i >= 0; i--){
            if(deckSrc.get(i).getSymbol().equals(symbol)){
                index = i;
                symbolExists = true;
                break;
            }
        }
        //System.out.println("index= " + index);

        Deck run = new Deck(); //the run thats gonna be taken out and added
        for(int i = index; i <= deckSrc.getSize() - 1; i++){
            if(deckSrc.get(i).isFaceUp()){
                run.add(deckSrc.get(i));
            }
        }

        //CHECK IF IT IS RUN
        boolean isRun = false;
        if(run.getSize() == 1){
            isRun = true;
        }else{
            for(int i = 0; i < run.getSize() - 1; i++){
                if(run.get(i).compareTo(run.get(i + 1)) == 1){
                    isRun = true;
                }else if(run.get(i).compareTo(run.get(i + 1)) == 0){
                    for(int k = i; k >= 0; k--){
                        run.remove(k);
                    }
                    isRun = true;
                }else{
                    isRun = false;
                    break;
                }
            }
        }

        //CHECK IF MOVE IS LEGAL
        boolean isLegal = false;
        //System.out.println("isRun: " + isRun);
        //System.out.println("run: " + run);
        if(deckDest.getSize() == 0|| run.get(0).compareTo(deckDest.get(deckDest.getSize() - 1))== -1){
            isLegal = true;
        }
        //System.out.println("isLegal: " + isLegal);

        //CHECK IF FACEUP
        boolean isFaceUp = false;
        for(int i = 0; i < run.getSize(); i++){
            if(run.get(i).isFaceUp()){
                isFaceUp = true;
            }else{
                isFaceUp = false;
                break;
            }
        }
        System.out.println("isfaceup: " + isFaceUp);

        //CHECK IF MOVE CAN BE MADE
        if(isRun && isLegal && symbolExists && isFaceUp){
            if(index > 0){
                deckSrc.get(index -1);
            }

            for(int i = deckSrc.getSize() - 1; i >= index; i--){
                deckSrc.remove(i);
            }

            int runSize = run.getSize();
            for(int i = 0; i < runSize; i++){
                deckDest.add(run.get(i));
            }
        }else{
            System.out.println("Invalid move");
        }

    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        int size = drawPile.getSize();
        if(numberOfStacks == 0){
        }else if(drawPile.getSize() >= numberOfStacks){
            for(int i = 0; i < numberOfStacks; i++){
                Card card = drawPile.get(drawPile.getSize() - 1);
                //card.setFaceUp(true);
                list.get(i).add(card);
                drawPile.remove(drawPile.getSize() - 1);
            }
        }else{
            for(int k = 0; k < size; k++){
                Card card = drawPile.get(drawPile.getSize() - 1);
                //card.setFaceUp(true);
                list.get(k).add(card);
                drawPile.remove(drawPile.getSize() - 1);
            }
        }
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getSize() != 0){
                return false;
            }
        }

        if(drawPile.getSize() != 0){
            return false;
        }

        return true;
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        ArrayList<String> symbolsList = new ArrayList<String>();
        symbolsList.add("A");
        symbolsList.add("2");
        symbolsList.add("3");
        symbolsList.add("4");
        symbolsList.add("5");
        symbolsList.add("6");
        symbolsList.add("7");
        symbolsList.add("8");
        symbolsList.add("9");
        symbolsList.add("10");
        symbolsList.add("J");
        symbolsList.add("Q");
        symbolsList.add("K");

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

        Deck compare = new Deck();
        for(int i = 12; i >= 0; i--){
            Card card = new Card(symbolsList.get(i), values.get(i), "club");
            compare.add(card);
        }

        Deck source = list.get(sourceStack);
        System.out.println("sourceStack:" + source);
        System.out.println("compare list:" + compare);
        boolean isRun = false;

        int h = 12;
        if(source.getSize() < 13){
            System.out.println("invalid. not a run.");
        }else{

            for(int i = source.getSize() - 1; i > source.getSize() - 13; i--){
                if(source.get(i).equals(compare.get(h)) && source.get(i).isFaceUp()){
                    isRun = true;
                    h--;
                }else{
                    System.out.println("invalid. not a run.");
                    isRun= false;
                    break;
                }
            }
        }

        int size = source.getSize() - 1;
        int sizeMin = source.getSize() - 13;
        if(isRun){
            for(int i = size; i >= sizeMin; i--){
                list.get(sourceStack).remove(i);
            }
        }
    }

    public void saveGame(){
        try {

            EventQueue.invokeAndWait(new Runnable() {

                    @Override

                    public void run() {

                        JFileChooser chooser = new JFileChooser(".");
                        File game;
                        FileWriter saver;
                        while(true){
                            try{
                                chooser.showSaveDialog(null);
                                game = chooser.getSelectedFile();
                                saver = new FileWriter("SolitaireGame.txt");
                                break;
                            }catch(IOException io){
                                System.out.println(io.getMessage());
                            }
                        }

                        String str = "";
                        for(int i = 0; i < numberOfStacks; i++){
                            str += "stack: " + list.get(i).getSize() + "\n";
                            str += list.get(i).getDeck();
                            str += "\n";
                        }

                        String drawPileStr = "drawPile \n";
                        drawPileStr += drawPile.getDeck();

                        try{
                            saver.write(str);
                            saver.write(drawPileStr);
                            saver.close();
                        }catch(IOException io){
                            System.out.println(io.getMessage());
                        }
                    }

                });

        }

        catch (InterruptedException e) {

            System.out.println("Error: " + e.getMessage());

        }

        catch (InvocationTargetException e) {

            System.out.println("Error: " + e.getMessage());

        }
    }

    public void restoreGame(){
        try {

            EventQueue.invokeAndWait(new Runnable() {

                    @Override

                    public void run() {
                        JFileChooser choose = new JFileChooser();

                        choose.showOpenDialog(null);
                        File game = choose.getSelectedFile();
                        Scanner scan;

                        while(true){
                            try{
                                scan = new Scanner(game);
                                break;
                            }catch(IOException io){
                                System.out.println(io.getMessage());
                            }
                        }

                        int i = 0;
                        String str = "";
                        while(scan.hasNext()){
                            try{
                                str = scan.next();
                            }catch(InputMismatchException input){
                                System.out.println(input.getMessage());
                                continue;
                            }
                            boolean stack = false;
                            boolean drawPile = false;
                            if(str.equals("stack:")){
                                stack = true;
                            }else if(str.equals("drawPile")){
                                drawPile = true;
                            }

                            if(stack && !drawPile){
                                String symbol = "";
                                int value = 0;
                                String suit = "";
                                String faceUp = "";

                                try{
                                    symbol = scan.next();
                                    value = scan.nextInt();
                                    suit = scan.next();
                                    faceUp = scan.next();
                                    continue;
                                }catch(InputMismatchException input){
                                    System.out.println(input.getMessage());
                                }

                                boolean isFaceUp = false;
                                if(faceUp.equals(true)){
                                    isFaceUp = true;
                                }

                                Card card = new Card(symbol, value, suit);
                                card.setFaceUp(isFaceUp);
                                list.get(i).add(card);

                            }else if(drawPile){
                                String symbol = "";
                                int value = 0;
                                String suit = "";
                                String faceUp = "";

                                try{
                                    symbol = scan.next();
                                    value = scan.nextInt();
                                    suit = scan.next();
                                    faceUp = scan.next();
                                }catch(InputMismatchException input){
                                    System.out.println(input.getMessage());
                                }

                                boolean isFaceUp = false;
                                if(faceUp.equals(true)){
                                    isFaceUp = true;
                                }

                                Card card = new Card(symbol, value, suit);
                                card.setFaceUp(isFaceUp);
                                list.get(i).add(card);
                            }
                        }

                    }

                });

        }

        catch (InterruptedException e) {

            System.out.println("Error: " + e.getMessage());

        }

        catch (InvocationTargetException e) {

            System.out.println("Error: " + e.getMessage());

        }

    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard(){
        int k = 1;
        System.out.println("Stacks: ");
        for(int i = 0; i < list.size(); i++){
            System.out.print(k + ": [");
            for(int h = 0; h < list.get(i).getSize(); h++){
                if(h == list.get(i).getSize() - 1){
                    list.get(i).get(h).setFaceUp(true);
                    System.out.print(list.get(i).get(h).toString());
                }else{
                    System.out.print(list.get(i).get(h).toString());
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            k++;
        }

        System.out.println("");
        System.out.println("Draw Pile: ");
        //for(int i = 0; i < drawPile.getSize(); i++){
        //    drawPile.get(i).setFaceUp(false);
        //}
        System.out.println(drawPile.toString());
    }
}