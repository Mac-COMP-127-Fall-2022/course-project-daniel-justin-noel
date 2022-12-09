package custodybattle;
import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.TextAlignment;
import edu.macalester.graphics.Image;

public class CustodyBattle {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private Paddle paddle1, paddle2, lawyer1, lawyer2;
    private Ball ball;
    private GraphicsText p1PointText, p2PointText, welcomeText, directionsText, winText;
    private int pointCounter1, pointCounter2;
    private String name;
    private Image court, paulImage;
    private boolean dadIncreased;
    private boolean momIncreased;
    private boolean isAnimating;
    private boolean lawyer1Appears = false;
    private boolean lawyer2Appears = false;
    private boolean flag = false;
    private boolean flag2 = false;

    public CustodyBattle() {
        canvas = new CanvasWindow("Custody Battle", CANVAS_WIDTH, CANVAS_HEIGHT);
        resetGame();
        canvas.onClick((event) -> {
            isAnimating = true;
            startGame();
        });
        canvas.animate(() -> {
            if (isAnimating) {
                ball.updatePosition();
                keyCheck();
                intersectsPaddle(ball);
                updateScore();
                biggerPaddle();
                if (!flag) {
                    makeLawyer1Paddle();
                }
                if (!flag2) {
                    makeLawyer2Paddle();
                }
                if (pointCounter1 >= 2000) {
                    name = "Player 1";
                    winLogic();
                } else if (pointCounter2 >= 2000) {
                    name = "Player 2";
                    winLogic();
                }
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

        directionsText = new GraphicsText("HOW TO PLAY:\n For Player 1 (lefthand paddle) use \n W/S to move.\n For Player 2 (righthand paddle) use the \n UP/DOWN arrow keys", CANVAS_WIDTH/2, (CANVAS_HEIGHT/2) +50);
        directionsText.setFontSize(30);
        directionsText.setFontStyle(FontStyle.BOLD);
        directionsText.setFillColor(Color.CYAN);
        directionsText.setAlignment(TextAlignment.CENTER);
        canvas.add(directionsText);
    }
    
    private void makeCourt() {
        court = new Image(0, 0, "divorce_court.png");
        court.setScale(0.5, 0.5);
        court.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        canvas.add(court);
    }

    private void intersectsPaddle(Ball ball) {
        if (lawyer1Appears) {
            if (lawyer1.testHit(ball.ballLeftSide())) {
                ball.positiveXVel();
            } else if (lawyer1.testHit(ball.ballRightSide())) {
                ball.negativeXVel();
            } else if (lawyer1.testHit(ball.ballTopSide())) {
                ball.positiveYVel();
            } else if (lawyer1.testHit(ball.ballBottomSide())) {
                ball.negativeYVel();
            }   
        }
        if (lawyer2Appears) {
            if (lawyer2.testHit(ball.ballLeftSide())) {
                ball.positiveXVel();
            } else if (lawyer2.testHit(ball.ballRightSide())) {
                ball.negativeXVel();
            } else if (lawyer2.testHit(ball.ballTopSide())) {
                ball.positiveYVel();
            } else if (lawyer2.testHit(ball.ballBottomSide())) {
                ball.negativeYVel();
            }
        }

        if (paddle1.testHit(ball.ballLeftSide()) || paddle2.testHit(ball.ballLeftSide())) {
            ball.positiveXVel();
        } else if (paddle1.testHit(ball.ballRightSide()) || paddle2.testHit(ball.ballRightSide()) ) {
            ball.negativeXVel();
        } else if (paddle1.testHit(ball.ballTopSide()) || paddle2.testHit(ball.ballTopSide())) {
            ball.positiveYVel();
        } else if (paddle1.testHit(ball.ballBottomSide()) || paddle2.testHit(ball.ballBottomSide())) {
            ball.negativeYVel();
        }
    }

    private void makeScoreboard() {
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
        lawyer1 = new Paddle((CANVAS_WIDTH/3) + 50, (CANVAS_HEIGHT/3)*2);
        lawyer2 = new Paddle((CANVAS_WIDTH/3)*2, (CANVAS_HEIGHT/3));
        canvas.add(paddle1.getGraphics());
        canvas.add(paddle2.getGraphics());
    }

    public void makeBall() {
        ball = new Ball(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.add(ball.getGraphics());
    }

    private void updateScore() {
        if (ball.player1Scored()) {
            pointCounter1 += 100;
            p1PointText.setText("$ " + pointCounter1);
        } else if (ball.player2Scored()) {
            pointCounter2 += 100;
            p2PointText.setText("$ " + pointCounter2);
        }
    }

    private void winLogic() {
        canvas.removeAll();
        winText = new GraphicsText(name + " wins!", CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        winText.setFillColor(Color.MAGENTA);
        winText.setAlignment(TextAlignment.CENTER);
        winText.setFontSize(100);
        canvas.add(winText);
        canvas.draw();
        canvas.pause(3000);
        resetGame();
    }

    private void resetGame() { //adds the scoreboard, the intro text and description
        canvas.removeAll();
        pointCounter1 = 0;
        pointCounter2 = 0;
        dadIncreased = false;
        momIncreased = false;
        isAnimating = false;
        lawyer1Appears = false;
        makeScoreboard();
        makeCourt();
        introText();
    }

    private void startGame() { //Adds only the court ball and paddles
        makeCourt();
        makeBall();
        makePaddles();
        paulImage = new Image(0, 0, "paulface1-removebg-preview.png");
        paulImage.setScale(0.395);
        paulImage.setCenter(CANVAS_WIDTH/2 - 10, CANVAS_HEIGHT/2 - 92);
        canvas.add(paulImage);
        canvas.add(p1PointText);
        canvas.add(p2PointText);
        canvas.add(ball.getImage());
        canvas.add(paddle1.getPaddle1Image());
        canvas.add(paddle2.getPaddle2Image());    
        canvas.remove(welcomeText);
        canvas.remove(directionsText);
        canvas.draw();
        canvas.pause(3000);
    }

    private void biggerPaddle() {
        if (pointCounter1 >= 600 && !dadIncreased) {
            dadIncreased = true;
            paddle1.setPaddleHeight(paddle1.getPaddleHeight() + 80, canvas);
            paddle1.setDad(paddle1.getPaddle1Image());
            canvas.add(paddle1.getPaddle1Image());
        } else if (pointCounter2 >= 600 && !momIncreased) {
            momIncreased = true;
            paddle2.setPaddleHeight(paddle2.getPaddleHeight() + 80, canvas);
            paddle2.setMom(paddle2.getPaddle2Image());
            canvas.add(paddle2.getPaddle2Image());
        }
    }

    private void makeLawyer1Paddle() {
        if (pointCounter1 >= 1300) {
            lawyer1Appears = true;
            canvas.add(lawyer1.getGraphics());
            lawyer1.setSaul(lawyer1.getLawyer1Image());
            canvas.add(lawyer1.getLawyer1Image());
            flag = true;
        } 
    }

    private void makeLawyer2Paddle() {
        if (pointCounter2 >= 1300) {
            lawyer2Appears = true;
            canvas.add(lawyer2.getGraphics());
            lawyer2.setMatt(lawyer2.getLawyer2Image());
            canvas.add(lawyer2.getLawyer2Image());
            flag2 = true;
        }
    }



    public static void main(String[] args){
        new CustodyBattle();
    }
}
