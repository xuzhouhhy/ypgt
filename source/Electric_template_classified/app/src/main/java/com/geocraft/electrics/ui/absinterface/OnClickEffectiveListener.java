package com.geocraft.electrics.ui.absinterface;

import android.view.View;
import android.view.View.OnClickListener;

import com.huace.log.logger.L;


/**
 * 按钮响应处理
 *
 * @author kingdon
 */
public abstract class OnClickEffectiveListener implements OnClickListener {

    private static final int st_effectiveClickInterval = 800;//millisecond
    private static long mLastClickTime = System.currentTimeMillis();

    public OnClickEffectiveListener() {
        super();
    }

    private synchronized static boolean isEffectiveClick() {
        long current = System.currentTimeMillis();
        long dt = System.currentTimeMillis() - mLastClickTime;
        if (Math.abs(dt) >= st_effectiveClickInterval) {
            mLastClickTime = current;
            return true;
        }
        return false;
    }

    @Override
    public final void onClick(View v) {
        try {
            if (isEffectiveClick()) {
                onEffectiveClick(v);
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    public abstract void onEffectiveClick(View v);
}
