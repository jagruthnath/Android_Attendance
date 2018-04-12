package com.example.machinex.attendance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {


    public static final String cin1="08:30:00";
    public static final String cin2="09:10:00";
    public static final String cin3="11:00:00";
    public static final String cin4="11:10:00";
    public static final String cin5="14:00:00";
    public static final String cin6="14:10:00";
    public static final String cout1="11:00:00";
    public static final String cout2="01:00:00";
    public static final String cout3="06:00:00";
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
 }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.item1) {
            // add your action here that you want
            startActivity(new Intent(MainActivity.this,Details.class));
            return true;
        }
        if (item.getItemId() == R.id.item2) {
            // add your action here that you want
            startActivity(new Intent(MainActivity.this,Course.class));
            return true;
        }
        //DoSomething();
        return super.onOptionsItemSelected(item);
    }
    public void getCheckIn(View view) throws ParseException {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy");
        String formattedDate1 = df1.format(c.getTime());
        Date curr_date=new SimpleDateFormat("dd/MM/yy").parse(formattedDate1);
        Date curr=new SimpleDateFormat("HH:mm:ss").parse(formattedDate);
        Toast.makeText(getApplicationContext(),formattedDate,Toast.LENGTH_LONG).show();
        Date date1=new SimpleDateFormat("HH:mm:ss").parse(cin1);
        Date date2=new SimpleDateFormat("HH:mm:ss").parse(cin2);
        Date date3=new SimpleDateFormat("HH:mm:ss").parse(cin3);
        Date date4=new SimpleDateFormat("HH:mm:ss").parse(cin4);
        Date date5=new SimpleDateFormat("HH:mm:ss").parse(cin5);
        Date date6=new SimpleDateFormat("HH:mm:ss").parse(cin6);
        if(curr.compareTo(date1)>0&&curr.compareTo(date2)<0){
            // Toast.makeText(getApplicationContext(),"pending",Toast.LENGTH_LONG).show();
            Boolean isadded=myDb.insertData(curr_date,"pending","n/a","n/a");
            if(isadded)
                Toast.makeText(MainActivity.this,"Data added",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Data not added",Toast.LENGTH_LONG).show();
        }
        else if(curr.compareTo(date3)>0&&curr.compareTo(date4)<0)
        {
            // Toast.makeText(getApplicationContext(),"pending1",Toast.LENGTH_LONG).show();
            Cursor s1= myDb.getSessionDate(1);
            Boolean isupdated=myDb.updateData(curr_date, String.valueOf(s1),"pending","n/a");
            if(isupdated)
                Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
        }
        else if (curr.compareTo(date5)>0&&curr.compareTo(date6)<0){
            // Toast.makeText(getApplicationContext(),"pending3",Toast.LENGTH_LONG).show();
            Cursor s1= myDb.getSessionDate(1);
            Cursor s2= myDb.getSessionDate(2);
            Boolean isupdated=myDb.updateData(curr_date, String.valueOf(s1), String.valueOf(s2),"pending");
            if(isupdated)
                Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
        }
        else{
            if(curr.compareTo(date2)>0&&curr.compareTo(date3)<0)
            {
                //Toast.makeText(getApplicationContext(),"ab1",Toast.LENGTH_LONG).show();
                Boolean isadded=myDb.insertData(curr_date,"Absent","n/a","n/a");
                if(isadded)
                    Toast.makeText(MainActivity.this,"Data added",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not added",Toast.LENGTH_LONG).show();
            }
            else if(curr.compareTo(date4)>0&&curr.compareTo(date5)<0)
            {
                // Toast.makeText(getApplicationContext(),"ab2",Toast.LENGTH_LONG).show();
                Cursor s1= myDb.getSessionDate(1);
                Boolean isupdated=myDb.updateData(curr_date, String.valueOf(s1),"Absent","n/a");
                if(isupdated)
                    Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
            }
            else if(curr.compareTo(date6)>0)
            {
                // Toast.makeText(getApplicationContext(),"ab3",Toast.LENGTH_LONG).show();
                Cursor s1= myDb.getSessionDate(1);
                Cursor s2= myDb.getSessionDate(2);
                Boolean isupdated=myDb.updateData(curr_date, String.valueOf(s1), String.valueOf(s2),"Absent");
                if(isupdated)
                    Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
            }

        }
        System.out.println(cin1+"\t"+date1);

    }

    public void getCheckOut(View view) throws ParseException {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        Date curr=new SimpleDateFormat("HH:mm:ss").parse(formattedDate);
        Toast.makeText(getApplicationContext(),formattedDate,Toast.LENGTH_LONG).show();
        Date date11=new SimpleDateFormat("HH:mm:ss").parse(cout1);
        Date date21=new SimpleDateFormat("HH:mm:ss").parse(cout2);
        Date date31=new SimpleDateFormat("HH:mm:ss").parse(cout3);
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy");
        String formattedDate1 = df1.format(c.getTime());
        Date curr_date=new SimpleDateFormat("dd/MM/yy").parse(formattedDate1);
        if(curr.compareTo(date11)>=0) {

            Cursor cursor=myDb.getSessionDate(1);
            if(String.valueOf(cursor).compareTo("pending")==0) {
                Boolean isadded = myDb.updateData(curr_date, "Present", "n/a", "n/a");
                if (isadded)
                    Toast.makeText(MainActivity.this, "Data updated present1", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
            }
            else{
                Boolean isadded = myDb.updateData(curr_date, "Absent", "n/a", "n/a");
                if (isadded)
                    Toast.makeText(MainActivity.this, "Data updated Absent1", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
            }
        }
        else if(curr.compareTo(date21)>=0) {

            Cursor cursor=myDb.getSessionDate(2);
            Cursor s1= myDb.getSessionDate(1);
            if(String.valueOf(cursor).compareTo("pending")==0) {
                Boolean isupdated = myDb.updateData(curr_date, String.valueOf(s1), "Present", "n/a");
                if (isupdated)
                    Toast.makeText(MainActivity.this, "Data Updated Present2", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
            }
            else{
                Boolean isupdated = myDb.updateData(curr_date, String.valueOf(s1), "Absent", "n/a");
                if (isupdated)
                    Toast.makeText(MainActivity.this, "Data Updated Absent2", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();

            }

        }
        else if(curr.compareTo(date31)>=0) {

            Cursor s1= myDb.getSessionDate(1);
            Cursor s2= myDb.getSessionDate(2);
            Cursor cursor=myDb.getSessionDate(3);
            if(String.valueOf(cursor).compareTo("pending")==0) {
                Boolean isupdated = myDb.updateData(curr_date, String.valueOf(s1), String.valueOf(s2), "Present");
                if (isupdated)
                    Toast.makeText(MainActivity.this, "Data Updated Present3", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
            }
            else {
                Boolean isupdated = myDb.updateData(curr_date, String.valueOf(s1), String.valueOf(s2), "Absent");
                if (isupdated)
                    Toast.makeText(MainActivity.this, "Data Updated Absent3", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();

            }

        }
        else{
            if(curr.compareTo(date11)<0)
            {
                Boolean isadded=myDb.updateData(curr_date,"Absent","n/a","n/a");
                if(isadded)
                    Toast.makeText(MainActivity.this,"Data added",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not added",Toast.LENGTH_LONG).show();
            }
            else if(curr.compareTo(date21)<0)
            {
                Cursor s1= myDb.getSessionDate(1);
                Boolean isupdated=myDb.updateData(curr_date, String.valueOf(s1),"Absent","n/a");
                if(isupdated)
                    Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
            }
            else if(curr.compareTo(date31)<0)
            {

                Cursor s1= myDb.getSessionDate(1);
                Cursor s2= myDb.getSessionDate(2);
                Boolean isupdated=myDb.updateData(curr_date, String.valueOf(s1), String.valueOf(s2),"Absent");
                if(isupdated)
                    Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
            }
        }

    }



}