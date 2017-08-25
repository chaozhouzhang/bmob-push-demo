package cn.bmob.push.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.push.R;
import cn.bmob.v3.BmobInstallation;
import rx.functions.Action1;

/**
 * Created on 17/8/24 16:29
 */

public class ChannelFragment extends BaseFragment {
    @BindView(R.id.tv_channel)
    TextView mTvChannel;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_channel, null);
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

    @OnClick({R.id.btn_subscribe, R.id.btn_unsubscribe, R.id.btn_channels})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_subscribe:
                BmobInstallation bmobInstallationSub = BmobInstallation.getCurrentInstallation();
                bmobInstallationSub.subscribe("NBA");
                bmobInstallationSub.subscribe("CBA");
                bmobInstallationSub.saveObservable()
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                toastI("订阅频道成功：" + s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                toastE("订阅频道失败：" + throwable.getMessage());
                            }
                        });
                break;
            case R.id.btn_unsubscribe:
                BmobInstallation bmobInstallationUnSub = BmobInstallation.getCurrentInstallation();
                bmobInstallationUnSub.unsubscribe("NBA");
                bmobInstallationUnSub.unsubscribe("CBA");
                bmobInstallationUnSub.saveObservable()
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                toastI("退订频道成功：" + s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                toastE("退订频道失败：" + throwable.getMessage());
                            }
                        });
                break;
            case R.id.btn_channels:
                BmobInstallation bmobInstallation = BmobInstallation.getCurrentInstallation();
                List<String> channels = bmobInstallation.getChannels();
                if (channels.size() < 1) {
                    toastI("您没有订阅任何频道！");
                } else {

                    for (String channel : channels) {
                        mTvChannel.append(channel + "\n");
                    }
                }
                break;
        }
    }
}
