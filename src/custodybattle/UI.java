package custodybattle;

import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.TextAlignment;
import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;

/**
 * Class that manages the the graphics texts that appear on canvas including the scoreboard and welcome
 * texts. Also manages updating the score.
 */
public class UI {
    private GraphicsText p1PointText, p2PointText, welcomeText, directionsText;
    private int pointCounter1, pointCounter2;
    
    /**
     * Places two point counters on screen that each represent the score for a player.
     */
    public void makeScoreboard() {
        pointCounter1 = 0;
        pointCounter2 = 0;
        p1PointText = new GraphicsText("$ " + pointCounter1, (CustodyBattle.CANVAS_WIDTH/3) - 100, 75);
        p2PointText = new GraphicsText("$ " + pointCounter2, (CustodyBattle.CANVAS_WIDTH/3) + 250, 75);
        p1PointText.setFillColor(Color.WHITE);
        p2PointText.setFillColor(Color.WHITE);
        p1PointText.setFontSize(50);
        p2PointText.setFontSize(50);
        p1PointText.setFontStyle(FontStyle.BOLD_ITALIC);
        p2PointText.setFontStyle(FontStyle.BOLD_ITALIC);
    }

    /**
     * @return Player 1's graphics text displaying the score.
     */
    public GraphicsText getp1PointText() {
        return p1PointText;
    }

    /**
     * @return PLayer 2's graphics text displaying the score.
     */
    public GraphicsText getp2PointText() {
        return p2PointText;
    }
    
    /**
     * Initial text that appears when the game is run. Gives instructions on how to play the game.
    */
    public void introText(CanvasWindow canvas) {
        welcomeText = new GraphicsText("WELCOME TO\nCUSTODY BATTLE!", CustodyBattle.CANVAS_WIDTH/2, CustodyBattle.CANVAS_HEIGHT/4);
        welcomeText.setFontSize(50);
        welcomeText.setFontStyle(FontStyle.BOLD);
        welcomeText.setFillColor(Color.GREEN);
        welcomeText.setAlignment(TextAlignment.CENTER);
        canvas.add(welcomeText);

        directionsText = new GraphicsText("HOW TO PLAY:\n For Player 1 (lefthand paddle) use \n W/S to move.\n For Player 2 (righthand paddle) use the \n UP/DOWN arrow keys \n CLICK to PLAY",
         CustodyBattle.CANVAS_WIDTH/2, (CustodyBattle.CANVAS_HEIGHT/2) +50);
        directionsText.setFontSize(30);
        directionsText.setFontStyle(FontStyle.BOLD);
        directionsText.setFillColor(Color.CYAN);
        directionsText.setAlignment(TextAlignment.CENTER);
        canvas.add(directionsText);
    }

    /**
     * @return "WELCOME TO CUSTODY BATTLE" graphics text.
     */
    public GraphicsText getWelcomeText() {
        return welcomeText;
    }

    /**
     * @return The directions for the game.
     */
    public GraphicsText getDirectionsText() {
        return directionsText;
    }

    /**
     * Simply adds 100 points to the respective player when the ball reaches their goal and updates the
     * graphics text respectively.
     * @param Ball Ball object used to determine if a player is scored
     * @return The winner of the game. Returns null if no winner if score limit is not reached.
    */
    public String updateScore(Ball ball) {
        if (ball.player1Scored()) {
            pointCounter1 += 100;
            p1PointText.setText("$ " + pointCounter1);
            if (pointCounter1 >= 2000)
                return "Player 1";
        } else if (ball.player2Scored()) {
            pointCounter2 += 100;
            p2PointText.setText("$ " + pointCounter2);
            if (pointCounter2 >= 2000)
                return "Player 2";
        }
        return null;
    }

    /**
     * @return The numerical point value of Player 1.
     */
    public int getPointCounter1() {
        return pointCounter1;
    }

    /**
     * @return The numberical point value of Player 2.
     */
    public int getPointCounter2() {
        return pointCounter2;
    }
}
