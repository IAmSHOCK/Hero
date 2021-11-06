import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.security.Key;

public class Game {
    private Screen screen;
    private int x = 10;
    private int y = 10;

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft -> {
                x -= 1;
            }
            case ArrowRight -> {
                x += 1;
            }
            case ArrowUp -> {
                y -= 1;
            }
            case ArrowDown -> {
                y += 1;
            }
        }
    }

    Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
            screen.clear();
            screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() {
        try {
            screen.clear();
            screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        KeyStroke key = new KeyStroke('q', false, false);
        do {
            try {
                draw();
                key = screen.readInput();
                processKey(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (key.getKeyType() != KeyType.EOF ||
                (key.getKeyType() == KeyType.Character && key.getCharacter() != 'q'));

    }
}
