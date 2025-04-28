import java.io.*;
import java.util.ArrayList;

public class Report {

    public static void main(String[] args) {
        ArrayList<Record> dati = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("dati.csv"))) {
            String riga = reader.readLine(); // Salta intestazione
            while ((riga = reader.readLine()) != null) {
                String[] colonne = riga.split(";");
                int anno = Integer.parseInt(colonne[0]);
                String regione = colonne[1];
                double percentuale = Double.parseDouble(colonne[2]);
                dati.add(new Record(anno, regione, percentuale));
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.csv"))) {
            writer.write("Anno;Regione;Percentuale diffusione PC");
            writer.newLine();
            for (Record record : dati) {
                writer.write(record.getAnno() + ";" + record.getRegione() + ";" + record.getPercentuale());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }

        System.out.println("Report creato con successo!");
    }
}

