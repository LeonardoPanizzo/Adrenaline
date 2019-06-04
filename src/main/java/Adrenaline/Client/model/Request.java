package Adrenaline.Client.model;

import Adrenaline.Server.model.Response;
import Adrenaline.Server.model.RequestHandler;

import java.io.Serializable;



public interface Request extends Serializable {
    Response handle(RequestHandler handler);
}
