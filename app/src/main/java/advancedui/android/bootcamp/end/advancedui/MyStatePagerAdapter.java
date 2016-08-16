package advancedui.android.bootcamp.end.advancedui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by APPSOLUTISTTA-PC on 2016-08-16
 */
public class MyStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<String> data;
    private Fragment currentFragment;

    public MyStatePagerAdapter(FragmentManager fm, List<String> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        currentFragment = new FragmentText();
        Bundle args = new Bundle();
        args.putString("text", data.get(position));
        currentFragment.setArguments(args);
        return currentFragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public TextFragment getCurrentFragment() {
        return (TextFragment) currentFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "" + (position + 1);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = (Fragment) object;
        }
        super.setPrimaryItem(container, position, object);
    }
}
