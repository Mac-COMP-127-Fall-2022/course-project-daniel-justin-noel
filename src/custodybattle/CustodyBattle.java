package custodybattle;
import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.Image;

public class CustodyBattle {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private Paddle paddle1, paddle2;
    private Ball ball;
    private GraphicsText p1PointText, p2PointText;
    private int pointCounter1, pointCounter2;
    private Image court;
    private boolean dadIncreased = false;
    // private final GraphicsGroup graphics;

    public CustodyBattle() {
        p1PointText = new GraphicsText("$ " + pointCounter1, (CANVAS_WIDTH/3) - 100, 75);
        p2PointText = new GraphicsText("$ " + pointCounter2, (CANVAS_WIDTH/3) + 250, 75);
        p1PointText.setFillColor(Color.WHITE);
        p2PointText.setFillColor(Color.WHITE);
        p1PointText.setFontSize(50);
        p2PointText.setFontSize(50);
        p1PointText.setFontStyle(FontStyle.BOLD_ITALIC);
        p2PointText.setFontStyle(FontStyle.BOLD_ITALIC);
        canvas = new CanvasWindow("Custody Battle", CANVAS_WIDTH, CANVAS_HEIGHT);
        court = new Image(0, 0, "divorce-court-background.jpg");
        
        court.setScale(0.5, 0.5);
        court.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        canvas.add(court);
    
        makePaddles();
        makeBall();
        canvas.add(p1PointText);
        canvas.add(p2PointText);
        canvas.add(ball.getImage());
        canvas.add(paddle1.getPaddle1Image());
        canvas.add(paddle2.getPaddle2Image());

        canvas.animate(event -> {
            ball.updatePosition();
            keyCheck();
            intersectsPaddle(ball);
            checkScore();
            biggerPaddle();
        });

    }

    public boolean intersectsPaddle(Ball ball) {
        if (paddle1.testHit(ball.ballLeftSide()) || paddle1.testHit(ball.ballRightSide())
            || paddle2.testHit(ball.ballLeftSide()) || paddle2.testHit(ball.ballRightSide())) {
            ball.setReverseXVel();
            return true;

        } else if (paddle1.testHit(ball.ballTopSide()) || paddle1.testHit(ball.ballBottomSide())
            || paddle2.testHit(ball.ballTopSide()) || paddle2.testHit(ball.ballBottomSide())) {
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

    private void checkScore() {
        if (ball.player1Scored()) {
            pointCounter1 += 100;
            p1PointText.setText("$ " + pointCounter1);
        } else if (ball.player2Scored()) {
            pointCounter2 += 100;
            p2PointText.setText("$ " + pointCounter2);
        }
    }

    public void biggerPaddle() {
        if (pointCounter1 >= 600 && !dadIncreased) {
            dadIncreased = true;
            paddle1.setPaddleHeight(paddle1.getPaddleHeight() + 80, canvas);
            paddle1.setDad(paddle1.getPaddle1Image());
            canvas.add(paddle1.getPaddle1Image());
        }
    }

    public static void main(String[] args){
        new CustodyBattle();
    }
}
