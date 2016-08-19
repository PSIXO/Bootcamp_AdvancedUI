package advancedui.android.bootcamp.end.advancedui;


import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.eftimoff.viewpagertransformers.BackgroundToForegroundTransformer;

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

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ViewGroup root;
    private ViewFlipper viewFlipper;

    private List<String> data = new ArrayList<>(
            Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data = data.subList(0, 3);

        toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbarTop);


        pager = (ViewPager) findViewById(R.id.pager);

        toolbarBottom = (Toolbar) findViewById(R.id.toolbar_bottom);
        toolbarBottom.inflateMenu(R.menu.bottom);

        navigationView = (NavigationView) findViewById(R.id.navigation);


        adapter = new MyStatePagerAdapter(getSupportFragmentManager(), data);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (data.size() > 3) {


            toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.placeholder_some_content, R.string.placeholder_some_content) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    ImageView headerImage = (ImageView) findViewById(R.id.header_image);
                    headerImage.setBackgroundResource(R.drawable.animated_image);
                    AnimationDrawable headerAnimation = (AnimationDrawable) headerImage.getBackground();
                    headerAnimation.start();
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    ImageView headerImage = (ImageView) findViewById(R.id.header_image);
                    headerImage.setBackgroundResource(R.drawable.animated_image);
                    AnimationDrawable headerAnimation = (AnimationDrawable) headerImage.getBackground();
                    headerAnimation.stop();
                    super.onDrawerClosed(drawerView);
                }
            };

            // Set the drawer toggle as the DrawerListener
            drawerLayout.addDrawerListener(toggle);
            drawerLayout.setScrimColor(Color.TRANSPARENT);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            tabLayout.setVisibility(View.GONE);
        } else {
            tabLayout.setupWithViewPager(pager);
        }

        //Or add navigation drawer header from code
        //navigationView.inflateHeaderView(R.layout.navigation_header);


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        if (menuItem.getNumericShortcut() != 0) {
                            pager.setCurrentItem(Integer.parseInt(String.valueOf(menuItem.getNumericShortcut())) - 1);
                            Menu menu = navigationView.getMenu();
                            for (int i = 0; i < menu.size(); i++) {
                                menu.getItem(i).setChecked(false);
                            }
                            drawerLayout.closeDrawers();
                        } else {
                            Toast.makeText(MainActivity.this, "Will open settings", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });

        Menu navigationMenu = navigationView.getMenu();

        SubMenu navigationSubmenu = navigationMenu.getItem(0).getSubMenu();
        for (String s : data.subList(2, data.size())) {
            navigationSubmenu.add(s).setNumericShortcut(Character.forDigit(data.indexOf(s) + 1, 10)).setCheckable(true);
        }

        root = (ViewGroup) findViewById(android.R.id.content);
        getLayoutInflater().inflate(R.layout.watermark, root, true);

        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

        pager.setPageTransformer(true, new BackgroundToForegroundTransformer());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if (toggle != null) {
            toggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (toggle != null) {
            toggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (toggle != null && toggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == R.id.create) {
            View v = findViewById(R.id.create);
            if (v.getTag() == null || !((Boolean) v.getTag())) {
                v.setTag(true);
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate));
            } else {
                v.setTag(false);
                v.clearAnimation();
            }

        } else if (item.getItemId() == R.id.delete) {
            ObjectAnimator objAnim = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.color_circle);
            objAnim.setTarget(adapter.getCurrentFragment().findTextContent());
            objAnim.start();
        } else {
            Toast.makeText(MainActivity.this, "Action " + item.getTitle(), Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
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
                        pager.setCurrentItem(data.indexOf(s));
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

    public void onSwitch(View view) {
        SwitchCompat switchCompat = (SwitchCompat) view;
        if (switchCompat.isChecked()) {
            getLayoutInflater().inflate(R.layout.watermark, root, true);
        } else {
            root.removeView(findViewById(R.id.watermark_text));
            root.removeView(findViewById(R.id.watermark_image));
        }
    }

    public void onActionNextToolbar(MenuItem item) {
        viewFlipper.setDisplayedChild(1);
    }

    public void onPreviousToolbar(View view) {
        viewFlipper.setDisplayedChild(2);
    }

    public void onClickText(View view) {
        final TextView text = (TextView) view;
        //Toast.makeText(MainActivity.this, "Hi I am " + text.getText().toString(), Toast.LENGTH_SHORT).show();
        final ViewPropertyAnimator animator = text.animate();
        animator.setDuration(250).scaleXBy(1.1f).scaleYBy(1.1f)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        //text.setTranslationY(-50);
                        animator.translationYBy(-50);
                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        if (text.getScaleX() > 4) {
                            animator.scaleX(1f).scaleY(1f);
                        }
                        animator.translationYBy(50);
                    }
                }).start();

        int colorFrom = getResources().getColor(R.color.colorAccent);
        int colorTo = getResources().getColor(R.color.black);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(600); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                text.setTextColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }
}
