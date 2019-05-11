package Objects;

import java.util.Random;

public enum Color {
    đỏ,
    đen,
    vàng,
    xanh;
    /** Get a random color
     * @return Color
     */
    public static Color generateColor() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }

    public javafx.scene.paint.Color getColor(String colour){
        switch (colour){
            case "đỏ": return javafx.scene.paint.Color.RED;
            case "đen": return javafx.scene.paint.Color.BLACK;
            case "vàng": return javafx.scene.paint.Color.YELLOW;
            case "xanh": return javafx.scene.paint.Color.BLUE;
            default: return javafx.scene.paint.Color.BLACK;
        }
    }
}
