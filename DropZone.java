//Drop Zone
//Mcoolid
//Made for AP CSA October 2024

//This project is on github
//https://github.com/Markcoolid/Drop-Zone

class DropZone {   
static int playerX = 5;
static boolean frameIsDifferent = true;
static int upperbound = 25;
static int start = 0 ;
public static void main(String[] args) {

    new ArrowKeys(); //start the arrow keys script

    renderer Renderer = new renderer(upperbound); //make a new renderer
    try {
        //print instructions
        Renderer.instructions(); 
        //until the start is pressed 
        while(start != 1){
            Thread.sleep((long)60/1000);
        }
        //while start
    while(start == 1){
    //new gamelogic
    gameLogic GameLogic = new gameLogic(upperbound);
    //start game anim
    Renderer.gameStartAnim(upperbound);
    //pass renderer and gamelogic to the game method 
    game(Renderer, GameLogic);
    //reset start
    start = 0;
    //timer for restart
    int restarttimer = 10;
    //while start not repressed
    while(start != 1){
        //render restart screen
        Renderer.restart(restarttimer);
        //wait a second
        Thread.sleep(1000);
        //count down
        restarttimer--;
        //when timer is 0
        if(restarttimer == 0){
            //clear console
            System.out.print("\033[H\033[2J");
            //close arrowkeys window
            ArrowKeys.closewindow();
            //exit
            return; 
        }

    }
    }

} catch (Exception e) {
    System.out.println("An error occured, yell at Mark or something.");
}
}

public static void setX(int dif){
    //for the arrowkeys script to interact with 
    //space sets start
     if(dif == 0){
        start = 1;
    }else if(start == 1){ 
        //move player left or right
        playerX = playerX + dif;
        //check bounds
        if(playerX > upperbound) 
            playerX = upperbound;
        else if(playerX < 0)
            playerX = 0;

        frameIsDifferent = true;
    }
}




public static void game(renderer Renderer, gameLogic GameLogic){
    //get vars from game logic to pass to the renderer
    boolean avoid = GameLogic.getAvoid();
    int ObjY = 0;
    int ObjX = (int) (Math.random() * 10);
    GameLogic.setObjX(ObjX);
    boolean gameOver = false;
    int score = 0;
    //whiel the game is running
    while(!gameOver){
        //if the frame is unique 
        if(frameIsDifferent){ 
            //render the array
            Renderer.render(ObjX, ObjY, playerX, avoid);
            //display the gameboard
            Renderer.display(score);
        }
        //pass the game state into gamelogic
        GameLogic.gameState(playerX);
        //tick the gamelogic once
        GameLogic.Tick();
        //get updated vars from gamelogic
        ObjX = GameLogic.getObjX();
        ObjY = GameLogic.getObjY();
        playerX = GameLogic.getPlayerX();
        gameOver = GameLogic.getGameOver();
        frameIsDifferent = GameLogic.getFrameState();
        score = GameLogic.getScore();
        upperbound = GameLogic.getUpperBound();
        avoid = GameLogic.getAvoid();
        Renderer.setUpperbound(upperbound);
        try {
        Thread.sleep((long) 1000/60);
  
        } catch (Exception e) {
        }
    }
    //reset gameboard when game ends
    upperbound = 25;
    GameLogic.setUpperBound(upperbound);
}

}
