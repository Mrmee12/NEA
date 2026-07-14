package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    ShapeRenderer sr;
    PlayerSelectionScreen playerSelectionScreen;
    TacticsScreen tacticsScreen;
    BitmapFont font;
    SpriteBatch batch;
    public static int screenCount = 0;

    @Override
    public void create() {
        sr = new ShapeRenderer();
        playerSelectionScreen = new PlayerSelectionScreen();
        playerSelectionScreen.players_create();
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
                tacticsScreen.Screen2();
        }
    }

    @Override
    public void dispose() {
    }
}
