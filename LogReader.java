import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class to read CSV-style records of child log reports.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class LogReader
{
    // How many fields are expected.
    private static final int NUMBER_OF_FIELDS = 5;
    // Index values for the fields in each record.
    private static final int TUTOR = 0,
                             CHILD = 1,
                             TIMES = 2,
                             SUBJECT = 3,
                             DAY = 4;
    
    /**
     * Create a LogReader.
     */
    public LogReader()
    {
    }
    
    /**
     * Read logs in CSV format from the given file.
     * Return an ArrayList of Log objects created from
     * the information in the file.
     * 
     * @param filename The file to be read - should be in CSV format.
     * @return  A list of Logs.
     */
    public ArrayList<Log> getLogs(String filename)
    {
        // Create a Log from a CSV input line.
        Function<String, Log> createLog = 
            record -> {
                           String[] parts = record.split(",");
                           if(parts.length == NUMBER_OF_FIELDS) {
                               try {
                                   int tutor = Integer.parseInt(parts[TUTOR].trim());
                                   String child = parts[CHILD].trim();
                                   int times = Integer.parseInt(parts[TIMES].trim());
                                   int subject = Integer.parseInt(parts[SUBJECT].trim());
                                   int day = Integer.parseInt(parts[DAY].trim());
                                   return new Log(child, tutor, times, subject, day);
                               }
                               catch(NumberFormatException e) {
                                   System.out.println("Log record has a malformed integer: " + record);
                                   return null;
                               }
                           }
                           else {
                               System.out.println("Log record has the wrong number of fields: " + record);
                               return null;
                           }
                       };
        ArrayList<Log> logs;
        try {
            logs = Files.lines(Paths.get(filename))
                             .filter(record -> record.length() > 0 && record.charAt(0) != '#')
                             .map(createLog)
                             .filter(log -> log != null)
                             .collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            System.out.println("Unable to open " + filename);
            logs = new ArrayList<>();
        }
        return logs;
    }
    
}
