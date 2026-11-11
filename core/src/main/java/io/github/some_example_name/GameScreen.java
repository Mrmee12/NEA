package io.github.some_example_name;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import jdk.jfr.internal.test.WhiteBox;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Random;
import java.util.WeakHashMap;

public class GameScreen {
    private ArrayList <Player> userTeam = new ArrayList<Player>();
    private ArrayList <Player> opp = new ArrayList<Player>();
    private Teams teams = new Teams();
    private ShapeRenderer sr = new ShapeRenderer();
    private Random rand = new Random();
    private boolean init = true, kickoff = true, go = true;
    private int inRuckCount = 0, x = 500, y = 520, temp = 0, kickPlayer, whereKick, bX = 620, bY = 300, count = 0, teamwithball = -1, passDir, playerWithBall;
    //testing
    Player p1 = new Player(5,5,5,5,"1",500,300,"1",true,1);
    Player p2 = new Player(5,5,5,5,"1",530,320,"1",true,1);
    public void gameScreen (){
        //fill teams with players
        if (init){
            //userTeam = Main.starting;
            teams.easyInit(userTeam);
            teams.easyInit(opp);
            /*
            for (int i = 0; i<15;i++){
                team2.get(i).setX(team2.get(i).getX()+50);
                team2.get(i).setY(team2.get(i).getY());
            }*/
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
        /*for(int i = 0; i<15; i++){
            team1.get(i).draw(sr);
            team2.get(i).draw(sr);
        }
        team1.get(7).setHasBall(true);
        attackFormation(team1, 0);
        defensiveFormation(team1,team2, 1);*/

        // simulation of the game
        //setup kickoff
        if (kickoff){
            count = 0;
            x = 500;
            y = 520;
            for (int i = 0; i<3;i++){
                userTeam.get(i).setX(x);
                userTeam.get(i).setY(y);
                y-=240;
            }
            x-=200;
            y=520;
            for (int i=3; i<7; i++){
                userTeam.get(i).setX(x);
                userTeam.get(i).setY(y);
                y-=155;
            }
            x-=130;
            y=280;
            userTeam.get(7).setX(x);
            userTeam.get(7).setY(y);
            y=450;
            x=400;
            for (int i=8; i<11; i++){
                userTeam.get(i).setX(x);
                userTeam.get(i).setY(y);
                y-=155;
            }
            x=225;
            y=470;
            for (int i=11; i<15; i++){
                userTeam.get(i).setX(x);
                userTeam.get(i).setY(y);
                y-=130;
            }

            // find the best kicker in opp
            for (int i = 0; i<opp.size(); i++){
                if (opp.get(i).getKicking() > temp){
                    temp = opp.get(i).getKicking();
                    kickPlayer = i;
                }
            }
            opp.get(kickPlayer).setHasBall(true);
            opp.get(7).setY(opp.get(kickPlayer).getY());
            opp.get(7).setX(opp.get(kickPlayer).getX());
            opp.get(kickPlayer).setY(300);
            opp.get(kickPlayer).setX(620);

            //start game
            whereKick = rand.nextInt(1,4);
            switch (whereKick){
                case 1:
                    x = userTeam.get(0).getX();
                    y = userTeam.get(0).getY();
                    whereKick=0;
                    break;
                case 2:
                    x = userTeam.get(3).getX();
                    y = userTeam.get(3).getY();
                    whereKick=3;
                    break;
                case 3:
                    x = userTeam.get(5).getX();
                    y = userTeam.get(5).getY();
                    whereKick = 5;
                    break;
            }
            kickoff=false;
        }
        //wait 2 secs before game start
        if (go) {
            if (count > 120) {
                opp.get(kickPlayer).setHasBall(false);
                userTeam.get(whereKick).setHasBall(true);
                go=false;
            }
        }

        if(count>120){
            //step 1 = determine which team has the ball
            for (int i = 0; i<userTeam.size(); i++){
                if (userTeam.get(i).isHasBall()){
                    teamwithball = 0;
                    break;
                }
                if (opp.get(i).isHasBall()){
                    teamwithball = 1;
                    break;
                }
            }

            //make teams do right move
            if (teamwithball == 0){
                // find who has ball
                for (int i = 0; i<15; i++){
                    if (userTeam.get(i).isHasBall()){
                        playerWithBall = i;
                        break;
                    }
                }

                attackFormation(userTeam,0);
                defensiveFormation(userTeam, opp,1);

                //check pass
                passDir = pass(userTeam, opp);

                if (userTeam.get(playerWithBall).getY() > 525 || userTeam.get(playerWithBall).getY() < 70 || passDir==0){
                    userTeam.get(playerWithBall).setY(userTeam.get(playerWithBall).getY()+userTeam.get(playerWithBall).getSpeed()/2);
                }
                if (passDir==1){
                    userTeam.get(playerWithBall).setHasBall(false);

                }
            }
        }


        // draw players
        for (int i = 0; i<userTeam.size(); i++){
            sr.setColor(Color.WHITE);
            userTeam.get(i).draw(sr);

            sr.setColor(Color.CYAN);
            opp.get(i).draw(sr);

            if (opp.get(i).isHasBall()){
                sr.setColor(Color.RED);
                opp.get(i).draw(sr);
                sr.setColor(Color.WHITE);
            }
            if (userTeam.get(i).isHasBall()){
                sr.setColor(Color.RED);
                userTeam.get(i).draw(sr);
                sr.setColor(Color.WHITE);
            }
        }
        count++;
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
        int hasBall = 0, distance = 0, lineBreak = 0, bestOption = 0, score=-1, temp = 0;
        for (int i = 0; i<15; i++){
            if (team.get(i).isHasBall()){
                hasBall = i;
                break;
            }
        }

        //get ball carriers score so if it is lowest they can run
        for (int i = 0; i<team.size(); i++){
            if ((team.get(hasBall).getY()+20>opp.get(i).getY()&&team.get(hasBall).getY()<opp.get(i).getY()+20)) {
                lineBreak = team.get(hasBall).getStrength()-opp.get(i).getTackling();
                break;
            }
        }
        score = lineBreak+10;
        bestOption = hasBall;

        //find out best option
        for (int i=0; i<team.size(); i++){
            //find distance
            if (i==hasBall) {
                distance = 10;
            }
            else if (team.get(i).getY()>team.get(hasBall).getY()){
                distance = Math.abs((team.get(hasBall).getY()+20) - (team.get(i).getY()))/2;
            }
            else if (team.get(i).getY()<team.get(hasBall).getY()){
                distance = Math.abs((team.get(hasBall).getY()) - (team.get(i).getY()+20))/2;
            }

            //find the chance of a line break
            for (int j=0; j<team.size(); j++){
                if ((team.get(i).getY()+20>opp.get(j).getY()&&team.get(i).getY()<opp.get(j).getY()+20)) {
                    lineBreak = team.get(i).getStrength()-opp.get(j).getTackling();
                    break;
                }
            }

            //combine the scores
            temp = lineBreak+distance;

            // see if they are better
            if (temp<score){
                bestOption = i;
                score = temp;
            }
        }
        if (team.get(bestOption).getY() > team.get(hasBall).getY()){
            return 1;
        }
        if (team.get(bestOption).getY() < team.get(hasBall).getY()){
            return 2;
        }

        return 0;
    }

    public boolean ruck (Player att, Player tackled){
        // use in ruck count to check if there are 2 players in the ruck already if so then ignore the out of this.
        if ((att.getY() <= tackled.getY() +40 && att.getY() +20 >= tackled.getY()-20)){
            if (att.getX() <= tackled.getX() + 40 && att.getX() + 20 >= p2.getX()-20 ){
                return true;
            }
        }
        return false;
    }

    public ArrayList <Player> attackFormation (ArrayList <Player> attack, int side) {
        int hasBall = -1;
        int withballID = -1;
        ArrayList <Player> up = new ArrayList<>();
        ArrayList <Player> down = new ArrayList<>();
        Player temp;
        // find player with the ball
        for (int i = 0; i < 15; i++) {
            if (attack.get(i).isHasBall()) {
                hasBall = i;
                withballID = attack.get(i).getId();
                break;
            }
        }
        // increment each player back to be in the correct position
        for (int i = 0; i < 15; i++) {
            //first find if player is on left or right of the ball carrier
            if (attack.get(i).getY() > attack.get(hasBall).getY()) {
                up.add(attack.get(i));
            }
            else {
                down.add(attack.get(i));
            }
        }
        //sort the lists via how close they are to the ball carrier
        for (int i=0; i<up.size(); i++){
            for (int j=0; j<up.size();j++){
                if (Math.abs(attack.get(hasBall).getY()-up.get(i).getY())<Math.abs(attack.get(hasBall).getY()-up.get(j).getY())){
                    temp = up.get(i);
                    up.set(i,up.get(j));
                    up.set(j,temp);
                }
            }
        }
        for (int i=0; i<down.size(); i++){
            for (int j=0; j<down.size();j++){
                if (Math.abs(attack.get(hasBall).getY()-down.get(i).getY())<Math.abs(attack.get(hasBall).getY()-down.get(j).getY())){
                    temp = down.get(i);
                    down.set(i,down.get(j));
                    down.set(j,temp);

                }
            }
        }
        //remove the ball carrier from the lists
        for (int i = 0; i<up.size();i++){
            if(up.get(i).getId()==withballID){
                up.remove(i);
            }
        }
        for (int i = 0; i<down.size();i++){
            if(down.get(i).getId()==withballID){
                down.remove(i);
            }
        }

        up.add(0,attack.get(hasBall));
        down.add(0,attack.get(hasBall));

        // index players back by their speed
        if (side == 0) {
            for (int i = 1; i < up.size(); i++) {
                if (up.get(i).getX()+15 < up.get(i - 1).getX()){
                }
                else if (up.get(i).getX()+20 < up.get(i - 1).getX()){
                    up.get(i).setX(up.get(i).getX()+up.get(i).getSpeed()/2);
                }
                else {
                    up.get(i).setX(up.get(i).getX()-up.get(i).getSpeed()/2);
                }
                if (up.get(i).getX()+20 < up.get(i-1).getX()-15){
                    up.get(i).setX(up.get(i).getX()+up.get(i).getSpeed()/2);
                }

                if (up.get(i).getY()-10 < up.get(i-1).getY()+20){
                    up.get(i).setY(up.get(i).getY()+up.get(i).getSpeed()/2);
                }
            }

            for (int i = 1; i < down.size(); i++) {
                if (down.get(i).getX()+15 < down.get(i - 1).getX()){
                }
                else if (down.get(i).getX()+20 < down.get(i - 1).getX()){
                    down.get(i).setX(up.get(i).getX()+down.get(i).getSpeed()/2);
                }
                else {
                    down.get(i).setX(down.get(i).getX()-down.get(i).getSpeed()/2);
                }
                if (down.get(i).getX()+20 < down.get(i-1).getX()-15){
                    down.get(i).setX(down.get(i).getX()+down.get(i).getSpeed()/2);
                }

                if (down.get(i).getY()+20 > down.get(i-1).getY()-10){
                    down.get(i).setY(down.get(i).getY()-down.get(i).getSpeed()/2);
                }
            }
        }

        if (side == 1) {
            for (int i = 1; i < up.size(); i++) {
                if (up.get(i).getX()-15 > up.get(i - 1).getX()){
                }
                else if (up.get(i).getX()-20 > up.get(i - 1).getX()){
                    up.get(i).setX(up.get(i).getX()-up.get(i).getSpeed()/2);
                }
                else {
                    up.get(i).setX(up.get(i).getX()+up.get(i).getSpeed()/2);
                }
                if (up.get(i).getX()-20 > up.get(i - 1).getX()+15){
                    up.get(i).setX(up.get(i).getX()-up.get(i).getSpeed()/2);
                }

                if (up.get(i).getY()-10 > up.get(i-1).getY()+20){
                    up.get(i).setY(up.get(i).getY()-up.get(i).getSpeed()/2);
                }
            }

            for (int i = 1; i < down.size(); i++) {
                if (down.get(i).getX()-15 > down.get(i - 1).getX()){
                }
                else {
                    down.get(i).setX(down.get(i).getX()+down.get(i).getSpeed()/2);
                }
                if (down.get(i).getX()-20 > down.get(i - 1).getX()+15){
                    down.get(i).setX(down.get(i).getX()-down.get(i).getSpeed()/2);
                }

                if (down.get(i).getY()+20 < down.get(i-1).getY()-10){
                    down.get(i).setY(down.get(i).getY()+down.get(i).getSpeed()/2);
                }
            }
        }
        up.remove(0);
        down.remove(0);

        //update all values
        for (int i = 0; i<attack.size(); i++){
            for (int j=0; j<up.size(); j++){
                if (attack.get(i).getId() == up.get(j).getId()){
                    attack.set(i, up.get(j));
                }
            }
            for (int j=0; j<down.size(); j++){
                if (attack.get(i).getId() == down.get(j).getId()){
                    attack.set(i, down.get(j));
                }
            }
        }

        return attack;
    }

    public ArrayList<Player> defensiveFormation(ArrayList <Player> attack,ArrayList <Player> defense, int side) {
        int forwardX = -1, backwardsX = 100000;

        // find most far forward attacker
        for (int i = 0; i<attack.size(); i++){
            if (attack.get(i).getX() > forwardX){
                forwardX = attack.get(i).getX();
            }
            //find furthest back attacker
            if (attack.get(i).getX()<backwardsX){
                backwardsX = attack.get(i).getX();
            }
        }

        //move them to where they need to be
        for (int i = 0; i<defense.size(); i++){
            if (side==0){
                if (defense.get(i).getX() > backwardsX - 30){
                    defense.get(i).setX(defense.get(i).getX() - defense.get(i).getSpeed()/2);
                }
                if (defense.get(i).getX() < backwardsX - 70){
                    defense.get(i).setX(defense.get(i).getX() + defense.get(i).getSpeed()/2);
                }
            }
            if (side==1){
                if (defense.get(i).getX() < forwardX + 30){
                    defense.get(i).setX(defense.get(i).getX() + defense.get(i).getSpeed()/2);
                }
                if (defense.get(i).getX() > forwardX + 70){
                    defense.get(i).setX(defense.get(i).getX() - defense.get(i).getSpeed()/2);
                }
            }
        }

        return defense;
    }
}
