package com.example.gul.snakeluddo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static Integer currentPosUser=0;
    public static Integer prevPosUser=0;
    TextView userRoll;
    TextView machineRoll;
    public static Integer prevPosMachine=0;
    public static Integer currentPosMachine=0;
    public GridView grid;
    public GridViewAdapter adapter;
    public List<Block> moves;
    Button mPlayButton;
    Random rand = new Random();
    private int diceRollMachine;
    private int diceRollUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayButton=(Button)findViewById(R.id.button);
        userRoll=(TextView)findViewById(R.id.usernumber);
        machineRoll=(TextView)findViewById(R.id.machinenumber);
        mPlayButton.setOnClickListener(this);
        moves=new ArrayList<Block>();
        createMoves();
        grid=(GridView)findViewById(R.id.gridview);

        adapter=new GridViewAdapter(this,R.layout.list_item_adapter,moves);

        grid.setAdapter(adapter);





    }

    public void onClick(View v){
        if(v.getId()==R.id.button){
            //generate random number

            diceRollUser = rand.nextInt(6) + 1;
            diceRollMachine=rand.nextInt(6)+1;
            userRoll.setText(String.valueOf("User Moves:"+String.valueOf(diceRollUser)));
            Log.e("User moves ",String.valueOf(diceRollUser+currentPosUser));
            if(!(currentPosUser+diceRollUser>99)) {
                prevPosUser = currentPosUser;
                currentPosUser = currentPosUser + diceRollUser;
                createMoves(currentPosUser, true);

                Log.e("MainActivity", String.valueOf(currentPosUser));

                adapter.notifyDataSetChanged();
                if(currentPosUser==99){
                    Toast.makeText(this,"User Won!!!",Toast.LENGTH_LONG).show();
                }
            }
            doMachineMove();



        }

    }

    public void createMoves(){
        moves.clear();
        for(int i = 1 ; i < 101 ; i++){
            moves.add(new Block(String.valueOf(i)));

        }

    }
    public void setMoves(boolean user){
        if(user) {
            for (int i = 1; i < 101; i++) {
                if(i-1!=currentPosMachine) {
                    moves.set(i - 1, new Block(String.valueOf(i)));
                }

            }
        }
        else{

            for (int i = 1; i < 101; i++) {
                if(i-1!=currentPosUser) {
                    moves.set(i - 1, new Block(String.valueOf(i)));
                }

            }
        }
    }
    public void createMoves(int position, boolean user){
        setMoves(user);
        adapter.notifyDataSetChanged();
        Block block=moves.get(position);
        block.light=true;
        if(user){

            Log.e("MainActivity","setting it true");
            block.user=true;


        }
        else{

            block.computer=true;
        }
        moves.set(position,block);

    }
    public void doMachineMove(){

        Log.e("Machine Moves: ",String.valueOf(currentPosMachine+diceRollMachine));
        machineRoll.setText("Machine Moves: "+String.valueOf(diceRollMachine));

        if(!(currentPosMachine+diceRollMachine>99)) {
            prevPosMachine = currentPosMachine;
            currentPosMachine = currentPosMachine + diceRollMachine;
            createMoves(currentPosMachine, false);

            Log.e("MainActivity", String.valueOf(currentPosMachine));

            adapter.notifyDataSetChanged();
            if(currentPosMachine ==99){
                Toast.makeText(this,"Machine Won!!!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
