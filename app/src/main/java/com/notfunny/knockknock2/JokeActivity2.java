package com.notfunny.knockknock2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity2 extends AppCompatActivity {

    private String arg;

    TextView tvJoke3, tvJoke4, tvJoke5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke2);

        Bundle b = getIntent().getExtras();
        arg = "animals";
        if (b != null)
            arg  = b.getString("arg");

        JokeDBHandler handler = new JokeDBHandler(this, null, null, 1, false);

        // If argument given is a number, find the joke with that ID
        String jokeStr;
        if (arg.matches("[0-9]+"))
            jokeStr = handler.getJoke(Integer.parseInt(arg));
            // Else, the argument is a category
        else
            jokeStr = handler.getJoke(arg);

        tvJoke3 = (TextView) findViewById(R.id.tvJoke3);
        tvJoke4 = (TextView) findViewById(R.id.tvJoke4);
        tvJoke5 = (TextView) findViewById(R.id.tvJoke5);

        if (jokeStr != null)
        {
            String[] jokeParts = jokeStr.split(";");
            tvJoke3.setText(jokeParts[0]);
            tvJoke4.setText(jokeParts[1]);
            tvJoke5.setText(jokeParts[2]);
        }
    }
}
