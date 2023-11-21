import java.awt.*;
class Effect extends MovingObject {
    Image img = getToolkit().getImage("img/Effect.png");

    Effect() {
        hp = 0;
        
    }

    void move(Graphics buf, int apWidth, int apHeight) {
        if (hp>0) {
            buf.drawImage(img, (int)(x - 62.5), (int)(y - 62.5), (int)(x + 61.5), (int)(y + 61.5), (hp - 1) * 125, 0, (hp - 1) * 125 + 124, 124, this);
            hp = hp - 1;
        }
    }

    void revive(double x, double y, double dx, double dy) {
        this.x = x + (int)(Math.random()*40 - 20);
        this.y = y + (int)(Math.random()*40 - 20);
        hp = 5;
    }
}
