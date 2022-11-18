package custodybattle;

import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

public class Ball extends GraphicsGroup{

    private static final double BALL_RADIUS = 15;
    private static final double speed = 5;

    private Ellipse ball;
    private double centerX;
    private double centerY;
    private double maxX;
    private double maxY;
    private double XVelocity;
    private double YVelocity;


    public Ball(double centerX, double centerY, double maxX, double maxY) {
        ball = new Ellipse(centerX, centerY, BALL_RADIUS, BALL_RADIUS);
        ball.setFillColor(Color.BLACK);
        ball.setStrokeColor(Color.BLACK);
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
    
    public void updatePosition() {
        double newX = centerX + XVelocity;
        double newY = centerY + YVelocity;

        ball.setCenter(newX, newY);
        centerX = newX;
        centerY = newY;

        if (newX <= 0 || newX >= maxX) {
            XVelocity *= -1;
        }
        if (newY <= 0 || newY >= maxY) {
            YVelocity *= -1;
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
}
