package Objects;

import java.util.Random;

public enum Value {
    át,
    hai,
    ba,
    bốn,
    năm,
    sáu,
    bảy,
    tám,
    chín,
    mười;
    public static Value generateValue(){
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
