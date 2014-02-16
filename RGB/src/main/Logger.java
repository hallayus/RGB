package main;

public class Logger {
	private static final String[] blackList = {"player","math"};
	
	public static void writeMessage(String message, Class c)
	{
		if(filterMessage(c)) 
		{
			System.out.println(c.getPackage().getName() + ": "  + message);
		}
	}
	
	public static void writeException(Exception e, Class c)
	{
		e.printStackTrace();
	}
	
	private static boolean filterMessage(Class c)
	{
		String name = c.getPackage().getName();
		for(int i = 0; i < blackList.length; i++)
		{
			if(blackList[i].equals(name))
			{
				return false;
			}
		}
		return true;
	}
}
