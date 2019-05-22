package sample;

import Objects.Color;
import Objects.Suit;
import Objects.Value;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Card extends Parent {
    private static final int CARD_WIDTH = 100;
    private static final int CARD_HEIGHT = 140;

    public Suit suit;
    public Color color;
    public Value value;

    public Suit getSuit() {
        return suit;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    public Card() {
        this.color = Color.generateColor();
        this.value = Value.generateValue();
        this.suit = Suit.generateSuit();

        Rectangle bg = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        bg.setArcWidth(20);
        bg.setArcHeight(20);
        bg.setFill(javafx.scene.paint.Color.GRAY);

        Text value1 = new Text(value.toString());
        value1.setFont(Font.font(20));
        value1.setX(CARD_WIDTH - value1.getLayoutBounds().getWidth() - 10);
        value1.setY(value1.getLayoutBounds().getHeight());
        value1.setFill(color.getColor(color.toString()));

        Text suit1 = new Text(suit.toString());
        suit1.setFont(Font.font(20));
        suit1.setX(10);
        suit1.setY(CARD_HEIGHT - 10);
        suit1.setFill(color.getColor(color.toString()));

        /*
        ImageView view = new ImageView(suit.image);
        view.setRotate(180);
        view.setX(CARD_WIDTH - 32);
        view.setY(CARD_HEIGHT - 32);
        */
        getChildren().addAll(bg, value1, suit1); // khong load anh
        //getChildren().addAll(bg, new ImageView(suit.image), view, value1, suit1);
    }
}
