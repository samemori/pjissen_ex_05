import java.awt.*;

abstract class MovingObject extends Frame {

    double x;
    double y;
    double dx;
    double dy;

    int w;
    int h;
    int hp;

    MovingObject () {}

    MovingObject (int width, int height) {
        x = (int) (Math.random()*width);
        y = (int) (Math.random()*height);
        dx = (int) (Math.random()*6-3);
        dy = (int) (Math.random()*6-3);
        w = 2;
        h = 2;
        hp = 10;
    }

    abstract void move(Graphics buf, int apWidth, int apHeight);

    abstract void revive(double w, double h, double dx, double dy);

    boolean collisionCheck(MovingObject obj) {

        if (Math.abs(this.x-obj.x) <= (this.w+obj.w) && Math.abs(this.y-obj.y) <= (this.h+obj.h)) {
            this.hp--;
            obj.hp--;
            return true;
        }   else
            return false;
    }
}
