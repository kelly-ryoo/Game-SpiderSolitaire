import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;
/*
 * Kelly Ryoo. P5. 12/6/2018. Activity 6 took me about an hour.
 * Most of my time was spent trying to remember and review try and catch, the
 * exceptions, and etc. But once I remembered and got the hang of it, I was able
 * to catch the major errors. I tried inputing many differnt errors and 
 * tried and caught all of them. I have tried all the errors that I could think
 * of, and I think I have the majority of them.
 */
public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 7;

    /** Number of complete decks used in the game.  
     *  The number of cards in a deck depends on the
     *  type of Card used.  For example, a 1-suit deck
     *  of standard playing cards consists of only 13 cards
     *  whereas a 4-suit deck consists of 52 cards.
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS, true, false, false, false);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");

            String command = input.next();

            if (command.equals("move")) {

                String symbol = "";
                int sourceStack = 0;
                int destinationStack = 0;
                try{
                    symbol = input.next();
                    if(input.hasNextInt()){
                        sourceStack = input.nextInt();
                    }else{
                        System.out.println("Out of bounds. Invalid input.");
                        continue;
                    }

                    if(input.hasNextInt()){
                        destinationStack = input.nextInt();
                    }else{
                        System.out.println("Out of bounds. Invalid input.");
                        continue;
                    }

                    if(sourceStack > NUM_STACKS || destinationStack > NUM_STACKS 
                    || sourceStack < 0 || destinationStack < 0){
                        System.out.println("Out of bounds. Invalid input.");
                        continue;
                    }

                }catch(InputMismatchException  input){
                    System.out.println(input.getMessage());
                    board.printBoard();
                    continue;
                }catch(IndexOutOfBoundsException out){
                    System.out.println(out.getMessage());
                    board.printBoard();
                    continue;
                }

                board.makeMove(symbol, sourceStack - 1, destinationStack - 1);

            }
            else if (command.equals("draw")) {
                board.drawCards();
            }
            else if (command.equals("clear")) {

                int sourceStack = 0;
                try{
                    sourceStack = input.nextInt();

                    if(sourceStack > NUM_STACKS ||  sourceStack < 0){
                        System.out.println("Out of bounds. Invalid input.");
                        continue;
                    }

                }catch(InputMismatchException  input){
                    System.out.println(input.getMessage());
                    continue;
                }catch(IndexOutOfBoundsException out){
                    System.out.println(out.getMessage());
                    continue;
                }

                board.clear(sourceStack - 1);
            }
            else if (command.equals("restart")) {
                board = new Board(NUM_STACKS, NUM_DECKS, true, false, false, false);
            }
            else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }else if(command.equals("save")){
                board.saveGame();
                continue;
            }else if(command.equals("load")){
                board.restoreGame();
                board.printBoard();
                continue;
            }else{
                System.out.println("Invalid command.");
            }

            board.printBoard();

            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}
