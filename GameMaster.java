import java.awt.*;
import java.awt.event.*;

public class GameMaster extends Canvas implements KeyListener {

    Image buf;
    Graphics buf_gc;
    Dimension d;
    private int imgW, imgH;

    private int enmyAnum = 20;
    private int enmyBnum = 10;
    private int ftrBltNum = 30;
    private int enmyBltNum = 40;
    private int effectNum = 30;
    private int mode = -1;
    private int i, j, k;

    Background bga, bgb, bgfront;
    Fighter ftr;
    FighterBullet ftrBlt[] = new FighterBullet[ftrBltNum];
    EnemyBullet enmyBlt[] = new EnemyBullet[enmyBltNum];
    EnemyA enmyA[] = new EnemyA[enmyAnum];
    EnemyB enmyB[] = new EnemyB[enmyBnum];
    Effect effect[] = new Effect[effectNum];

    GameMaster(int imgW, int imgH) {
        this.imgW = imgW;
        this.imgH = imgH;
        setSize(imgW, imgH);

        addKeyListener(this);

        bga = new Background(imgW, imgH, 8, "img/Background_01.png");
        bgb = new Background(imgW, imgH, 11, "img/Background_02.png");
        bgfront = new Background(imgW, imgH, 18, "img/Background_03.png");
        ftr = new Fighter(imgW, imgH);
        for (i = 0; i < ftrBltNum; i++)
            ftrBlt[i] = new FighterBullet();
        for (i = 0; i < enmyAnum; i++)
            enmyA[i] = new EnemyA(imgW, imgH);
        for (i = 0; i < enmyBnum; i++)
            enmyB[i] = new EnemyB(imgW, imgH);
        for (i = 0; i < enmyBltNum; i++)
            enmyBlt[i] = new EnemyBullet();
        for (i = 0; i < effectNum; i++)
            effect[i] = new Effect();
    }

    public void addNotify(){
        super.addNotify();
        buf = createImage(imgW, imgH);
        buf_gc = buf.getGraphics();
    }

    public void paint(Graphics g) {
        buf_gc.setColor(Color.black);
        buf_gc.fillRect(0, 0, imgW, imgH);
        switch (mode) {
        case -2:
            buf_gc.setColor(Color.white);
            buf_gc.drawString("     == Game over ==     ", imgW/2-80, imgH/2-20);
            buf_gc.drawString("     Hit Space Key     ", imgW/2-80, imgH/2+20);
            break;
        case -1:
            buf_gc.setColor(Color.white);
            buf_gc.drawString(" == Shooting Game Title == ", imgW/2-80, imgH/2-20);
            buf_gc.drawString("Hit SPACE bar to start game", imgW/2-80, imgH/2+20);
            break;
        default:

            makeEnmy: if (Math.random() < 0.1) {
                if (Math.random() < 0.7) {
                    for (i=0; i < enmyAnum; i++) {
                        if (enmyA[i].hp <= 0) {
                            enmyA[i].revive(imgW, imgH, 0, 0);
                            break makeEnmy;
                        }
                    }
                } else {
                    for (i=0; i < enmyBnum; i++) {
                        if (enmyB[i].hp <= 0) {
                            enmyB[i].revive(imgW, imgH, 0, 0);
                            break makeEnmy;
                        }
                    }
                }
            }
            
            for (i = 0; i < enmyBnum; i++) {
                if (enmyB[i].hp >= 1 && enmyB[i].delaytime <= 0) {
                    for (j = 0; j < enmyBltNum; j++) {
                        if (enmyBlt[j].hp == 0) {
                            enmyBlt[j].revive(enmyB[i].x, enmyB[i].y, 0, 6);
                            enmyB[i].delaytime = 40;
                            break;
                        }
                    }
                }   else if (enmyB[i].hp != 0 && enmyB[i].delaytime != 0)
                    enmyB[i].delaytime--;
            }


            if (ftr.sflag == true && ftr.delaytime == 0) {
                for (i = 0; i < ftrBltNum; i++) {
                    if (ftrBlt[i].hp == 0) {
                        ftrBlt[i].revive(ftr.x, ftr.y, ftr.dx, ftr.dy);
                        ftr.delaytime = 2;
                        break;
                    }
                }
            }   else if (ftr.delaytime > 0)
                ftr.delaytime--;



            for (i = 0; i < enmyAnum; i++)
                if (enmyA[i].hp > 0) {
                    ftr.collisionCheck(enmyA[i]);
                    for (j = 0; j < ftrBltNum; j++)
                        if (ftrBlt[j].hp > 0)
                            if (ftrBlt[j].collisionCheck(enmyA[i]) == true) {
                                for (k = 0; k < effectNum; k++) {
                                    effect[k].revive(enmyA[i].x, enmyA[i].y, 0, 0);
                                }
                            }
                }
            for (i = 0; i < enmyBnum; i++)
                if (enmyB[i].hp > 0) {
                    ftr.collisionCheck(enmyB[i]);
                    for (j = 0; j < ftrBltNum; j++)
                        if (ftrBlt[j].hp > 0)
                            if (ftrBlt[j].collisionCheck(enmyB[i]) == true) {
                                for (k = 0; k < effectNum; k++) {
                                    effect[k].revive(enmyB[i].x, enmyB[i].y, 0, 0);
                                }
                            }
                }
            for (i = 0; i < enmyBltNum; i++)
                if (enmyBlt[i].hp > 0) {
                    ftr.collisionCheck(enmyBlt[i]);
                }
            
            if (ftr.hp < 1)
                mode = -2;

            bga.move(buf_gc, imgW, imgH);
            bgb.move(buf_gc, imgW, imgH);
            for (i = 0; i < enmyAnum; i++)
                enmyA[i].move(buf_gc, imgW, imgH);
            for (i = 0; i < enmyBnum; i++)
                enmyB[i].move(buf_gc, imgW, imgH);
            for (i = 0; i < enmyBltNum; i++)
                enmyBlt[i].move(buf_gc, imgW, imgH);
            for (i = 0; i < ftrBltNum; i++)
                ftrBlt[i].move(buf_gc, imgW, imgH);
            for (i = 0; i < effectNum; i++)
                effect[i].move(buf_gc, imgW, imgH);
            ftr.move(buf_gc, imgW, imgH);
            bgfront.move(buf_gc, imgW, imgH);

            for (i = 0; i < enmyAnum; i++) {
                System.out.print(enmyA[i].hp + " ");
            }
            for (i = 0; i < enmyBnum; i++) {
                System.out.print(enmyB[i].hp + " ");
            }
            System.out.println("");
        }
        g.drawImage(buf, 0, 0, this);
    }

    // ■ メソッド (Canvas)
    public void update(Graphics gc) {
        paint(gc);
    }

    // ■ メソッド (KeyListener)
    public void keyTyped(KeyEvent ke) {

    }

    public void keyPressed(KeyEvent ke) {
        int cd = ke.getKeyCode();
        switch (cd) {
        case KeyEvent.VK_LEFT:
            ftr.lflag = true;
            break;
        case KeyEvent.VK_RIGHT:
            ftr.rflag = true;
            break;
        case KeyEvent.VK_UP:
            ftr.uflag = true;
            break;
        case KeyEvent.VK_DOWN:
            ftr.dflag = true;
            break;
        case KeyEvent.VK_SPACE:
            ftr.sflag = true;
            if (this.mode != 1){
                this.mode++;
            }
            ftr.hp = 10;
            break;
        }
    }
    
    // ■ メソッド (KeyListener)
    public void keyReleased(KeyEvent ke) {
        int cd = ke.getKeyCode();
        switch (cd) {
        case KeyEvent.VK_LEFT:
            ftr.lflag = false;
            break;
        case KeyEvent.VK_RIGHT:
            ftr.rflag = false;
            break;
        case KeyEvent.VK_UP:
            ftr.uflag = false;
            break;
        case KeyEvent.VK_DOWN:
            ftr.dflag = false;
            break;
        case KeyEvent.VK_SPACE:
            ftr.sflag = false;
            break;
        }
    }
}
