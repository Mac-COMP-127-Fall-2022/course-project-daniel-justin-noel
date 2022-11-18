package custodybattle;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class CustodyBattle {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private Paddle paddle1, paddle2;
    private Ball ball;

    public CustodyBattle() {
        canvas = new CanvasWindow("Custody Battle", CANVAS_WIDTH, CANVAS_HEIGHT);
        makePaddles();
        makeBall();
        canvas.animate(event -> {
            ball.updatePosition();
            paddle1.intersectsPaddle(ball);
            paddle2.intersectsPaddle(ball);
            List<String> keysPressed = canvas.getKeysPressed().stream().map(key -> key.toString()).toList();
            if (keysPressed.contains("S")) {
                paddle1.movePaddle(10);
            } else if (keysPressed.contains("W")) {
                paddle1.movePaddle(-10);
            }
            
            if (keysPressed.contains("DOWN_ARROW")) {
                paddle2.movePaddle(10);
            } else if (keysPressed.contains("UP_ARROW")) {
                paddle2.movePaddle(-10);
            }

        });

    }

    // public boolean intersectsPaddle1() {
    //     if (ball.getElementAt(paddle1.getX(), paddle1.getY()) != null) {
    //         double dx = ball.getXVelocity();
    //         dx *= -1;
    //         ball.setXVelocity(dx);
    //     } else if (ball.)


    //     return false;
    // }



    public void makePaddles() {
        paddle1 = new Paddle(CANVAS_WIDTH/8, CANVAS_HEIGHT/2);
        paddle2 = new Paddle((CANVAS_WIDTH/8)*7, CANVAS_HEIGHT/2);
        canvas.add(paddle1.getGraphics());
        canvas.add(paddle2.getGraphics());
    }

    public void makeBall() {
        ball = new Ball(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.add(ball.getGraphics());
    }

    public static void main(String[] args){
        new CustodyBattle();
    }
}
