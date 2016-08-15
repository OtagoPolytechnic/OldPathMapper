package bit.hallnj7.drawertrial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ProhibitedAlertBuilder extends DialogFragment
{
    public ProhibitedAlertBuilder() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder hourBuilder = new AlertDialog.Builder(getActivity());

        hourBuilder.setItems(R.array.prohibitedArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        Dialog customDialog = hourBuilder.create();

        return customDialog;
    }
}
