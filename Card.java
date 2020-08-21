/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;
    
    private String suit;
    
    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value, String suit) {
        this.symbol = symbol;
        this.value = value;
        this.suit = suit;
        isFaceUp = false;
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Getter method to acess this <code>Card</code>'s value.
     * 
     * @return this <code>Card</code>'s value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter method to acess whether this <code>Card</code> is face up or not.
     * 
     * @return this <code>Card</code>'s boolean.
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Getter method to acess this <code>Card</code>'s state.
     * 
     * @return a boolean <code>Card</code>'s state.
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }
    
    /**
     * Getter method to access this <code>Card</code>'s suit.
     * 
     * @return a string of the <code>Card</code>'s suit.
     */
    public String getSuit(){
        return suit;
    }
    
    /**
     * Compare method that finds the difference of value between the <code>Card</code> 
     * and <code>Card</code>.
     * 
     * @return thedifference in value.
     */
    @Override
    public int compareTo(Card other){
        return (this.getValue() - other.getValue());
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @return whether or not this Card is equal to other.
     */
    public boolean equals(Card other) {
        if((other.getValue()) == (this.getValue())){
            return true;
        }

        return false;
    }
    

    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of the card.
     */
    @Override
    public String toString() {
        if(!isFaceUp){
            return "X";
        }else{
            String card = symbol;
            //card += " " + value; 
            return card;
        }
    }
}
