package Adrenaline.Server.model;

import Adrenaline.Client.model.ResponseHandler;

import java.io.Serializable;



public interface
Response extends Serializable {
    void handle(ResponseHandler handler);
}
