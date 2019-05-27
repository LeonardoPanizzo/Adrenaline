package Adrenaline.Server.model;

import Adrenaline.Client.model.Response;
import Adrenaline.Server.model.commands.CreateBoardRequest;
import Adrenaline.Server.model.commands.CreatePUDeckRequest;


public interface RequestHandler {
    Response handle(CreateBoardRequest request);
    Response handle(CreatePUDeckRequest request);
}
