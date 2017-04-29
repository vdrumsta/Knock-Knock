package com.notfunny.knockknock2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    private ImageButton heart;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private JokeDBHandler handler;
    private ArrayList<Integer> favJokeIDs;
    private ArrayList<ArrayList<String>> favJokes;
    private String[] jokeHeadings;
    private String[] jokeContent;
    private int selectedJoke;
    private boolean jokeFavourited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_favourites);

        selectedJoke = -1;
        jokeFavourited = false;
        heart = (ImageButton) findViewById(R.id.Heart);

        // Set up the Joke Database handler
        File f = new File(getBaseContext().getExternalCacheDir() + "/jokesDB.sqlite");
        handler = new JokeDBHandler(this, null, null, 1, f.exists());

        // Retrieve IDs of favourited jokes
        favJokeIDs = handler.getFavouritedJokes();

        // If there are some favourited jokes, retrieve their content
        if (favJokeIDs != null) {
            String[] temp;
            favJokes = new ArrayList<ArrayList<String>>();

            for (int i = 0; i < favJokeIDs.size(); i++) {
                favJokes.add(new ArrayList<String>());

                temp = handler.getJoke(favJokeIDs.get(i)).split(";");

                // Add the joke contents to favJokes
                for (int j = 0; j < temp.length; j++) {
                    favJokes.get(i).add(temp[j]);
                }
            }

            jokeHeadings = new String[favJokes.size()];
            jokeContent = new String[favJokes.size()];

            for (int i = 0; i < favJokes.size(); i++) {
                jokeHeadings[i] = favJokes.get(i).get(0);
                jokeContent[i] = "Knock Knock!\nWho'sThere?";
                for (int j = 0; j < favJokes.get(i).size(); j++) {
                    jokeContent[i] += "\n" + favJokes.get(i).get(j);
                }
            }

            listView = (ListView) findViewById(R.id.favJokes);
            adapter = new ArrayAdapter<String>(this, R.layout.listview, R.id.textView1, jokeHeadings);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) view.findViewById(R.id.textView2);
                    textView.setText(jokeContent[position]);

                    if (textView.getVisibility() == View.GONE) {
                        for (int i = 0; i < listView.getChildCount(); i++) {
                            listView.getChildAt(i).findViewById(R.id.textView2).setVisibility(View.GONE);
                        }

                        textView.setVisibility(View.VISIBLE);
                        selectedJoke = position;
                        jokeFavourited = handler.getJokeFavourited(favJokeIDs.get(selectedJoke));

                        if (jokeFavourited) {
                            heart.setBackgroundResource(R.drawable.heart_colored);
                        } else {
                            heart.setBackgroundResource(R.drawable.heart_uncolored);
                        }
                    } else {
                        textView.setVisibility(View.GONE);
                        selectedJoke = -1;
                    }
                }
            });
        }
        OnClickButtonListener();
    }

    private void OnClickButtonListener() {
        heart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedJoke >= 0) {
                            String toastMessage;

                            handler.toggleFavourite(favJokeIDs.get(selectedJoke));
                            jokeFavourited = !jokeFavourited;

                            if (jokeFavourited) {
                                listView.getChildAt(selectedJoke).setAlpha(1.0f);
                                heart.setBackgroundResource(R.drawable.heart_colored);
                                toastMessage = "Favourited";
                            } else {
                                listView.getChildAt(selectedJoke).setAlpha(0.2f);
                                heart.setBackgroundResource(R.drawable.heart_uncolored);
                                toastMessage = "Unfavourited";
                            }

                            Toast.makeText(FavouritesActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
