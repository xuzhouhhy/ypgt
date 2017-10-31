package com.geocraft.electrics.sr;

import java.util.List;

import static com.geocraft.electrics.sr.BasicFragmentProxy.KEY_GY_DLXL_Base;
import static com.geocraft.electrics.sr.JKFragments.KEY_GY_JKXLTZXX_BASE;

/**
 * 可选项frament管理
 */
public class CommonFragment {
    private final static String NAME_KEY_MARK = "&";

    public static void initCheckedFragmentkeyValue(String checkedFragmentkeyValue,
                                                   List<FragmentOption> fragmentOptions) {
        if (null == checkedFragmentkeyValue || checkedFragmentkeyValue.isEmpty()) {
            return;
        }
        String[] keyArry = checkedFragmentkeyValue.split(NAME_KEY_MARK);
        for (int i = 0; i < fragmentOptions.size(); i++) {
            FragmentOption option = fragmentOptions.get(i);
            String fragmentNameKey = option.getNameKey();
            for (String key : keyArry) {
                if (key.equals(fragmentNameKey)) {
                    option.setChecked(true);
                }
            }
        }
    }

    /**
     * 判断当前fragment是否为基础必填界面
     *
     * @param fragmentKey 界面标识key
     */
    public static boolean isBaseFragment(String fragmentKey) {
        if (fragmentKey.equals(KEY_GY_JKXLTZXX_BASE) ||
                fragmentKey.equals(KEY_GY_DLXL_Base)) {
            return true;
        }
        return false;
    }


}
