package Adrenaline.Server.model.commands;

import Adrenaline.Client.model.Response;
import Adrenaline.Server.model.Request;
import Adrenaline.Server.model.RequestHandler;

public class CreatePUDeckRequest implements Request {

    @Override
    public Response handle(RequestHandler handler) { return handler.handle(this);}
}
