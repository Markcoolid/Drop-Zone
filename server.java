import java.io.*;
import java.net.*;
import java.util.*;

class server implements Runnable {

    private int port;
    private ServerSocket server = null;
    private List<Socket> clients = Collections.synchronizedList(new ArrayList<>());
    private Map<String, String> dataMap = new HashMap<>();
    private int maxConnections = 1;
    private int numOfConnections = 0;

    public server() {
    }

    public server(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    void serverStart() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            System.out.println("EZSocket ver" + EZSocketInfo.versionNumber + " Server Started on port " + port);

            while (true) {
                Socket clientSocket = server.accept();
                clients.add(clientSocket);
                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (IOException e) {
            System.err.print("UhOh Something went wrong running EZSocket Version " + EZSocketInfo.versionNumber);
            System.err.print(e);
        }
    }

    void setPort(int port) {
        this.port = port;
    }

    private void handleClient(Socket clientSocket) {
        numOfConnections++;
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            String fromClient = "";
            while (!fromClient.equals("EZSocketClientCloseConnection")) {
                fromClient = in.readUTF();
                processClientData(fromClient);
                out.writeUTF(compileData());
                out.flush();
            }

            clients.remove(clientSocket);
            clientSocket.close();

        } catch (IOException e) {
            System.err.print("UhOh Something went wrong running EZSocket Version " + EZSocketInfo.versionNumber);
            System.err.print(e);
        }
    }

    private void processClientData(String fromClient) {
        String[] dataPairs = fromClient.split("\\|EZS\\|");
        for (String pair : dataPairs) {
            if (!pair.isEmpty()) {
                String[] keyValue = pair.split("=", 2);
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    if (dataMap.containsKey(key) && !dataMap.get(key).equals(value)) {
                        System.err.println("Conflict for " + key + ": " + dataMap.get(key) + " vs " + value);
                    } else {
                        dataMap.put(key, value);
                    }
                }
            }
        }
    }

    private String compileData() {
        StringBuilder compiledData = new StringBuilder();
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            compiledData.append(entry.getKey()).append("=").append(entry.getValue()).append("|EZS|");
        }
        return compiledData.toString();
    }

    String GetDataString(String varName) {
        return dataMap.getOrDefault(varName, "");
    }

    int GetDataInt(String varName) {
        return Integer.parseInt(dataMap.getOrDefault(varName, "-1"));
    }

    double GetDataDouble(String varName) {
        return Double.parseDouble(dataMap.getOrDefault(varName, "-1.0"));
    }

    Boolean GetDataBoolean(String varName) {
        return Integer.parseInt(dataMap.getOrDefault(varName, "0")) != 0;
    }

    void setDataString(String varName, String data) {
        dataMap.put(varName, data);
    }

    void setDataInt(String varName, int data) {
        dataMap.put(varName, String.valueOf(data));
    }

    void setDataDouble(String varName, double data) {
        dataMap.put(varName, String.valueOf(data));
    }

    void setDataBoolean(String varName, boolean data) {
        dataMap.put(varName, data ? "1" : "0");
    }

    int numberOfConnections(){
        return numOfConnections;
    }
}