import java.awt.*;
class Background extends MovingObject {

    int yfront;
    int yyfront;
    Image img;

    Background(int apWidth, int apHeight) {
        this.dy = 3;
        img = getToolkit().getImage("img/Background_03.png");
    }

    Background(int apWidth, int apHeight, int dy, String fName) {
        this.dy = dy;
        img = getToolkit().getImage(fName);
    }

    void revive(double apWidth, double apHeight, double ddx, double ddy) {

    }

    void move(Graphics buf, int apWidth, int apHeight) {

        buf.drawImage(img, 0, (int)(y), this);
        buf.drawImage(img, 0, (int)(y) - apHeight, this);
        y = (y + dy) % apHeight;

    }
    
}
