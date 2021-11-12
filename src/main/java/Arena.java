import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10);
        
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
        if(p.getX() >= width || p.getY() >= height || p.getX() <= 0 || p.getY() <= 0) return false;
        return true;
    }

    public void draw(Screen screen) {
        hero.draw(screen);
    }
}
