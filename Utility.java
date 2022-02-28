
public class Utility {
	public static String[] parseString(int size, String input)
	{
		String[] data = new String[size];
		
		//default each string to be empty string
		for (int i=0; i< size; i++) {
			data[i] = "";
		}
		int i=0;
		while (input.length() > 0 && i< size)
		{
			//System.out.println("INPUT: "+ input);
			int pos = 0;
			//get first string
			if (input.length() > 0 && input.charAt(0) != '"')
			{
				pos = input.indexOf(',');
				if (pos == -1)
				{
					data[i] = input;
					input = "";
				}
				else
				{
					data[i] = input.substring(0, pos);
					input=input.substring(pos);
					
				}
			}
			else if (input.charAt(0)=='"')
			{
				//getrid of "
				input = input.substring(1);
				
				pos = input.indexOf('"');
				if (pos == -1) // error but just end the string
				{
					data[i] = input;
					input = "";
				}
				else
				{
					data[i] = input.substring(0, pos);
					input = input.substring(pos);
					//get rid of "
					input =input.substring(1);
				}
			}
			
			i++;
			//remove comma
			if(input.length() > 0)
				input = input.substring(1);
			input = input.trim();
		}
	

		return data;
	}
}
