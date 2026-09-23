package io.github.some_example_name;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen {
    private ArrayList <Player> team1 = new ArrayList<Player>();
    private ArrayList <Player> team2 = new ArrayList<Player>();
    private Teams teams = new Teams();
    private ShapeRenderer sr = new ShapeRenderer();
    private Random rand = new Random();
    private boolean init = true;
    public void gameScreen (){
        if (init){
            teams.easyInit(team1);
            teams.easyInit(team2);
            //team2.add(new Player(5,5,5,5,"1",0,600,"p",true,1));
            for (int i = 0; i<15;i++){
                team2.get(i).setY(team2.get(i).getY()+80);
            }
            init=false;
        }

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

        for (int i = 0; i<15; i++){
            int x, y;
            sr.begin(ShapeRenderer.ShapeType.Filled);
            x = team1.get(i).getX();
            y = team1.get(i).getY();
            sr.rect(x,y,20,20);
            x = team2.get(i).getX()+300;
            y = team2.get(i).getY();
            sr.rect(x,y,20,20);
            sr.end();
        }
        team1.get(7).setHasBall(true);
        System.out.println(pass(team1,team2));
    }
    //p1 is the ball carrier and p2 is the defender
    public Boolean tackled (Player p1, Player p2){
        int carryChance = (p1.getStrength()+p1.getSpeed())-p2.getTackling();
        //check if player gets tackled
        if ((p1.getY() +20 <= p2.getY() +20 && p1.getY() +20 >= p2.getY()) || (p1.getY() >= p2.getY() && p1.getY() <= p2.getY() +20)){
            if (p1.getX() <= p2.getX() + 20 && p1.getX() + 20 >= p2.getX() ){
                if (rand.nextInt(0,80) > carryChance) {
                    return true;
                }
            }
        }
        return false;
    }

    public int pass (ArrayList <Player> team, ArrayList <Player> opp){
        // return options = 0, 1, 2; 0 means carry, 1 means pass up the screen, 2 means pass down the screen
        int[] difficulty = new int[15];
        int count = 0;
        int hasBall = 0, distance, lineBreak = 0, bestOption = -1, score=-1;
        for (int i = 0; i<15; i++){
            if (team.get(i).isHasBall()){
                hasBall = i;
                break;
            }
        }
        for (int i = 0; i<opp.size(); i++) {
            //check if ball carrier has anyone in front of them
            if ((team.get(hasBall).getY()+20>opp.get(i).getY()&&team.get(hasBall).getY()<opp.get(i).getY()+20)) {
                count++;
                if (count==opp.size()) {
                    bestOption = hasBall;
                    return 0;
                }
            }
            else{
                count=0;
            }
        }

        //calculate easiest route
        for (int i = 0; i<team.size();i++){
            //calculate how far away from the player they are
            distance = Math.abs(team.get(i).getY()-team.get(hasBall).getY());

            // calculate likelihood of a line break
            for (int j = 0; j<opp.size(); j++) {
                if (team.get(i).getY()+20>opp.get(j).getY()&&team.get(i).getY()<opp.get(j).getY()+20){
                    lineBreak = (team.get(i).getStrength()-opp.get(j).getTackling())+10;
                    break;
                }
            }
            if ((lineBreak-distance)<score){
                score = lineBreak-distance;
                bestOption = i;
            }
            lineBreak=0;
        }
        if (team.get(bestOption).getY()>team.get(hasBall).getY()){
            return 1;
        }
        if (team.get(bestOption).getY()<team.get(hasBall).getY()){
            return 2;
        }
        return 0;
    }
}
