package toandoan.framgia.com.rxjavaretrofit.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import toandoan.framgia.com.rxjavaretrofit.R;
import toandoan.framgia.com.rxjavaretrofit.databinding.ActivityMainBinding;
import toandoan.framgia.com.rxjavaretrofit.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private  ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Utils.disableShiftMode(mBinding.navigation);
    }
}
