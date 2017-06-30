package me.sunshu.homeapp.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.sunshu.homeapp.R;
import me.sunshu.homeapp.activity.base.BaseActivity;
import me.sunshu.homeapp.view.fragment.home.HomeFragment;
import me.sunshu.homeapp.view.fragment.home.MineFagment;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * fragment 相关
     */

    private FragmentManager fm;
    private Fragment mHomeFragment;
    private Fragment mMineFragment;
    private Fragment mMessageFragment;
    private View mHomeView;
    private View mMessageView;
    private View mMineView;


    /**
     * 首页Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 初始化view
        initView();

        // 添加默认显示的fragment
        mHomeFragment = new HomeFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout,mHomeFragment);
        fragmentTransaction.commit();


    }

    private void initView() {
        mHomeView = findViewById(R.id.home_layout_view);
        mMessageView = findViewById(R.id.message_layout_view);
        mMineView = findViewById(R.id.mine_layout_view);

        mHomeView.setOnClickListener(this);
        mMessageView.setOnClickListener(this);
        mMineView.setOnClickListener(this);

        mHomeView = (TextView) findViewById(R.id.home_image_view);
//        mPondView = (TextView) findViewById(R.id.fish_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mMineView = (TextView) findViewById(R.id.mine_image_view);


    }

    /**
     * 用来隐藏具体fragment
     */
    private void hideFragment(Fragment fragment,FragmentTransaction fragmentTransaction){
        if (fragment != null){
            fragmentTransaction.hide(fragment);
        }

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (v.getId()){
            case R.id.home_layout_view:

                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mMessageFragment,fragmentTransaction);
                hideFragment(mMineFragment,fragmentTransaction);
                // 将HomeFragment 显示出来
                if (mHomeFragment == null){
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout,mHomeFragment);
                }else{
                    fragmentTransaction.show(mHomeFragment);
                }

                break;
            case R.id.message_layout_view:

                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);


                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mMineFragment,fragmentTransaction);
                // 将HomeFragment 显示出来
                if (mHomeFragment == null){
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout,mHomeFragment);
                }else{
                    fragmentTransaction.show(mHomeFragment);
                }

                break;
            case R.id.mine_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);


                hideFragment(mHomeFragment,fragmentTransaction);
                hideFragment(mMineFragment,fragmentTransaction);
                // 将HomeFragment 显示出来
                if (mMineFragment == null){
                    mMineFragment = new MineFagment();
                    fragmentTransaction.add(R.id.content_layout,mMineFragment);
                }else{
                    fragmentTransaction.show(mMineFragment);
                }

                break;
        }

    }
}
