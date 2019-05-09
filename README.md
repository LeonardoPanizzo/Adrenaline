# ing-sw-2019-22

**Note utili:**
-
- Valori delle munizioni in AmmoCard: b -> munizioni blu; y -> munizioni gialle; r -> munizioni rosse.
- Valori di Player.number vanno da 0 (giocatore 1) a 4 (giocatore 5).
- I valori di Player.ammo rappresentano: ammo[0] -> valori delle munzioni blu; ammo[1] -> valori delle munizioni gialle; ammo[2] -> valori delle munizioni rosse.
- I valori in Position.room sono b blu, r rossa, y gialla, w bianca, v viola, g verde
- Nel momento dell'attacco la funzione chiamata avrà la struttura armaSpecifica.attack(attaccante, modalità1, modalità2, giocatoriColpiti, movimento, pagamento)
    dove
    attaccante è tipo Player ed è il giocatore che ha eseguito l'attacco
    modalità1 è l'effetto scelto fra le carte con due effetti distinti (es. falce protonica) di tipo int e può valere 1 o 0 dove
        0 è l'effetto base, che non ha costi
        1 è la modalità di fuoco alternativa
    modalità2 riguarda gli effetti opzionali (es. distruttore) e di tipo int[] perchè gli effetti opzionali possono essere usati in ordine arbitrario
        0 è l'effetto principale della carta
        1 è il primo effetto (quello a sinistra)
        2 il secondo
        ad esempio fucile al plasma puo ricevere [0,2,1] come [1,0,2]
        quindi questo array dovrà avere sempre dim>=1 èerchè nel caso volesse attivare solo l'effetto principale passerebbe un array con solo l'elemento 0
    giocatoriColpiti è una Collections di tutti i giocatori colpiti dal'effetto di una carta, se la carta colpisce i 
        giocatori con un ordine (es. torpedine) i giocatori dovranno essere in quell'ordine, se l'arma colpisce per 
        zona (es. vulcanizzatore) questo attributo può essere null
    movimento è una Collections dei movimenti fatti da un personaggio, ogni arma che concede dei movimenti permette di 
        muovere solo un personaggio (attaccante o attaccato) quindi un'unica Collections è sufficiente, le armi che 
        permettono di muovere più personaggi seguono movimenti standard quindi non è necessario passarli
    pagamento è un array di munizioni e sono le munizioni che il giocatore che utilizza l'arma intende usare per pagare
    questa funzione distribuisce i danni e i marchi ai vari giocatori, inoltre scarica l'arma vista che è stata usata
    Questa funzione restituisce un valore boolean che indica se l'attacco è andato a fine o no
-Nel momento in cui un giocatore vuole usare un powerup (ammesso che possa usarlo,solo due possono essere usati liberamente)
    chiamerà il metodo powerup.use(giocatore1, giocatore2, posizioni, munizione) dove
        giocatore1 è il giocatore che ha attivato il powerup
        giocatore2 è il giocatore che ne subisce gli effetti
        posizioni è un vettore di posizioni che conterrà le posizioni influenzate dal powerup
        munizione è un carattere di tipo char che indica che colore di munizione il giocatore vuole usare
    Questa funzione restituisce un valore boolean che indica se l'uso è andato a fine o no