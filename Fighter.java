import java.awt.*;
class Fighter extends MovingObject {
    boolean lflag;
    boolean rflag;
    boolean uflag;
    boolean dflag;
    boolean sflag;
    int delaytime;


    Fighter(int apWidth, int apHeight) {

        x = apWidth/2;
        y = apHeight*0.9;
        dx = 0;
        dy = 0;
        w = 10;
        h = 10;
        lflag = false;
        rflag = false;
        uflag = false;
        dflag = false;
        sflag = false;
        delaytime = 5;

    }

    void revive(double apWidth, double apHeight, double ddx, double ddy) {

    }

    void move(Graphics buf, int apWidth, int apHeight) {
    buf.setColor(Color.blue);
    buf.fillRect((int)(x) - w, (int)(y) - h, 2 * w, 2 * h);
    
    boolean nomoveflg = true;

    if (lflag && !rflag)
        if (dx > -6) {
            dx = dx - 2;
            nomoveflg = false;
        }
    if (rflag && !lflag)
        if (dx < 6) {
            dx = dx + 2;
            nomoveflg = false;
        }
    if (nomoveflg == true)
        if (sflag) {
            dx = dx * 0.8;
        }
        else {
            dx = dx * 0.9;
        }
    
    if (dx < 0 && x > w)
        x = x + dx;
    if (dx > 0 && x < apWidth - w)
        x = x + dx;
    



    nomoveflg = true;

    if (uflag && !dflag)
        if (dy > -6) {
            dy = dy - 2;
            nomoveflg = false;
        }
    if (dflag && !uflag)
        if (dy < 6) {
            dy = dy + 2;
            nomoveflg = false;
        }
    if (nomoveflg == true)
        if (sflag) {
            dy = dy * 0.8;
        }
        else {
            dy = dy * 0.9;
        }

    if (dy < 0 && y > h)
        y = y + dy;
    if (dy > 0 && y < apHeight - h)
        y = y + dy;


    
    }
    

}