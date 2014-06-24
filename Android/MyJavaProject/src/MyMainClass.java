import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class MyMainClass {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
String str;
String newname;
String password;
int j = 0;
int i = 0;
int x = 0;
String [] usernames = new String [5];
String [] passwords = new String [5];
while (x != 5)
{
BufferedReader br = new BufferedReader(new 
        InputStreamReader(System.in));

System.out.println("do you have a username or not.");


    str = br.readLine();
    System.out.println(str);
    if (str.equals("yes"))
    {
    	System.out.println("go to login page");
		System.out.println("enter username");
		String username3 = br.readLine();
    	for ( i = 0;i<=j;i++)
    	{
    		 
    		
    		if (username3.equals(usernames[i]))
    		{
    			System.out.println("enter your password");
    			String password3 = br.readLine();
    			if (password3.equals(passwords[i]))
    					{
    				System.out.println("you are loged in :)");
    				break;
    					}
    		}
    		if (i==j)
    		{
    			System.out.println("username and password are incorrect");
    		}
    		
    		
    	}
    	
    	
    	
    	
    }else
    {
    	System.out.println("enter new username");
    	newname = br.readLine();
    	usernames [j] = newname;
    	System.out.println("enter password");
    	password = br.readLine();
    	passwords[j] = password;
    	
    }
    j++;

 System.out.println("enter 5");
 String y = br.readLine();
 x = Integer.parseInt(y);



}
	}
}
