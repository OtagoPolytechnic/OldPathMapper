package bit.hallnj7.drawertrial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

public class InformationAlertBuilder extends DialogFragment
{
    HoursAlertBuilder hourAlert;
    String[] items = { "John", "Michael", "Vincent", "Dalisay" };

    public InformationAlertBuilder() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder infoBuilder = new AlertDialog.Builder(getActivity());

        infoBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                String chosen = items[item];
                if (chosen == "John")
                {
                    hourAlert = new HoursAlertBuilder();
                    FragmentManager hourFragm = getFragmentManager();
                    hourAlert.show(hourFragm, "confirm");
                }
            }
        });

        Dialog customDialog = infoBuilder.create();

        return customDialog;
    }
}
