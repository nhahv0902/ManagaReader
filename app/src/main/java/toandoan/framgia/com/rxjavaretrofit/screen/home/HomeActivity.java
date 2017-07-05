package toandoan.framgia.com.rxjavaretrofit.screen.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityHomeBinding;
import toandoan.framgia.com.rxjavaretrofit.screen.BaseActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.Utils;

import static toandoan.framgia.com.rxjavaretrofit.screen.home.HomeActivity.HomeTab.DOWNLOAD;
import static toandoan.framgia.com.rxjavaretrofit.screen.home.HomeActivity.HomeTab.FAVORITE;
import static toandoan.framgia.com.rxjavaretrofit.screen.home.HomeActivity.HomeTab.MANGA;
import static toandoan.framgia.com.rxjavaretrofit.screen.home.HomeActivity.HomeTab.RECENT;
import static toandoan.framgia.com.rxjavaretrofit.screen.home.HomeActivity.HomeTab.SETTINGS;

/**
 * Home Screen.
 */
public class HomeActivity extends BaseActivity {

    private HomeContract.ViewModel mViewModel;
    private ViewPager mViewPager;
    private ActivityHomeBinding mBinding;
    private Menu mMenu;

    public static Intent getInstance(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new HomeViewModel(this);

        HomeContract.Presenter presenter = new HomePresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mBinding = binding;

        binding.setViewModel((HomeViewModel) mViewModel);
        mViewPager = binding.viewPager;
        ((HomeViewModel) mViewModel).setViewPager(mViewPager);

        Utils.disableShiftMode(binding.navigation);
        binding.navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mMenu.clear();
                        switch (item.getItemId()) {
                            default:
                            case R.id.navigation_home:
                                getMenuInflater().inflate(R.menu.home_manga_menu, mMenu);
                                onNavigationClick(MANGA);
                                setTitle(R.string.title_manga);
                                break;
                            case R.id.navigation_recent:
                                getMenuInflater().inflate(R.menu.recent_menu, mMenu);
                                onNavigationClick(RECENT);
                                setTitle(R.string.title_recent);
                                break;
                            case R.id.navigation_download:
                                getMenuInflater().inflate(R.menu.mangak_download_menu, mMenu);
                                onNavigationClick(DOWNLOAD);
                                setTitle(R.string.title_download);
                                break;
                            case R.id.navigation_favorite:
                                getMenuInflater().inflate(R.menu.recent_menu, mMenu);
                                onNavigationClick(FAVORITE);
                                setTitle(R.string.title_favorites);
                                break;
                            case R.id.navigation_setting:
                                onNavigationClick(SETTINGS);
                                setTitle(R.string.title_setting);
                                break;
                        }
                        item.setChecked(true);
                        return false;
                    }
                });
    }

    private void onNavigationClick(int openNavigation) {
        mViewPager.setCurrentItem(openNavigation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.home_manga_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                mViewModel.onSearchClick();
                break;
            case R.id.menu_filter:
                mViewModel.onFilterClick();
                break;
            case R.id.menu_source:
                mViewModel.onMenuSourceClick();
                break;
            case R.id.menu_clear:
                mViewModel.onMenuClearClick();
                break;
            case R.id.menu_downloading:
                mViewModel.onStartDownloadingActivity();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @IntDef({ MANGA, RECENT, DOWNLOAD, FAVORITE, SETTINGS })
    @interface HomeTab {
        int MANGA = 0;
        int RECENT = 1;
        int DOWNLOAD = 2;
        int FAVORITE = 3;
        int SETTINGS = 4;
    }
}
