package com.overall.developer.overrendicion.ui.user.interactor.Login;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goka.kenburnsview.KenBurnsView;
import com.goka.kenburnsview.LoopViewPager;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.RendicionApplication;
import com.overall.developer.overrendicion.data.model.entity.UserEntity;
import com.overall.developer.overrendicion.data.repository.Login.LoginRepository;
import com.overall.developer.overrendicion.data.repository.Login.LoginRepositoryImpl;
import com.overall.developer.overrendicion.ui.user.presenter.Login.LoginPresenter;
import com.overall.developer.overrendicion.ui.user.view.Login.ImageList.ImageList;
import com.overall.developer.overrendicion.utils.Util;


import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by terry on 3/9/2018.
 */

public class LoginInteractorImpl implements LoginInteractor
{
    private static final String TAG = "LoginInteractorImpl";
    private LoginPresenter mPresenter;
    private LoginRepository mRepository;
    private Context mContext;


    public LoginInteractorImpl(LoginPresenter loginPresenter, Context context)
    {
        this.mPresenter = loginPresenter;
        mRepository = new LoginRepositoryImpl(this);
        mContext = context;
    }

    //region Efecto BackGround

    @Override
    public boolean checkLogin()
    {
        return mRepository.checkLogin();
    }

    @Override
    public void backgroundAnimation(KenBurnsView kenBurnsView, FrameLayout frameLayout)
    {
        initializeKenBurnsView(kenBurnsView,frameLayout);
    }

    private void initializeKenBurnsView(final KenBurnsView mImage, FrameLayout mViewPagerFrame)
    {
        mImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        List<Integer> resourceIDs = Arrays.asList(ImageList.IMAGES_RESOURCE);
        mImage.initResourceIDs(resourceIDs);

        LoopViewPager.LoopViewPagerListener listener = new LoopViewPager.LoopViewPagerListener() {
            @Override
            public View OnInstantiateItem(int page) {
                TextView counterText = new TextView(RendicionApplication.getContext());
                return counterText;
            }

            @Override
            public void onPageScroll(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mImage.forceSelected(position);
            }

            @Override
            public void onPageScrollChanged(int page) {
            }
        };

        LoopViewPager loopViewPager = new LoopViewPager(RendicionApplication.getContext(), resourceIDs.size(), listener);
        mViewPagerFrame.addView(loopViewPager);
        mImage.setPager(loopViewPager);
    }
    // endregion


    //region Login
    @Override
    public void loginAccess(String usuario, String password)
    {
        searchTypeConnect(usuario, password);

    }

    @Override
    public void loginRecovery(String dniUser)
    {
        if (Util.isOnline()) mRepository.loginRecovery(dniUser);
            else Toast.makeText(mContext,String.valueOf(mContext.getString(R.string.servidorError)), Toast.LENGTH_LONG).show();


    }

    @Override
    public void passwordRecoveryResponse(String message)
    {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

    }
    @Override
    public void passwordRecoveryError(String message)
    {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Observable validateSucces(String dniUser, String nombreUser, String emailUser)
    {
        return mPresenter.validateSucces(dniUser, nombreUser, emailUser);

    }

    @Override
    public Observable validateError(String errorMessaje) {
        return mPresenter.validateError(errorMessaje);
    }



    private void searchTypeConnect(String usuario, String password)
    {
        if (Util.isOnline())
        {
            mRepository.validateUserApi(new UserEntity(usuario,password));

        }else
        {

            if (mRepository.searchUserBD())  mRepository.validateUserDB(usuario, password);

            else mRepository.validateError(mContext.getResources().getString(R.string.sessionError));


        }

    }
    //endregion
}
