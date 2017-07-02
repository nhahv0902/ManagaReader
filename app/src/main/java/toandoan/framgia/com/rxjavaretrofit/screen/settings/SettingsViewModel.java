package toandoan.framgia.com.rxjavaretrofit.screen.settings;

import android.content.Intent;
import android.net.Uri;
import toandoan.framgia.com.rxjavaretrofit.AppApplication;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.screen.title.TitleActivity;
import toandoan.framgia.com.rxjavaretrofit.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Settings screen.
 */

public class SettingsViewModel implements SettingsContract.ViewModel {

    private SettingsContract.Presenter mPresenter;
    private Navigator mNavigator;

    public SettingsViewModel(Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onShareAppClick() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT,
                    AppApplication.getContext().getString(R.string.app_name));
            String appLink =
                    "https://play.google.com/store/apps/details?id=" + AppApplication.getContext()
                            .getPackageName();
            i.putExtra(Intent.EXTRA_TEXT, appLink);
            mNavigator.startActivity(Intent.createChooser(i, "Choose one"));
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void onRateAppClick() {
        mNavigator.startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + AppApplication.getContext().getPackageName())));
    }

    @Override
    public void onDisclaimerClick() {
        mNavigator.startActivity(
                TitleActivity.getInstance(mNavigator.getContext(), R.string.title_disclaimer,
                        R.string.description_disclaimer));
    }

    @Override
    public void onAboutUsClick() {
        mNavigator.startActivity(
                TitleActivity.getInstance(mNavigator.getContext(), R.string.title_about_us,
                        R.string.description_about_us));
    }
}
