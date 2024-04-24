package entities;

public record TondeuseRecord(int x, int y, String orientation, String instructions) {
    public TondeuseRecord tournerADroite() {
        return switch (orientation) {
            case "N" -> new TondeuseRecord(x, y, "E", instructions);
            case "E" -> new TondeuseRecord(x, y, "S", instructions);
            case "S" -> new TondeuseRecord(x, y, "W", instructions);
            case "W" -> new TondeuseRecord(x, y, "N", instructions);
            default -> this;
        };
    }

    public TondeuseRecord tournerAGauche() {
        return switch (orientation) {
            case "N" -> new TondeuseRecord(x, y, "W", instructions);
            case "E" -> new TondeuseRecord(x, y, "N", instructions);
            case "S" -> new TondeuseRecord(x, y, "E", instructions);
            case "W" -> new TondeuseRecord(x, y, "S", instructions);
            default -> this;
        };
    }

    public TondeuseRecord avancer(int maxX, int maxY) {
        return switch (orientation) {
            case "N" -> (y < maxY) ? new TondeuseRecord(x, y + 1, orientation, instructions) : this;
            case "E" -> (x < maxX) ? new TondeuseRecord(x + 1, y, orientation, instructions) : this;
            case "S" -> (y > 0) ? new TondeuseRecord(x, y - 1, orientation, instructions) : this;
            case "W" -> (x > 0) ? new TondeuseRecord(x - 1, y, orientation, instructions) : this;
            default -> this;
        };
    }

    public TondeuseRecord executeInstructions(int maxX, int maxY) {
        TondeuseRecord tondeuseRecord = this;
        for (char commande : instructions.toCharArray()) {
            switch (commande) {
                case 'D' -> tondeuseRecord = tondeuseRecord.tournerADroite();
                case 'G' -> tondeuseRecord = tondeuseRecord.tournerAGauche();
                case 'A' -> {
                    TondeuseRecord tondeuseDeplacee = tondeuseRecord.avancer(maxX, maxY);
                    if (tondeuseDeplacee.equals(this)) {
                        System.out.println("La tondeuse est bloquée et n'a pas bougée");
                    }
                    tondeuseRecord = tondeuseDeplacee;
                }
            }
        }
        return tondeuseRecord;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation + " " + instructions;
    }
}
