package custodybattle;
import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.TextAlignment;
import edu.macalester.graphics.Image;

/**
 * Runs the main game. Game consists of making the background alongside the paddles, and ball. Resets the
 * game as well.
 */
public class CustodyBattle {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private Paddle paddle1, paddle2, lawyer1, lawyer2;
    private Ball ball;
    private String name;
    private Image court, paulImage;
    private UI uI;
    private GraphicsText winText;
    private boolean dadIncreased, momIncreased, isAnimating;
    private boolean lawyer1Appears = false;
    private boolean lawyer2Appears = false;


    /**
     * Game of pong that revolves around continuous scoring and 2 power ups that can occur during the game
     * after a certain point is reached. Creates a new canvas, and runs the game until someone wins then
     * resets.
     */
    public CustodyBattle() {
        uI = new UI();
        canvas = new CanvasWindow("Custody Battle", CANVAS_WIDTH, CANVAS_HEIGHT);
        resetGame();
        canvas.onClick((event) -> {
            if (!isAnimating) {
                isAnimating = true;
                startGame();
            }

        });
        canvas.animate(() -> {
            if (isAnimating) {
                ball.updatePosition();
                keyCheck();
                whoToIntersect(ball);
                name = uI.updateScore(ball);
                if (name != null)
                    winLogic();
                    biggerPaddle();
                if (!lawyer1Appears) {
                    makeLawyer1Paddle();
                }
                if (!lawyer2Appears) {
                    makeLawyer2Paddle();
                }
            }
        });
    }

    /**
     * Makes the background court area to be displayed on canvas.
     */
    private void makeCourt() {
        court = new Image(0, 0, "divorce_court.png");
        court.setScale(0.5, 0.5);
        court.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        canvas.add(court);
    }

    /**
     * Determines paddle intersection with a ball object. Lawyer paddles should appear first before ball
     * can interact with them. 
     * @param ball Ball object that has boss baby's head on it.
     */
    private void whoToIntersect(Ball ball) {
        if (lawyer1Appears) {
            lawyer1.intersects(ball, lawyer1);
        }
        if (lawyer2Appears) {
            lawyer2.intersects(ball, lawyer2);
        }
        paddle1.intersects(ball, paddle1);
        paddle2.intersects(ball, paddle2);
    }

    /**
     * Creates a list of strings referencing the keys pressed on canvas. W/S move Player 1 while DownArrow/
     * UpArrow move the Player 2.
     */
    public void keyCheck() {
        List<String> keysPressed = canvas.getKeysPressed().stream().map(key -> key.toString()).toList();
        if (keysPressed.contains("S")) {
            paddle1.movePaddle(10);
        } if (keysPressed.contains("W")) {
            paddle1.movePaddle(-10);
        }
        if (keysPressed.contains("DOWN_ARROW")) {
            paddle2.movePaddle(10);
        } else if (keysPressed.contains("UP_ARROW")) {
            paddle2.movePaddle(-10);
        }
    }


    /**
     * Makes paddles to be displayed and used in game. Lawyer paddles are not displayed on canvas yet.
     */
    public void makePaddles() {
        paddle1 = new Paddle(CANVAS_WIDTH/8, CANVAS_HEIGHT/2);
        paddle2 = new Paddle((CANVAS_WIDTH/8)*7, CANVAS_HEIGHT/2);
        lawyer1 = new Paddle((CANVAS_WIDTH/3) + 50, (CANVAS_HEIGHT/3)*2);
        lawyer2 = new Paddle((CANVAS_WIDTH/3)*2, (CANVAS_HEIGHT/3));
        canvas.add(paddle1.getGraphics());
        canvas.add(paddle2.getGraphics());
    }

    /**
     * Makes a ball to be displayed on canvas
     */
    public void makeBall() {
        ball = new Ball(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.add(ball.getGraphics());
    }

    /**
     * Displays a white screen showing which player has won the game. Player 1 is the the paddle on the
     * left side and Player 2 refers to the paddle on the right side.
     */
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

    /**
     * Clears canvas and resents instance variables to set the game back to start. Displays intro and 
     * welcoming text as well.
     */
    private void resetGame() { //adds the scoreboard, the intro text and description
        canvas.removeAll();
        name = null;
        dadIncreased = false;
        momIncreased = false;
        isAnimating = false;
        lawyer1Appears = false;
        lawyer2Appears = false;
        uI.makeScoreboard();
        makeCourt();
        uI.introText(canvas);
    }

    /**
     * Sets the background of the game, consisting of a court, ball, and paddles. Also puases the canvas
     * for 3 seconds once screen is clicked. 
     */
    private void startGame() {
        makeCourt();
        makeBall();
        makePaddles();
        paulImage = new Image(0, 0, "paulface1-removebg-preview.png");
        paulImage.setScale(0.395);
        paulImage.setCenter(CANVAS_WIDTH/2 - 10, CANVAS_HEIGHT/2 - 92);
        canvas.add(paulImage);
        canvas.add(uI.getp1PointText());
        canvas.add(uI.getp2PointText());
        canvas.add(ball.getImage());
        canvas.add(paddle1.getPaddle1Image());
        canvas.add(paddle2.getPaddle2Image());    
        canvas.remove(uI.getWelcomeText());
        canvas.remove(uI.getDirectionsText());
        canvas.draw();
        canvas.pause(3000);
    }

    /**
     * Increases the side of a paddle based on which player scored. Image associated with the canvas also
     * increases in size after 600 points are reached.
     */
    private void biggerPaddle() {
        if (uI.getPointCounter1() >= 600 && !dadIncreased) {
            dadIncreased = true;
            paddle1.setPaddleHeight(paddle1.getPaddleHeight() + 80, canvas);
            paddle1.setDad(paddle1.getPaddle1Image());
            canvas.add(paddle1.getPaddle1Image());
        } else if (uI.getPointCounter2() >= 600 && !momIncreased) {
            momIncreased = true;
            paddle2.setPaddleHeight(paddle2.getPaddleHeight() + 80, canvas);
            paddle2.setMom(paddle2.getPaddle2Image());
            canvas.add(paddle2.getPaddle2Image());
        }
    }

    /**
     * Adds lawyer1 paddle and its associated image once a score limit of 1300 is reached.
     */
    private void makeLawyer1Paddle() {
        if (uI.getPointCounter1() >= 1300) {
            lawyer1Appears = true;
            canvas.add(lawyer1.getGraphics());
            lawyer1.setSaul(lawyer1.getLawyer1Image());
            canvas.add(lawyer1.getLawyer1Image());
        } 
    }

    /**
     * Adds lawyer2 paddle and its associated image once a score imit of 1300 is reached.
     */
    private void makeLawyer2Paddle() {
        if (uI.getPointCounter2() >= 1300) {
            lawyer2Appears = true;
            canvas.add(lawyer2.getGraphics());
            lawyer2.setMatt(lawyer2.getLawyer2Image());
            canvas.add(lawyer2.getLawyer2Image());
        }
    }

    public static void main(String[] args){
        new CustodyBattle();
    }
}
