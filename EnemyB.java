import java.awt.*;
class EnemyB extends MovingObject {
    int delaytime;
    Image img = getToolkit().getImage("img/EnemyB.png");

    EnemyB(int apWidth, int apHeight) {
        super(apWidth, apHeight);
        w = 28;
        h = 29;
        hp = 0;
        delaytime = (int)(Math.random()*300);
        
    }

    void move(Graphics buf, int apWidth, int apHeight) {
        buf.setColor(Color.pink);
        if (hp>0) {
            buf.drawImage(img, (int)(x) - w, (int)(y) - h, this);
            x = x + dx;
            y = y + dy;
            
            if (x < w) {
                dx = 5;
            }
            else if (x > apWidth + w) {
                dx = -5;
            }
            else {
                if (Math.random() < 0.01) {
                    dx = (int)(Math.random()*30) - 15;
                } else {
                    dx = dx * 0.9;
                }
            }
            dy = dy * 0.9;


            if (y>apHeight+h)
                hp = 0;
        }
    }

    void revive(double apWidth, double apHeight, double dx, double dy) {
        x = (int)(Math.random()*(apWidth-2*w)+w);
        y = -h;
        this.dy = (int)(Math.random()*10) + 10;
        if (x<apWidth/2)
            this.dx = (int)(Math.random()*30) - 15;
        else
            this.dx = -(int)(Math.random()*30);
        hp = 4;
        delaytime = (int)(Math.random()*300);
    }

}
