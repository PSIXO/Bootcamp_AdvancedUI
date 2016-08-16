package advancedui.android.bootcamp.end.advancedui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarTop;
    private Toolbar toolbarBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbarTop);

        toolbarBottom = (Toolbar) findViewById(R.id.toolbar_bottom);
        toolbarBottom.inflateMenu(R.menu.bottom);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

}
