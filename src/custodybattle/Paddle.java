package custodybattle;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;


public class Paddle extends GraphicsGroup {
    private Rectangle paddle;
    private Image dad, mom;
    private double centerX, centerY;
    private final double PADDLE_WIDTH = 10;
    private final double PADDLE_HEIGHT = 100;

    public Paddle(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        paddle = new Rectangle(centerX, centerY, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFillColor(Color.WHITE);
        paddle.setStrokeColor(Color.WHITE);

        dad = new Image(centerX - 50, centerY - 2.5, "peter angry.png");
        dad.setMaxHeight(130);
        mom = new Image(centerX - 35, centerY - 10, "Mac-sheila.png");
        mom.setMaxHeight(140);

    }

    public void movePaddle(double dy) {  //dy is prolly just the change in y values
        double halfLength = paddle.getHeight()/2;

        //adding the centerY value the difference of y values and half the length of paddle
        if (dy + halfLength + paddle.getCenter().getY() <= CustodyBattle.CANVAS_HEIGHT && dy - halfLength + paddle.getCenter().getY() >= 0) {
            paddle.moveBy(0, dy);
            dad.moveBy(0,dy);
            mom.moveBy(0,dy);
        }
    }

    public Image getPaddle1Image() {
        return dad;
    }

    public Image getPaddle2Image() {
        return mom;
    }

    // public boolean intersectsPaddle(Ball ball, CanvasWindow canvas) {
    //     // System.out.println(canvas.getElementAt(ball.ballLeftSide()));
    //     if (canvas.getElementAt(ball.ballLeftSide()) != null || canvas.getElementAt(ball.ballRightSide()) != null) {
    //         // double dx = ball.getXVelocity();
    //         // dx *= -1;
    //         // ball.setXVelocity(dx);
    //         System.out.println(ball.getXVelocity());
    //         ball.setReverseXVel();
    //         System.out.println(ball.getXVelocity());
    //         // System.out.println("hello");
    //         return true;
    //     } else if (canvas.getElementAt(ball.ballTopSide()) != null || canvas.getElementAt(ball.ballBottomSide()) != null) {
    //         // double dy = ball.getYVelocity();
    //         // dy *= -1;
    //         // ball.setYVelocity(dy);
    //         System.out.println(ball.getXVelocity());
    //         ball.setReverseYVel();
    //         System.out.println(ball.getYVelocity());
    //         return true;
    //     }
    //     return false;
    // }

    public boolean testHit(Point point) {
        return paddle.testHit(point.getX(), point.getY());
    }

    public double getCenterY() {
        return centerY;
    }

    public Rectangle getGraphics() {
        return paddle;
    }

}
