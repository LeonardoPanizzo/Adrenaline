package Adrenaline.Server.model.commands;

import Adrenaline.Server.model.Response;
import Adrenaline.Client.model.ResponseHandler;
import Adrenaline.Server.model.PowerupDeck;

public class PUDeckResponse implements Response {

    public final PowerupDeck powerupDeck;

    public PUDeckResponse(PowerupDeck powerupDeck) { this.powerupDeck = powerupDeck; }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
