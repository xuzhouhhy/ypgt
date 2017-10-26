package common.geocraft.fileselectlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import common.geocraft.untiltools.ScreenUtils;

/**
 * 作者  zhouqin
 * 时间 2016/5/7.
 */
public class FileSelectItemView extends LinearLayout {
    private ImageView mImageViewIcon;
    private TextView mTextViewName;
    private LinearLayout mLinearLayoutItemView;
    private CheckBox mCheckBoxSelect;

    private Context mContext;

    public FileSelectItemView(Context context) {
        super(context);
        InitView(context);
    }

    public FileSelectItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitView(context);
    }

    public FileSelectItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitView(context);
    }

    private void InitView(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.file_select_item_view, this, true);
        mImageViewIcon = (ImageView) findViewById(R.id.imageViewIcon);
        mTextViewName = (TextView) findViewById(R.id.textViewName);
        mLinearLayoutItemView = (LinearLayout) findViewById(R.id.linearLayoutItemView);
        mCheckBoxSelect = (CheckBox) findViewById(R.id.checkBoxSelect);
        mLinearLayoutItemView.setMinimumHeight(ScreenUtils.getScreenHeight(mContext) / 10);

    }

    public void SetFileName(String name) {
        mTextViewName.setText(name);
    }

    public void SetImageViewIcon(int id) {
        mImageViewIcon.setBackgroundResource(id);
    }

    public void SetChecked(boolean isChecked) {
        mCheckBoxSelect.setChecked(isChecked);
    }

    public void SetOnCheckChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        mCheckBoxSelect.setOnCheckedChangeListener(listener);
    }
}
