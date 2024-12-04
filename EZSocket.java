public class EZSocket {
    
    private Boolean isServer = true;
    private String ip;
    private int port;
    private client Client = null;
    private server Server = null;

    public EZSocket(){
    }

    void setIpAddress(String ipAddress){
        ip = ipAddress;
    }

    void setPort(int port){
        this.port = port;
    }

    void StartClient(){
        isServer = false;
        Client = new client();
        Client.setAddress(ip);
        Client.setPort(port);
        Client.clientStart();
    }

    void StartServer(){
        isServer = true;
        Server = new server();
        Server.setPort(port);
        Server.serverStart();
    }

    void StartServer(int maxConnections){
        isServer = true;
        Server = new server(maxConnections);
        Server.setPort(port);
        Server.serverStart();
    }

    String getDataString(String varName) {
        if(isServer) {
            return Server.GetDataString(varName);
        }
        return Client.GetDataString(varName);
    }

    int getDataInt(String varName) {
        if(isServer) {
            return Server.GetDataInt(varName);
        }
        return Client.GetDataInt(varName);
    }

    double getDataDouble(String varName) {
        if(isServer) {
            return Server.GetDataDouble(varName);
        }
        return Client.GetDataDouble(varName);
    }

    Boolean getDataBoolean(String varName) {
        if(isServer) {
            return Server.GetDataBoolean(varName);
        }
        return Client.GetDataBoolean(varName);
    }

    void setDataString(String varName, String data) {
        if(isServer) {
            Server.setDataString(varName, data);
        } else {
            Client.setDataString(varName, data);
        }
    }

    void setDataInt(String varName, int data) {
        if(isServer) {
            Server.setDataInt(varName, data);
        } else {
            Client.setDataInt(varName, data);
        }
    }

    void setDataDouble(String varName, double data) {
        if(isServer) {
            Server.setDataDouble(varName, data);
        } else {
            Client.setDataDouble(varName, data);
        }
    }

    void setDataBoolean(String varName, boolean data) {
        if(isServer) {
            Server.setDataBoolean(varName, data);
        } else {
            Client.setDataBoolean(varName, data);
        }
    }

    int numberOfConnections(){
        return Server.numberOfConnections();
    }
}