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

    public Paddle(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        paddle = new Rectangle(centerX, centerY, PADDLE_WIDTH, paddleHeight);
        // paddle.setFillColor(Color.WHITE);
        paddle.setStrokeColor(Color.WHITE);
        paddle.setStrokeWidth(.1);

        
        dad = new Image(centerX - 45, centerY - 2.5, "peter angry.png");
        dad.setMaxHeight(130);

        mom = new Image(centerX - 30, centerY - 10, "Mac-sheila.png");
        mom.setMaxHeight(140);

        saulGoodman = new Image(centerX, centerY, "saulgoodman-removebg-preview.png");

        mattMurdock = new Image(centerX, centerY, "mattMurdock-removebg-preview.png");
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

    public Image getLawyer1Image() {
        return saulGoodman;
    }
    
    public Image getLawyer2Image() {
        return mattMurdock;
    }

    public void setSaul(Image saulGoodman) {
        this.saulGoodman = saulGoodman;
        saulGoodman.setMaxHeight(120);
        saulGoodman.setCenter(paddle.getCenter().getX(), paddle.getCenter().getY());
    }

    public void setMatt(Image mattMurdock) {
        this.mattMurdock = mattMurdock;
        mattMurdock.setMaxHeight(145);
        mattMurdock.setCenter(paddle.getCenter().getX() - 5, paddle.getCenter().getY() - 15);
    }
    
    public void setDad(Image dad) {
        this.dad = dad;
        dad.setMaxHeight(200);
        dad.setCenter(dad.getCenter().getX() - 20, dad.getCenter().getY() - 8);
    }

    public void setMom(Image mom) {
        this.mom = mom;
        mom.setMaxHeight(200);
        mom.setCenter(mom.getCenter().getX() - 20, mom.getCenter().getY());
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
        // paddle.setFillColor(Color.WHITE);
        paddle.setStrokeColor(Color.WHITE);
        paddle.setStrokeWidth(.1);
        canvas.add(paddle);
    }

    public double getPaddleHeight() {
        return paddleHeight;
    }

}
