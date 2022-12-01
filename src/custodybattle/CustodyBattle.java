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
    private GraphicsText player1Points, player2Points;
    private Integer pointCounter1, pointCounter2;
    private Image court, dad, mom;
    // private final GraphicsGroup graphics;

    public CustodyBattle() {
        pointCounter1 = 0;
        pointCounter2 = 0;
        player1Points = new GraphicsText("$ " + pointCounter1, (CANVAS_WIDTH/3) - 100, 75);
        player2Points = new GraphicsText("$ " + pointCounter2, (CANVAS_WIDTH/3) + 250, 75);
        player1Points.setFillColor(Color.WHITE);
        player2Points.setFillColor(Color.WHITE);
        player1Points.setFontSize(50);
        player2Points.setFontSize(50);
        player1Points.setFontStyle(FontStyle.BOLD_ITALIC);
        player2Points.setFontStyle(FontStyle.BOLD_ITALIC);
        canvas = new CanvasWindow("Custody Battle", CANVAS_WIDTH, CANVAS_HEIGHT);
        court = new Image(0, 0, "divorce-court-background.jpg");
        
        court.setScale(0.5, 0.5);
        court.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        canvas.add(court);
    
        makePaddles();
        makeBall();
        canvas.add(player1Points);
        canvas.add(player2Points);
        canvas.add(ball.getImage());
        canvas.add(paddle1.getPaddle1Image());
        canvas.add(paddle2.getPaddle2Image());

        canvas.animate(event -> {
            ball.updatePosition();
            keyCheck();
            intersectsPaddle(ball);
            checkScore();

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
            player1Points.setText("$ " + pointCounter1);
        } else if (ball.player2Scored()) {
            pointCounter2 += 100;
            player2Points.setText("$ " + pointCounter2);
        }
    }

        public Image getPaddle1Image() {
        return dad;
    }

    public Image getPaddle2Image() {
        return mom;
    }

    public static void main(String[] args){
        new CustodyBattle();
    }
}
