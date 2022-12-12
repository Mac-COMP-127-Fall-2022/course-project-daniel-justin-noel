# Custody Battle!
Justin Chalichemala
Noel Raehl
Daniel Seo

## SUMMARY
A twist on the classic game Pong, this is a two-player competitive game with some famous recognizable characters!

## TECHNICAL GUIDE
Requires **Java 17 for Kilt Graphics**

The program starts by running CustodyBattle.java which contains the main class.

We have four classes: CustodyBattle, Ball, Paddle, and UI.

The ball class creates a new ball, with specific dimensions and color to appear on the canvas. It keeps track of the ball by getting the X and Y coordinates. The ball speed is set as an instance variable. 

The paddle class sets the mainframe for each paddle that is created within the game. Only two paddles can
be moved around (paddle1 and paddle2) in which they can be controlled using the W/S keys and the UP_ARROW and DOWN_ARROW respectively. The class checks interactions with the ball object as well to work with all paddles.

The UI class manages the scoring and displays the beginning text prior to the game starting.

The CustodyBattle class serves as the main class that contains methods contribuiting to the game's function. These methods are, but not limited to, the power-ups, resetting the game, and checking which keys are pressed by mapping each key to a list and running a check on them. In order to run the game, a player must click the mouse on the canvas and wait 3 seconds before the ball begins to move.

## CREDIT
References obtained from COMP127, Breakout HW, Preceptors Tim, Jeremy, Miriam, and Pong.
Art references include 'Family Guy', 'The Boss Baby', 'South Park', 'Daredevil', and 'Better Call Saul'.

## Design Limitations
The game _may_ have some frame lags because multiple graphical objects are moving across the screen. Yet, this shouldn't be a problem in the long run since much of it also depends on the processor of the user's computer. Also, currently the ball only shoots towards the righthand side of the screen.

## BUGS
Currently, there aren't any major technical limitations or bugs that prevent the game from functioning. 
However, there is a rare chance that the ball can get stuck on the edge of the canvas, but ultimately it
should not be a noticeable problem during gameplay.

## PURPOSE
This project is a fusion of different characters from alternate universes. The concept of the game is within the title of the game, "Custody Battle", where we can see two terrible parents fighting over an innocent baby. This project was created for entertainment purposes only, using pop-culture references that people would recognize. Even though we are using fictional characters, we can acknowledge the realness of divorce and the struggles people experience in the real world. Understanding that this game could potentially be used as a way to undermine the real emotional impact of such circumstances does present the game as offensive, but the intention was that it was purely made for entertainment.
