package Adrenaline.Server.model.commands;

import Adrenaline.Server.model.Response;
import Adrenaline.Client.model.ResponseHandler;
import Adrenaline.Server.model.Board;

public class BoardResponse implements Response {
    public final Board variation;

    public BoardResponse(Board variation) { this.variation = variation; }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}