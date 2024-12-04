import java.io.*;
import java.net.*;

public class client implements Runnable {

    private String address;
    private int port;
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private String fromServer = "";
    private String toServer = "";

    public client() {
        
    }

    void clientStart(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to server at " + address + " on port " + port);

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            while (!fromServer.equals("EZSocketServerCloseConnection")) {
                // Send the data to the server
                out.writeUTF(toServer);
                out.flush();

                // Read the response from the server
                fromServer = in.readUTF();
            }

            socket.close();
            in.close();
            out.close();

        } catch (IOException e) {
            System.err.print("UhOh Something went wrong running EZSocket Version " + EZSocketInfo.versionNumber);
            System.err.print(e);
        }
    }

    void setAddress(String address) {
        this.address = address;
    }

    void setPort(int port) {
        this.port = port;
    }

    String GetDataString(String varName) {
        int startIndex = fromServer.indexOf(varName) + varName.length() + 1;
        int endIndex = fromServer.indexOf("|EZS|", startIndex);
        if (startIndex >= varName.length() && endIndex > startIndex) {
            return fromServer.substring(startIndex, endIndex);
        } else {
            return ""; // or any default value
        }
    }
    
    int GetDataInt(String varName) {
        int startIndex = fromServer.indexOf(varName) + varName.length() + 1;
        int endIndex = fromServer.indexOf("|EZS|", startIndex);
        if (startIndex >= varName.length() && endIndex > startIndex) {
            return Integer.parseInt(fromServer.substring(startIndex, endIndex));
        } else {
            return -1; // or any default value
        }
    }
    
    double GetDataDouble(String varName) {
        int startIndex = fromServer.indexOf(varName) + varName.length() + 1;
        int endIndex = fromServer.indexOf("|EZS|", startIndex);
        if (startIndex >= varName.length() && endIndex > startIndex) {
            return Double.parseDouble(fromServer.substring(startIndex, endIndex));
        } else {
            return -1.0; // or any default value
        }
    }
    
    Boolean GetDataBoolean(String varName) {
        int startIndex = fromServer.indexOf(varName) + varName.length() + 1;
        int endIndex = fromServer.indexOf("|EZS|", startIndex);
        if (startIndex >= varName.length() && endIndex > startIndex) {
            return Integer.parseInt(fromServer.substring(startIndex, endIndex)) != 0;
        } else {
            return false; // or any default value
        }
    }

    void setDataString(String varName, String data) {
        toServer = setData(toServer, varName, data);
    }

    void setDataInt(String varName, int data) {
        toServer = setData(toServer, varName, String.valueOf(data));
    }

    void setDataDouble(String varName, double data) {
        toServer = setData(toServer, varName, String.valueOf(data));
    }

    void setDataBoolean(String varName, boolean data) {
        toServer = setData(toServer, varName, data ? "1" : "0");
    }

    private String setData(String original, String varName, String data) {
        String newVar = varName + "=" + data + "|EZS|";
        if (original.contains(varName)) {
            int start = original.indexOf(varName);
            int end = original.indexOf("|EZS|", start) + 5;
            return original.substring(0, start) + newVar + original.substring(end);
        } else {
            return original + newVar;
        }
    }
}