package advancedui.android.bootcamp.end.advancedui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by igor on 2016-08-16
 */
public class FragmentThereWasContentContent extends Fragment implements TextFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_there_was_content, container, false);
    }

    @Override
    public TextView findTextContent() {
        return (TextView) getView().findViewById(R.id.text2);
    }
}
