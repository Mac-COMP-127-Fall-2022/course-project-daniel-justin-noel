package custodybattle;
import java.awt.Color;
import java.util.List;
import java.util.Timer;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.TextAlignment;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.Image;

public class CustodyBattle {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private Paddle paddle1, paddle2;
    private Ball ball;
    private GraphicsText p1PointText, p2PointText, welcomeText, directionsText;
    private int pointCounter1, pointCounter2;
    private Image court;
    private boolean dadIncreased = false;
    private boolean momIncreased = false;
    private boolean isAnimating = false;

    public CustodyBattle() {
        makesScoreboard();
        canvas = new CanvasWindow("Custody Battle", CANVAS_WIDTH, CANVAS_HEIGHT);
        court = new Image(0, 0, "divorce-court-background.jpg");
        
        court.setScale(0.5, 0.5);
        court.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        canvas.add(court);
    
        introText();
        canvas.onClick((event) -> {
            isAnimating = true;
            makeBall();
            makePaddles();
            canvas.add(p1PointText);
            canvas.add(p2PointText);
            canvas.add(ball.getImage());
            canvas.add(paddle1.getPaddle1Image());
            canvas.add(paddle2.getPaddle2Image());    
            canvas.remove(welcomeText);
            canvas.remove(directionsText);   // maybe try adding some sort of counttdown before the game starts
        });
        canvas.animate(() -> {
            if (isAnimating) {
                ball.updatePosition();
                keyCheck();
                intersectsPaddle(ball);
                checkScore();
                biggerPaddle();
            }
        });

    }

    private void introText() {
        welcomeText = new GraphicsText("WELCOME TO\nCUSTODY BATTLE!", CANVAS_WIDTH/2, CANVAS_HEIGHT/4);
        welcomeText.setFontSize(50);
        welcomeText.setFontStyle(FontStyle.BOLD);
        welcomeText.setFillColor(Color.GREEN);
        welcomeText.setAlignment(TextAlignment.CENTER);
        canvas.add(welcomeText);

        directionsText = new GraphicsText("You've prolly played Pong\nbefore tbh.\nFigure it out\n(hint: use the keys)", CANVAS_WIDTH/2, (CANVAS_HEIGHT/2) +20);
        directionsText.setFontSize(30);
        directionsText.setFontStyle(FontStyle.BOLD);
        directionsText.setFillColor(Color.CYAN);
        directionsText.setAlignment(TextAlignment.CENTER);
        canvas.add(directionsText);
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

    private void makesScoreboard() {
        p1PointText = new GraphicsText("$ " + pointCounter1, (CANVAS_WIDTH/3) - 100, 75);
        p2PointText = new GraphicsText("$ " + pointCounter2, (CANVAS_WIDTH/3) + 250, 75);
        p1PointText.setFillColor(Color.WHITE);
        p2PointText.setFillColor(Color.WHITE);
        p1PointText.setFontSize(50);
        p2PointText.setFontSize(50);
        p1PointText.setFontStyle(FontStyle.BOLD_ITALIC);
        p2PointText.setFontStyle(FontStyle.BOLD_ITALIC);
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
        } else {
            if (pointCounter2 >= 600 && !momIncreased) {
                momIncreased = true;
                paddle2.setPaddleHeight(paddle2.getPaddleHeight() + 80, canvas);
                paddle2.setMom(paddle2.getPaddle2Image());
                canvas.add(paddle2.getPaddle2Image());
            }
        }
    }

    public static void main(String[] args){
        new CustodyBattle();
    }
}
