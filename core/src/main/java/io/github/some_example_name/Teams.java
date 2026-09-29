package io.github.some_example_name;

import java.util.ArrayList;

public class Teams {
    // need to make a class to store all of the preset teams so they can be imported each time
    // Easy team
    public void easyInit (ArrayList <Player> easy) {
        easy.add(new Player(4, 5, 6, 2, "p1", 500, 30, "front", true, 0));
        easy.add(new Player(3, 7, 6, 2, "p2", 500, 60, "front", true, 1));
        easy.add(new Player(3, 6, 7, 1, "p3", 500, 90, "front", true, 2));
        easy.add(new Player(5, 6, 7, 4, "s1", 500, 120, "Second", true, 3));
        easy.add(new Player(6, 7, 6, 5, "s2", 500, 150, "Second", true, 4));
        easy.add(new Player(6, 6, 7, 4, "s3", 500, 180, "Second", true, 5));
        easy.add(new Player(5, 8, 6, 5, "s4", 500, 210, "Second", true, 6));
        easy.add(new Player(6, 7, 7, 4, "8", 500, 240, "8", true, 7));
        easy.add(new Player(7, 5, 5, 7, "b1", 500, 270, "back", true, 8));
        easy.add(new Player(7, 7, 7, 6, "b2", 500, 300, "back", true, 9));
        easy.add(new Player(6, 6, 6, 7, "b3", 500, 330, "back", true, 10));
        easy.add(new Player(8, 7, 5, 7, "b4", 500, 360, "back", true, 11));
        easy.add(new Player(7, 5, 6, 5, "b5", 500, 390, "back", true, 12));
        easy.add(new Player(6, 5, 5, 4, "b6", 500, 420, "back", true, 13));
        easy.add(new Player(6, 5, 7, 5, "b7", 500, 450, "back", true, 14));
    }
}
