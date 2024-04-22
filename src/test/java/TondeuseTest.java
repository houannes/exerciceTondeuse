import entities.Pelouse;
import entities.Tondeuse;
import entities.TondeuseRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TondeuseTest {

    private String instructions = "ADG";

    @Test
    public void taillePelouse_creerPelouse_pelouse() {
        String taillePelouse = "6 6";
        Pelouse pelouse = creerPelouse(taillePelouse);
        Assertions.assertEquals(pelouse.getX(), 6);
        Assertions.assertEquals(pelouse.getY(), 6);
    }

    @Test
    public void taillePelouse_isPositionValide() {
        Pelouse pelouse = new Pelouse(5, 5);
        Assertions.assertTrue(pelouse.isValidPosition(5, 5));
        Assertions.assertFalse(pelouse.isValidPosition(6, 3));
        Assertions.assertFalse(pelouse.isValidPosition(3, -1));
    }

    @Test
    public void positionTondeuse_creerTondeuse_tondeuse() {
        String positionInitiale = "1 2 N";
        String commandes = "AGGGAA";
        Tondeuse tondeuse = creerTondeuse(positionInitiale, commandes);
        Assertions.assertEquals(tondeuse.getX(), 1);
        Assertions.assertEquals(tondeuse.getY(), 2);
        Assertions.assertEquals(tondeuse.getOrientation(), "N");
        Assertions.assertEquals("AGGGAA", tondeuse.getInstructions());
    }

    @Test
    public void avancerTondeuse_PositionFinale() {
        TondeuseRecord tondeuseRecord = new TondeuseRecord(1, 1, "N", instructions);
        tondeuseRecord = tondeuseRecord.avancer(5, 5);
        Assertions.assertEquals("1 2 N", tondeuseRecord.toString());
    }

    @Test
    public void tournerADroiteTondeuse_isPositionFinale() {
        String positionFinale = "1 1 E";
        TondeuseRecord tondeuseRecord = new TondeuseRecord(1, 1, "N", instructions);
        tondeuseRecord = tondeuseRecord.tournerADroite();
        Assertions.assertEquals(positionFinale, tondeuseRecord.toString());
    }

    @Test
    public void tournerAGaucheTondeuse_isPositionFinale() {
        TondeuseRecord tondeuseRecord = new TondeuseRecord(1, 1, "N", instructions);
        tondeuseRecord = tondeuseRecord.tournerAGauche();
        Assertions.assertEquals("1 1 W", tondeuseRecord.toString());
    }

    private Pelouse creerPelouse(String taillePelouse) {
        String[] coordonnes = taillePelouse.split(" ");
        return new Pelouse(Integer.parseInt(coordonnes[0]),
                Integer.parseInt(coordonnes[1]));
    }

    private Tondeuse creerTondeuse(String positionInitiale, String commandes) {
        String[] coordonnes = positionInitiale.split(" ");
        return new Tondeuse(Integer.parseInt(coordonnes[0]),
                Integer.parseInt(coordonnes[1]), coordonnes[2], commandes);
    }

}
