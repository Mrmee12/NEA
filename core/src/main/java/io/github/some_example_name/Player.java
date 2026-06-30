package io.github.some_example_name;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    private int speed, strength, tackling, kicking, x, y, price, id;
    private String name, position;
    private boolean bought;

    public Player(int speed, int strength, int tackling, int kicking, String name, int x, int y, String position, Boolean bought, int id) {
        this.speed = speed;
        this.strength = strength;
        this.tackling = tackling;
        this.kicking = kicking;
        this.name = name;
        this.x = x;
        this.y = y;
        this.position = position;
        this.bought = bought;
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getTackling() {
        return tackling;
    }
    public void setTackling(int tackling) {
        this.tackling = tackling;
    }
    public int getKicking() {
        return kicking;
    }
    public void setKicking(int kicking) {
        this.kicking = kicking;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public boolean isBought() {
        return bought;
    }
    public void setBought(boolean bought) {
        this.bought = bought;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public void draw (ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(x,y,10);
        sr.end();
    }
}
