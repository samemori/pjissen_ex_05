import java.awt.*;
class EnemyBullet extends MovingObject {

    /** コンストラクタ **/
    EnemyBullet() {
        w = h = 3;
        
        dx = 0; dy = 5;
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
        this.dx = dx;
        this.dy = dy;
        hp = 1;
    }
}
