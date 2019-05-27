package Adrenaline.Server.model;

import Adrenaline.Client.model.Response;

import java.io.Serializable;



public interface Request extends Serializable {
    Response handle(RequestHandler handler);
}
