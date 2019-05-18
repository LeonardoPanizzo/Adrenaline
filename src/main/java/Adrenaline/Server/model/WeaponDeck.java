package Adrenaline.Server.model;

import java.util.Collections;
import java.util.Vector;

public class WeaponDeck {
    private int size;
    private Vector<WeaponCard> weaponDeck;

    public WeaponDeck(){
        size=0;
        weaponDeck=new Vector<WeaponCard>(21);
        weaponDeck.add(new WCCyberblade());
        weaponDeck.add(new WCElectroscythe());
        weaponDeck.add(new WCFlamethrower());
        weaponDeck.add(new WCFurnace());
        weaponDeck.add(new WCGrenadeLauncher());
        weaponDeck.add(new WCHeatseeker());
        weaponDeck.add(new WCHellion());
        weaponDeck.add(new WCLockRifle());
        weaponDeck.add(new WCMachineGun());
        weaponDeck.add(new WCPlasmaGun());
        weaponDeck.add(new WCPowerGlove());
        weaponDeck.add(new WCRailgun());
        weaponDeck.add(new WCRocketLauncher());
        weaponDeck.add(new WCShockwave());
        weaponDeck.add(new WCShotgun());
        weaponDeck.add(new WCSledgehammer());
        weaponDeck.add(new WCTHOR());
        weaponDeck.add(new WCTractorBeam());
        weaponDeck.add(new WCVortexcannon());
        weaponDeck.add(new WCWhisper());
        weaponDeck.add(new WCZX2());
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(weaponDeck);
    }

    public WeaponCard pickUpWeapon(){
        WeaponCard x=weaponDeck.elementAt(size);
        size++;
        if(size>=21) {       //se size raggiunge la dimensione del vettore lo azzero, sposto il punatore al posto di spostare gli elementi dentro vector, bisognerà controllare il metodo capacity
            size = 0;
            shuffle();
        }
        return x;
    }
}