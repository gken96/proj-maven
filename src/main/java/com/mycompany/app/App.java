package com.mycompany.app;
import java.util.*;
import java.io.*;
abstract class Transaction{
	int a=0;
	long card[]= new long[10];
	int accno[]=new int[10];
	String name[]=new String[10];
	int pin[]=new int[10];
	//String Address[]=new String[10] ;
	int balance[]=new int[10];
	abstract void addmoney(int amount,int number);
	abstract void withdraw(int amount,int number);
	abstract void transfer(int amount, int number1,int number2);
	abstract void displaybalance(int number);
	abstract void register();
	
}

public class App extends Transaction {
	FileOutputStream fout= new FileOutputStream("Atm.txt");
	BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fout));
	Scanner i= new Scanner(System.in);
	void addmoney(int amount,int number) {
		
		balance[number]+=amount;
	}
	void withdraw(int amount,int number) {
		
		if(balance[number]<0 || (balance[number]-amount)<0) {
			System.out.println("Not Sufficient Balance withdraw");
		}
		else {
			balance[number]=balance[number]-amount;
		}
		
	}
	
	void transfer(int amount, int number1,int number2) {
		
		for(int m=0;m<2;m++) {
			if(accno[m]==number2) {
				balance[number1]=balance[number1]-amount;
				balance[m]=balance[m]+amount;
			}
		}
	}
	void displaybalance(int number) {
		System.out.println(balance[number]);
	}
	public App() throws FileNotFoundException {
	} 
	void register() {
		System.out.println("Enter details : name,card,pin,accno");
		name[a]=i.nextLine();
		card[a]=Integer.parseInt(i.nextLine());
		pin[a]=Integer.parseInt(i.nextLine());
		accno[a]=Integer.parseInt(i.nextLine());
		balance[a]=0;
		String s= name[a]+" "+Integer.toString(pin[a])+" "+Integer.toString(accno[a]);
		byte b[]= s.getBytes();
		try {
			fout.write(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a++;
		
		System.out.println();
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		App o = new App();
		
		while (true) {
			int ch1,ch2;
			System.out.println(" ******************ATM****************");
			System.out.println(" 1. New User ? Register by pressing 1 ");
			System.out.println(" 2. Already User. Press 2");
			System.out.println(" 3. To exit press 3");
			ch1=in.nextInt();
			if(ch1==1) {
				o.register();}
				else if(ch1==2) {
					int amt;
					System.out.println("Enter your acc no:");
		
					int acc=in.nextInt();
					for(int k=0;k<2;k++) {
					if(o.accno[k]==acc) {
					System.out.println("Enter your pin:");
					int p=in.nextInt();
					if(o.pin[k]==p) {
					System.out.println("**********Welcome Back************");
					System.out.println("1.displayBalance");
					System.out.println("2.addmoney");
					System.out.println("3.withdraw money");
					System.out.println("4.Transfer");
					ch2=in.nextInt();
					
					if(ch2==1) { o.displaybalance(k);}
					else if(ch2==2) { System.out.println("Enter amount");
							amt=in.nextInt();
							o.addmoney(amt,k);}
					else if(ch2==3) { System.out.println("Enter amount");
							amt=in.nextInt();
							o.withdraw(amt, k);}
					else if(ch2==4) { System.out.println("Enter amount");
							amt=in.nextInt();
							System.out.println("Enter other account");
							int acc2=in.nextInt();
							o.transfer(amt,k,acc2);
						}
					}
				}
			}
			
		}
	  }	
	}

}

