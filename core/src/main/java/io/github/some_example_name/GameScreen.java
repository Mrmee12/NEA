package io.github.some_example_name;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen {
    private ShapeRenderer sr = new ShapeRenderer();
    public void gameScreen (){
        // create the pitch for the players
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.OLIVE);
        sr.rect(0,0,1200,600);
        //side lines
        sr.setColor(Color.WHITE);
        //right side
        sr.rect(50,20,1100,5);
        //Left side
        sr.rect(50,575,1100,5);
        //back left
        sr.rect(50,20,5,555);
        //back right
        sr.rect(1145,20,5,555);
        //tri line R
        sr.rect(1045,20,5,555);
        //tri line L
        sr.rect(150,20,5,555);
        //halfway
        sr.rect(592,20,5,555);
        //lineout lines
        sr.rect(200,40,30,5);
        sr.rect(400,40,30,5);
        sr.rect(753,40,30,5);
        sr.rect(965,40,30,5);

        sr.rect(200,90,30,5);
        sr.rect(400,90,30,5);
        sr.rect(753,90,30,5);
        sr.rect(965,90,30,5);

        sr.rect(212, 40,5,50);
        sr.rect(412, 40,5,50);
        sr.rect(765, 40,5,50);
        sr.rect(977, 40,5,50);


        sr.rect(200,555,30,5);
        sr.rect(400,555,30,5);
        sr.rect(753,555,30,5);
        sr.rect(965,555,30,5);

        sr.rect(200,505,30,5);
        sr.rect(400,505,30,5);
        sr.rect(753,505,30,5);
        sr.rect(965,505,30,5);

        sr.rect(212, 505,5,50);
        sr.rect(412, 505,5,50);
        sr.rect(765, 505,5,50);
        sr.rect(977, 505,5,50);
        sr.end();
    }
}
