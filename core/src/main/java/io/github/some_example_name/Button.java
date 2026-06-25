package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;

public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private String identifier;

    public Button(int x, int y, int width, int height, String identifier) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.identifier = identifier;
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
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void draw (ShapeRenderer sr){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x,y,width,height);
        sr.end();
    }

    public boolean buttonPress (int mouseX, int mouseY){
        if (mouseX>x && mouseX<(x+width)){
            if (mouseY < (y+height) && mouseY > y){
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean buttonhover (int mouseX, int mouseY){
        if (mouseX>x && mouseX<(x+width)){
            if (mouseY < (y+height) && mouseY > y){
                return true;
            }
        }
        return false;
    }
}
