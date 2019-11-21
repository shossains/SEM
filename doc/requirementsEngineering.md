Requirements Air Hockey
======

##### Game created by: 
* Matei Anton
* Ben Provan-Bessell
* Krzysztof Garbowicz
* Shaan Hossain
* Ioana Savu


## Contents
1. Functional Requirements  
    1.1 Must Haves  
    1.2 Should Haves    
    1.3 Could Haves     
    1.4 Won't Haves
2. Non-functional Requirements
# 1. Functional Requirements
For the game Air-Hockey the functionality and service requirements are grouped under the Functionality Requirements.    
Within these functional requirements four categories can be identified using the MoSCoW model for prioritizing requirements.
    
## 1.1 Must Haves : 
 * When the program starts the user is presented with an authentication window in which : 
    * The user is able to register new account with a unique username and password.
    * The user is able to type in his/her username and password to log in.
 * The usernames and passwords are stored in a database.
 * The passwords stored in the database are safely stored.
 * After authentication the user sees the main menu from which he/she can choose to : 
    * Start a new game
    * See the scoreboard
    * Exit the game
 * When the user starts a new game he/she is taken to a new game session.
 * The game shows a board with one puck and two paddles before the game starts.    
 * The game's board has a clear layout with two goals, one for each player.    
 * The game shall let the puck slide to one of the player's halves when the game starts.
 * The game's clock should start when the game starts.
 * The game's score should reset when the game starts.
 * The game shall randomly slide the puck into one of the players halves when the game starts.
 * The player shall be able to move his/her paddle in all directions.
 * The game shall not allow the player to move his/her paddle outside of the board.
 * The game shall not allow the puck to move outside of the board.
 * The game shall not allow for the puck and the paddle to overlap.
 * The player shall not be able to move his/her paddle beyond his/her half of the board.
 * The player shall be able to touch the puck with his/her paddle.  
 * The game shall move the puck in the appropriate direction after the paddle-puck collision.
 * The game shall move the puck in the appropriate direction after the collision of the paddle and the side of the board.
 * The game shall add one point to the score of the player when the puck crosses the goal line of the other player.
 * The game shall reset the paddles and the puck to the initial state after the goal.
 * The game shall slide the puck into the half of the player that lost a point.
 * The winner of the game is :
    * The player who scores eleven points before the other player does.
    * The player with more points when the clock stops.
* The game shall not allow any movement on the board after the winner was announced.
* The score, the duration of the game and the names of the players are stored in the database after the game.
* The program shall take the user back to the main menu after the game ends.
* When the user opens the scoreboard the program shall show ten finished games with the shortest duration 
  and the names of the players that played in those games.
* After the user presses the Exit button the program closes. 
