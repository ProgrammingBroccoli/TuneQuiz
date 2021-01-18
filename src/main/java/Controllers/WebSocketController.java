package Controllers;

import Data.LobbyData;
import Models.Player;
import Models.PlayerWrapper;
import com.google.gson.Gson;
import communicatorclient.Communicator;
import communicatorclient.CommunicatorClientWebSocket;
import communicatorclient.CommunicatorMessage;

import java.util.Observable;
import java.util.Observer;

public class WebSocketController implements Observer {
    private static WebSocketController instance;
    private static LobbyController _lobbyController;
    private LobbyData lobbyData;
    public Communicator communicator = null;
    Gson gson = new Gson();

    private WebSocketController(){
        lobbyData = LobbyData.getInstance();
        communicator = CommunicatorClientWebSocket.getInstance();
        communicator.addObserver(this);
        _lobbyController = null;

    }

    public static WebSocketController getInstance(){
        if(instance == null){
            System.out.print("INSTANCE HAS BEEN INITIATED");
            instance = new WebSocketController();
        }
        return instance;
    }
    public static WebSocketController getInstance(LobbyController lobbyController){
        if(instance == null){
            System.out.print("INSTANCE HAS BEEN INITIATED");
            instance = new WebSocketController();
        }
        else if(lobbyController == null){
            _lobbyController = lobbyController;
        }
        return instance;
    }

    private final String[] properties = {
            "register",
            "test",
            "login",
            "joinLobby",
            "showLobby",
            "updatePack",
            "gameStateUpdate",
            "getTunes",
            "rightAnswer",
            "pointsUpdate"
    };

    public void ConnectToServer(Player player){
        PlayerWrapper wrapper = new PlayerWrapper(properties[0], player);
        communicator.start();

        CommunicatorMessage message = new CommunicatorMessage();
        message.setProperty(properties[0]);
        message.setContent(gson.toJson(wrapper, PlayerWrapper.class));
        registerAndSubscribeAll();

        communicator.update(message);
        System.out.println("CONNECTED");
    }

    private void registerAndSubscribeAll() {
        communicator.register(properties[0]);
        communicator.subscribe(properties[0]);
//        communicator.register(properties[1]);
//        communicator.subscribe(properties[1]);
//        communicator.register(properties[2]);
//        communicator.subscribe(properties[2]);
        communicator.register(properties[3]);
        communicator.subscribe(properties[3]);
        communicator.register(properties[4]);
        communicator.subscribe(properties[4]);
        communicator.register(properties[5]);
        communicator.subscribe(properties[5]);
        communicator.register(properties[6]);
        communicator.subscribe(properties[6]);
        communicator.register(properties[7]);
        communicator.subscribe(properties[7]);
        communicator.register(properties[8]);
        communicator.subscribe(properties[8]);
        communicator.register(properties[9]);
        communicator.subscribe(properties[9]);
    }

    public void sendMessage(String property, String Content ){
//        communicator = CommunicatorClientWebSocket.getInstance();
//        communicator.addObserver(this);
//        communicator.start();
        CommunicatorMessage message = new CommunicatorMessage();
        message.setProperty(property);
        message.setContent(Content);
        communicator.update(message);
    }


    @Override
    public void update(Observable o, Object arg) {
       //System.shared.out.println(arg);
        CommunicatorMessage message = (CommunicatorMessage) arg;
        String property = message.getProperty();
        String jsoncontent = message.getContent();

//       if (property.equals(properties[4])){
////           LobbyWrapper wrapper = gson.fromJson(jsoncontent, LobbyWrapper.class);
////           lobbyData.setPlayers(wrapper.getPlayers());
////           for (Player player : wrapper.getPlayers()) {
////               System.shared.out.println(player.getName());
//           }
//       }else if (property.equals(properties[5])){
//            PackWrapper wrapper = gson.fromJson(jsoncontent, PackWrapper.class);
//            lobbyData.setPack(wrapper.getSelectedPack());
//           System.shared.out.println("point");
//       }
//
   }
}
