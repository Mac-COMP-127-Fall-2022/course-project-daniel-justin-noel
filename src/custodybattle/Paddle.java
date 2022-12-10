package custodybattle;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;


public class Paddle extends GraphicsGroup {
    private Rectangle paddle;
    private Image dad, mom, saulGoodman, mattMurdock;
    private double centerX, centerY;
    private final double PADDLE_WIDTH = 25;
    private double paddleHeight = 100;

    /**
     * A paddle is a rectangle with a certain height and width, with a centerX position
     * and centerY position. Each paddle has a respective image associated with it.
     * @param centerX The center x coordinate of a paddle in accordance to its position on the canvas
     * @param centerY The center y coordinate of a paddle in accordance to its position on the canvas
     */
    public Paddle(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        paddle = new Rectangle(centerX, centerY, PADDLE_WIDTH, paddleHeight);
        // paddle.setFillColor(Color.WHITE);
        // paddle.setStrokeColor(Color.WHITE);
        // paddle.setStrokeWidth(.1);

        
        dad = new Image(centerX - 45, centerY - 2.5, "peter angry.png");
        dad.setMaxHeight(130);

        mom = new Image(centerX - 30, centerY - 10, "Mac-sheila.png");
        mom.setMaxHeight(140);

        saulGoodman = new Image(centerX, centerY, "saulgoodman-removebg-preview.png");

        mattMurdock = new Image(centerX, centerY, "mattMurdock-removebg-preview.png");
    }

    /**
     * Moves the player paddles vertically until they reach the canvas's border. Half a paddle's height is
     * added to the centerY coordinate make a point where the paddle does not exceed past the canvas.
     * @param dy Change in y values
     */
    public void movePaddle(double dy) {
        double halfLength = paddle.getHeight()/2;

        if (dy + halfLength + paddle.getCenter().getY() <= CustodyBattle.CANVAS_HEIGHT && dy - halfLength + paddle.getCenter().getY() >= 0) {
            paddle.moveBy(0, dy);
            dad.moveBy(0,dy);
            mom.moveBy(0,dy);
        }
    }

    /**
     * @return the image associated with Player 1
     */
    public Image getPaddle1Image() {
        return dad;
    }

    /**
     * @return The image associated with Lawyer 1
     */
    public Image getLawyer1Image() {
        return saulGoodman;
    }
    
    /**
     * @return The image associated with Lawyer 2
     */
    public Image getLawyer2Image() {
        return mattMurdock;
    }

    /**
     * Shrinks the saulGoodman image associated with Lawyer 1 from its original size. Also sets the center
     * of the image to a paddle's x and y center.
     * @param saulGoodman Image of Saul Goodman from the Internet
     */
    public void setSaul(Image saulGoodman) {
        this.saulGoodman = saulGoodman;
        saulGoodman.setMaxHeight(120);
        saulGoodman.setCenter(paddle.getCenter().getX(), paddle.getCenter().getY());
    }

    /**
     * Shrinks the mattMurdock image associated with Lawyer 2 from its original size. Adjusts the center of
     * the image so that it (mostly) covers the paddle. 
     * @param mattMurdock Image of Matt Murdock from the Internet
     */
    public void setMatt(Image mattMurdock) {
        this.mattMurdock = mattMurdock;
        mattMurdock.setMaxHeight(145);
        mattMurdock.setCenter(paddle.getCenter().getX() - 5, paddle.getCenter().getY() - 15);
    }
    
    /**
     * Sets the max height of the dad image associated with Player 1 to cover the paddle it is on top of.
     * Adjusts the center cover the paddle (mostly).
     * @param dad Image of Peter Griffin from the Internet
     */
    public void setDad(Image dad) {
        this.dad = dad;
        dad.setMaxHeight(200);
        dad.setCenter(dad.getCenter().getX() - 20, dad.getCenter().getY() - 8);
    }

    /**
     * Sets the max height of the mom image associated with Player 2 to cover the paddle it is on top of.
     * Adjusts the center cover the paddle (mostly).
     * @param mom Image of Sheila Broflovski from the Internet.
     */
    public void setMom(Image mom) {
        this.mom = mom;
        mom.setMaxHeight(200);
        mom.setCenter(mom.getCenter().getX() - 20, mom.getCenter().getY());
    }

    /**
     * @return The image associated with Player 2.
     */
    public Image getPaddle2Image() {
        return mom;
    }

    /**
     * @param point A point on canvas.
     * @return An x and y point coordinate for a paddle to help test interactions.
     */
    public boolean testHit(Point point) {
        return paddle.testHit(point.getX(), point.getY());
    }

    /**
     * @return The paddle object (a rectangle) so it can be visible on canvas.
     */
    public Rectangle getGraphics() {
        return paddle;
    }

    /**
     * Adjusts the paddle height so when the first score threshold is reached, the old paddle is removed
     * from screen and replaced with a larger paddle.
     * @param paddleHeight The height of the paddle by a specific number of units. 
     * @param canvas Canvas object to facilitate removing and adding the paddle.
     */
    public void setPaddleHeight(double paddleHeight, CanvasWindow canvas) {
        double xVal = paddle.getX();
        double yVal = paddle.getY();
        
        canvas.remove(paddle);
        paddle = new Rectangle(xVal, yVal, PADDLE_WIDTH, paddleHeight);
        // paddle.setStrokeColor(Color.WHITE);
        // paddle.setStrokeWidth(.1);
        canvas.add(paddle);
    }

    /**
     * Tests interactions with the paddle and ball. Based on the side of the ball that hits the paddle, the
     * direction of the ball changes in relation to the canvas.
     * @param ball Graphical ball object with points to aid in testHit
     * @param paddle Paddle object used for testing all paddle interactions with the ball
     */
    public void intersects(Ball ball, Paddle paddle) {
        if (paddle.testHit(ball.ballLeftSide())) {
            ball.positiveXVel();
        } else if (paddle.testHit(ball.ballRightSide())) {
            ball.negativeXVel();
        } else if (paddle.testHit(ball.ballTopSide())) {
            ball.positiveYVel();
        } else if (paddle.testHit(ball.ballBottomSide())) {
            ball.negativeYVel();
        }
    }

    /**
     * @return The height of the paddle in pixels to be used in other files
     */
    public double getPaddleHeight() {
        return paddleHeight;
    }

}
