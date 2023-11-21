import java.awt.*;
class EnemyA extends MovingObject {
    int delaytime;
    Image img = getToolkit().getImage("img/EnemyA.png");

    EnemyA(int apWidth, int apHeight) {
        super(apWidth, apHeight);
        w = 12;
        h = 14;
        hp = 0;
        delaytime = (int)(Math.random()*500);
        
    }

    void move(Graphics buf, int apWidth, int apHeight) {
        buf.setColor(Color.yellow);
        if (hp>0) {
            buf.drawImage(img, (int)(x) - w, (int)(y) - h, this);
            x = x + dx;
            y = y + dy;
            if (y>apHeight+h)
                hp = 0;
        }
    }

    void revive(double apWidth, double apHeight, double dx, double dy) {
        x = (int)(Math.random()*(apWidth-2*w)+w);
        y = -h;
        this.dy = 3;
        if (x<apWidth/2)
            this.dx = (int)(Math.random()*2);
        else
            this.dx = -(int)(Math.random()*2);
        hp = 1;
    }
}
