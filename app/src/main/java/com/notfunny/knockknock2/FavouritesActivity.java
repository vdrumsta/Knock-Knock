package com.notfunny.knockknock2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FavouritesActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] jokeHeadings = {
            "Rhino",
            "Europe",
            "Anita"
    };
    private String[] jokeContent = {
            "Knock Knock\nWho's there?\nRhino...\nRhino who?\nRhino every knock knock joke",
            "Knock Knock\nWho's there?\nEurope\nEurope who?\nNo, you're a poo!",
            "Knock Knock\nWho's there?\nAnita\nAnita who\nAnita suck your dick!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_favourites);

        listView = (ListView) findViewById(R.id.favJokes);
        adapter = new ArrayAdapter<String>(this, R.layout.listview, R.id.textView1, jokeHeadings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.textView2);
                textView.setText(jokeContent[position]);

                if ( textView.getVisibility() == View.GONE)
                {
                    //expandedChildList.set(arg2, true);
                    textView.setVisibility(View.VISIBLE);
                }
                else
                {
                    //expandedChildList.set(arg2, false);
                    textView.setVisibility(View.GONE);
                }
            }
        });
    }
}
