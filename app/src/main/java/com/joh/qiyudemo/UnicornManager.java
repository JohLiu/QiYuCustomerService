package com.joh.qiyudemo;

import android.content.Context;

import com.google.gson.Gson;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.UICustomization;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.qiyukf.unicorn.api.msg.UnicornMessage;
import com.qiyukf.unicorn.api.pop.POPManager;
import com.qiyukf.unicorn.api.pop.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * 七鱼工具类
 *
 * @author : Joh_hz
 * @date : 2019/5/24 15:27
 */
public class UnicornManager {
    /**
     * 进入智能客服页面
     *
     * @param context
     */
    public static void inToUnicorn(Context context) {
        /**
         * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
         * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
         * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
         */
        ConsultSource source = new ConsultSource(null, null, null);
//        source.prompt = "连接客服成功的提示语";
//        source.VIPStaffAvatarUrl = "头像的 url";
//        source.vipStaffName = "客服的的名字";
//        source.vipStaffWelcomeMsg = "客服的欢迎语";
        /**
         * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
         * 如果返回为false，该接口不会有任何动作
         *
         * @param context 上下文
         * @param title   聊天窗口的标题
         * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
         */
        String title = "七鱼客服";
        Unicorn.openServiceActivity(context, title, source);
    }

    /**
     * 设置设置对话UI
     */
    public static void setUiCustomization() {
        MyApplication.ysfOptions.uiCustomization = uiCustomization();
    }

    /**
     * 设置对话UI（七鱼客服）
     *
     * @return
     */
    private static UICustomization uiCustomization() {
        // 以下示例的图片均无版权，请勿使用
        UICustomization customization = new UICustomization();
        //客服窗口UI自定义
        customization = new UICustomization();
        //标题栏背景颜色
        customization.titleBackgroundResId = R.color.colorPrimary;
        //标题栏风格，影响标题和标题栏上按钮的颜色(0浅色系，1深色系)
        customization.titleBarStyle = 1;
        //键盘控制
        customization.hideKeyboardOnEnterConsult = true;
        //输入框内字体颜色（一定要设置这个属性，有的手机上不设置此属性会出现输入的字体色是透明的）
        customization.inputTextColor = MyApplication.getContext().getResources().getColor(R.color.base_text);
        //访问者头像
        customization.rightAvatar = "";
        //客服头像
        // customization.leftAvatar = url;

        return customization;
    }

    /**
     * 设置用户信息(七鱼客服)
     */
    public static void setUnicornUserInfo() {
        YSFUserInfo userInfo = new YSFUserInfo();
        // App 的用户 ID
        userInfo.userId = "用户ID";
        // CRM 扩展字段
        userInfo.data = userInfoData("用户名字", "用户手机号", "用户头像");
        Unicorn.setUserInfo(userInfo);
    }

    /**
     * 构造用户信息
     *
     * @param name   名字
     * @param mobile 手机
     * @param avatar 头像
     * @return
     */
    private static String userInfoData(String name, String mobile, String avatar) {
        List<YSFUser> mListUser = new ArrayList<>();
        YSFUser rName = new YSFUser("real_name", name);
        YSFUser rMoblie = new YSFUser("mobile_phone", mobile);
        YSFUser rAvatar = new YSFUser("avatar", avatar);
        mListUser.add(rName);
        mListUser.add(rMoblie);
        mListUser.add(rAvatar);
        return new Gson().toJson(mListUser);
    }

    /**
     * 客服未读消息数变化监听
     *
     * @param listener
     */
    public static void addUnreadCountChangeListener(final UnreadCountChangeListener listener) {
        Unicorn.addUnreadCountChangeListener(listener, true);
    }

    /**
     * 获取最后一条消息
     */
    public static UnicornMessage queryLastMessage() {
        UnicornMessage message = Unicorn.queryLastMessage();
        return message;
    }

    /**
     * 获取客服与商家消息列表
     */
    public static List<Session> getSessionList() {
        List<Session> sessionList = POPManager.getSessionList();
        return sessionList;
    }

    /**
     * 网易七鱼客服
     * 清除文件缓存，将删除SDK接收过的所有文件。<br>
     * 建议在工作线程中执行该操作。
     * 该操作放到设置中 清除缓存操作下
     */
    public static void clearCache() {
        Unicorn.clearCache();
    }

    /**
     * 注销网易七鱼客服
     */
    public static void logout() {
        Unicorn.logout();
    }
}
