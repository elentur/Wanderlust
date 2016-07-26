package com.se2.wanderlust;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Marcus Bätz on 05.06.2016.
 */
public class ProfileControl {
    private final MainActivity act;

    private final TextView name;
    private final TextView lastname;
    private final TextView email;

    public ProfileControl(MainActivity mainActivity) {

        act = mainActivity;

        name = (TextView) act.findViewById(R.id.edtForeName);
        lastname = (TextView) act.findViewById(R.id.edtName);
        email = (TextView) act.findViewById(R.id.edtEmail);

        if(act.user != null){
            name.setText(act.user.getName());
            lastname.setText(act.user.getLastname());
            email.setText(act.user.getEmail());
        }

        // TODO speichern bei Änderung
    }


}
