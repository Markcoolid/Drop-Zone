public class gameLogic {
    //all data for GameLogic
    private int ObjX;
    private int ObjY;
    private int upperbound;
    private int playerX;
    private int frames = 0;
    private int score;
    private int speed = 25;
    private boolean gameOver = false;
    private boolean frameIsDifferent = true;
    private int boundChanger = 0;
    private boolean avoid = true;
    //game logic defintion
    public gameLogic(int Upperbound){
        upperbound = Upperbound;
    }
    //get gamestate gets the player location
    public void gameState(int PlayerX){
        playerX = PlayerX;

    }

    //gets/sets

    public int getObjX(){
        return ObjX;
    }
    
    public int getObjY(){
        return ObjY;
    }
    
    public int getPlayerX(){
        return playerX;
    }
    public boolean getGameOver(){
        return gameOver;
    }
    public boolean getFrameState(){
        return frameIsDifferent;
    }
    public int getScore(){
        return score;
    }
    public int getUpperBound(){
        return upperbound;
    }
    public void setUpperBound(int up){
        upperbound = up;
    }

    public boolean getAvoid(){
        return avoid;
    }

    public void setObjX(int objXi){
        ObjX = objXi;
    }

    //ticks the game forward
    public void Tick(){
        //count frames
        frames++;
        //if frames is equal to the speed of the falling object
        if(frames==speed){
            //fall
            ObjY++;
            //change in frame
            frameIsDifferent = true;
            //reset counter
            frames = 0;
            }
            else{
                //else frame is the same
                frameIsDifferent = false;
            }
            //if object hits the bottom
            if(ObjY==9){
                //shrink the bound to increase difficulty
                if(boundChanger == 5  && upperbound > 5){
                    //move player with bound
                    if(playerX >= upperbound){
                        playerX--;
                    }
                    upperbound--;
                    boundChanger = 0;
                    
                }
                boundChanger++;
                score++;
                // increase speed
                if(speed > 3){
                speed--;
                }
                else{
                    //if speed is below 3 slow it down
                    if(upperbound > 5){
                    speed = 10;
                    }else{
                        speed = 5;
                    }
                }
                //if an object meant to be avoided hits the player they lose, if an object meant to be hit is missed they lose
                if((ObjX == playerX && avoid) || (ObjX != playerX && !avoid)){
                    gameOver = true;
                }
                //reset object
                ObjY = 0;
                ObjX = (int)(Math.random() * (upperbound + 3));
                
                //30 percent chance after upperbound is 15 that you have to hit a green box
                if(Math.random() > .7 && upperbound < 15){
                    speed = 15;
                    avoid = false;
                }else{

                    avoid = true;
                }
                //if object is out of bounds set to above the player
                if(ObjX > upperbound){
                    ObjX = playerX  ;
                }

            }

            
    }
}   
