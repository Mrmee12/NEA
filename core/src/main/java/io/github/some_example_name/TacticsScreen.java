package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.ArrayList;

public class TacticsScreen {
    ShapeRenderer sr = new ShapeRenderer();
    private int x=950, y=300, count = 0;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch = new SpriteBatch();
    //making main.team eaisier for me to work with
    private ArrayList<Player> team = Main.team;

    public void tacticsScreen() {
        //make UI for this screen
        sr.begin(ShapeRenderer.ShapeType.Filled);

        //vertical separation
        sr.rect(295,0,10,600);
        sr.rect(895,0,10,600);
        sr.end();

       // print out players
        for (int i=0; i<team.size(); i++){
            team.get(i).setId(i);
            Button button = new Button(x,y,50,50, Integer.toString(i));
            batch.begin();
            font.draw(batch,""+team.get(i).getName(),x,y);
            batch.end();
            x+=150;
            count++;
            if (count==2){
                x=950;
                y-=75;
                count=0;
            }
        }
        count=0;
        x=950;
        y=300;

        //Header
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.FOREST);
        sr.rect(0, 550, 1200, 50);
        sr.setColor(Color.WHITE);
        sr.end();
    }
}
