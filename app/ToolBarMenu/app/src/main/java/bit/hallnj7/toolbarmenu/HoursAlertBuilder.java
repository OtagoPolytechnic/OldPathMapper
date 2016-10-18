package bit.hallnj7.toolbarmenu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

public class HoursAlertBuilder extends DialogFragment
{
    public HoursAlertBuilder() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder hourBuilder = new AlertDialog.Builder(getActivity());

        hourBuilder.setIcon(R.drawable.information);
        hourBuilder.setTitle("Information");
        hourBuilder.setItems(R.array.hoursArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        Dialog customDialog = hourBuilder.create();

        return customDialog;
    }
}
