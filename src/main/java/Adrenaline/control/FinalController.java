package Adrenaline.control;

import Adrenaline.view.View;

public abstract class FinalController {


    abstract void createBoard(Integer boardNumber);

    abstract void sendMessage(String message); //from TextView to Controller

    abstract String getMessage(View view); //from Controller to TextView

    abstract void registerClient(View client);

    abstract Object[] createDecks();


}
