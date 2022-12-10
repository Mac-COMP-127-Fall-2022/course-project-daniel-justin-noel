package custodybattle;

import edu.macalester.graphics.FontStyle;

import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.CanvasWindow;

import java.awt.Color;

public class UI {
    private int pointCounter1, pointCounter2;
    private GraphicsText p1PointText, p2PointText, welcomeText, directionsText, winText;
    private CanvasWindow canvas;

    public UI(CanvasWindow canvas){
        this.canvas = canvas;
    }
    
    public void makeScoreboard() {
        p1PointText = new GraphicsText("$ " + pointCounter1, (canvas.getHeight()/3) - 100, 75);
        p2PointText = new GraphicsText("$ " + pointCounter2, (canvas.getWidth()/3) + 250, 75);
        p1PointText.setFillColor(Color.WHITE);
        p2PointText.setFillColor(Color.WHITE);
        p1PointText.setFontSize(50);
        p2PointText.setFontSize(50);
        p1PointText.setFontStyle(FontStyle.BOLD_ITALIC);
        p2PointText.setFontStyle(FontStyle.BOLD_ITALIC);
    }
}
