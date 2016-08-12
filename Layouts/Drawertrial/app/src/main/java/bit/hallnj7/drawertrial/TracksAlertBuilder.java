package bit.hallnj7.drawertrial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class TracksAlertBuilder extends DialogFragment
{
    public TracksAlertBuilder() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(R.array.trackArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        Dialog customDialog = builder.create();

        return customDialog;
    }
}
