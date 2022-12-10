# Custody Battle!
Justin Chalichemala
Noel Raehl
Daniel Seo

SUMMARY
A twist on the classic game Pong, this is a two-player competitive game with some famous recognizable characters!

TECHNICAL GUIDE
Requires Java 17 for Kilt Graphics
The program starts by running CustodyBattle.Java which contains the main class.
We have four classes; CustodyBattle, Ball, Paddle, and PaddleManager.
This project implements GraphicsGroups in both the ball and paddle class. 
The ball class creates a new ball, with specific dimensions and color to appear on the canvas. It keeps track of the ball by getting the X and Y coordinates. The ball speed is set as an instance variable. 
The paddle class creates two rectangles, with images fixed on top of them. Movement of the paddles is controlled by WS and the up-arrow and the bottom-arrow. This class generates the paddles within a certain height. 
The main class animates both the ball and paddle for movement, and updates the score using a point counter boolean
by increments of $100. This class determines whether the ball has hit any of the paddles, and reflects the ball if it does. This class checks for which keys were pressed, putting them within a list and moving the paddles.

CREDIT
References obtained from COMP127, Breakout HW, Preceptors Tim, Jeremy, Miriam, and Pong.
Art references include 'Family Guy', 'The Boss Baby', 'South Park', 'Daredevil', and 'Better Call Saul'. 

BUGS
There is a bug when the paddle will get stuck if it increases size near the edge of the canvas. The image and paddle size increases, but the player is unable to move the paddles with the movement keys. 

PURPOSE
This project is a fusion of different characters from alternate universes. The concept of the game is within the title of the game, "Custody Battle", where we can see two terrible parents fighting over an innocent baby. This project was created for entertainment purposes only, using references that people would recognize. Even though we are using fictional characters, we can acknowledge the realness of divorce and the struggles of a custody battle through this game.

 
