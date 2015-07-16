package com.example.orm;

import java.util.ArrayList;

import com.orm.demo.R;
import com.oss.common.model.DataBaseProvider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Aorm extends Activity {
    Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private TextView btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orm);
        btn1 = (Button) this.findViewById(R.id.btn1);
        btn2 = (Button) this.findViewById(R.id.btn2);
        btn3 = (Button) this.findViewById(R.id.btn3);
        btn4 = (Button) this.findViewById(R.id.btn4);
        btn5 = (TextView) this.findViewById(R.id.btn5);
        btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
                House h = new House("AT", "A楼房");
                Student s = new Student("AT", "A张文同学", 11);
                ArrayList<Student> stus = new ArrayList<Student>();
                stus.add(s);

                Techcher t = new Techcher("AT", 222, h, stus);
                t.sex = "nan";
                DataBaseProvider<Techcher> db = DataBaseProvider.getDbProvider(Techcher.class);
                db.insert(t);
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
            }
        });
        btn2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
                DataBaseProvider<Techcher> db = DataBaseProvider.getDbProvider(Techcher.class);
                Techcher t = db.get("AT");
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
                System.out.println("t = " + t);
                btn5.setText(t.hose.name);
            }
        });
        btn3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
                DataBaseProvider<Techcher> db = DataBaseProvider.getDbProvider(Techcher.class);
                House h = new House("AT", "A楼房");
                Student s = new Student("AT", "A张文同学", 222222222);
                ArrayList<Student> stus = new ArrayList<Student>();
                stus.add(s);

                Techcher t = new Techcher("AT", 444444444, h, stus);
                t.sex = "nan";
                db.update(t);
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
                System.out.println("t = " + t);
            }
        });
        btn4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
                DataBaseProvider<Techcher> db = DataBaseProvider.getDbProvider(Techcher.class);
                db.deleteById("AT");
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
            }
        });
    }

}
