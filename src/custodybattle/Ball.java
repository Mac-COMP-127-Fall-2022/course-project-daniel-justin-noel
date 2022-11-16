package custodybattle;

import java.awt.Color;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;

public class Ball extends GraphicsGroup{

    public static final double BALL_RADIUS = 15;

    private Ellipse ballShape;
    private double centerX;
    private double centerY;
    private double maxX;
    private double maxY;
    private double XVelocity;
    private double YVelocity;


    public Ball(double centerX, double centerY, double initialSpeed, double initialAngle, double maxX, double maxY) {
        ballShape = new Ellipse(centerX, centerY, BALL_RADIUS, BALL_RADIUS);
        ballShape.setFillColor(Color.BLACK);
        ballShape.setStrokeColor(Color.BLACK);
        this.centerX = centerX;
        this.centerY = centerY;
        this.maxX = maxX;
        this.maxY = maxY;
        
        double initialAngleRadians = Math.toRadians(initialAngle);
        XVelocity = initialSpeed + Math.cos(initialAngleRadians);
        YVelocity = initialSpeed + -Math.sin(initialAngleRadians);
        
    }
    
    public void updatePosition() {
        double newX = centerX + XVelocity;
        double newY = centerY + YVelocity;

        ballShape.setCenter(newX, newY);
        centerX = newX;
        centerY = newY;

        if (newX <= 0 || newX >= maxX) {
            XVelocity *= -1;
        }
        if (newY <= 0 || newY >= maxY) {
            YVelocity *= -1;
        }
    }

}
