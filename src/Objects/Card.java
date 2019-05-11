package Objects;

public class Card {
    Color color;
    Value value;
    Suit suit;
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Suit getSuit(){return suit;}
    public Card() {
        this.color = Color.generateColor();
        this.value = Value.generateValue();
        this.suit = Suit.generateSuit();
    }

    public boolean compare(String value,String suit,String color){
        /*
        System.out.println(this.number+ " "+ this.color +" " +this.suit);
        System.out.println(number+ " "+color +" " +suit);*/
        if (this.getValue().toString().compareTo(value)==0 &&
                this.getColor().toString().compareTo(color)== 0 &&
                this.getSuit().toString().compareTo(suit)==0
        )
                return true;
        return false;
    }
}
