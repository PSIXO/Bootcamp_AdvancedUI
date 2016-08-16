package advancedui.android.bootcamp.end.advancedui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by APPSOLUTISTTA-PC on 2016-08-16
 */
public class MyPagerAdapter extends FragmentPagerAdapter {


    TextFragment currentFragment;


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            currentFragment = new FragmentWillBeContent();
            return new FragmentWillBeContent();
        } else {
            return new FragmentThereWasContentContent();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public TextFragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "" + (position + 1);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = (TextFragment) object;
        }
        super.setPrimaryItem(container, position, object);
    }
}
