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
    private double paddleHeight = 100;

    public Paddle(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        paddle = new Rectangle(centerX, centerY, PADDLE_WIDTH, paddleHeight);
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
    
    public void setDad(Image dad) {
        this.dad = dad;
        dad.setMaxHeight(200);
        dad.setCenter(dad.getCenter().getX() - 20, dad.getCenter().getY() - 5);
    }

    public void setMom(Image mom) {
        this.mom = mom;
    }

    public Image getPaddle2Image() {
        return mom;
    }

    public boolean testHit(Point point) {
        return paddle.testHit(point.getX(), point.getY());
    }

    public double getCenterY() {
        return centerY;
    }

    public Rectangle getGraphics() {
        return paddle;
    }

    public void setPaddleHeight(double paddleHeight, CanvasWindow canvas) {
        double xVal = paddle.getX();
        double yVal = paddle.getY();
        
        canvas.remove(paddle);
        paddle = new Rectangle(xVal, yVal, PADDLE_WIDTH, paddleHeight);
        paddle.setFillColor(Color.WHITE);
        paddle.setStrokeColor(Color.WHITE);
        canvas.add(paddle);
    }

    public double getPaddleHeight() {
        return paddleHeight;
    }


}
