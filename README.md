# Custody Battle!

This project is based off the classic original game Pong, created in 1972. 
However, we take a twist on this game, ours being called Custody Battle. 
This game is a competitive two player game, where players attempt to gain points/money in a court battle for the child.
Our game runs continously, the ball constantly bouncing off the walls and paddles. The winner is decided when player one or two
reaches a certain amount of points. 

We have three classes; the main class, ball and paddle.
This project implements GraphicsGroups in both the ball and paddle class. 

The ball class creates a new ball, with specific dimensions and color to appear on the canvas. It keeps track of the ball 
by getting the X and Y coordinates. The ball speed is set as an instance variable. 

The paddle class creates two rectangles, with images fixed on top of them. Movement of the paddles is controlled by WS and the up-arrow
and the bottom-arrow. This class generates the paddles within a certain height. 

The main class animates both the ball and paddle for movement, and updates the score using a point counter boolean
by increments of $100. This class determines whether the ball has hit any of the paddles, and reflects the ball if it does. 
This class checks for which keys were pressed, putting them within a list and moving the paddles. 
