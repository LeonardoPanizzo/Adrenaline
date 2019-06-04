package Adrenaline.Server.model.commands;

import Adrenaline.Server.model.Response;
import Adrenaline.Client.model.ResponseHandler;
import Adrenaline.ClientContext;
import Adrenaline.Message;
import Adrenaline.Server.model.Board;

public class MessageNotification implements Response {
    public final Message message;

    public MessageNotification(Message message) {
        this.message = message;
    }

    public void handle(ResponseHandler handler) {
        Board board = ClientContext.get().getCurrentBoard();
        board.getVariation();
    }

}
