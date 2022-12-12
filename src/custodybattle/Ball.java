package custodybattle;

import java.awt.Color;
import java.util.Random;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Image;

/**
 * Class that manages how a ball object is made and its characteristics. Deals with how it moves and 
 * interacts with the border of the canvas as well as scoring.
 */
public class Ball{
    private static final double BALL_RADIUS = 30;
    private static final double speed = 15;

    private Ellipse ball;
    private Image babyHead;
    private double centerX;
    private double centerY;
    private double maxX;
    private double maxY;
    private double XVelocity;
    private double YVelocity;

    /**
     * A ball is an ellipse with a certain radius, with a centerX position
     * and centerY position. Each ball has a respective image associated with it.
     * @param centerX The center x coordinate of a ball in accordance to its position on the canvas
     * @param centerY The center y coordinate of a ball in accordance to its position on the canvas
     * @param maxX The maximum x coordinate of a ball
     * @param maxY The maximum x coordinate of a ball
    */
    public Ball(double centerX, double centerY, double maxX, double maxY) {
        ball = new Ellipse(centerX, centerY, BALL_RADIUS, BALL_RADIUS);
        ball.setFillColor(Color.BLACK);
        ball.setStrokeColor(Color.BLACK);
 
        babyHead = new Image(centerX, centerY, "bossBabyFace.png");
        babyHead.setMaxHeight(45);

        this.centerX = centerX;
        this.centerY = centerY;
        this.maxX = maxX;
        this.maxY = maxY;

        Random random = new Random();
        double rand = random.nextDouble();
        double max = 360;
        double min = 10;
        double initialAngleRadians = Math.toRadians(min + ((max-min) * rand));
        XVelocity = speed + Math.cos(initialAngleRadians);
        YVelocity = speed + -Math.sin(initialAngleRadians);
    }
    
    /**
     * This allows the position of the ball to change by setting its center
     * using newly calculated x and y coordinate values. There are conditionals that address
     * when the direction of the ball should change its postion relative to its maxX and maxY coordinates.
    */
    public void updatePosition() {
        double newX = centerX + XVelocity;
        double newY = centerY + YVelocity;

        ball.setCenter(newX, newY);
        babyHead.setCenter(ball.getCenter().getX(), ball.getCenter().getY());
        babyHead.setRotation((babyHead.getRotation() + 10) % 360);
        centerX = newX;
        centerY = newY;


        if (ballTopSide().getY() <= 0) {
            positiveYVel();
        }
        if (ballBottomSide().getY() >= maxY) {
            negativeYVel();
        }
        if (ballLeftSide().getX() <=0) {
            positiveXVel();
        }
        if (ballRightSide().getX() >= maxX) {
            negativeXVel();
        }
    }

    /**
     * @return The current velocity in the x direction of the ball.
     */
    public double getXVelocity() {
        return XVelocity;
    }

    /**
     * @return The center x coordinate of the ball.
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * @return The center y coordinate of the ball.
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * Changes the velocity in the x direction to be negative.
     */
    public void negativeXVel() {
        XVelocity = -Math.abs(XVelocity);
    }

    /**
     * Changes the velocity in the x direction to be positive.
     */
    public void positiveXVel() {
        XVelocity = Math.abs(XVelocity);
    }

    /**
     * Changes the velocity in the y direction to be negative.
     */
    public void negativeYVel() {
        YVelocity = -Math.abs(YVelocity);
    }

    /**
     * Changes the velocity inn the y direction to be positive.
     */
    public void positiveYVel() {
        YVelocity = Math.abs(YVelocity);
    }

    /**
     * Sets the x velocity of the ball to be of a current state.
     * @param xVelocity Refers to the current x velocity of the ball.
     */
    public void setXVelocity(double xVelocity) {
        XVelocity = xVelocity;
    }

    /**
     * Sets the y velocity of the ball to be of a current state.
     * @param yVelocity Refers to the current y velocity of the ball.
     */
    public void setYVelocity(double yVelocity) {
        YVelocity = yVelocity;
    }

    /**
     * @return The current velocity of the ball in the y direction.
     */
    public double getYVelocity() {
        return YVelocity;
    }

    /**
     * @return The graphics of the ball object for use on canvas.
     */
    public Ellipse getGraphics() {
        return ball;
    }

    /**
     * @return The rightmost point of the ball from its center.
     */
    public Point ballRightSide(){
        Point right = new Point(getCenterX() + BALL_RADIUS, getCenterY());
        return right;
    }

    /**
     * @return The leftmost point of the ball from its center.
     */
    public Point ballLeftSide(){
        Point left = new Point(getCenterX() - BALL_RADIUS, getCenterY());
        return left;
    }

    /**
     * @return The topmost point of the ball from its center.
     */
    public Point ballTopSide(){
        Point top = new Point(getCenterX(), getCenterY() - BALL_RADIUS);
        return top;
    }

    /**
     * @return The bottommost point of the ball from its center.
     */
    public Point ballBottomSide(){
        Point bottom = new Point(getCenterX(), getCenterY() + BALL_RADIUS);
        return bottom;
    }

    /**
     * @return Whether the ball's rightmost side has interacted with the righthand border of the canvas.
     */
    public boolean player1Scored() {
        return ballRightSide().getX() >= maxX;
    }

    /**
     * @return Whether the ball's leftmost side has interacted with the lefthand border of the canvas.
     */
    public boolean player2Scored() {
        return ballLeftSide().getX() <= 0;
    }

    /**
     * @return The baby image associated with the ball object.
     */
    public Image getImage() {
        return babyHead;
    }
}
