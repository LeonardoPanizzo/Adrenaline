package Adrenaline.Server.model;

/**
 * basic mode: Choose a cardinal direction and 1 target in that direction.
 * Deal 3 damage to it.
 * in piercing mode: Choose a cardinal direction and 1 or 2 targets in that
 * direction. Deal 2 damage to each.
 * Notes: Basically, you're shooting in a straight line and ignoring walls.
 * You don't have to pick a target on the other side of a wall – it could even
 * be someone on your own square – but shooting through walls sure is
 * fun. There are only 4 cardinal directions. You imagine facing one wall or
 * door, square-on, and firing in that direction. Anyone on a square in that
 * direction (including yours) is a valid target. In piercing mode,
 * the 2 targets can be on the same square or on different squares.
 */

public class WCRailgun extends WeaponCard{

    public WCRailgun(){
        super("Railgun", new char[]{'y','y','b'}, new char[]{});
    }

    @Override
    /*
    to get the position in which the attacker shoot is checked the position of the attacked
    onedirectionx and onedirectiony is used to check of the attacker and the attacked are in the same "cardinal line"
     */
    public boolean attack(Player attacker, int mode1, int[] mode2, Player[] attackedPlayers, Position[] movements, PowerupCard[] payment) {
        boolean done = false;
        if(isLoaded() && attackedPlayers.length>=1 && attackedPlayers.length<=2){
            boolean onedirectionx=attacker.getPosition().getCoordinate()[0]==attackedPlayers[0].getPosition().getCoordinate()[0];
            boolean onedirectiony=attacker.getPosition().getCoordinate()[1]==attackedPlayers[0].getPosition().getCoordinate()[1];
            if(mode1==0 && (onedirectionx || onedirectiony)){
                for(int i=0; i<3; i++)
                    attackedPlayers[0].receivedDamages(attacker);
                loaded=false;
                done=true;
            }else if(mode1==1 && (onedirectionx || onedirectiony)){
                if(attackedPlayers.length==1){
                    for(int i=0; i<2; i++)
                        attackedPlayers[0].receivedDamages(attacker);
                    loaded=false;
                    done=true;
                }else if(attackedPlayers.length==2){
                    onedirectionx=onedirectionx && attackedPlayers[0].getPosition().getCoordinate()[0]==attackedPlayers[1].getPosition().getCoordinate()[0];
                    onedirectiony=onedirectiony && attackedPlayers[0].getPosition().getCoordinate()[1]==attackedPlayers[1].getPosition().getCoordinate()[1];
                    if(onedirectionx || onedirectiony){
                        for(int i=0; i<2; i++){
                            attackedPlayers[0].receivedDamages(attacker);
                            attackedPlayers[1].receivedDamages(attacker);
                        }
                        loaded=false;
                        done=true;
                    }
                }

            }
        }
        return done;
    }
}
