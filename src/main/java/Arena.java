import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(5, 5);
        this.walls = createWalls();
    }

    public int getX() {
        return hero.getX();
    }

    public int getY() {
        return hero.getY();
    }


    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft -> {
                moveHero(hero.moveLeft());
            }
            case ArrowRight -> {
                moveHero(hero.moveRight());
            }
            case ArrowUp -> {
                moveHero(hero.moveUp());
            }
            case ArrowDown -> {
                moveHero(hero.moveDown());
            }
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    private boolean canHeroMove(Position p){
        return p.getX() < width - 1 && p.getY() < height - 1 && p.getX() > 0 && p.getY() > 0;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#880808"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
}
