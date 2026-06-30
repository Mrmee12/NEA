package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;

public class PlayerSelectionScreen {
    Button sortingButton = new Button(5,557,120,40,"Sort");
    private ShapeRenderer sr = new ShapeRenderer();
    private int x=30,y=525, price = 0;
    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();
    private Random rand = new Random();
    //create list of possible player names
    private Player[] players = new Player[36];
    //player buttons to select players from the list
    private Button[] playerButtons = new Button[36];
    String[] f_names = {"Liam","Noah","Oliver","Theodore","James","Henry","Mateo","William","Benjamin","Levi","Nathan","Sebastian","Jack","Daniel","Samuel","Michael","Ethan","John","Leo","David", "Bob","Josh","Rufus"};
    String[] l_names = {"Garcia","Cornish-barlow","Becker","Martin","Shepard","Burton","Todd","Novak","Herman","O'Brien","Braun","Dougherty","McMillan","Cherry","Hodges","House","Stanton","Howard","Jones","Smith","Field","Dylan","Mack","Woods","Goode"};
    private int sortCount = 0;
    String[] sortTitles = {"Speed","Strength","Tackling","Kicking"};
    Player[] team = new Player[20];
    private int boughtPlayers = 0, money = 400;

    //subroutine to create players
    public void players_create () {
        //create props
        for (int i = 0; i < 9; i++) {
            int first = rand.nextInt(0, f_names.length);
            int last = rand.nextInt(0, l_names.length);
            String name = f_names[first] + " " + l_names[last];
            //                    speed,strength,tackling,kicking,name,x,y,position
            players[i] = new Player(rand.nextInt(2, 6), rand.nextInt(5, 11), rand.nextInt(5, 11), rand.nextInt(1, 5), name, 0, y, "Front Row",false,i);
            players[i].setPrice(players[i].getSpeed()+players[i].getStrength()+players[i].getTackling()+players[i].getKicking());
            playerButtons[i]= new Button(0,players[i].getY(),1200,25,String.valueOf(i));
            y-=25;
        }
        //create 2nd rows
        for (int i = 9; i < 13; i++) {
            int first = rand.nextInt(0, f_names.length);
            int last = rand.nextInt(0, l_names.length);
            String name = f_names[first] + " " + l_names[last];
            players[i] = new Player(rand.nextInt(4, 8), rand.nextInt(5, 11), rand.nextInt(5, 11), rand.nextInt(1, 6), name, 0, y, "Second Row",false,i);
            players[i].setPrice(players[i].getSpeed()+players[i].getStrength()+players[i].getTackling()+players[i].getKicking());
            playerButtons[i]= new Button(0,players[i].getY(),1200,25,String.valueOf(i));
            y-=25;
        }
        //create back rows
        for (int i = 13; i < 19; i++) {
            int first = rand.nextInt(0, f_names.length);
            int last = rand.nextInt(0, l_names.length);
            String name = f_names[first] + " " + l_names[last];
            players[i] = new Player(rand.nextInt(5, 8), rand.nextInt(5, 9), rand.nextInt(5, 11), rand.nextInt(1, 6), name, 0, y, "Flanker",false,i);
            players[i].setPrice(players[i].getSpeed()+players[i].getStrength()+players[i].getTackling()+players[i].getKicking());
            playerButtons[i]= new Button(0,players[i].getY(),1200,25,String.valueOf(i));
            y-=25;
        }
        //create 8
        for (int i = 19; i < 22; i++) {
            int first = rand.nextInt(0, f_names.length);
            int last = rand.nextInt(0, l_names.length);
            String name = f_names[first] + " " + l_names[last];
            players[i] = new Player(rand.nextInt(6, 8), rand.nextInt(5, 9), rand.nextInt(5, 11), rand.nextInt(1, 6), name, 0, y, "Eight",false,i);
            players[i].setPrice(players[i].getSpeed()+players[i].getStrength()+players[i].getTackling()+players[i].getKicking());
            playerButtons[i]= new Button(0,players[i].getY(),1200,25,String.valueOf(i));
            y-=25;
        }
        //create backs
        for (int i = 22; i < 36; i++) {
            int first = rand.nextInt(0, f_names.length);
            int last = rand.nextInt(0, l_names.length);
            String name = f_names[first] + " " + l_names[last];
            players[i] = new Player(rand.nextInt(5, 11), rand.nextInt(2, 7), rand.nextInt(3, 8), rand.nextInt(5, 11), name, 0, y, "Back",false,i);
            players[i].setPrice(players[i].getSpeed()+players[i].getStrength()+players[i].getTackling()+players[i].getKicking());
            playerButtons[i]= new Button(0,players[i].getY(),1200,25,String.valueOf(i));
            y-=25;
        }
        y=525;
    }

    public void printDisplay() {
        int speed, strength, tackling, kicking, py = y;
        String name, position;
        //check if hovering over player
        for (int i=0;i<playerButtons.length;i++){
            if (600-Gdx.input.getY()<530) {
                if (playerButtons[i].buttonhover(Gdx.input.getX(), 600 - Gdx.input.getY())) {
                    sr.setColor(Color.OLIVE);
                    playerButtons[i].draw(sr);
                    sr.setColor(Color.WHITE);
                    sr.end();
                }
            }

            //checks if player has been clicked on and therefore bought and add them to the team
            if (600-Gdx.input.getY()<530) {
                if (boughtPlayers!=20) {
                    if (playerButtons[i].buttonPress(Gdx.input.getX(), 600 - Gdx.input.getY())) {
                        if (!players[i].isBought()) {
                            players[i].setBought(true);
                            team[boughtPlayers] = players[i];
                            boughtPlayers++;
                            money -= players[i].getPrice();
                        }
                    }
                }
            }
        }
        // prints the players stats in the right positions
        for (int i = 0; i < players.length; i++) {
            speed = players[i].getSpeed();
            strength = players[i].getStrength();
            tackling = players[i].getTackling();
            kicking = players[i].getKicking();
            name = players[i].getName();
            position = players[i].getPosition();
            price = players[i].getPrice();
            batch.begin();
            font.draw(batch, name, x, py);
            x += 240;
            font.draw(batch, "" + speed, x, py);
            x += 150;
            font.draw(batch, "" + strength, x, py);
            x += 140;
            font.draw(batch, "" + tackling, x, py);
            x += 150;
            font.draw(batch, "" + kicking, x, py);
            x += 160;
            font.draw(batch, "" + price, x, py);
            x += 180;
            font.draw(batch, position, x, py);
            batch.end();
            x = 30;
            py -= 25;
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.rect(0, py, 1200, 3);
            sr.end();
            py -= 5;
            playerButtons[i].setY(py+9);
            //check if player is bought
            if (players[i].isBought()) {
                sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(Color.RED);
                sr.rect(0, playerButtons[i].getY() + 12, 1200, 3);
                sr.setColor(Color.WHITE);
                sr.end();
            }
        }
        // prints the header for the table of player stats
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.CORAL);
        sr.rect(0, 530, 1200, 30);
        sr.setColor(Color.FOREST);
        sr.rect(0, 550, 1200, 50);
        sr.setColor(Color.WHITE);
        sr.end();
        batch.begin();
        font.draw(batch, "Name", 30, 547);
        font.draw(batch, "Speed", 240, 547);
        font.draw(batch, "Strength", 390, 547);
        font.draw(batch, "Tackling", 550, 547);
        font.draw(batch, "Kicking", 690, 547);
        font.draw(batch, "Price", 850,547);
        font.draw(batch, "Position", 1040, 547);
        batch.end();
        // Drawing table lines
        sr.begin(ShapeRenderer.ShapeType.Filled);
        x = 200;
        for (int i = 0; i < 6; i++) {
            sr.rect(x, 0, 3, 550);
            x += 150;
        }
        x = 30;
        sr.rect(0, 530, 1200, 3);
        sr.rect(0, 550, 1200, 3);
        sr.end();
        // scrolling
        if (y > 1040) {
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                y += 3;
            }
        }
        if (y == 525) {
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                y -= 3;
            }
        }
        // draw bank
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.ROYAL);
        sr.rect(150,557,120,40);
        sr.setColor(Color.WHITE);
        sr.end();
        batch.begin();
        font.setColor(Color.GOLD);
        font.draw(batch, "Money: "+money, 170,584);
        font.setColor(Color.WHITE);
        batch.end();

        // draw number of owned player
        batch.begin();
        font.draw(batch, "Number of owned players: "+boughtPlayers,500,584);
        batch.end();

        //print the filter by button
        sortingButton.draw(sr);
        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, ""+sortTitles[sortCount],42,584);
        font.setColor(Color.WHITE);
        batch.end();

        //change colour if hovering
        if (sortingButton.buttonPress(Gdx.input.getX(), 600-Gdx.input.getY())){
            sr.setColor(Color.GRAY);
            sortingButton.draw(sr);
            sortCount++;

                // check to see if sorting counter needs to be looped back round
                if(sortCount>3){
                    sortCount=0;
                }

            //sort players by their stats
            players = sortPlayers(players, sortCount);

            //have to swap buttons aswell
            for (int i = 0;i< players.length;i++){
                int compare = Integer.parseInt(playerButtons[i].getIdentifier());
                if(players[i].getId() != compare){
                    Button temp;
                    for(int j = 0; j< playerButtons.length;j++){
                        if(players[i].getId()==Integer.parseInt(playerButtons[j].getIdentifier())){
                            temp = playerButtons[i];
                            playerButtons[i]=playerButtons[j];
                            playerButtons[j]=temp;
                        }
                    }
                }
            }
        }
        // check to see if sorting counter needs to be looped back round
        if(sortCount>3){
            sortCount=0;
        }

        sr.setColor(Color.WHITE);

    }
    // To allow the user to sort the players that are being displayed by their stats
    public Player[] sortPlayers (Player[] array, int count){
        Player temp;
        Button temporary;
        int comp1=0, comp2=0;
        // bubble sort algorithm
        for (int i=0; i < array.length;i++){
            for (int j=0;j < array.length;j++){
                switch (count){
                    case 0:
                        comp1 = array[i].getSpeed();
                        comp2 = array[j].getSpeed();
                        break;
                    case 1:
                        comp1 = array[i].getStrength();
                        comp2 = array[j].getStrength();
                        break;
                    case 2:
                        comp1 = array[i].getTackling();
                        comp2 = array[j].getTackling();
                        break;
                    case 3:
                        comp1 = array[i].getKicking();
                        comp2 = array[j].getKicking();
                        break;
                }
                if (comp1 > comp2){
                    temp=array[i];
                    array[i] = array[j];
                    array[j] = temp;

                }
            }
        }
        return array;
    }
}
