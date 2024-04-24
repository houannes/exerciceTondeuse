import entities.Pelouse;
import entities.TondeuseRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseurTest {
    private Pelouse pelouse;
    private List<TondeuseRecord> listTondeuse = new ArrayList<>();

    @Test
    public void fichierAvecUneTondeuse_parser_pelouseEtTondeuse() throws FileNotFoundException {
        File file = new File("src/main/resources/testUneTondeuse.txt");
        creerPelouseEtTondeuse(file);
        Assertions.assertTrue(file.exists());
        Assertions.assertEquals(5, this.pelouse.getX());
        Assertions.assertEquals(5, this.pelouse.getY());
        Assertions.assertEquals("1 1 N AGGAAG", this.listTondeuse.get(0).toString());
        Assertions.assertEquals(1, this.listTondeuse.size());
    }

    @Test
    public void fichierAvecListeTondeuses_parser_pelouseEtDesTondeuses() throws FileNotFoundException {
        File file = new File("src/main/resources/testListeTondeuses.txt");
        creerPelouseEtDesTondeuses(file);
        Assertions.assertTrue(file.exists());
        Assertions.assertEquals(5, this.pelouse.getX());
        Assertions.assertEquals(5, this.pelouse.getY());
        Assertions.assertEquals(2, listTondeuse.size());
        Assertions.assertEquals("1 2 N GAGAGAGAA", this.listTondeuse.get(0).toString());
        Assertions.assertEquals("3 3 E AADAADADDA", this.listTondeuse.get(1).toString());
    }

    private void creerPelouseEtDesTondeuses(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        creerPelouse(scanner);
        scanner.nextLine();
        creerDesTondeuses(scanner);
    }

    private void creerPelouseEtTondeuse(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        creerPelouse(scanner);
        scanner.nextLine();
        String ligne = scanner.nextLine();
        creerTondeuse(scanner, ligne);
        scanner.close();
    }

    private void creerDesTondeuses(Scanner scanner) {
        for (String line; scanner.hasNextLine() && (line = scanner.nextLine()) != null; ) {
            creerTondeuse(scanner, line);
        }
    }

    private void creerTondeuse(Scanner scanner, String line) {
        String[] positionInitiale = line.trim().split(" ");
        int tondeuseX = Integer.parseInt(positionInitiale[0]);
        int tondeuseY = Integer.parseInt(positionInitiale[1]);
        String orientation = positionInitiale[2];
        String instructions = scanner.nextLine();
        TondeuseRecord tondeuseRecord = new TondeuseRecord(tondeuseX, tondeuseY, orientation, instructions);
        listTondeuse.add(tondeuseRecord);
    }

    private void creerPelouse(Scanner scanner) {
        int pelouseX = scanner.nextInt();
        int pelouseY = scanner.nextInt();
        this.pelouse = new Pelouse(pelouseX, pelouseY);
    }

}
