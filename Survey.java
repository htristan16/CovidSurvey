
public class Survey {
	
	private String subject; //Define the fields
	private String pollster;
	private String start;
	private String end;
	private int sampleSize;
	private double very;
	private double somewhat;
	private double notVery;
	private double notAtAll;
	
	//No-Arg Constructor with null string and 0 values
	public Survey() 
	{
		start = "";
		end = "";
		subject = "";
		pollster = "";
		sampleSize = 0;
		very = 0;
		somewhat = 0;
		notVery = 0;
		notAtAll = 0;
	}
	//Setters and Getters for the fields
	public String getStart() 
	{
		return start;
	}
	public void setStart(String start) 
	{
		this.start = start;
	}
	public String getEnd() 
	{
		return end;
	}
	public void setEnd(String end) 
	{
		this.end = end;
	}
	public String getSubject() 
	{
		return subject;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	public String getPollster() 
	{
		return pollster;
	}
	public void setPollster(String pollster) 
	{
		this.pollster = pollster;
	}
	public int getSampleSize() 
	{
		return sampleSize;
	}
	public void setSampleSize(int sampleSize) 
	{
		this.sampleSize = sampleSize;
	}
	public double getVery() 
	{
		return very;
	}
	public void setVery(double very) 
	{
		this.very = very;
	}
	public double getSomewhat() 
	{
		return somewhat;
	}
	public void setSomewhat(double somewhat) 
	{
		this.somewhat = somewhat;
	}
	public double getNotVery() 
	{
		return notVery;
	}
	public void setNotVery(double notVery) 
	{
		this.notVery = notVery;
	}
	public double getNotAtAll() 
	{
		return notAtAll;
	}
	public void setNotAtAll(double notAtAll) 
	{
		this.notAtAll = notAtAll;
	}
	@Override
	/*"\t"+start +" "+ end + "\t"+ sampleSize + "\t "+very
	+"\t "+ somewhat+"\t "+ notVery +"\t "+ notAtAll
	*/
	public String toString() {
		return String.format("\t%s %-10s %-10s %-10s %-10s %-10s %-10s",start,end,sampleSize,very,somewhat,notVery,notAtAll);
	}
}