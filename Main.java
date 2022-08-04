import java.util.*;
//========================================TABLE CLASS=====================================
class table{
    String tableName;
    int capacity ;
    String arr[]={"Avalabile","Avalabile","Avalabile","Avalabile"};
    table(String tname,int cap){
        tableName=tname;
        capacity=cap;
    }
   String[] gettable(){
        return arr;
    }
    int getTablecap(){
        return capacity;
    }
    String[] gettableava(){
        return arr;
    }
    String gettablename(){
        return tableName;
    }
    String get_seatname(){
        return tableName;
    }
    void altera(int a,int b){
        a=a-6;
        b=b-6;
       for(int i=a;i<b;i++){
           arr[i]="Not Avalabile";
       }
    }
    void alteratonot(int a,int b){
         a=a-6;
        b=b-6;
       for(int i=a;i<b;i++){
           arr[i]="Avalabile";
       }
    }
     boolean checkavalityoftable(int from,int to){
         from=from-6;
         to=to-6;
        boolean ans=true;
        for(int i=from;i<to;i++){
            if(arr[i]=="Not Avalabile"){
                return false;
            }
        }
        
        return ans;
    }
}
//==========================================BOOKING CLASS===================================
class booking{
    int booking_id;
    int seatcapacity;
    String status;
    String seatname;
    int time[]=new int[2];
    booking(int bi,String sname,String sstatus,int seatcap,int from,int to){
        booking_id=bi;
        seatname=sname;
        status=sstatus;
        seatcapacity=seatcap;
        time[0]=from;
        time[1]=to;
    }
    int get_from(){
        return time[0];
    }
    int get_to(){
        return time[1];
    }
    int get_seatcap(){
        return seatcapacity;
    }
    String  get_status(){
        return status;
    }
    void set_status(String s){
        status=s;
    }
    String get_seatname(){
        return seatname;
    }
    void set_seatname(String s){
        seatname=s;
    }
    int get_booking_id(){
        return booking_id;
    }
}
//==========================================waiting class=====================================
class waiting{
     int booking_id;
    int seatcapacity;
    String status;
    String seatname;
    int time[]=new int[2];
    waiting(int bi,String sname,String sstatus,int seatcap,int from,int to){
        booking_id=bi;
        seatname=sname;
        status=sstatus;
        seatcapacity=seatcap;
        time[0]=from;
        time[1]=to;
    }
    int get_from(){
        return time[0];
    }
    int get_to(){
        return time[1];
    }
    int get_seatcap(){
        return seatcapacity;
    }
    String  get_status(){
        return status;
    }
    
    String get_seatname(){
        return seatname;
    }
    int get_booking_id(){
        return booking_id;
    }
}


//=========================================CUSTOMER CLASS====================================
class customer{
   static int  startbooking(){
        	Scanner obj=new Scanner(System.in);
        	System.out.println("Enter the number seat requried");
             int cap=obj.nextInt();
             System.out.println("Enter the time slot *from and *to");
             int time_slot_from=obj.nextInt();
             int time_slot_to=obj.nextInt();
            int current_id=Main.getid();
             for(table x:Main.tablelist){
             if(x.getTablecap()>=cap&x.checkavalityoftable(time_slot_from,time_slot_to)){
                 System.out.println("Yes the seat are Avalabile");
                Main.bookinglist.add(new booking(current_id,x.get_seatname(),"Booked",cap,time_slot_from,time_slot_to));
                System.out.println(current_id+" "+x.get_seatname()+" " +"Booked"+" "+cap+" "+time_slot_from+" "+time_slot_to);
                x.altera(time_slot_from,time_slot_to);
                 return 0;
             }
             
             }
              System.out.println("No the seat Not are Avalabile");
                Main.bookinglist.add(new booking(current_id,"Not assigned","waiting",cap,time_slot_from,time_slot_to));
                Main.waitinglist.add(new waiting(current_id,"Not assigned","waiting",cap,time_slot_from,time_slot_to));
                System.out.println(current_id+" "+"Not assigned"+" " +"waiting"+" "+cap+" "+time_slot_from+" "+time_slot_to);
                return 0;
    }
}
//=========================================menu=============================================
class menu{
   static int display_menu(){
        	Scanner obj=new Scanner(System.in);
        System.out.println("1.booking\n2.cancel\n3.Display Table\n4.get avalablity\n5.display bookingList\n6.Display WaitingList\n7.get status\n10.exit");
        int m=obj.nextInt();
        if(m==1){
            customer.startbooking();
            display_menu();
        }
        else if(m==2){
            Main.cancel();
            display_menu();
        }
        else if(m==3){
            Main.Diplay_table();
            display_menu();
        }
        else if(m==4){
           Main.getavalaiblity1();
           display_menu();
        }
        else if(m==5){
            Main.display_tablelist();
            display_menu();
        }
        else if(m==6){
            Main.display_waitinglist();
            display_menu();
        }
        else if(m==7){
            Main.get_status();
            display_menu();
        }
        else if(m==10){
            return 0;
        }
        else{
            System.out.println("Enter the valid output");
            display_menu();
        }
        return 0;
    }
}
//==========================================MAIN CLASS=======================================
public class Main
{
    static 	ArrayList<table> tablelist=new ArrayList<table>();
    static ArrayList<booking> bookinglist=new ArrayList<booking>();
    static ArrayList<waiting> waitinglist=new ArrayList<waiting>();
    static int id=0;
    static int getid(){
        id++;
        return id;
    }
    static void getavalaiblity1(){
        Scanner obj=new Scanner(System.in);
        System.out.println("1.check avalablity by capacity\n2.check avalablity by time");
        int input=obj.nextInt();
        if(input==1){
            System.out.println("Enter the capacity");
            int w=obj.nextInt();
            for(table c:tablelist){
                if(c.getTablecap()>=w){
                    String arr[]=c.gettableava();
                    System.out.println(c.gettablename()+" "+c.getTablecap()+" "+displayarray(arr));
                }
            }
        }
        else if(input ==2){
            System.out.println("Enter the timing");
            int mfrom=obj.nextInt();
            int mto=obj.nextInt();
            for(table c:tablelist){
                boolean ans=c.checkavalityoftable(mfrom,mto);
                System.out.println(c.gettablename()+" "+c.getTablecap()+" "+(ans?"Avalabile":"Not Avalabile"));
            }
        }
        else{
            System.out.println("invalid input");
            Main.getavalaiblity1();
        }
        
    }
    static ArrayList getbookingList(){
        return bookinglist;
    }
    static ArrayList gettableList(){
        return tablelist;
    }
    static void get_status(){
        System.out.println("enter your booking id");
        Scanner obj=new Scanner(System.in);
        int y=obj.nextInt();
        for(booking x:bookinglist){
            if(x.get_booking_id()==y){
                System.out.println(x.get_status());
            }
        }
    }
   static void Diplay_table(){
       
        for(table x:tablelist){
            String arr[]=x.gettableava();
            System.out.println(x.gettablename()+" "+x.getTablecap()+" "+displayarray(arr));
        }
        
    }
    static String displayarray(String a[]){
        String s="";
        for(String x:a){
            s=s+x+" ";
        }
       return s;
    }
   static void display_tablelist(){
        for(booking x:bookinglist){
            System.out.println(x.get_booking_id()+" "+x.get_seatname()+" "+x.get_seatcap()+" "+x.get_status());
        }
        
    }
    static void display_waitinglist(){
        for(waiting x:waitinglist){
            System.out.println(x.get_booking_id()+" "+x.get_seatname()+" "+x.get_seatcap()+" "+x.get_status());
        }
        
    }
    static void cancel(){
        	Scanner obj=new Scanner(System.in);
        	int flag=0;
        	 ArrayList<waiting> aa=new ArrayList<waiting>();
        System.out.println("Enter your Booking id");
        int a=obj.nextInt();
        for(booking x:bookinglist){
            if(x.get_booking_id()==a){
                if(x.get_status()=="waiting"){
                    int rrr=0;
                    for(waiting y:waitinglist){
                        rrr++;
                        if(y.get_booking_id()==a){
                        
                        flag=1;
                        aa.add(y);
                        }
                    }
                    if(flag==1){
                        for(waiting v:aa){
                             waitinglist.remove(v);
                        }
                    }
                }
              
                for(table z:tablelist){
                    if(x.get_seatname()==z.gettablename()){
                        z.alteratonot(x.get_from(),x.get_to());
                    }
                }
                x.set_status("cancelled");
                
                System.out.println("Your ticket is successfully cancelled!");
                Main.check_for_waiting();
               
            }
        }
        
        
    }
    static void check_for_waiting(){
        int rrr=0;
        int flag=0;
        ArrayList<waiting> aa=new ArrayList<waiting>();
        for(waiting x:waitinglist){
            if(x.get_status()=="waiting"){
            rrr++;
            int cap=x.get_seatcap();
            int from=x.get_from();
            int to=x.get_to();
            for(table y:tablelist){
                if(y.getTablecap()>=cap&&y.checkavalityoftable(from,to)){
                    for(booking z:bookinglist){
                       if(x.get_booking_id()==z.get_booking_id()){
                           z.set_status("Booked");
                           z.set_seatname(y.gettablename());
                            y.altera(from,to);
                            flag=1;
                            aa.add(x);
                       }
                    }
                }
            }
        }
      
        }
          if(flag==1){
            for(waiting xx:aa){
                 waitinglist.remove(xx);
            //Main.remove(x);
            }
        }
    }
   
	public static void main(String[] args) {
	Scanner obj=new Scanner(System.in);
	tablelist.add(new table("2S1",2));
	tablelist.add(new table("2S2",2));
	tablelist.add(new table("4S1",4));
	tablelist.add(new table("4S2",4));
	tablelist.add(new table("6S1",6));
	tablelist.add(new table("6S2",6));
	bookinglist.add(new booking(0,"dummy_name","dummy_status",0,0,0));
	waitinglist.add(new waiting(0,"dummy_name","dummy_status",0,0,0));
	System.out.println("Welcome to zoho restaurant");
	menu.display_menu();
	
}
}



