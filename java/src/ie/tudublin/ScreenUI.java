package ie.tudublin;

import processing.core.*;
import processing.data.*;
import java.util.ArrayList;
import ddf.minim.AudioInput;
import ddf.minim.*;
import ddf.minim.analysis.FFT;

public class ScreenUI extends PApplet {
    Button nextButton;
    Button pauseButton;
    Button prevButton;
    Sphere sphere;
    Radar r;
    IOBox iob;
    Logo logo;
    boolean[] keys = new boolean[1024];
    IOBox iob2;

    AudioInput ai;
    FFT fft;
    Minim minim;
    public static final int FRAME_SIZE = 1024;
    public static final int SAMPLE_RATE = 44100;
    public static final int BITS_PER_SAMPLE = 16;

    public int jukebox = 0;

    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private ArrayList<AudioPlayer> tracklists = new ArrayList<AudioPlayer>();

    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = true;
    }

    public boolean checkKey(int c) {
        return keys[c] || keys[Character.toUpperCase(c)];
    }

    public void settings() {
        fullScreen();
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, FRAME_SIZE, SAMPLE_RATE, BITS_PER_SAMPLE);
        fft = new FFT(FRAME_SIZE, SAMPLE_RATE);
    }

    public void setup() {

        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track1.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track2.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track3.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track4.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track5.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track6.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track7.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track8.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track9.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track10.mp3"));
        tracklists.add(minim.loadFile("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/track11.mp3"));

        tracklists.get(jukebox).play();

        PFont fnt = createFont("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/HADES.otf", 40);
        textFont(fnt);

        nextButton = new Button(this, 50, height * 0.6f, 100, 50, "Next");
        pauseButton = new Button(this, 300, height * 0.6f, 100, 50, "Play/Pause");
        prevButton = new Button(this, 500, height * 0.6f, 100, 50, "Prev");
        sphere = new Sphere(this, 500, height * 0.6f, 500, 500);
        r = new Radar(this, width / 2, height / 2, 350);
        iob = new IOBox(this, (width / 2) - 250, height * 0.8f, 500, 200);
        logo = new Logo(this, 0, 0, width, height / 5, "Space Drift");
        iob2 = new IOBox(this, width - 540, height * 0.8f, 500, 200);

        for (int i = 0; i <= 5; i++)
            sprites.add(new Planet(this));

        for (int i = 0; i <= 100; i++)
            sprites.add(new Star(this));

        loadData();
        printWeapons();
    }

    public void mousePressed() {
        float x = 50;
        float y = height * 0.6f;
        float w = 100;
        float h = 50;
        if (mouseX > x && mouseX < x + w
                && mouseY > y && mouseY < y + h) {
            jukebox++;

            if (jukebox > 11) {
                jukebox = 1;
            }
            jukeboxPlay();
        }

        x = 300;
        if (mouseX > x && mouseX < x + w
                && mouseY > y && mouseY < y + h) {
            if (tracklists.get(jukebox).isPlaying() == true)
                tracklists.get(jukebox).pause();
            else
                tracklists.get(jukebox).play();

        }

        x = 500;
        if (mouseX > x && mouseX < x + w
                && mouseY > y && mouseY < y + h && jukebox > 0) {

            jukebox--;
            jukeboxPlay();
        }

        
    }

    public void loadData() {
        Table table = loadTable("/Users/bobby/Downloads/OOP-2021-2022-HelloProcessing/java/data/weapons.csv", "header");

        for (TableRow row : table.rows()) {
            Weapon w = new Weapon(row);
            weapons.add(w);
        }
    }

    public void printWeapons() {
        for (Weapon w : weapons) {
            System.out.println(w);
        }
    }

    public void drawWeapons() {
        int i = 0;
        textSize(20);
        textAlign(LEFT, CENTER);
        for (Weapon w : weapons) {
            text("Weapon Name:    " + w.getWeaponName(), (width / 2) - 100, height * 0.81f);
            text("Mag Size:    " + w.getMagSize(), (width / 2) - 100, height * 0.84f);
            text("Damage:    " + w.getDamage(), (width / 2) - 100, height * 0.87f);
            text("Description:    " + w.getDesc(), (width / 2) - 100, height * 0.9f);

            i++;
            if (i == 1)
                break;
        }
    }

    public void jukeboxPlay() {

        if (tracklists.get(jukebox + 1).isPlaying() == true) {
            tracklists.get(jukebox + 1).pause();
        } else {
            tracklists.get(jukebox - 1).pause();
        }

        tracklists.get(jukebox).play();

    }

    public float timeDelta;
    private float last;

    public void draw() {
        background(0);

        float now = millis();
        timeDelta = (now - last) / 1000.0f;
        last = now;

        fill(0);

        for (int i = sprites.size() - 1; i >= 0; i--) {
            Sprite s = sprites.get(i);
            s.render();
            s.update();
        }

        nextButton.render();
        prevButton.render();
        pauseButton.render();
        sphere.render();


        r.render();
        r.update();

        iob.render();
        iob2.render();

        drawWeapons();

        logo.render();

        ellipse(mouseX, mouseY, 40, 40);
        ellipse(mouseX, mouseY, 1, 1);
        noCursor();

        stroke(33, 157, 150);
        float audioHeight = height * 0.9f;
        float gap = 20;
        float audioWidth = ai.bufferSize() / 2;
        float middle = audioHeight;

        rect(gap, height * 0.8f, audioWidth, 200);
        textSize(30);
        text("Radio", audioWidth / 2, height * 0.78f);

        for (int i = 0; i < audioWidth; i++) {
            stroke(map(i, 0, ai.bufferSize(), 0, 255), 255, 255);
            line(i + gap, middle, i + gap, middle + ai.left.get(i) * middle / 6);
        }
    }
}
