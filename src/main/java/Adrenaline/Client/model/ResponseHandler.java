package Adrenaline.Client.model;

import Adrenaline.Server.model.commands.BoardResponse;
import Adrenaline.Server.model.commands.PUDeckResponse;



public interface ResponseHandler {
    void handle(BoardResponse boardResponse);
    void handle(PUDeckResponse puDeckResponse);
}
