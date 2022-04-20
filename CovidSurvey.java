//Tristan Howard
//CSCI 3410
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CovidSurvey 
{
	public static void showSurveys(ArrayList<Survey> list, String poll, String con)
	{
		//print header
		System.out.println(String.format("%s %-10s %-10s %-10s %-10s %-10s %-10s","\tstart","\t  end","sample","very","some","!very","!at_all"));
		int count = 0;
		for(int i = 0;i<list.size();i++)
		{
			if(list.get(i).getPollster().contains(poll) & list.get(i).getSubject().contains(con))
			{
				count++;
				System.out.println(count+ "."+ list.get(i).toString());
			}
		}
	}
	@SuppressWarnings("resource")
	//ignores warning from scanner not being closed if the file doesn't exist and
	// the scanner doesn't close so it can still loop.
	public static void main(String[] args) throws FileNotFoundException, NumberFormatException
	{
		File file; //initialize file
		String str; //initialize string
		Scanner kb = new Scanner(System.in); //set up scanner
		//do all this while the file doesn't exist
		do 
		{	
			System.out.print("Enter a file: ");
				if(args.length>0) //read from command line //file has to be inside Project source file
				{
					str = args[0];
					file = new File(str);
				}
				else //read from nextLine
				{
					str = kb.nextLine();
					file = new File(str);
				}
			try //see if file exists if it does it breaks out of do while loop
			{
				kb = new Scanner(file);
					
				if(!file.exists()) 
				{
					throw new FileNotFoundException();
				}
				else
				{
					kb.close();
					break;
				}
			}
			catch(FileNotFoundException fnf) 
			{
				System.out.println("File not found.");
			}
		}while(!file.exists());
		kb.close();
		ArrayList <Survey> list = new ArrayList<>();
		String line;
		String[] surveyList;
		Survey surv = null;
		Scanner scan = new Scanner(file);
		scan.nextLine(); //skip first line
		//parse each line and enter the useful info into a new survey object then add to the arraylist
		do 
		{
			try 
			{
				surv = new Survey();
				line = scan.nextLine();
				surveyList = Utility.parseString(line.length(),line);			
				surv.setSubject(surveyList[0]);
				surv.setStart(surveyList[3]);
				surv.setEnd(surveyList[4]);
				surv.setPollster(surveyList[5]);
				surv.setSampleSize(Integer.parseInt(surveyList[7]));
				surv.setVery(Double.parseDouble(surveyList[13]));
				surv.setSomewhat(Double.parseDouble(surveyList[14]));
				surv.setNotVery(Double.parseDouble(surveyList[15]));
				surv.setNotAtAll(Double.parseDouble(surveyList[16]));
				list.add(surv);
			}
			catch(Exception e) //catch NumberFormatException?
			{
				
			}
		}
		while(scan.hasNextLine()); //while the the file has a next line
		scan.close(); //closes file
		
		
		Scanner in = new Scanner(System.in);
		String con;
		String poll = "";
		boolean find = false;
		do
		{
			//read input from after asking for pollster
			System.out.print("Enter pollster: ");
			poll = in.nextLine();
			//if poll equals empty string break out of do while
			if(poll.equals("") || poll.equals("N"))
			{
				break;
			}
			//ask for concern
			System.out.print("Enter concern: ");
			con = in.nextLine();
			
			//if concern doesn't equal economy or infected display error message and
			//make them re enter until it does
			if(!con.equalsIgnoreCase("economy") || !con.equalsIgnoreCase("infected"))
			{
				do
				{
					if(con.equalsIgnoreCase("economy") || con.equalsIgnoreCase("infected"))
					{
						find = true;
					}
					else 
					{
						System.out.print("Error: Enter economy or infected: ");
						con = in.nextLine();
					}
				}
				while(!find);
			}
			
			int count = 0;
			double sAvg = 0;
			double vAvg = 0;
			double smAvg = 0;
			double nvAvg = 0;
			double naaAvg = 0;
			
			//print out table header
			System.out.println(String.format("%s %-10s %-10s %-10s %-10s %-10s %-10s","\tstart","\t  end","sample","very","some","!very","!at_all"));
			
			//get the totals for existing polls
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).getPollster().contains(poll) & list.get(i).getSubject().contains(con))
				{
					count++;
					System.out.println((count)+"."+list.get(i).toString());
					sAvg += list.get(i).getSampleSize();
					vAvg += list.get(i).getVery();
					smAvg += list.get(i).getSomewhat();
					nvAvg += list.get(i).getNotVery();
					naaAvg += list.get(i).getNotAtAll();
				}
			}
			System.out.println("\nNumber of Surveys: " + count);
			System.out.println("Averages");
			
			if(count>0) 
			{
				//print averages for existing surveys
				System.out.printf("sample_size: %.1f\n",(sAvg/count));
				System.out.printf("very: %.1f\n" , (vAvg/count));
				System.out.printf("somewhat: %.1f\n" , (smAvg/count));
				System.out.printf("not_very: %.1f\n" , (nvAvg/count));
				System.out.printf("not_at_all: %.1f\n" , (naaAvg/count));
			}
			else
			{
				//print 0 averages for nonexisting pollster
				System.out.println("sample_size: " + (sAvg));
				System.out.println("very: " + (vAvg));
				System.out.println("somewhat: " + (smAvg));
				System.out.println("not_very: " + (nvAvg));
				System.out.println("not_at_all: " + (naaAvg));
			}
		}
		while(!poll.equals("")); //while pollster input isn't an empty string
		in.close();
	}
}
