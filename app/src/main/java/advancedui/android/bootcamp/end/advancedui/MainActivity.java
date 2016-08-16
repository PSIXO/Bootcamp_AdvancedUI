package advancedui.android.bootcamp.end.advancedui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarTop;
    private TabLayout tabLayout;
    private TextView text;
    private Toolbar toolbarBottom;

    private ViewPager pager;
    private MyStatePagerAdapter adapter;

    private List<String> data = new ArrayList<>(
            Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbarTop);


        pager = (ViewPager) findViewById(R.id.pager);

        toolbarBottom = (Toolbar) findViewById(R.id.toolbar_bottom);
        toolbarBottom.inflateMenu(R.menu.bottom);


        adapter = new MyStatePagerAdapter(getSupportFragmentManager(), data);
        pager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    public void onActionAlert(MenuItem item) {
        Toast.makeText(MainActivity.this, "On Alert", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                for (String s : data) {
                    if (s.toLowerCase().startsWith(query.toLowerCase())) {
                        text.setText(s);
                        return false;
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        Intent intentShare = new Intent();
        intentShare.setAction(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        TextFragment f = adapter.getCurrentFragment();
        text = f.findTextContent();
        intentShare.putExtra(Intent.EXTRA_TEXT, text.getText().toString());
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.menu_item_share));
        mShareActionProvider.setShareIntent(intentShare);

        return true;
    }
}
