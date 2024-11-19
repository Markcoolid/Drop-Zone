public class renderer {
    //lines array used for rendering, we print one line at a time
    private String[] lines = {"", "", "", "", "", "", "", "", "", "", ""};
    //object location
    private int ObjX;
    private int ObjY;
    //bounds
    private int upperbound;
    //play location
    private int playerX;
    //renderer definition
    public renderer(int UpperBound){
        upperbound = UpperBound;
    }
    //set upperbound to provided number
    public void setUpperbound(int up){
        upperbound = up;
    }
    
    
    //create the lines to display
    public void render(int ObjectX, int ObjectY, int PlayerX, boolean avoid) {
        ObjX = ObjectX;
        ObjY = ObjectY;
        playerX = PlayerX;
        //for all rows add a space to the left
        for(int i = 0; i<9; i++){
            lines[i] = "     |";
        }
        // add all the spaces before the obj add a space
        for(int i = 0; i<ObjX; i++){ 
            lines[ObjY] = lines[ObjY] + " ";  
        }
        //if the object is meant to be avoided 
        if(avoid){
            //change color to red store a # change color to white
            lines[ObjY] = lines[ObjY] + "\u001B[31m#\u001B[37m";
        }else{
            //change color to green store a # change color to white
            lines[ObjY] = lines[ObjY] + "\u001B[32m#\u001B[37m";

        }
        //until upperbound print spaces
        for(int i = 0; i<upperbound - ObjX ; i++){
            lines[ObjY] = lines[ObjY] + " ";
        }
        //close the gameboard
        lines[ObjY] = lines[ObjY] + "|";
        //close the gameboard for the row with the object
        for(int i = 0; i < 9; i++){
            if(i != ObjY){
                for(int j = 0; j <= upperbound; j++){
                lines[i] = lines[i] + " ";
                }
                lines[i] = lines[i] + "|";
            }
        }
    //make the space before the player line
    lines[9] = "     |";
    //move the player the correct amount of spaces to the left
        for(int i = 0; playerX > i; i++){
            lines[9] = lines[9] + " ";
        }
        //print the player
        lines[9] = lines[9] + "O";
        //finish the gameboard
        for(int i = 0; i < upperbound-playerX; i++){
            lines[9] = lines[9] + " ";
        }
        lines[9] = lines[9] + "|";

    }

    public void display(int score){
        //clear the console
        System.out.print("\033[H\033[2J");
        //print the gameboard from the array
        for(int i = 0; i < 10; i++){
            System.out.println(lines[i]);
        }
        //update the score
        System.out.println("Score: " + score);
    }

    public void gameStartAnim(int upperbound){
        //clear the console
        System.out.print("\033[H\033[2J");
        //for every row
        for(int n = 0; n < 9; n++){
        try {
            //wait .3 seconds
            Thread.sleep(300);
        } catch (Exception e) {
    
        }
        //gameboard left bound
        System.out.print("     |");
        for(int i = 0; i < upperbound+1; i++){
            System.out.print(" ");
        }
        //gameboard right bound
        System.out.println("|");
    }
    }

    public void gameOverText()
    {  
        //clear the console
        System.out.print("\033[H\033[2J");
        //print game over
          System.out.println("\r\n" + //
                            "\r\n" + //
                            " ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄       ▄▄  ▄▄▄▄▄▄▄▄▄▄▄       ▄▄▄▄▄▄▄▄▄▄▄  ▄               ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ \r\n" + //
                            "▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌     ▐░░▌▐░░░░░░░░░░░▌     ▐░░░░░░░░░░░▌▐░▌             ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\r\n" + //
                            "▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌   ▐░▐░▌▐░█▀▀▀▀▀▀▀▀▀      ▐░█▀▀▀▀▀▀▀█░▌ ▐░▌           ▐░▌ ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌\r\n" + //
                            "▐░▌          ▐░▌       ▐░▌▐░▌▐░▌ ▐░▌▐░▌▐░▌               ▐░▌       ▐░▌  ▐░▌         ▐░▌  ▐░▌          ▐░▌       ▐░▌\r\n" + //
                            "▐░▌ ▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌ ▐░▐░▌ ▐░▌▐░█▄▄▄▄▄▄▄▄▄      ▐░▌       ▐░▌   ▐░▌       ▐░▌   ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌\r\n" + //
                            "▐░▌▐░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌     ▐░▌       ▐░▌    ▐░▌     ▐░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\r\n" + //
                            "▐░▌ ▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░▌   ▀   ▐░▌▐░█▀▀▀▀▀▀▀▀▀      ▐░▌       ▐░▌     ▐░▌   ▐░▌     ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀█░█▀▀ \r\n" + //
                            "▐░▌       ▐░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░▌               ▐░▌       ▐░▌      ▐░▌ ▐░▌      ▐░▌          ▐░▌     ▐░▌  \r\n" + //
                            "▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄      ▐░█▄▄▄▄▄▄▄█░▌       ▐░▐░▌       ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌      ▐░▌ \r\n" + //
                            "▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌     ▐░░░░░░░░░░░▌        ▐░▌        ▐░░░░░░░░░░░▌▐░▌       ▐░▌\r\n" + //
                            " ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀       ▀▀▀▀▀▀▀▀▀▀▀          ▀          ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀ \r\n" + //
                            "                                                                                                                   \r\n" + //
                            "\r\n" + //
                            "");
                            
    }

    public void restart(int timer){
        try {
            //wait a second
            Thread.sleep(1000);
          } catch (Exception e) {

          }
        // print game over
        gameOverText();
        System.out.println("Press Space to play again");
        //count down
        System.out.println(timer);


    }

    public String toString(){
        //print instructions
        return "The renderer function uses print to draw the game board to the screen\n                              Drop Zone\n                             By Mcoolid\n             This is a needlessly complicated terminal game\nBlocks will fall toward you, use the arrow keys to dodge the red ones\n                   You must hit the green ones\nIf at any point input stops working click on the java icon in the taskbar\n                           Best of luck\n                        Hit Space to Start";
        
    }
}
