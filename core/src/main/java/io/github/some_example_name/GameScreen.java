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
    private int inRuckCount = 0;
    //testing
    Player p1 = new Player(5,5,5,5,"1",500,300,"1",true,1);
    Player p2 = new Player(5,5,5,5,"1",530,320,"1",true,1);
    public void gameScreen (){
        if (init){
            teams.easyInit(team1);
            teams.easyInit(team2);
            //team2.add(new Player(5,5,5,5,"1",0,600,"p",true,1));
            for (int i = 0; i<15;i++){
                team2.get(i).setX(team2.get(i).getX()+50);
                team2.get(i).setY(team2.get(i).getY()+50);
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
        team1.get(7).setHasBall(true);
        for (int i = 0; i<15; i++){
            if (team1.get(i).isHasBall()) {
                sr.setColor(Color.GREEN);
            }
            team1.get(i).draw(sr);
            sr.setColor(Color.WHITE);
            team2.get(i).draw(sr);
        }
        //pass(team1,team2);
        System.out.println(pass(team1, team2));

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
        int hasBall = 0, distance, lineBreak = 0, bestOption = 0, score=-1;
        for (int i = 0; i<15; i++){
            if (team.get(i).isHasBall()){
                hasBall = i;
                break;
            }
        }
        // get ball carriers score so if their score is the best they will run it in
        for (int i = 0; i < opp.size(); i++) {
            if ((team.get(hasBall).getY() +20 <= opp.get(i).getY() +20 && team.get(hasBall).getY() +20 >= opp.get(i).getY()) || (team.get(hasBall).getY() >= opp.get(i).getY() && team.get(hasBall).getY() <= opp.get(i).getY() +20)){
                lineBreak = ((team.get(hasBall).getStrength() + team.get(hasBall).getSpeed())  - opp.get(i).getTackling()) + 10;
                break;
            }
        }
        score = lineBreak;
        bestOption = hasBall;

        //
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
            distance = Math.abs(team.get(hasBall).getY()-team.get(i).getY());

            // calculate likelihood of a line break
            for (int j = 0; j<opp.size(); j++) {
                if ((team.get(i).getY()+20>opp.get(j).getY()&&team.get(i).getY()<opp.get(j).getY()+20)) {
                    lineBreak = ((team.get(i).getStrength()+team.get(i).getSpeed())-opp.get(j).getTackling())+10;
                    break;
                }
            }
            //System.out.println(lineBreak);
            if ((lineBreak+distance)>score){
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
        if (bestOption==hasBall) {
            return 0;
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
            }

            for (int i = 1; i < down.size(); i++) {
                if (down.get(i).getX()-15 > down.get(i - 1).getX()){
                }
                else {
                    down.get(i).setX(down.get(i).getX()+down.get(i).getSpeed()/2);
                }
                if (down.get(i).getX()-20 > down.get(i - 1).getX()){
                    down.get(i).setX(down.get(i).getX()-down.get(i).getSpeed()/2);
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
