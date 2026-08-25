package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.awt.*;
import java.util.ArrayList;

public class TacticsScreen {
    ShapeRenderer sr = new ShapeRenderer();
    private int x=930, y=510, count = 0, x2 = 930, playerHeld = -1, temp;
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch = new SpriteBatch();
    private boolean intitalSetup=true;
    //making main.team eaisier for me to work with
    private ArrayList<Player> team = Main.team;
    private Button[] buttons = new Button[20];
    private Slot[] slots = new Slot[15];
    private Button nextScreen = new Button(1075,557,120,40,"continue 2");
    private Button crashBalls = new Button(150,495,70,20, "Crash Balls");

    public void tacticsScreen() {
        //make UI for this screen
        sr.begin(ShapeRenderer.ShapeType.Filled);

        //vertical separation
        sr.rect(295,0,10,600);
        sr.rect(895,0,10,600);
        sr.end();

        // reset the ID of all players to link to the team and make them all have a button
        if(intitalSetup) {
            for (int i = 0; i < team.size(); i++) {
                team.get(i).setId(i);
                buttons[i] = new Button(x, y-15, 125, 50, Integer.toString(i));
                x += 150;
                count++;
                if (count == 2) {
                    y -= 55;
                    x = 930;
                    count = 0;
                }

            }

            // generate slots for players to be dragged into
            x=410;
            y=490;
            for (int i = 1; i<16; i++){
                if (i<4){
                    slots[i-1] = new Slot(x,y,125,50,-1,true);
                    x+=130;
                }
                else if (i>3 && i<8){
                    if (i==4){
                        x=345;
                        y-=55;
                    }
                    slots[i-1]  = new Slot(x,y,125,50,-1,true);
                    x+=130;
                }
                else if (i==8){
                    x=537;
                    y-=55;
                    slots [i-1] = new Slot(x,y,125, 50,-1,true);
                }
                else if (i>8 && i<15) {
                    if (i == 9) {
                        x = 310;
                        y -= 55;
                    }
                    slots[i-1] = new Slot(x, y, 125, 50, -1, true);
                    y -= 55;
                    x += 90;
                }
                else{
                    x=372;
                    y+=55;
                    slots[i-1] = new Slot(x,y,125,50,-1,true);
                }
            }

            intitalSetup=false;
        }
        count=0;

        //check if button is being held
        if (playerHeld == -1) {
            for (int i = 0; i<team.size();i++) {
                if (buttons[i].buttonhold(Gdx.input.getX(), 600 - Gdx.input.getY())) {
                    playerHeld = Integer.valueOf(buttons[i].getIdentifier());
                    break;
                }
            }
        }
        //match to mouse
        if(playerHeld!=-1) {
            if (buttons[playerHeld].buttonhold(Gdx.input.getX(), 600 - Gdx.input.getY())) {
                buttons[playerHeld].setX(Gdx.input.getX()-50);
                buttons[playerHeld].setY(600 - Gdx.input.getY()-20);
            } else {
                playerHeld = -1;
            }
        }

        // print out the slots
        for (int i =0; i<15; i++){
            if (slots[i].isEmpty()){
                sr.setColor(Color.RED);
                slots[i].draw(sr);
            }
            else{
                sr.setColor(Color.TEAL);
                slots[i].draw(sr);
            }
        }
        sr.setColor(Color.WHITE);

        //check if hovering over player
        for (int i=0;i<team.size();i++) {
            if (buttons[i].buttonhover(Gdx.input.getX(), 600 - Gdx.input.getY())) {
                sr.setColor(Color.OLIVE);
                buttons[i].draw(sr);
                sr.setColor(Color.WHITE);
            }
        }

        // see if needed to click into slot
        if (playerHeld==-1){
            for (int i=0; i<15; i++) {
                for (int j = 0; j < team.size(); j++) {
                    if (slots[i].isEmpty()) {
                        if (slots[i].detect(buttons[j].getX(), buttons[j].getY())) {
                            slots[i].setEmpty(false);
                            slots[i].setId(Integer.valueOf(buttons[j].getIdentifier()));
                            buttons[j].setX(slots[i].getX());
                            buttons[j].setY(slots[i].getY());
                            if (!Main.starting.contains(team.get(j))) {
                                Main.starting.add(team.get(j));
                            }
                        }
                    }
                    // detect if removed
                    temp = slots[i].getId();
                    if (temp != -1){
                        if (slots[i].getX() != buttons[temp].getX()){
                            if (slots[i].getY() != buttons[temp].getY()){
                                slots[i].setEmpty(true);
                                slots[i].setId(-1);
                                Main.starting.remove(team.get(temp));
                            }
                        }
                    }
                }
            }
        }

        //let the user decide how many crash balls they want
        if (crashBalls.buttonhover(Gdx.input.getX(), 600-Gdx.input.getY())){
            sr.setColor(Color.OLIVE);
            crashBalls.draw(sr);
            sr.setColor(Color.WHITE);
        }
        if (crashBalls.buttonPress(Gdx.input.getX(), 600 - Gdx.input.getY())) {
            Main.crashBalls++;
            if (Main.crashBalls>10){
                Main.crashBalls=0;
            }
        }
        batch.begin();
        font.draw(batch, "How many Crash Balls before sending the", 10,540);
        font.draw(batch,  "ball out to the backs:", 10,510);
        font.draw(batch,"" + Main.crashBalls, 155,510);
        batch.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(150,495,70,20);
        sr.end();



        // print out players
        for (int i=0; i<team.size(); i++){
            printPlayer(team.get(i), buttons[i].getX(), buttons[i].getY());
            count++;
        }

        //Header
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.FOREST);
        sr.rect(0, 550, 1200, 50);
        sr.setColor(Color.WHITE);
        sr.end();

        // next screen advance
        sr.setColor(Color.PURPLE);
        nextScreen.draw(sr);
        sr.setColor(Color.WHITE);
        batch.begin();
        font.draw(batch, "Advance -->", 1100,583);
        batch.end();
        if (nextScreen.buttonPress(Gdx.input.getX(),600-Gdx.input.getY())){
            Main.screenCount++;
        }
    }

    // player print algorithm
    public void printPlayer (Player player,int x, int y){
        y += 45;
        batch.begin();
        font.draw(batch, "" + player.getName(), x, y);
        y -= 15;
        font.draw(batch, "Spd  Str  Tck  Kck", x, y);
        y -= 15;
        font.draw(batch, "  " + player.getSpeed() + "      " + player.getStrength() + "     " + player.getTackling() + "      " + player.getKicking(), x, y);
        batch.end();
    }
}
