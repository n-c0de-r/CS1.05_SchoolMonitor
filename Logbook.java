import java.util.ArrayList;
import java.util.Iterator;

/**
 * Monitor times of different tutorings of child.
 * Logs are recorded by tutors.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2016.02.29 (imperative)
 */
public class Logbook 
{
    // Records of all the logs of childs.
    private ArrayList<Log> logs;
    
    /**
     * Create an Logbook.
     */
    public Logbook()
    {
        this.logs = new ArrayList<>();
        addLogs("logs.csv");
    }
    
    /**
     * Add the logs recorded in the given filename to the current list.
     * @param filename  A CSV file of Log records.
     */
    public void addLogs(String filename)
    {
        LogReader reader = new LogReader();
        logs.addAll(reader.getLogs(filename));
    }
    
    /**
     * Print details of all the logs.
     */
    public void printList()
    {
        // for(Log record : logs) {
            // System.out.println(record.getDetails());
        // }
        
        // Assignment 1
        logs.forEach(record -> System.out.println(record.getDetails()));
    }
    
    /**
     * Print the details of all the logs of the given child.
     * @param child The child.
     */
    public void printLogsOf(String child)
    {
        // for(Log record : logs) {
            // if(child.equals(record.getChild())) {
                // System.out.println(record.getDetails());
            // }
        // }
        
        // Assignment 2
        logs.stream()
                 .filter (record -> child.equals(record.getChild()))
                 .forEach(record -> System.out.println(record.getDetails()));
    }
    
    /**
     * Print the details of all the logs of a given day.
     * @param dayID The ID number of a given day.
     */
    public void printLogsByDay(int dayID)
    {
        // Assigment 3
        logs.stream()
                 .filter (record -> dayID == record.getDay())
                 .forEach(record -> System.out.println(record.getDetails()));
    }
    
    /**
     * Print the details of all the logs of the given child by day.
     * @param child The child.
     * @param dayID The ID number of a given day.
     */
    public void printLogsOfChildByDay(String child, int dayID)
    {
        // Assigment 4
        logs.stream()
                 .filter (record -> child.equals(record.getChild()))
                 .filter (record -> dayID == record.getDay())
                 .forEach(record -> System.out.println(record.getDetails()));
    }
    
    /**
     * Print the details of all the logs of the given tutor by day.
     * @param tutor The ID of the tutor.
     * @param dayID The ID number of a given day.
     */
    public void printLogsOfTutorByDay(int tutor, int dayID)
    {
        // Assigment 8
        logs.stream()
                 .filter(record -> record.getTimes() > 0)
                 .filter(record -> record.getTutor() == tutor)
                 .filter (record -> dayID == record.getDay())
                 .map(Log::getDetails)
                 .forEach(System.out::println); // Assignment 7
    }
    
    /**
     * Print the times of all logs of a particular child.
     */
    public void printLogsTimes(String child)
    {
        // Assigment 5
        logs.stream()
                 .filter(record -> child.equals(record.getChild()))
                 .map(record -> record.getTimes())// here the change happens
                 .forEach(time -> System.out.println("Tutored " + child +
                         ": " + time + " times."));
    }
    
    /**
     * Print all the logs by the given tutor.
     * @param tutor The ID of the tutor.
     */
    public void printLogsBy(int tutor)
    {
        // for(Log record : logs) {
            // if(record.getTutor() == tutor) {
                // System.out.println(record.getDetails());
            // }
        // }
        
        logs.stream()
                 .filter(record -> record.getTutor() == tutor)
                 .map(Log::getDetails)
                 .forEach(System.out::println);
    }
    
    /**
     * Print a list of the children considered to be endangered.
     * @param childNames    A list of childs names.
     * @param passThreshold Times less-than or equal-to to this level
     * are considered to prone to failing class.
     */
    public void printEndangered(ArrayList<String> childNames,
                                int passThreshold)
    {
        // for(String child : childNames) {
            // if(getTimes(child) <= passThreshold) {
                // System.out.println(child + " needs more classes.");
            // }
        // }
        
        // Assignment 6
        childNames.stream()
                   .filter(child -> getTimes(child) <= passThreshold)
                   .forEach(child -> System.out.println(child + " needs more classes."));
    }
    
    /**
     * Return a time of the number of logs of the given child.
     * @param child The child.
     * @return      The time of logs of the given child.
     */
    public int getTimes(String child)
    {
        int total = 0;
        for(Log log : logs) {
            if(child.equals(log.getChild())) {
                total = total + log.getTimes();
            }
        }
        return total;
    }
    
    /**
     * Remove from the logs list all of
     * those records with a time of zero.
     */
    public void removeZeroTimes()
    {
        Iterator<Log> it = logs.iterator();
        while(it.hasNext()) {
            Log record = it.next();
            if(record.getTimes() == 0) {
                it.remove();
            }
        }
    }
    
    /**
     * Return a list of all logs of the given
     * child in a particular subject.
     * @param child     The child.
     * @param subject   The ID of the subject.
     * @return          A list of logs.
     */
    public ArrayList<Log> getLogsInSubject(String child, int subject)
    {
        ArrayList<Log> records = new ArrayList<>();
        for(Log record : logs) {
            if(child.equals(record.getChild())) {
                if(record.getSubject() == subject) {
                    records.add(record);
                }
            }
        }
        return records;
    }
    
    /**
     * Return a list of all the logs of the given child.
     * @param child The child.
     * @return      A list of all logs of the given child.
     */
    public ArrayList<Log> getLogsOf(String child)
    {
        ArrayList<Log> filtered = new ArrayList<>();
        for(Log record : logs) {
            if(child.equals(record.getChild())) {
                filtered.add(record);
            }
        }
        return filtered;
    }

}
