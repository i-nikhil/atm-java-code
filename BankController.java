import java.text.*;
import java.util.*;

//controller part
public class BankController
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);//for input support

        ArrayList<User> table=new ArrayList<>();//storing user objects
        DateFormat df=new SimpleDateFormat("dd/MM/yy HH:mm:ss");//Date-time format
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

            System.out.println("\n***************************************");
            System.out.println("    WELCOME TO STATE BANK OF INDIA\n");
            while(true)
            {
                System.out.println("________________________________________");
                System.out.println("For New User >>Press 1 -Open new Account");
                System.out.println("Exixting User>>Press 2 -go to Login Page\n");
                System.out.println("________________________________________");

                int acc,c,found=0,pin,op;
                double amt;
                User active=null;//stores the active user object
                c=sc.nextInt();
                switch(c)
                {
                    case 1:System.out.println("    >> SIGN UP <<");
                           System.out.println("(Note: Don't use Space in Name)");
                           System.out.print("Enter First Name: ");
                           String new_name_f=sc.next();
                           System.out.print("Enter Last Name: ");
                           String new_name_l=sc.next();

                           int random_acc=(int)(Math.random()*(99999999-10000000+1)+10000000);
                           int random_pin=(int)(Math.random()*(9999-1000+1)+1000);

                           table.add(new User(random_acc,(new_name_f+" "+new_name_l),random_pin,0));

                           System.out.println("\n>>New Account Created Successfully<<");
                           System.out.println("New Acc. no.="+random_acc);
                           System.out.println("New Pin="+random_pin);
                           System.out.println("Login to access your new Account & Change your Pin");
                           break;

                    case 2:System.out.println("    >>LOGIN<<");
                           System.out.println("Enter Account Number:");
                           acc=sc.nextInt();
                           //Iterate through the database to find the user
                           Iterator it=table.iterator();
                           while (it.hasNext())
                           {
                               User ur=(User)it.next();
                               if(ur.getAccount_id()==acc)
                               {
                                   found=1;
                                   System.out.print("Enter Pin:");
                                   pin=sc.nextInt();
                                   if(ur.getPin()==pin)
                                   {
                                       active=ur;//active user
                                       System.out.println("\n<Successfully Logged in at "+df.format(new Date())+">\n");
                                       System.out.println("\n **Welcome "+active.getName()+"**");
                                       found=2;
                                       break;
                                   }
                               }
                           }
                           if(found==0)
                           {
                               System.out.println("\n!!INVALID ACCOUNT NUMBER!!");
                               System.out.println("!!PLEASE TRY AGAIN LATER!!");
                               System.out.println("\n<Session ended at "+df.format(new Date())+">\n");
                               System.exit(0);
                           }
                           if(found==1)
                           {
                               System.out.println("\n!!INVALID PIN!!");
                               System.out.println("!!PLEASE TRY AGAIN LATER!!");
                               System.out.println("\n<Session ended at "+df.format(new Date())+">\n");
                               System.exit(0);
                           }
                           while (true)
                           {
                               System.out.println("\n__________________");
                               System.out.println("SELECT OPERATION:");
                               System.out.println("1-Update Pin");
                               System.out.println("2=Balance Enquiry");
                               System.out.println("3-Withdraw Money");
                               System.out.println("4-Deposit Money");
                               System.out.println("5-Logout");
                               System.out.println("\n__________________");
                               int ch=sc.nextInt();
                               switch (ch)
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
                                          {
                                              System.out.println("\n	!!INVALID PIN!! ");
                                          }
                                          break;

                                   case 2:System.out.println("AVAILABLE BALANCE = Rs."+active.getBalance());
                                          break;

                                   case 3:System.out.print("\nEnter Amount to be Withdrawn: Rs.");
                                          amt=sc.nextDouble();
                                          if(amt>active.getBalance())
                                              System.out.println("\n !!INSUFFICIENT BALANCE!!");
                                          else
                                          {
                                              active.setBalance(active.getBalance()-amt);
                                              System.out.println(">> WITHDRAWL SUCCESSFUL<<");
                                              System.out.println("AVAILABLE BALANCE = Rs."+active.getBalance());
                                          }
                                          break;

                                   case 4:System.out.println("\n Enter Amount to be Deposited: Rs.");
                                          amt=sc.nextDouble();
                                          if(amt>1000000)
                                          {
                                              System.out.println("\nYour amount exceeds limit.");
                                              System.out.println("Maximum depositing amount at once = Rs. 1000000");
                                          }
                                          else
                                          {
                                              active.setBalance(active.getBalance()+amt);
                                              System.out.println(">> DEPOSIT SUCCESSFUL<<");
                                              System.out.println("AVAILABLE BALANCE = Rs."+active.getBalance());
                                          }
                                          break;

                                   case 5:System.out.println("\nThanks for using SBI, visit again :)");
                                          System.out.println("\n<Session ended at "+df.format(new Date())+">\n");
                                          System.exit(0);
                                   default:System.out.println("\n!!WRONG CHOICE!!\nPress between 1 to 5");
                               }//end of inner switch
                           }//end of inner while
                    default:System.out.println("\n!!WRONG CHOICE!!\nPress between 1 to 2");
                }//end of outer switch
            }//end of outer while
        }//end of try block
        catch (Exception e)
        {
            System.out.println("\n     !!WARNING!!\nPlease use correct input format");
            System.out.println("\n<Session Expired at "+df.format(new Date())+">\n");
        }//end of catch block
    }//end of main
}//end of class
