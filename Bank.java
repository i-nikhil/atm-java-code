import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.Iterator;
import java.text.DateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

class User//data base of users
{
    private int account_id;
    private String name;
	private int pin;
	private long balance;

	public User(int account_id, String name, int pin, long balance) 
	{
        this.account_id = account_id;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public long getBalance() {
        return balance;
    }
}
public class Bank//controller part
{
	public static void main(String args[])throws IOException
	{
		Scanner sc=new Scanner(System.in);//For input support
		ArrayList<User> table = new ArrayList<>();//Storing User objects
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");//Date-time Format
        Date dateobj = new Date();//system date-time
		try
		{
		    //use this dummy data for testing
			table.add(new User(14523698,"Elon Musk",1289,50000000));
			table.add(new User(78549632,"Donald Trump",2526,39999990));
			table.add(new User(54856947,"Steve Jobs",9398,35000990));
			table.add(new User(11455587,"Sachin Tendulkar",8612,9008800));
			table.add(new User(99676898,"Rahul Gandhi",1234,0));
			table.add(new User(41212455,"Alex Morton",1279,5800000));
			table.add(new User(48963689,"Amitabh Bacchan",2526,785000));
			table.add(new User(55454899,"Arnab Goswami",9398,3880990));
			table.add(new User(78333588,"Mamta Banerjee",8612,900));
			table.add(new User(88996633,"Deepak Kumar",1234,100));
			
		int c,found=0,acc,pin,op,ch;
		long amt;
		User active=null;//stores the active User object
		System.out.println("\n***************************************");
		System.out.println("    WELCOME TO STATE BANK OF INDIA\n");
		while(true)
		{
		System.out.println("________________________________________");
		System.out.println("For New User >>Press 1 -Open new Account");
		System.out.println("Existing User>>Press 2 -go to Login Page\n");
		System.out.println("________________________________________");
		c=sc.nextInt();
		switch(c)
		{
			case 1:System.out.println("	>> SIGN IN<<\n\n(Note: Don't use Space in Name)");
				   System.out.print("Enter First Name:");
				   String new_name_f=sc.next();
				   System.out.print("Enter Last Name:");
				   String new_name_l=sc.next();
				   
				   int random_acc = (int)(Math.random() * (99999999 - 10000000 + 1) + 10000000);
				   int random_pin = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
			       table.add(new User(random_acc,(new_name_f+" "+new_name_l),random_pin,0));
				   System.out.println("\n>>New Account Created Successfully<<\n>New Acc. no.="+random_acc+"\n>New Pin="+random_pin);
				   System.out.println("\nLogin to Access your New Account & Change your Pin");
				   break;
			case 2:System.out.println("	>> LOGIN<<");
				   System.out.print("Enter Account Number:");
				   acc=sc.nextInt();
				   Iterator it = table.iterator();//Iterate through database to find the user
				   while (it.hasNext())
				   {
					   User ur=(User)it.next();
					   if (ur.getAccount_id() == acc)
					   {
							found=1;
							System.out.print("Enter Pin:");
							pin=sc.nextInt();
							if(ur.getPin()==pin)
							{
								active=ur;//active user
								System.out.println("\n<Successfully logged in at "+df.format(dateobj)+">\n");
								System.out.println("\n **Welcome "+ur.getName()+"**");
								found=2;
								break;
							}
						}
					}
					if(found==0)
				    {
						System.out.println("\n!!INVALID ACCOUNT NUMBER!! ");
					   	System.out.println("!!PLEASE TRY AGAIN LATER!! ");
					   	System.out.println("\n<Session ended at "+df.format(dateobj)+">\n");
				   		System.exit(0);
					}
					if(found==1)
					{
						System.out.println("\n!!INVALID PIN!! ");
						System.out.println("!!PLEASE TRY AGAIN LATER!! ");
						System.out.println("\n<Session ended at "+df.format(dateobj)+">\n");
						System.exit(0);
					}
					while(true)
					{
						System.out.println("\n__________________");
						System.out.println("SELECT YOUR CHOICE:\n1-Update Pin\n2-Balance Enquiry\n3-Withdraw Money\n4-Deposit Money\n5-Logout");
						System.out.println("\n__________________");
						ch=sc.nextInt();
						switch(ch)
						{
							case 1:System.out.print("Enter Old Pin:");
								op=sc.nextInt();
								if(op==active.getPin())
								{
									System.out.print("Enter New Pin:");
									active.setPin(sc.nextInt());						
									System.out.println(">> PIN UPDATED SUCCESSFULLY <<");  
								}
								else
								System.out.println("\n	!!INVALID PIN!! ");
								break;
							case 2:System.out.println("AVAILABLE BALANCE = Rs."+active.getBalance());
								break;
							case 3:System.out.print("\nEnter Amout to be Withdrawn: Rs.");
								amt=sc.nextLong();
								if(amt>active.getBalance())
									System.out.println("\n !!INSUFFICIENT BALANCE!!");
								else
								{
									active.setBalance(active.getBalance()-amt);
									System.out.println(">> WITHDRAWL SUCCESSFUL <<\nAVAILABLE BALANCE = Rs."+active.getBalance());
								}
								break;
							case 4:System.out.print("\nEnter Amout to be Deposited: Rs.");
								amt=sc.nextLong();
								if(amt>1000000)
									System.out.print("\nYour amout exceeds limit.\nMaximum depositing amount at once= Rs.10 Lakh");
								else
								{
									active.setBalance(active.getBalance()+amt);
									System.out.println(">> DEPOSIT SUCCESSFUL <<\nAVAILABLE BALANCE = Rs."+active.getBalance());
								}
								break;
							case 5:System.out.println("\nThanks for using SBI, visit again :)");
								System.out.println("\n<Session ended at "+df.format(dateobj)+">\n");
								System.exit(0);
							default:System.out.println("\n!!WRONG CHOICE!!\nPress between 1 to 5");
						}//end of inner switch
					}//end of inner loop
			default:System.out.println("\n!!WRONG CHOICE!!\nPress 1 or 2");
		}//end of outer switch
		}//end of outer while
					
					
		}//try block
	catch(IllegalArgumentException e)  
    {
		System.out.println("\n     !!WARNING!!\nPlease use correct input format");
		System.out.println("\n<Session Expired at "+df.format(dateobj)+">\n");
    }
	catch(java.util.InputMismatchException e)
	{
		System.out.println(e);
		System.out.println("\n     !!WARNING!!\nPlease use correct input format");
		System.out.println("\n<Session Expired at "+df.format(dateobj)+">\n");
	}
	catch(java.util.NoSuchElementException e)
	{
        System.out.println("Hey, please run this code OFFLINE. It works great on System Command Prompt");
		System.out.println("\n<Session Expired at "+df.format(dateobj)+">\n");
    }
	}//end of main
}//end of public class	