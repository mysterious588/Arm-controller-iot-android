package com.example.ahmad.armcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    void resets() {

        TextView txtY = findViewById(R.id.servo2);
        TextView txtX = findViewById(R.id.servo1);
        TextView txtg = findViewById(R.id.servo3);
        TextView txth = findViewById(R.id.servo4);

        x = 0;
        y = 0;
        z = 0;
        j = 0;
        mServo2.setValue(0);
        mServo1.setValue(0);
        mPick.setValue(0);
        mZ.setValue(0);
        txtX.setText("0");
        txtY.setText("0");
        txtg.setText("0");
        txth.setText("0");

    }

    int x;
    int y;
    int z;
    int j;
    DatabaseReference mrootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mServo1 = mrootRef.child("servo1");
    DatabaseReference mServo2 = mrootRef.child("servo2");
    DatabaseReference mPick = mrootRef.child("grapper");
    DatabaseReference mZ = mrootRef.child("z");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button servo1Left = findViewById(R.id.servo1Left);
        Button servo1Right = findViewById(R.id.servo1Right);
        Button servo2Left = findViewById(R.id.servo2Left);
        Button servo2Right = findViewById(R.id.servo2Right);
        Button pickUp = findViewById(R.id.pickUp);
        Button Drop = findViewById(R.id.Drop);
        Button zUp = findViewById(R.id.zUp);
        Button zDown = findViewById(R.id.zDown);
        final Button reset = findViewById(R.id.reset);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        pickUp.setOnClickListener(new View.OnClickListener() {
            TextView txt = findViewById(R.id.servo3);

            @Override
            public void onClick(View view) {
                if (j != 180) {
                    j += 3;
                    mZ.setValue(j);
                    String k = Integer.toString(j);
                    txt.setText(k);
                }
            }
        });
        Drop.setOnClickListener(new View.OnClickListener() {
            TextView txt = findViewById(R.id.servo3);

            @Override
            public void onClick(View view) {
                if (j != 0) {
                    j -= 3;
                    mZ.setValue(j);
                    String k = Integer.toString(j);
                    txt.setText(k);
                }
            }
        });
        zDown.setOnClickListener(new View.OnClickListener() {
            TextView txt = findViewById(R.id.servo4);

            @Override
            public void onClick(View view) {
                if (j != 0) {
                    j -= 3;
                    mZ.setValue(j);
                    String k = Integer.toString(j);
                    txt.setText(k);
                }
            }
        });
        zUp.setOnClickListener(new View.OnClickListener() {
            TextView txt = findViewById(R.id.servo4);

            @Override
            public void onClick(View view) {
                if (j != 180) {
                    j += 3;
                    mZ.setValue(j);
                    String k = Integer.toString(j);
                    txt.setText(k);
                }
            }
        });
        servo1Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtX = findViewById(R.id.servo1);
                if (x != 0) {
                    x -= 3;
                    mServo1.setValue(x);
                    String f = Integer.toString(x);
                    txtX.setText(f);
                }
            }
        });
        servo1Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (x != 180) {
                    x += 3;
                    TextView txtX = findViewById(R.id.servo1);
                    mServo1.setValue(x);
                    String f = Integer.toString(x);
                    txtX.setText(f);
                }
            }
        });
        servo2Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (y != 0) {
                    y -= 3;
                    TextView txtY = findViewById(R.id.servo2);
                    mServo2.setValue(y);
                    String f = Integer.toString(y);
                    txtY.setText(f);
                }
            }
        });
        servo2Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (y != 180) {
                    y += 3;
                    TextView txtY = findViewById(R.id.servo2);
                    mServo2.setValue(y);
                    String f = Integer.toString(y);
                    txtY.setText(f);
                }
            }
        });
        pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txt = findViewById(R.id.servo3);
                if (z != 180) {
                    z += 3;
                    //pick
                    mPick.setValue(z);
                    String djaja = Integer.toString(z);
                    txt.setText(djaja);
                }
            }
        });
        Drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txt = findViewById(R.id.servo3);
                if (z != 0) {
                    z -= 3;
                    //drop
                    mPick.setValue(z);
                    String djaja = Integer.toString(z);
                    txt.setText(djaja);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resets();
            }
        });
        mServo1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                x = dataSnapshot.getValue(Integer.class);
                TextView txtX = findViewById(R.id.servo1);
                String j = Integer.toString(x);
                txtX.setText(j);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mServo2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                y = dataSnapshot.getValue(Integer.class);
                TextView txtX = findViewById(R.id.servo2);
                String j = Integer.toString(y);
                txtX.setText(j);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mPick.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                z = dataSnapshot.getValue(Integer.class);
                TextView txtX = findViewById(R.id.servo3);
                String j = Integer.toString(z);
                txtX.setText(j);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mZ.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                j = dataSnapshot.getValue(Integer.class);
                TextView txtX = findViewById(R.id.servo4);
                String t = Integer.toString(j);
                txtX.setText(t);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(getApplicationContext(), "Ahmed Khaled", Toast.LENGTH_LONG);
            toast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.setMode) {

            Intent Automatic = new Intent(Main2Activity.this, MainActivity.class);
            resets();
            startActivity(Automatic);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


}

