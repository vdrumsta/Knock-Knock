package com.notfunny.knockknock2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class JokeDBHandler extends SQLiteOpenHelper {

    /* The SQL code to run which populates the database if it doesn't exist yet */
    private static final String defaultSql =
        "CREATE TABLE jokes (_id INTEGER PRIMARY KEY, content TEXT, category TEXT, favourite BOOLEAN DEFAULT FALSE) \n" +
        "INSERT INTO jokes (content, category) VALUES ('Lettuce;Lettuce Who?;Lettuce in!','random') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Ahmed;Ahmed who?;Ahmed the payphone trying to call home. All of my change I spent on you.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Daisy!;Daisy who?;Daisy me rollin, they hatin;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Wendy!;Wendy who?;Wendy wind blows de cradle will fall.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Britney Spears!;Britney Spears who?;Knock Knock;Who'+\"'\"+'s There?;Oops I did it again;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Usher!;Usher who?;Usher wish you would let me in.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Hello!;Hello who?;Hello from the other side I must'+\"'\"+'ve called a thousand times.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Elvis!;Elvis who?;Elvis has left the building.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Rain!;Rain who!?;U rain nothing but a hound dog!!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Joana!;Joana who?;I JOANA CLOSE MY EYES, I JOANA FALL ASLEEP '+\"'\"+'CAUSE I MISS YOU BABE AND I DON'+\"'\"+'T WANNA MISS A THING;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('A mall!;A mall who?;A mall shook up!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Allah!;Allah who?;Allah these stars will guide us home.;','musicsubline') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Truffle.;Truffle who?;Truffle with love is, it can tear you up inside.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Why!;Why who?;WHY-M-C-A!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Everybody;Everybody who?;Everybody was Kung fu fighting.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Slum dog!;Slum dog who?;I slum dog a land down under!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Leaf.;Leaf who?;Leaf it all to me...;('+\"'\"+'Leave It All to Me'+\"'\"+' iCarly theme);','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('you;you who?;yoo-hoo big summer blowout.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Tissue.;Tissue Who?;All I Want For Christmas Tissue...;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Bully!;Bully who?;Bully Jean is not my lover.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Radio!;Radio who?;Radio (ready or) not here I come, you can'+\"'\"+'t hide.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Hungry!;Hungry who?;Smell like I sound, I'+\"'\"+'m lost in a crowd. And I'+\"'\"+'m hungry like the wolf.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Kenya!;Kenya who?;Kenya feel the love tonight!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Haiti!;Haiti who?;Haiti see your heartbreak.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Barbara!;Barbara who?;Barbara black sheep have you any wool?;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Donald!;Donald who?;Donald come baby cradle in all.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Amal!;Amal Who?;I'+\"'\"+'m in love, Amal shook up;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Ella.;Ella who?;Ella '+\"'\"+'bout that bass, '+\"'\"+'bout that bass, no treble...;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Harrison!;Harrison who?;Harrison (here is a song) for the broken hearted.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Rupert!;Rupert who?;Rupert (you put) your left foot in and you shake it all about.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Shelby!;Shelby who?;Shelby comin'+\"'\"+' round the mountain when she comes..!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Chopin!;Chopin who?;Let'+\"'\"+'s go Chopin (shopping) for a new piano.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Turnip.;Turnip who?;Turnip the volume, it'+\"'\"+'s my favorite song!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Goat;Goat who?;Goat to believe in magic;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Goat;Goat who?;Goat love you! (yeah yeah) (baby girl);','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Sexy!;Sexy who?;Sexy Lady, OP OP OP OP OP OPPAN GANGNAM STYLE.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Jamaican!;Jamaican who?;'+\"'\"+'Jamaica wanna scream and shout and let it all out'+\"'\"+';','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Baghdad!;Baghdad who?;Girl, you looks good, won'+\"'\"+'t you Baghdad ass up;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Rhoda!;Rhoda who?;Row, Row, Rhoda boat...!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Ringo!;Ringo who?;Ringo round the roses!;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Wendy!;Wendy who?;Wendy wind blows de cradle will fall.;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Barbara!;Barbara who?;Barbara black sheep have you any wool?;','music') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Art;Art Who?;R2-D2;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Toby;Toby Who?;Toby or not Toby, that is the question.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Carrot;Carrot Who?;Karate Chop!;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Yah;Yah Who?;Yahoo, lets Party!;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('2:30;2:30 who?;I made a dentist appointment cause my 2:30 (tooth hurty);','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Atch;Atch Who?;God Bless You!;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Yah.;Yah who;Nooo! Thanks I use Google.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Georgia;Georgia Who?;Georgia the Jungle, watch out for that tree!;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Two 4'+\"'\"+'s;Two 4'+\"'\"+'s who?;No need to make lunch we already 8.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Ya;Ya who?;.com;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('USB!;USB who?;USB makes better honey.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('C.;C Who?;I'+\"'\"+'m coming to see you!;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Broccoli;Broccoli Who?;Broccoli doesn'+\"'\"+'t have a last name, silly.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Narnia!;Narnia who?;Narnia business.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Cotton!;Cotton who?;Cotton a trap, can you help me out.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Juicy.;Juicy who?;Juicy (did you see) last nights episode of the Big Bang Theory?;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Wire.;Wire who?;Wire you asking i just told you.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Weirdo.;Weirdo who?;Weirdo you think you'+\"'\"+'re going?;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Olive;Olive Who?;Olive You;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Cozy!;Cozy who?;Cozy whose knocking please.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Stopwatch!;Stopwatch who?;Stopwatch your doing and pay attention!;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Spell!;Spell who?;W H O;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Heart...;Heart who?;I'+\"'\"+'m having a Heart (hard) time opening the door.;','nerdy') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Africa!;Africa who?;Don'+\"'\"+'t go cause African love you.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Amazon!;Amazon who?;Amazon of a gun.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Amsterdam!;Amsterdam who?;Amsterdam tired of your lies.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Beirut!;Beirut who?;Open the door or I'+\"'\"+'ll have to use Beirut force.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Boise!;Boise who?;One man'+\"'\"+'s meat is another man'+\"'\"+'s Boise.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Botswana!;Botswana who?;Botswana follow me on Twitter.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Congo!;Congo who?;Actually you Congo home again.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Czech!;Czech who?;Czech your ego at the door.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Denial!;Denial who?;Denial is in Egypt, but I'+\"'\"+'m right here.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Vampire!;Vampire who?;Vampire (Empire) State Building.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Glasgow!;Glasgow who?;Glasgow to the beach.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Greece!;Greece who?;Are Greece and oil the same thing?;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Hawaii.;Hawaii who?;I'+\"'\"+'m fine, how are you?;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Irish.;Irish who?;Irish I knew better knock knock jokes.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Kuwait!;Kuwait who?;Why does your girlfriend make Kuwait on her hand and foot?;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Kyoto!;Kyoto who?;I will Kyoto any length to open this door.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Mecca!;Mecca who?;Love Mecca the world go round.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Nebraska!;Nebraska who?;Nebraska woman her age.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Oman!;Oman who?;'+\"'\"+'Oh man, I shot Marvin in the face!'+\"'\"+' (Pulp Fiction</i>;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Quebec!;Quebec who?;Quebec to the end of the line!;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Rome!;Rome who?;Rome is where the heart is!;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Tibet!;Tibet who?;Early Tibet and early to rise!;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Uganda!;Uganda who?;Uganda get away with this!;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Utica!;Utica who?;Utica the high road. I'+\"'\"+'ll take the low road.;','geography') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Goat!;Goat who?;Goat on a limb and open the door.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Toucan!;Toucan who?;Toucan play that game!;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Wood Ant!;Wood Ant who?;Don'+\"'\"+'t be afraid. Wood ant harm a fly.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Baby Owl.;Baby Owl who?;Owl see you later.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Whale!;Whale who?;Isn'+\"'\"+'t this a WHALE of a good time.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Spider!;Spider who?;Spider what everyone says. I like you!;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Safari!;Safari who?;Safari so good!;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Flea!;Flea who?;Fleas a jolly good fellow.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Owl!;Owl who?;Owl Aboard!;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Nana;Nana who?;Nana your bees wax!;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Quacker!;Quacker who?;Quacker another bad joke and I'+\"'\"+'m leaving!;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Porpoise!;Porpoise who?;For all intents and porpoises the case is closed.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Chickens!;Chickens who?;Chickens come home to roost.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Duncan!;Duncan who?;Duncan your chickens before they hatch.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Rhino!;Rhino who?;Rhino every knock knock joke there is.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Rabbit!;Rabbit who?;Rabbit up carefully, it'+\"'\"+'s fragile;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Goat!;Goat who?;Goat to believe in magic.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Bee;bee who?;Bee at the door when I knock;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Yak!;Yak who?;Actually, I prefer Google.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Bass!;Bass who?;Bass the salt and pepper please!;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Gorilla;Gorilla who;Gorilla me a hamburger;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Roof;Roof who?;I had a roof day;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Whale;Whale who?;Whale whale whale what do we have here?;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Chimp!;Chimp who?;'+\"'\"+'A chimp off the old block'+\"'\"+'.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Fossil!;Fossil who?;Fossil last time open this door.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Who;Who Who?;Sorry I don'+\"'\"+'t speak to owls!;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Cod!;Cod who?;I was cod in the crossfire.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Fangs!;Fangs who?;Fangs for letting me in.;','animals') \n" +
        "INSERT INTO jokes (content, category) VALUES ('Dragon;Dragon who?;Stop dragon your feet get to work.;','animals') \n";


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "jokesDB.sqlite";

    private static final String TABLE_JOKES = "jokes";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_FAVOURITE = "favourite";

    private Context context;

    public JokeDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, boolean exists) {
        super(context, context.getExternalCacheDir() + "/" + DATABASE_NAME, factory, DATABASE_VERSION);

        /* Populate the database with default table if the file doesn't exist yet */
        if (!exists) {
            SQLiteDatabase db = this.getWritableDatabase();
            // execute the statements one by one
            for (String statement : defaultSql.split("\n")) {
                db.execSQL(statement);
            }
        }

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /* get a random joke from the given category */
    public String getJoke(String category) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_JOKES +
                " WHERE " + COLUMN_CATEGORY + " = \"" + category + "\"" +
                " ORDER BY RANDOM()";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //Log.d("joke", cursor.getString(1).toString());
            return cursor.getString(0).toString() + ";" + cursor.getString(1).toString();
        }
        return null;
    }

    /* get the content of the joke with the given id */
    public String getJoke(int jokeId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_JOKES +
                " WHERE " + COLUMN_CATEGORY + " = " + jokeId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
            return cursor.getString(1);
        return null;
    }

    /* Favourite a joke if it isn't currently favourited, and vice versa */
    public void toggleFavourite(int jokeId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String favQuery = "SELECT " + COLUMN_FAVOURITE
                + " FROM " + TABLE_JOKES
                + " WHERE " + COLUMN_ID + " = " + String.valueOf(jokeId);
        Cursor cursor = db.rawQuery(favQuery, null);

        if (!cursor.moveToFirst()) {
            Log.e("JOKE", "joke with id = " + jokeId + " not found");
            return;
        }

        Log.d("JOKE", "sssss " + cursor.getString(0));
        boolean favourited = cursor.getString(0).equalsIgnoreCase("TRUE");

        String command = "UPDATE " + TABLE_JOKES +
                " SET " + COLUMN_FAVOURITE + " = " + (!favourited ? "\"TRUE\"" : "\"FALSE\"")
                + " WHERE " + COLUMN_ID + " = " + String.valueOf(jokeId);
        db.execSQL(command);
    }

    /* unfavourite the joke with the given id */
    public void unFavourite(int jokeId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String command = "UPDATE " + TABLE_JOKES +
                " SET " + COLUMN_FAVOURITE + " = \"FALSE\"" +
                " WHERE " + COLUMN_ID + " = " + String.valueOf(jokeId);
        db.execSQL(command);
    }

    /* Returns an ArrayList of currently favourited jokes */
    public ArrayList<Integer> getFavouritedJokes() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_JOKES +
                " WHERE " + COLUMN_FAVOURITE + " = \"TRUE\"";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            ArrayList<Integer> result = new ArrayList<Integer>();

            do {
                result.add(cursor.getInt(0));
            } while (cursor.moveToNext());

            return result;
        }

        // return null if no favourited jokes were found
        return null;
    }

    /* Return true if the joke with the given id is favourited */
    public boolean getJokeFavourited(int jokeId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_FAVOURITE + " FROM " + TABLE_JOKES +
                " WHERE " + COLUMN_ID + " = " + String.valueOf(jokeId);
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
            return cursor.getString(0).equalsIgnoreCase("TRUE");

        Log.d("fav", "Joke with id = " + String.valueOf(jokeId) + " not found");
        return false;
    }
}