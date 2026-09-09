package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Random rand = new Random();
    ShapeRenderer sr;
    PlayerSelectionScreen playerSelectionScreen;
    TacticsScreen tacticsScreen;
    GameScreen gameScreen;
    BitmapFont font;
    SpriteBatch batch;
    public static int screenCount = 0;
    public static ArrayList<Player> team = new ArrayList<>();
    public static ArrayList<Player> starting = new ArrayList<>();
    public static int crashBalls = 0, whereToThrow = 0;
    public Player[] easy = new Player[15];

    @Override
    public void create() {
        // create the easy team
        for (int i = 0; i<15; i++){
            //speed, stregth, tackling, kicking
            easy[i] = new Player(rand.nextInt(4, 7), rand.nextInt(2, 7), rand.nextInt(5, 8), rand.nextInt(1, 5), "i", 0, 0, "easy",false,i);
        }
        sr = new ShapeRenderer();
        playerSelectionScreen = new PlayerSelectionScreen();
        playerSelectionScreen.players_create();
        gameScreen = new GameScreen();
        tacticsScreen = new TacticsScreen();
        font = new BitmapFont();
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        switch (screenCount){
            case 0:
                playerSelectionScreen.printDisplay();
                break;
            case 1:
                tacticsScreen.tacticsScreen();
                break;
            case 2:
                gameScreen.gameScreen();
                break;
        }
    }

    @Override
    public void dispose() {
    }
}
