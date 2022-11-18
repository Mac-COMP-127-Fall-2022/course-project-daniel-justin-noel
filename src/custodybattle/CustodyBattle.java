package custodybattle;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;

public class CustodyBattle {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private Paddle paddle1, paddle2;
    private Ball ball;

    public CustodyBattle() {
        canvas = new CanvasWindow("Custody Battle", CANVAS_WIDTH, CANVAS_HEIGHT);
        // Rectangle rectangle = new Rectangle(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, 100, 400);
        // canvas.add(rectangle);
        // System.out.println(canvas.getElementAt(rectangle.getCenter()));
        makePaddles();
        makeBall();
        canvas.animate(event -> {
            ball.updatePosition();
            keyCheck();
            // paddle1.intersectsPaddle(ball);
            // paddle2.intersectsPaddle(ball);
            intersectsPaddle(ball);

        });

    }

    public boolean intersectsPaddle(Ball ball) {
        if (paddle1.testHit(ball.ballLeftSide()) || paddle1.testHit(ball.ballRightSide())
            || paddle2.testHit(ball.ballLeftSide()) || paddle2.testHit(ball.ballRightSide())) {
            ball.setReverseXVel();
            return true;

        } else if (paddle1.testHit(ball.ballTopSide()) || paddle1.testHit(ball.ballTopSide())
            || paddle2.testHit(ball.ballBottomSide()) || paddle2.testHit(ball.ballBottomSide())) {
            ball.setReverseYVel();
            return true;
        }
        return false;
    }

    public void keyCheck() {
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
    }


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
