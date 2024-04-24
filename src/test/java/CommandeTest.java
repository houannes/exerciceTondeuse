import entities.Pelouse;
import entities.TondeuseRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandeTest {

    private String positionsFinales;
    private Pelouse pelouse;
    private List<TondeuseRecord> tondeusesList = new ArrayList<>();

    @Test
    public void executerCommande_positionFinaleDesTondeuse() throws FileNotFoundException {
        File file = new File("src/main/resources/testCommande.txt");
        this.positionsFinales =  executerCommandes(file);
        Assertions.assertEquals(5, this.pelouse.getX());
        Assertions.assertEquals(5, this.pelouse.getY());
        Assertions.assertEquals(2, this.tondeusesList.size());
        Assertions.assertEquals("1 3 N\n5 1 E", this.positionsFinales.trim());
    }

    private String executerCommandes(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        creerPelouse(scanner);
        scanner.nextLine();
        creerDesTondeuses(scanner);
        scanner.close();
        return retournerResultatFinal(tondeusesList);
    }

    private void creerPelouse(Scanner scanner) {
        int pelouseX = scanner.nextInt();
        int pelouseY = scanner.nextInt();
        this.pelouse = new Pelouse(pelouseX, pelouseY);
    }

    private void creerDesTondeuses(Scanner scanner) {
        for (String line; scanner.hasNextLine() && (line = scanner.nextLine()) != null; ) {
            String[] positionInitiale = line.trim().split(" ");
            int tondeuseX = Integer.parseInt(positionInitiale[0]);
            int tondeuseY = Integer.parseInt(positionInitiale[1]);
            String orientation = positionInitiale[2];
            String instructions = scanner.nextLine();
            TondeuseRecord tondeuseRecord = new TondeuseRecord(tondeuseX, tondeuseY, orientation, instructions);
            tondeuseRecord = tondeuseRecord.executeInstructions(this.pelouse.getX(), this.pelouse.getY());
            tondeusesList.add(tondeuseRecord);
        }
    }

    private String retournerResultatFinal(List<TondeuseRecord> tondeusesList) {
        StringBuilder result = new StringBuilder();
        for (TondeuseRecord tondeuseRecord : tondeusesList) {
            result.append(tondeuseRecord.x()).append(" ").append(tondeuseRecord.y()).append(" ")
                    .append(tondeuseRecord.orientation()).append("\n");
        }
        return result.toString();
    }

}
