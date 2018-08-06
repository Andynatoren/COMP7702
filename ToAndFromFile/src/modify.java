import java.io.*;

public class modify {

    public static void main(String[] args) {
    BufferedReader reader = null;
    BufferedWriter writer = null;
    String inputFileName = args[0];
    String outoutFileName =args[1];
    String letterToRemove =args[2];

        try {
            File inputfile = new File(inputFileName);
            File outputFile = new File(outoutFileName);

            reader = new BufferedReader(new FileReader(inputfile));
            writer = new BufferedWriter(new FileWriter(outputFile));
            String line;
            System.out.println("letter to remove: "+letterToRemove);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                line = line.replace(letterToRemove,"");
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
