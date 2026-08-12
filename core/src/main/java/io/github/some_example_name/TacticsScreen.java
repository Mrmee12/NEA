package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.ArrayList;

public class TacticsScreen {
    ShapeRenderer sr = new ShapeRenderer();
    private int x=930, y=510, count = 0, x2 = 930;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch = new SpriteBatch();
    //making main.team eaisier for me to work with
    private ArrayList<Player> team = Main.team;
    private Button[] buttons = new Button[20];

    public void tacticsScreen() {
        //make UI for this screen
        sr.begin(ShapeRenderer.ShapeType.Filled);

        //vertical separation
        sr.rect(295,0,10,600);
        sr.rect(895,0,10,600);
        sr.end();

        // reset the ID of all players to link to the team and make them all have a button
        for (int i=0; i<team.size(); i++){
            team.get(i).setId(i);
            buttons[i] = new Button(x-5,y-15,125,50, Integer.toString(i));
            x+=150;
            count++;
            if(count==2){
                y-=55;
                x=930;
                count=0;
            }
        }
        count=0;

        //check if hovering over player
        for (int i=0;i<team.size();i++){
            if (buttons[i].buttonhover(Gdx.input.getX(), 600-Gdx.input.getY())){
                sr.setColor(Color.OLIVE);
                buttons[i].draw(sr);
                sr.setColor(Color.WHITE);
            }
        }

       // print out players
        y=510;
        for (int i=0; i<team.size(); i++){
            y+=30;
            batch.begin();
            font.draw(batch,""+team.get(i).getName(),x,y);
            y-=15;
            font.draw(batch,"Spd  Str  Tck  Kck",x,y);
            y-=15;
            font.draw(batch,"  "+team.get(i).getSpeed()+"      "+team.get(i).getStrength()+"     "+team.get(i).getTackling()+"      "+team.get(i).getKicking(),x,y);
            batch.end();
            x+=150;
            count++;
            if (count==2){
                x=930;
                y-=55;
                count=0;
            }
        }
        count=0;
        x=930;
        y=510;

        //Header
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.FOREST);
        sr.rect(0, 550, 1200, 50);
        sr.setColor(Color.WHITE);
        sr.end();
    }
}
