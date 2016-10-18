package bit.hallnj7.toolbarmenu;

import android.app.FragmentManager;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    ImageView colourSquare;
    HoursAlertBuilder hoursAlert;
    seasonAlertBuilder seasonAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.colour_menu_list, menu);
        for (int i = 0; i < menu.size(); i++)
        {
            menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        colourSquare = (ImageView)findViewById(R.id.imageView);

        int itemID = item.getItemId();
        String itemTitle = (String) item.getTitle();

        colourSquare.setImageBitmap(null);
        int displayColour = Color.WHITE;

        switch(itemTitle)
        {
            case "Hours":
                hoursAlert = new HoursAlertBuilder();
                FragmentManager fm = getFragmentManager();
                hoursAlert.show(fm, "confirm");
                break;

            case "Seasonal Attractions":
                seasonAlert = new seasonAlertBuilder();
                FragmentManager fm2 = getFragmentManager();
                seasonAlert.show(fm2, "confirm");
                break;
        }

        colourSquare.setBackgroundColor(displayColour);

        return true;
    }
}
