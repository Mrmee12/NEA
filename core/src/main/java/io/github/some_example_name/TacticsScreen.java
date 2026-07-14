package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class TacticsScreen {
    ShapeRenderer sr = new ShapeRenderer();
    private int x=915, y=490, count = 0;
    private BitmapFont font  = new BitmapFont();
    private Batch batch = new SpriteBatch();
    private Player[] team = Main.team.toArray(new Player[Main.team.size()]);

    public void tacticsScreen() {
        //make UI for this screen
        sr.begin(ShapeRenderer.ShapeType.Filled);

        //vertical separation
        sr.rect(295,0,10,600);
        sr.rect(895,0,10,600);

       // print out players
        for (int i=0; i<team.length; i++){
            team[i].setId(i);
            Button button = new Button(x,y,50,50, Integer.toString(i));
            batch.begin();
            font.draw(batch,""+team[i].getName(),x,y);
            batch.end();
            System.out.println(team[i].getName());
            x+=75;
            count++;
            if (count==3){
                x=915;
                y-=75;
                count=0;
            }
        }

        //Header
        sr.setColor(Color.FOREST);
        sr.rect(0, 550, 1200, 50);
        sr.setColor(Color.WHITE);
        sr.end();
    }
}
