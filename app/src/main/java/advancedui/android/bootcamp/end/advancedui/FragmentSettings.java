package advancedui.android.bootcamp.end.advancedui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by igor on 2016-08-19
 */
public class FragmentSettings extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings);
    }
}