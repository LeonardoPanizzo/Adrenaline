package Adrenaline.Client.model;

import java.io.Serializable;



public interface
Response extends Serializable {
    void handle(ResponseHandler handler);
}
