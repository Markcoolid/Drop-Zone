
public class DropZoneServer {
    public static void main(String[] args) {
        EZSocket socket = new EZSocket();
        socket.setPort(8080);
        socket.StartServer(2);

        while(socket.numberOfConnections() < 2){
            try {
                Thread.sleep(10);
            } 
            catch (Exception e) {
            } 
        }
        socket.setDataBoolean("GameStart", true);

     
    }


}
