package com.joh.qiyudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import com.qiyukf.unicorn.api.pop.Session;

import java.util.List;

/**
 * 首页
 *
 * @author : Joh_hz
 * @date : 2019/5/24 15:26
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 最近一条消息记录
     */
    TextView tvMsg;
    RelativeLayout layoutCust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMsg = (TextView) findViewById(R.id.tv_msg);
        layoutCust = (RelativeLayout) findViewById(R.id.layout_cust);

        // 设置当前访问用户的信息
        UnicornManager.setUnicornUserInfo();

        // 设置七鱼客服对话页面的UI
        UnicornManager.setUiCustomization();

        // 设置未读消息监听
        setCountChangeListener();

        // 点击事件
        layoutCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入七鱼客服首页（即智能机器人页面）
                UnicornManager.inToUnicorn(getApplicationContext());
            }
        });
    }

    /**
     * 客服未读消息监听
     */
    private void setCountChangeListener() {
        UnicornManager.addUnreadCountChangeListener(new UnreadCountChangeListener() {
            @Override
            public void onUnreadCountChange(int count) {
                // count 为当前未读数，
                // custNoReadCount = count;
                /**
                 * 获取和客服的最后一条聊天消息内容。
                 * 可用于未读消息变化时，展示最后一条未读消息，或者展示客服的最后一条消息。
                 * @return 最后一条消息
                 */
                List<Session> sessionList = UnicornManager.getSessionList();
                if (sessionList != null && sessionList.size() > 0) {
                    Session message = sessionList.get(0);
                    if (message != null && !TextUtils.isEmpty(message.getContent())) {
                        tvMsg.setText(message.getContent());
                    } else {
                        tvMsg.setText("暂无消息");
                    }
                }
            }
        });

        Unicorn.addUnreadCountChangeListener(new UnreadCountChangeListener() {
            @Override
            public void onUnreadCountChange(int count) {
                // count 为当前未读数，
                // custNoReadCount = count;
                /**
                 * 获取和客服的最后一条聊天消息内容。
                 * 可用于未读消息变化时，展示最后一条未读消息，或者展示客服的最后一条消息。
                 * @return 最后一条消息
                 */
                List<Session> sessionList = UnicornManager.getSessionList();
                if (sessionList != null && sessionList.size() > 0) {
                    Session message = sessionList.get(0);
                    if (message != null && !TextUtils.isEmpty(message.getContent())) {
                        tvMsg.setText(message.getContent());
                    } else {
                        tvMsg.setText("暂无消息");
                    }
                }
            }
        }, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销网易七鱼客服
        UnicornManager.logout();
    }
}
