package Adrenaline.Client.control;

import Adrenaline.Client.model.Request;
import Adrenaline.Server.model.Board;
import Adrenaline.Server.model.Response;

public interface ControlTunnelClient {

    Response createBoard(Integer variation); // io faccio una request però mi aspetto una board come valore di ritorno

}
