package io.github.some_example_name;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Slot {
    private int x;
    private int y;
    private int width;
    private int height;
    private int id;
    private boolean empty;
    //
    public Slot(int x, int y, int width, int height, int id, boolean empty) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.empty = empty;
    }
    //
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
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isEmpty() {
        return empty;
    }
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    //
    public void draw (ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x,y,width,height);
        sr.end();
    }
    //
    public boolean detect (int objX,int objY){
        if (objY < y+height && objY > y){
            if (objX < x+width && objX > x){
                return true;
            }
        }
        return false;
    }
}
