# ing-sw-2019-22

# **Parti Implementate**

**Interfaccia Utente**
-
Comandi di gioco gestiti tramite linea di comando.
//aggiungere se riusciamo ad inserire la GUI//

**Connettività**
-
La connessione al server avviene tramite RMI.

**Regole di Gioco**
-
Sono state implementate le regole di gioco compete, ovvero considerando sia la versione completa degli effetti delle
armi, sia implementando la frenesia finale.

**Funzioni avanzate**
-
//Da scrivere se ci siamo riusciti//

**Come Lanciare Server e Client**
- 
//descrizione di come effettuare il lancio//



**Note utili:**
-
- Valori delle munizioni in AmmoCard: b -> munizioni blu; y -> munizioni gialle; r -> munizioni rosse.
- Valori di Player.number vanno da 0 (giocatore 1) a 4 (giocatore 5).
- I valori di Player.ammo rappresentano: ammo[0] -> valori delle munzioni blu; ammo[1] -> valori delle munizioni gialle; ammo[2] -> valori delle munizioni rosse.
- I valori in Position.room sono b blu, r rossa, y gialla, w bianca, v viola, g verde
- Nel momento dell'attacco la funzione chiamata avrà la struttura armaSpecifica.attack(attaccante, modalità1, modalità2, giocatoriColpiti, movimento, pagamento)
    dove
    <p>attaccante è tipo Player ed è il giocatore che ha eseguito l'attacco</p>
    <p>modalità1 è l'effetto scelto fra le carte con due effetti distinti (es. falce protonica) di tipo int e può valere 1 o 0 dove</p>
        <p>- 0 è l'effetto base, che non ha costi</p>
        <p>- 1 è la modalità di fuoco alternativa</p>
    <p>modalità2 riguarda gli effetti opzionali (es. distruttore) e di tipo int[] perchè gli effetti opzionali possono essere usati in ordine arbitrario</p>
        <p>- 0 è l'effetto principale della carta</p>
        <p>- 1 è il primo effetto (quello a sinistra)</p>
        <p>- 2 il secondo</p>
        ad esempio fucile al plasma puo ricevere [0,2,1] come [1,0,2];
        quindi questo array dovrà avere sempre dim>=1 perchè nel caso volesse attivare solo l'effetto principale passerebbe un array con solo l'elemento 0
    <p>giocatoriColpiti è una Collections di tutti i giocatori colpiti dal'effetto di una carta, se la carta colpisce i 
        giocatori con un ordine (es. torpedine) i giocatori dovranno essere in quell'ordine, se l'arma colpisce per 
        zona (es. vulcanizzatore) questo attributo può essere null</p>
    <p>movimento è una Collections dei movimenti fatti da un personaggio, ogni arma che concede dei movimenti permette di 
        muovere solo un personaggio (attaccante o attaccato) quindi un'unica Collections è sufficiente, le armi che 
        permettono di muovere più personaggi seguono movimenti standard quindi non è necessario passarli</p>
    <p>pagamento è un array di munizioni e sono le munizioni che il giocatore che utilizza l'arma intende usare per pagare
    questa funzione distribuisce i danni e i marchi ai vari giocatori, inoltre scarica l'arma vista che è stata usata
    Questa funzione restituisce un valore boolean che indica se l'attacco è andato a fine o no</p>
- Nel momento in cui un giocatore vuole usare un powerup (ammesso che possa usarlo,solo due possono essere usati liberamente)
    chiamerà il metodo powerup.use(giocatore1, giocatore2, posizioni, munizione) dove
        <p>giocatore1 è il giocatore che ha attivato il powerup</p>
        <p>giocatore2 è il giocatore che ne subisce gli effetti</p>
        <p>posizioni è un vettore di posizioni che conterrà le posizioni influenzate dal powerup</p>
        <p>munizione è un carattere di tipo char che indica che colore di munizione il giocatore vuole usare</p>
    Questa funzione restituisce un valore boolean che indica se l'uso è andato a fine o no