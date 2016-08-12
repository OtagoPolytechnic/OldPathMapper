package bit.hallnj7.drawertrial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class HoursAlertBuilder extends DialogFragment
{
    public HoursAlertBuilder() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder hourBuilder = new AlertDialog.Builder(getActivity());

        hourBuilder.setMessage(R.string.openingHours);

        Dialog customDialog = hourBuilder.create();

        return customDialog;
    }
}
