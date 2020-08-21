public class CardTester
{
    public static void main(String[] args) {
        /*
        Card card = new Card("Ace", 5, "heart");
        Card card2 = new Card("10", 4, "diamond");
        System.out.println(card.getSymbol());
        System.out.println(card.getValue());
        card.setFaceUp(true);
        System.out.println(card.isFaceUp());
        System.out.println(card.compareTo(card2));
        System.out.println(card.equals(card2));
        System.out.println(card.toString());
        */
        
        Board board = new Board(2, 2, true, false, false, false);
        board.printBoard();
        System.out.println(board.isEmpty());
        
    }
}
