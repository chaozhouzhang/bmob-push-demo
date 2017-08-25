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
                /**
                 * TODO 此处是SDK的bug，所获取的不是当前的设备信息，需等待数据SDK下个版本修复bug，或者自己操作Installation表解决。
                 * TODO 实现思路：先根据当前installationId查询线上installation数据，再对channel字段的数值进行增删查，订阅一个频道就是给channel数组增加一个数值，退订一个频道就是减少一个数值，查询频道就是查询这个数组，请注意在增加数值的时候需要做数值过滤，以防订阅了多个相同的频道。
                 */
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
                /**
                 * TODO 此处是SDK的bug，所获取的不是当前的设备信息，需等待数据SDK下个版本修复bug，或者自己操作Installation表解决。
                 * TODO 实现思路：先根据当前installationId查询线上installation数据，再对channel字段的数值进行增删查，订阅一个频道就是给channel数组增加一个数值，退订一个频道就是减少一个数值，查询频道就是查询这个数组，请注意在增加数值的时候需要做数值过滤，以防订阅了多个相同的频道。
                 */
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
                /**
                 * TODO 此处是SDK的bug，所获取的不是当前的设备信息，需等待数据SDK下个版本修复bug，或者自己操作Installation表解决。
                 * TODO 实现思路：先根据当前installationId查询线上installation数据，再对channel字段的数值进行增删查，订阅一个频道就是给channel数组增加一个数值，退订一个频道就是减少一个数值，查询频道就是查询这个数组，请注意在增加数值的时候需要做数值过滤，以防订阅了多个相同的频道。
                 */
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
