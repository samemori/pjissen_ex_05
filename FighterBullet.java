import java.awt.*;
class FighterBullet extends MovingObject {

    /** コンストラクタ **/
    FighterBullet() {
        w = h = 3;

        double a = Math.random() * 0.20 - 0.10 + (Math.PI * 3 / 2);
        dx = 8 * Math.cos(a); dy = 8 * Math.sin(a);
        hp = 0;
    }

    /** メソッド **/
    void move(Graphics buf, int apWidth, int apHeight) {
        if (hp>0) {
            buf.setColor(Color.white);
            buf.fillOval((int)(x)-w, (int)(y)-h, 2*w, 2*h);
            if (y > 0 && y < apHeight && x > 0 && x < apWidth) {
                x = x + dx;
                y = y + dy;
            }
            else {
                hp = 0;
            }
        }
    }

    void revive(double x, double y, double dx, double dy) {
        this.x = x;
        this.y = y;
        double a = Math.random() * 0.20 - 0.10 + (Math.PI * 3 / 2);
        this.dx = 8 * Math.cos(a) + dx * 0.2;
        this.dy = 8 * Math.sin(a) + dy * 0.2;
        hp = 1;
    }
}