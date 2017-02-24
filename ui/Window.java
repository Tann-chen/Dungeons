package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * The class is to build the window of the game, every screen in the game will show in this window
 * An stack is attached to the window to store the screens shown in window
 * Whenever, there is only a screen in the window, additional screens are stored in the stack
 * @author Tann chen
 */
public class Window extends JFrame{

    private Stack<Screen> screenStack;

    public Window(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(800,600);
        this.setLayout(null);
        this.setVisible(true);

        screenStack=new Stack<Screen>();
    }

    /**
     * The method is used to push a screen to the stack and update the content view of the window
     */
    public void pushScreen(Screen screen){
        if(screenStack.size()>0) {
            Screen topScreen = screenStack.peek();
            this.remove(topScreen);
        }
        this.add(screen);
        screenStack.push(screen);
        this.repaint();

    }
    /**
     * The method is used to pop a screen from the stack and update the content view of the window
     */
    public void popScreen(){
        Screen topScreen = screenStack.peek();
        if(screenStack.size()>1){
            this.remove(topScreen);
            screenStack.pop();
            this.add((Screen)screenStack.peek());
        }
        this.repaint();
    }
}
