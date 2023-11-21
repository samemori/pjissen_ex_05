import java.awt.*;

public class ShootingGame extends Frame implements Runnable {

    // ■ フィールド変数
    Thread th;
    GameMaster gm;

    // ■ main メソッド (スタート地点)
    public static void main(String[] args) {
        new ShootingGame();
    }
    

    // ■ コンストラクタ
    ShootingGame() {
        super("Shooting Game (Sample)");
        int cW=640, cH=480;
        this.setSize(cW+30, cH+40);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        gm = new GameMaster(cW,cH);
        this.add(gm);
        this.setVisible(true);

        th = new Thread(this);
        th.start();
        requestFocusInWindow();
    }

    // ■ メソッド (Runnable インターフェース用)
    public void run() {
        try {
            while (true) {
                Thread.sleep(20);
                gm.repaint();
            }
        }
        catch (Exception e) {System.out.println("Exception: " + e);}
    }
}
