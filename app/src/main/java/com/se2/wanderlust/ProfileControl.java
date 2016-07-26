package com.se2.wanderlust;

import android.widget.TextView;

/**
 * This class represents the profile controller.
 * It shows the profiles fields of the user.
 * Created by
 * Team Wanderlust on 05.06.2016.
 */
public class ProfileControl {
    private final MainActivity act;

    private final TextView name;
    private final TextView lastname;
    private final TextView email;

    /**
     * Creates a instance of the ProfileControl object
     * @param mainActivity from main activity
     */
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
