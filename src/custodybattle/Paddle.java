package custodybattle;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;


public class Paddle extends GraphicsGroup {
    private Rectangle paddle;
    private double centerX, centerY;
    private final double PADDLE_WIDTH = 10;
    private final double PADDLE_HEIGHT = 100;

    public Paddle(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        paddle = new Rectangle(centerX, centerY, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFillColor(Color.black);
        paddle.setStrokeColor(Color.black);

    }

    public void movePaddle(double dy) {  //dy is prolly just the change in y values
        double halfLength = paddle.getHeight()/2;

        //adding the centerY value the difference of y values and half the length of paddle
        if (dy + halfLength + paddle.getCenter().getY() <= CustodyBattle.CANVAS_HEIGHT && dy - halfLength + paddle.getCenter().getY() >= 0) {
            paddle.moveBy(0, dy);
        }
    }

    public boolean intersectsPaddle(Ball ball) {
        if (getElementAt(ball.ballTopSide()) != null || getElementAt(ball.ballBottomSide()) != null ||
            getElementAt(ball.ballLeftSide()) != null || getElementAt(ball.ballRightSide()) != null) {
            double dx = ball.getXVelocity();
            dx *= -1;
            ball.setXVelocity(dx);
            return true;
        } 
        return false;
    }

    public double getCenterY() {
        return centerY;
    }

    public Rectangle getGraphics() {
        return paddle;
    }

}
