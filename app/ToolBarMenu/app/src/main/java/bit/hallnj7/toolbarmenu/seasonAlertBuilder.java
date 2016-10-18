package bit.hallnj7.toolbarmenu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class seasonAlertBuilder extends DialogFragment
{
    public seasonAlertBuilder() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder seasonBuilder = new AlertDialog.Builder(getActivity());

        seasonBuilder.setIcon(R.drawable.information);
        seasonBuilder.setTitle("Seasonal Attractions");
        seasonBuilder.setItems(R.array.seasonalArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        Dialog customDialog = seasonBuilder.create();

        return customDialog;
    }
}
