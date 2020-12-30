module communicatorclient {
    requires gson;
    requires communicatorshared;
    requires javax.websocket.client.api;
    requires java.sql;
    exports communicatorclient;
}