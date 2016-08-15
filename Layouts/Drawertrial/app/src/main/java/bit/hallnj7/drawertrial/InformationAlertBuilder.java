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
    StatisticsAlertBuilder statisticAlert;
    SeasonAlertBuilder seasonAlert;
    ProhibitedAlertBuilder prohibitAlert;
    String[] items = { "Hours", "Prohibited", "Statistics", "Seasonal attractions", "Back" };

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
                if (chosen == "Hours")
                {
                    hourAlert = new HoursAlertBuilder();
                    FragmentManager hourFragm = getFragmentManager();
                    hourAlert.show(hourFragm, "confirm");
                }

                else if (chosen == "Prohibited")
                {
                    prohibitAlert = new ProhibitedAlertBuilder();
                    FragmentManager prohibitFragm = getFragmentManager();
                    prohibitAlert.show(prohibitFragm, "confirm");
                }

                else if (chosen == "Statistics")
                {
                    statisticAlert = new StatisticsAlertBuilder();
                    FragmentManager statsFragm = getFragmentManager();
                    statisticAlert.show(statsFragm, "confirm");
                }
            }
        });

        Dialog customDialog = infoBuilder.create();

        return customDialog;
    }
}
