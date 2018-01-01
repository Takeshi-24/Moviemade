package org.michaelbel.application.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.michaelbel.application.R;
import org.michaelbel.application.rest.model.Movie;
import org.michaelbel.application.rest.model.Trailer;
import org.michaelbel.application.ui.fragment.TrailersFragment;
import org.michaelbel.application.ui.view.TitleView;
import org.michaelbel.application.util.AndroidUtilsDev;

import java.util.ArrayList;

public class TrailersActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public TitleView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(AndroidUtilsDev.floatingToolbar() ? AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP : 0);
        toolbar.setLayoutParams(params);

        titleView = findViewById(R.id.toolbar_title);

        if (savedInstanceState == null) {
            ArrayList<Trailer> list = (ArrayList<Trailer>) getIntent().getSerializableExtra("list");
            Movie movie = (Movie) getIntent().getSerializableExtra("movie");

            startFragment(TrailersFragment.newInstance(movie, list));
        }
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .commit();
    }
}