/**
 * Details of a log of a child by an individual tutor.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Log
{
    // The child tutored.
    private final String child;
    // The ID of the tutor.
    private final int tutor;
    // How many times were tutored.
    private final int times;
    // The ID of the subject in which they were tutored.
    private final int subject;
    // The tutoring day.
    private final int day;
    
    /**
     * Create a record of a log of a particular child.
     * @param child     The child tutored.
     * @param tutor     The ID of the tutor.
     * @param times     How many were tutored (>= 0).
     * @param subject   The ID of the subject in which they were tutored.
     * @param day       The tutoring day.
     */
    public Log(String child, int tutor, int times, int subject, int day)
    {
        this.child = child;
        this.tutor = tutor;
        this.times = times;
        this.subject = subject;
        this.day = day;
    }

    /**
     * Return the child tutored.
     * @return  The child.
     */
    public String getChild() 
    {
        return child;
    }

    /**
     * Return the ID of the tutor.
     * @return  The tutor's ID.
     */
    public int getTutor() 
    {
        return tutor;
    }

    /**
     * Return how many were tutored.
     * @return  The times how often tutored.
     */
    public int getTimes() 
    {
        return times;
    }

    /**
     * Return the ID of the subject in which they were tutored.
     * @return  At what they were tutored.
     */
    public int getSubject() 
    {
        return subject;
    }

    /**
     * Return the day on which they were tutored.
     * @return  When they were tutored.
     */
    public int getDay() 
    {
        return day;
    }

    /**
     * Return a string containing details of the child, the times tutored,
     * in what they were tutored, who tutored them and when.
     * @return  A string giving details of the log.
     */
    public String getDetails() 
    {
        return child + 
               ", times = " + times + 
               ", subject = " + subject + 
               ", tutor = " + tutor + 
               ", day = " + day;
    }
    
}
