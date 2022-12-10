package custodybattle;

import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Image;




public class Ball extends GraphicsGroup{

    private static final double BALL_RADIUS = 30;
    private static final double speed = 15;   //pixels per second

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
        // XVelocity = speed + initialAngleRadians;
        // YVelocity = speed + initialAngleRadians;
        
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

    public double getXVelocity() {
        return XVelocity;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void negativeXVel() {
        XVelocity = -Math.abs(XVelocity);
    }

    public void positiveXVel() {
        XVelocity = Math.abs(XVelocity);
    }

    public void negativeYVel() {
        YVelocity = -Math.abs(YVelocity);
    }

    public void positiveYVel() {
        YVelocity = Math.abs(YVelocity);
    }

    public void setXVelocity(double xVelocity) {
        XVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        YVelocity = yVelocity;
    }

    public double getYVelocity() {
        return YVelocity;
    }

    public Ellipse getGraphics() {
        return ball;
    }


    public Point ballRightSide(){
        Point right = new Point(getCenterX() + BALL_RADIUS, getCenterY());
        return right;
    }

    public Point ballLeftSide(){
        Point left = new Point(getCenterX() - BALL_RADIUS, getCenterY());
        return left;
    }

    public Point ballTopSide(){
        Point top = new Point(getCenterX(), getCenterY() - BALL_RADIUS);
        return top;
    }

    public Point ballBottomSide(){
        Point bottom = new Point(getCenterX(), getCenterY() + BALL_RADIUS);
        return bottom;
    }

    public boolean player1Scored() {
        return ballRightSide().getX() >= maxX;
    }

    public boolean player2Scored() {
        return ballLeftSide().getX() <= 0;
    }

    public Image getImage() {
        return babyHead;
    }
}
