import java.util.ArrayList;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile ( String csvFile ) {

        // DO NOT remove these two lines
        StdIn.setFile(csvFile); // Opens the file
        StdIn.readLine();       // Reads and discards the header line

        // WRITE YOUR CODE HERE
        int yearI = 0;
        while (!StdIn.isEmpty())
        {
            String[] recordEntries = StdIn.readLine().split(",");
            int year = Integer.parseInt(recordEntries[0]);
            boolean temp = false;

            for (int i = 0; i < database.size(); i++)
            {
                if (database.get(i).getcurrentYear() == year)
                {
                    temp = true;
                    yearI = i;
                    break;
                }
            }
            String description = recordEntries[2];
            String gender = recordEntries[52];
            String race = recordEntries[66];
            String location = recordEntries[71];
            boolean arrested = recordEntries[13].equals("Y");
            boolean frisked = recordEntries[16].equals("Y");
            SFRecord record = new SFRecord(description, arrested, frisked, gender, race, location);

            if (temp)
            {
                database.get(yearI).addRecord(record);
            }
            else
            {
                SFYear sfyear = new SFYear(year);
                database.add(sfyear);
                database.get(database.size() - 1).addRecord(record);
            }
        }
    }

    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

    public ArrayList<SFRecord> populationStopped ( int year, String race ) {

        // WRITE YOUR CODE HERE
        ArrayList<SFRecord> sfRecord = new ArrayList<>();
        ArrayList<SFRecord> records = new ArrayList<>();

        for(SFYear sfryear: database)
        {
            if(sfryear.getcurrentYear() == year)
            {
                records = sfryear.getRecordsForYear();
                for(SFRecord record:records)
                {
                    if(record.getRace().equals(race))
                    {
                        sfRecord.add(record);
                    }
                }
                break;
            }
        }
        return sfRecord;

    }

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested ( int year ) {
        
        // WRITE YOUR CODE HERE
        double[] var = new double[2];
        double frisked = 0.0;
        double arrested = 0.0;
        double size = 0.0;
        int yearI = 0;
        for(int i =0;i<database.size();i++)
        {
            if(database.get(i).getcurrentYear()==year)
            {
                yearI = i;
                break;
            }
        }
        ArrayList<SFRecord> records = database.get(yearI).getRecordsForYear();
        for(int i =0;i<records.size();i++)
        {
            if(records.get(i).getFrisked()==true)
            {
                frisked++;
            }
            if(records.get(i).getArrested()==true)
            {
                arrested++;
            }
        }
        size = records.size();
        var[0] = (frisked / size) * 100.0;
        var[1] = (arrested / size) * 100.0;
        return var; // update the return value
    }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias ( int year ) {

        // WRITE YOUR CODE HERE
        double[][] people = new double[2][3];
        ArrayList<SFRecord> records = new ArrayList<>();
        double black = 0;
        double white = 0;
        double blackMen = 0;
        double blackWomen = 0;
        double whiteMen = 0;
        double whiteWomen = 0;
        for(SFYear sfryear:database)
        {
            if(sfryear.getcurrentYear() == year)
            {
                records = sfryear.getRecordsForYear();
                for(SFRecord record:records)
                {
                    if(record.getRace().equals("B"))
                    {
                        black++;
                        if(record.getGender().equals("F"))
                        {
                            blackWomen++;
                        }
                        if(record.getGender().equals("M"))
                        {
                            blackMen++;
                        }
                    }
                    if(record.getRace().equals("W"))
                    {
                        white++;
                        if(record.getGender().equals("F"))
                        {
                            whiteWomen++;
                        }
                        if(record.getGender().equals("M"))
                        {
                            whiteMen++;
                        } 
                    }
                }
                break;
            }
        }
        people[0][0]= (blackWomen/black)*0.5*100;
        people[0][1]= (whiteWomen/white)*0.5*100;
        people[0][2]= people[0][0]+people[0][1];
        people[1][0]= (blackMen/black)*0.5*100;
        people[1][1]= (whiteMen/white)*0.5*100;
        people[1][2]= people[1][0]+people[1][1];
        return people; // update the return value
    }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

    public double crimeIncrease ( String crimeDescription, int year1, int year2 ) {
        // WRITE YOUR CODE HERE
        int yearI1 = 0;
        int yearI2 = 0;
        for(int i =0;i<database.size();i++)
        {
            if(database.get(i).getcurrentYear()==year1)
            {
                yearI1 = i;
                break;
            }
        }
        for(int i =0;i<database.size();i++)
        {
            if(database.get(i).getcurrentYear()==year2)
            {
                yearI2 = i;
                break;
            }
        }
        if(database.get(yearI1).getcurrentYear()>=database.get(yearI2).getcurrentYear())
        {
            return 0.0;
        }
        ArrayList<SFRecord> records1 = database.get(yearI1).getRecordsForYear();
        ArrayList<SFRecord> records2 = database.get(yearI2).getRecordsForYear();
        double crime1 = 0;
        double crime2 = 0;
        for(SFRecord record: records1)
        {
            if(record.getDescription().indexOf(crimeDescription) != -1)
            {
                crime1 += 1.0;
            }
        }
        for(SFRecord record2: records2)
        {
            if(record2.getDescription().indexOf(crimeDescription) != -1)
            {
                crime2 += 1.0;
            }
        }
        double crimePer1 = (crime1/records1.size())*100;
        double crimePer2 = (crime2/records2.size())*100;
        return crimePer2 - crimePer1;
    }

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough ( int year ) {

        // WRITE YOUR CODE HERE
        int year0 = 0;
        for(int i =0;i<database.size();i++)
        {
            if(database.get(i).getcurrentYear()==year)
            {
                year0 = i;
                break;
            }
        }
        ArrayList<SFRecord> records = database.get(year0).getRecordsForYear();
        int[] borough = new int[5];
        String[] boroughs = {"Brooklyn", "Manhattan", "Bronx", "Queens", "Staten Island"};
    
        int i1 = 0;
        int i2 = 0;
        for(SFRecord record:records)
        {
            for(int j = 0; j<boroughs.length;j++)
            {
                if(record.getLocation().equals(boroughs[j].toUpperCase()))
                {
                    i1=j;
                    break;
                }
            }
            borough[i1]++;
        }
        int max = Integer.MIN_VALUE;
        for(int i =0;i<borough.length;i++)
        {
            if(borough[i]>max)
            {
                max = borough[i];
            }
        }
        for(int i =0;i<borough.length;i++)
        {
            if(borough[i]==max)
            {
                i2=i;
                break;
            }
        }
        return boroughs[i2]; // update the return value
    }

}
