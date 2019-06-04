package Adrenaline.Server.model.commands;

import Adrenaline.Server.model.Response;
import Adrenaline.Client.model.Request;
import Adrenaline.Server.model.RequestHandler;

public class CreatePUDeckRequest implements Request {

    @Override
    public Response handle(RequestHandler handler) { return handler.handle(this);}
}
