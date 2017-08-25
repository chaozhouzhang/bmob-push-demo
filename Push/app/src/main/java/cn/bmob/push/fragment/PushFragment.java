package cn.bmob.push.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.push.R;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;

/**
 * Created on 17/8/24 16:29
 */

public class PushFragment extends Fragment {
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_push, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_broadcast, R.id.btn_multi_cast_channel, R.id.btn_multi_cast_platform,
            R.id.btn_multi_cast_location, R.id.btn_multi_cast_active, R.id.btn_multi_cast_query, R.id.btn_uni_cast_android,R.id.btn_uni_cast_ios})
    public void onViewClicked(View view) {
        BmobPushManager bmobPushManager = new BmobPushManager();
        switch (view.getId()) {
            case R.id.btn_broadcast:
                bmobPushManager.pushMessageAll("有listener的测试", new PushListener() {
                    @Override
                    public void done(BmobException e) {
                        Logger.e("异常：" + e.getMessage());
                    }
                });
                break;
            case R.id.btn_multi_cast_channel:

                break;
            case R.id.btn_multi_cast_platform:
                break;
            case R.id.btn_multi_cast_location:
                break;
            case R.id.btn_multi_cast_active:
                break;
            case R.id.btn_multi_cast_query:
                break;
            case R.id.btn_uni_cast_android:
                String installationId = "其他客户端installationId";
                BmobPushManager bmobPush = new BmobPushManager();
                BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
                query.addWhereEqualTo("installationId", installationId);
                bmobPush.setQuery(query);
                bmobPush.pushMessage("消息内容");

                break;
            case R.id.btn_uni_cast_ios:
                break;
        }
    }
}
