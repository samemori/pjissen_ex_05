import java.awt.*;
class EnemyA extends MovingObject {
    int delaytime;

    EnemyA(int apWidth, int apHeight) {
        super(apWidth, apHeight);
        w = 10;
        h = 10;
        hp = 0;
        delaytime = (int)(Math.random()*500);
        
    }

    void move(Graphics buf, int apWidth, int apHeight) {
        buf.setColor(Color.yellow);
        if (hp>0) {
            buf.drawOval((int)(x) - w, (int)(y) - h, 2 * w, 2 * h);
            x = x + dx;
            y = y + dy;
            if (y>apHeight+h)
                hp = 0;
        }
    }

    void revive(double apWidth, double apHeight, double dx, double dy) {
        x = (int)(Math.random()*(apWidth-2*w)+w);
        y = -h;
        this.dy = 1;
        if (x<apWidth/2)
            this.dx = (int)(Math.random()*2);
        else
            this.dx = -(int)(Math.random()*2);
        hp = 1;
    }
}