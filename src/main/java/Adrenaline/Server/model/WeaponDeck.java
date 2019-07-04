package Adrenaline.Server.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

public class WeaponDeck implements Serializable {
    private int size;
    private Vector<WeaponCard> weaponDeck;

    /**
     * WeaponDeck constructor. All card are created (one copy of each card) and then they are shuffled.
     */
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

    /**
     * Method to shuffle the Weapon Deck
     */
    public void shuffle(){
        Collections.shuffle(weaponDeck);
    }

    /**
     * When a weapon card is drown, the size is increased. When all weapons are finished, no others can be drown.
     *
     * @return
     */
    public WeaponCard pickUpWeapon(){
        WeaponCard x=weaponDeck.elementAt(size);
        size++;
        if(size>=21) {
            x=null;
        }
        return x;
    }
}