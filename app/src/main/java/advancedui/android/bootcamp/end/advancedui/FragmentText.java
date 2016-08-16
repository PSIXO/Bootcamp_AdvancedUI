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
public class FragmentText extends Fragment implements TextFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_text, container, false);
        String text = getArguments().getString("text");
        ((TextView) v.findViewById(R.id.text)).setText(text);
        return v;
    }

    @Override
    public TextView findTextContent() {
        return (TextView) getView().findViewById(R.id.text);
    }

}
