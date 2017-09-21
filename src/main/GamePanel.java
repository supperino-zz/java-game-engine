package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import entity.Player;
import level.Level;
import level.RandomLevel;

public class GamePanel extends Canvas implements Runnable {

    private Thread thread;
    // gameconfig
    private static final int WIDTH = 160, HEIGHT = WIDTH / 12 * 9, SCALE = 3;
    private boolean running = true;

    // imagestuff
    private Graphics g;
    private BufferedImage image;
    private BufferStrategy buffer;
    public int[] pixels;
    private Screen screen;

    // gamestuff
    private Level level;
    KeyHandler key;
    private Player player;

    public GamePanel() {
        // TODO Auto-generated constructor stub
        thread = new Thread(this);
        setSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

    }

    public void initialize() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);// 0xRRGGBB =
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        screen = new Screen(WIDTH, HEIGHT, this.pixels);

        key = new KeyHandler(this);
        level = new RandomLevel(64, 64);
        player = new Player(key);

        this.createBufferStrategy(3);
        g = null;
    }

    public void render() {
        try {
            buffer = getBufferStrategy();
            if (!buffer.contentsLost()) {
                g = buffer.getDrawGraphics();
                screen.clear();

                int xScroll = player.x - screen.width / 2;
                int yScroll = player.y - screen.height / 2;

                level.render(xScroll, yScroll, screen);
                player.render(screen);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                buffer.show();
            }
        } finally {
            g.dispose();
        }
    }

    public void update() {
        player.update();
    }

    public void run() {
        initialize();
        requestFocus();
        int frames = 0, updates = 0;
        long lastMesured = System.currentTimeMillis(), startMesure = System.currentTimeMillis();
        double delta = 0;
        final double ms = 1000 / 60d;

        while (running) {
            long now = System.currentTimeMillis();
            delta += (now - lastMesured) / ms;
            lastMesured = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - startMesure > 1000) {
                startMesure += 1000;
                // System.out.println("frames: " + frames + " updates: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void stop() {
        running = false;
    }

    private void start() {
        thread.start();

    }

    public static void main(String[] args) {
        GamePanel gp = new GamePanel();
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gp);
        frame.pack();
        gp.start();
    }

}
