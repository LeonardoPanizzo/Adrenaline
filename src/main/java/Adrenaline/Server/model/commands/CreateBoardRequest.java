package Adrenaline.Server.model.commands;

import Adrenaline.Server.model.Response;
import Adrenaline.Client.model.Request;
import Adrenaline.Server.model.RequestHandler;

public class CreateBoardRequest implements Request {

    public final Integer variation;


    public CreateBoardRequest(Integer variation) {
        this.variation = variation;
    }

    @Override
    public Response handle(RequestHandler handler) { return handler.handle(this);}
}
