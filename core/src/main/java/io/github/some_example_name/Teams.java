package io.github.some_example_name;

import java.util.ArrayList;

public class Teams {
    // need to make a class to store all of the preset teams so they can be imported each time
    // Easy team
    public void easyInit (ArrayList <Player> easy) {
        easy.add(new Player(4, 5, 6, 2, "p1", 620, 530, "front", true, 0));
        easy.add(new Player(3, 7, 6, 2, "p2", 620, 490, "front", true, 1));
        easy.add(new Player(3, 6, 7, 1, "p3", 620, 450, "front", true, 2));
        easy.add(new Player(5, 6, 7, 4, "s1", 620, 420, "Second", true, 3));
        easy.add(new Player(6, 7, 6, 5, "s2", 620, 390, "Second", true, 4));
        easy.add(new Player(6, 6, 7, 4, "s3", 620, 360, "Second", true, 5));
        easy.add(new Player(5, 8, 6, 5, "s4", 620, 330, "Second", true, 6));
        easy.add(new Player(6, 7, 7, 4, "8", 620, 300, "8", true, 7));
        easy.add(new Player(7, 5, 5, 7, "b1", 620, 270, "back", true, 8));
        easy.add(new Player(7, 7, 7, 6, "b2", 620, 240, "back", true, 9));
        easy.add(new Player(6, 6, 6, 7, "b3", 620, 210, "back", true, 10));
        easy.add(new Player(8, 7, 5, 7, "b4", 620, 180, "back", true, 11));
        easy.add(new Player(7, 5, 6, 5, "b5", 620, 150, "back", true, 12));
        easy.add(new Player(6, 5, 5, 4, "b6", 620, 110, "back", true, 13));
        easy.add(new Player(6, 5, 7, 5, "b7", 620, 70, "back", true, 14));
    }
}
