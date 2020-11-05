/**
* This utility will generate a code review report that conforms with the requirements for SER316
* 
* The text file should be formatted with each flaw found as follows
* 
* file1.java
* FIRST FLAW DESCRIPTION
* CG2 (2-3 characters to describe which violation from code standards chart)
* LOW (2-3 characters to describe importance)
*
* file2.java
* SECOND FLAW DESCRIPTION
* CG2 (2-3 characters to describe which violation from code standards chart
* LOW (2-3 characters to describe importance)
*
* ...
*
* @author Greg Ross
* @version 1.0
*/


import java.util.LinkedList;
import java.io.PrintWriter;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class CodeReview{
  
     /**
    * Stores all the information about each defect
    */
    public static class Defect{
        int id;
        String fileName;
        int lineNumber;
        String description;
        String category;
        String severity;

        public Defect(int IDNUM, String fNAME, int lineNUM, String desc, String cat, String sev){
            id=IDNUM;
            fileName=fNAME;
            lineNumber=lineNUM;
            description=desc;
            category=cat;
            severity=sev;
        }
    }
    
  /**
   * Main method, input parameter is name of file  
   * @param args[0] Name of file to read
   */
  public static void main(String[] args){

    int id=0;
    String fileName="";
    int lineNumber=0;
    String description="";
    String category="";
    String severity="";
    LinkedList<Defect> defectList = new LinkedList<Defect>();
      
    // Read Flaws Document
        try{
                Scanner in=new Scanner ( new File (args[0]) );
                      // Go through document 1 line at a time.
                      while (in.hasNextLine()) {
                        id++;
                        fileName=in.nextLine();
                        lineNumber=Integer.parseInt(in.nextLine());
                        description=in.nextLine();
                        category=in.nextLine();
                        severity = in.nextLine();
                        defectList.add(new Defect( id, fileName, lineNumber, description, category, severity));
                        if (in.hasNextLine()) in.nextLine();
                      }                   
            in.close();
        } catch (Exception e){
          System.out.println("Could not open "+args[0]);
          e.printStackTrace();
        }
        
        try{
            PrintWriter out= new PrintWriter( args[0]+".md" );
                  
    // Output to file
    // HEADER
 
    out.printf("``` \n"); //Markdown mark for code to prevent md default formatting
    out.printf("%15s %20s %37s %20s \n\n", " ", "____________________", "       Code Review Defect List       ", "____________________");      
    out.printf("Reviewer: REVIEWER NAME \t\t\t GH Repo: https://github.com/amehlhase316/Aachen-2\n\n");   
    // ID  Location  Problem Description Problem
    out.printf("%-10s %-20s %-45s %-20s\n", "ID", "               Location", "               Problem Description", "                          Problem");

    // ID#   FileName   LineNumber    Description   ProblemCategory  ProblemSeverity
    out.printf("%3s %26s|%-9s %-60s %8s|%-8s\n", "ID#", "fileName", "lineNum", "*Description of the Problem*","category","severity");
    out.printf("=== ==========================|========= ============================================================ ======== ========\n");
    String rowFormat="%3d %26s Line %-3d %-60s %7s | %7s\n";

    // Loop for each flaw
    for( Defect defect:defectList){
      out.printf(rowFormat, defect.id, defect.fileName, defect.lineNumber, defect.description, defect.category, defect.severity);         
    }
    
    //Markdown mark for code to prevent md default formatting
    out.printf("```");
    // Close File             
    out.close();
    } catch (Exception e){ // File failed to be created
     e.printStackTrace();   
    }
          
          
  } // End of main method
} // End Class Code Review
