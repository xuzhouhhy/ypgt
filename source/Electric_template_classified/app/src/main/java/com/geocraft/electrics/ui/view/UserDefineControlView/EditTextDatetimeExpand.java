package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.geocraft.electrics.R;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.sr.DATETIMETYPE;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.geocraft.untiltools.Tools;

/**
 * Created by liu on 2015/10/28.
 */
public class EditTextDatetimeExpand extends BaseControl implements DataInterActionInterface {
    private EditText editTextDateTime;
    private Button btnDate;
    private TextView TextViewTitle;

    private DATETIMETYPE m_nDATETYPE = DATETIMETYPE.DATETIMETYPEDATE;
    private String strButtonText = "日期";
    private boolean m_bHor;
    private int m_nID;
    private int m_nSeconds;

    private MyOnClickListener onClickListener;
    private MyOnDateSetListener onDateSetListener;
    private MyOnTimeSetListener onTimeSetListener;
    private Context nContext;

    public EditTextDatetimeExpand(Context context) {
        super(context);
        nContext = context;
        initView(context, m_bHor);
    }

    public EditTextDatetimeExpand(Context context, boolean bhor) {
        super(context);
        nContext = context;
        m_bHor = bhor;
        initView(context, m_bHor);
    }

    public EditTextDatetimeExpand(Context context, AttributeSet attrs) {
        super(context, attrs);
        nContext = context;
        initView(context, m_bHor);
    }

    public EditTextDatetimeExpand(Context context, AttributeSet attrs, boolean bhor) {
        super(context, attrs);
        nContext = context;
        m_bHor = bhor;
        initView(context, m_bHor);
    }

    public EditTextDatetimeExpand(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        nContext = context;
        initView(context, m_bHor);
    }

    public EditTextDatetimeExpand(Context context, AttributeSet attrs, int defStyle, boolean bhor) {
        super(context, attrs, defStyle);
        nContext = context;
        m_bHor = bhor;
        initView(context, m_bHor);
    }

    public void SetDateType(DATETIMETYPE ndatetype) {
        m_nDATETYPE = ndatetype;
        if (m_nDATETYPE == DATETIMETYPE.DATETIMETYPEDATE) {
            strButtonText = "日期";
        } else {
            strButtonText = "时间";
        }
        if (btnDate != null) {
            btnDate.setText(strButtonText);
        }
    }

    public DATETIMETYPE GetDateType() {
        return m_nDATETYPE;
    }

    private void initHor(Context context) {
        try {
            String strtitle = "";
            String strText = "";
            if (TextViewTitle != null) {
                strtitle = TextViewTitle.getText().toString();
            }
            if (editTextDateTime != null) {
                strText = editTextDateTime.getText().toString();
            }

            this.removeAllViews();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.view_edit_datatime_expland_hor, this);

            TextViewTitle = (TextView) findViewById(R.id.datetimetitle);
            onClickListener = new MyOnClickListener();
            onDateSetListener = new MyOnDateSetListener();
            onTimeSetListener = new MyOnTimeSetListener();

            editTextDateTime = (EditText) findViewById(R.id.edittext_datetime_expland);
            editTextDateTime.setOnClickListener(onClickListener);

            btnDate = (Button) findViewById(R.id.button_date);
            btnDate.setOnClickListener(onClickListener);
            if (strButtonText != null && !strButtonText.isEmpty() && btnDate != null) {
                btnDate.setText(strButtonText);
            }

            if (TextViewTitle != null) {
                TextViewTitle.setText(strtitle);
            }
            if (editTextDateTime != null) {
                editTextDateTime.setText(strText);
            }
        } catch (Exception ex) {
        }
    }

    private void initVec(Context context) {
        try {
            String strtitle = "";
            String strText = "";
            if (TextViewTitle != null) {
                strtitle = TextViewTitle.getText().toString();
            }
            if (editTextDateTime != null) {
                strText = editTextDateTime.getText().toString();
            }

            this.removeAllViews();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.view_edit_datatime_expland, this);

            TextViewTitle = (TextView) findViewById(R.id.datetimetitle);
            onClickListener = new MyOnClickListener();
            onDateSetListener = new MyOnDateSetListener();
            onTimeSetListener = new MyOnTimeSetListener();

            editTextDateTime = (EditText) findViewById(R.id.edittext_datetime_expland);
            editTextDateTime.setOnClickListener(onClickListener);

            btnDate = (Button) findViewById(R.id.button_date);
            btnDate.setOnClickListener(onClickListener);
            if (strButtonText != null && !strButtonText.isEmpty() && btnDate != null) {
                btnDate.setText(strButtonText);
            }

            if (TextViewTitle != null) {
                TextViewTitle.setText(strtitle);
            }
            if (editTextDateTime != null) {
                editTextDateTime.setText(strText);
            }
        } catch (Exception ex) {
        }
    }

    private void initView(Context context, boolean bhor) {
        try {
            if (bhor) {
                initHor(context);
            } else {
                initVec(context);
            }
        } catch (Exception ex) {
        }
    }

    public void SetButtonText(String strText) {
        if (strText == null) {
            return;
        }
        try {
            strButtonText = strText;
            if (btnDate != null) {
                btnDate.setText(strButtonText);
            }
        } catch (Exception ex) {
        }
    }

    public String GetButtonText() {
        if (btnDate != null) {
            strButtonText = btnDate.getText().toString();
        }
        return strButtonText;
    }

    /**
     * 设置标签字段
     *
     * @param strtitle
     */
    public void SetTitle(String strtitle) {
        if (TextViewTitle == null || strtitle == null) {
            return;
        }
        TextViewTitle.setText(strtitle);
    }

    /**
     * 获取标签字段
     *
     * @return
     */
    public String GetTitle() {
        String strReuslt = "";
        if (TextViewTitle == null) {
            return strReuslt;
        }
        strReuslt = TextViewTitle.getText().toString();
        return strReuslt;
    }

    /**
     * 设置资源ID，可以作为控件的唯一识别码
     *
     * @param nid
     */
    public void SetID(int nid) {
        if (nid < 0) {
            return;
        }
        m_nID = nid;
    }

    /**
     * 获取控件ID值
     *
     * @return
     */
    public int GetID() {
        return m_nID;
    }

    /**
     * 设置是否水平排列
     *
     * @param bhor
     */
    public void SetBHor(boolean bhor) {
        m_bHor = bhor;
        if (m_bHor) {
            initHor(nContext);
        }
    }

    /**
     * 获取是否水平排列
     *
     * @return
     */
    public boolean GetBHor() {
        return m_bHor;
    }

    /**
     * 名称设置/获取
     *
     * @param strname
     */
    @Override
    public void SetName(String strname) {
        if (editTextDateTime == null || strname == null) {
            return;
        }
        editTextDateTime.setText(strname);
    }

    @Override
    public String GetName() {
        String strResult = "";
        if (editTextDateTime == null) {
            return strResult;
        }
        strResult = editTextDateTime.getText().toString();
        return strResult;
    }

    /**
     * 代码设置/获取
     *
     * @param strcode
     */
    @Override
    public void SetCode(String strcode) {
        SetName(strcode);
    }

    @Override
    public String GetCode() {
        return GetName();
    }

    /**
     * 设置/获取控件值
     *
     * @param strvalue
     */
    @Override
    public void SetValue(String strvalue) {
        SetName(strvalue);
    }

    @Override
    public String GetValue() {
        return GetName();
    }

    /**
     * 设置/获取EDIT控件是否可用
     *
     * @param benable
     */
    @Override
    public void SetEditEnable(boolean benable) {
        if (editTextDateTime == null) {
            return;
        }
        editTextDateTime.setEnabled(benable);
    }

    @Override
    public boolean GetEditEnable() {
        if (editTextDateTime == null) {
            return false;
        }
        return editTextDateTime.isEnabled();
    }

    /**
     * 设置/获取控件是否可用
     *
     * @param benable
     */
    @Override
    public void SetEnable(boolean benable) {
        if (editTextDateTime != null) {
            editTextDateTime.setEnabled(benable);
        }
        if (btnDate != null) {
            btnDate.setEnabled(benable);
        }
    }

    @Override
    public boolean GetEnable() {
        if (editTextDateTime == null || btnDate == null) {
            return false;
        }
        if (editTextDateTime.isEnabled() || btnDate.isEnabled()) {
            return true;
        }
        return false;
    }

    @Override
    public int GetControlID() {
        return m_nID;
    }

    private Date FormateStringToDate(String strDate, boolean isDate) {
        Date date = null;

        try {
            if (strDate.isEmpty()) {
                date = new Date(System.currentTimeMillis());
                return date;
            }

            SimpleDateFormat e = null;
            if (isDate) {
                e = new SimpleDateFormat("yyyy/MM/dd");
            } else {
                e = new SimpleDateFormat("HH:mm:ss");
            }

            date = e.parse(strDate);
        } catch (ParseException var3) {
            if (date == null) {
                date = new Date(System.currentTimeMillis());
            } else {
                date.setTime(System.currentTimeMillis());
            }
        }
        return date;
    }

    @Override
    public void setControlValue(FieldInfo fieldInfo, String text) {
        if (null != editTextDateTime) {
            editTextDateTime.setText(text);
        }
    }

    @Override
    public String getControlValue() {
        if (null != editTextDateTime) {
            return editTextDateTime.getText().toString();
        }
        return null;
    }

    @Override
    public void setControlValue(String text) {
        if (null != editTextDateTime) {
            editTextDateTime.setText(text);
        }
    }

    @Override
    public int getControlerType() {
        return 0;
    }

    private class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
//            java.util.Date date = Tools.FormateStringToDate(editTextDateTime.getText().toString());
            Date date = FormateStringToDate(editTextDateTime.getText().toString(), true);

            date.setYear(year - 1900);
            date.setMonth(monthOfYear);
            date.setDate(dayOfMonth);

            String dateString = Tools.FormateTimeDateToString(date.getTime());
            editTextDateTime.setText(dateString);
        }
    }

    private class MyOnTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            java.util.Date date = Tools.FormateStringToDate(editTextDateTime.getText().toString());
            Date date = FormateStringToDate(editTextDateTime.getText().toString(), false);

            date.setHours(hourOfDay);
            date.setMinutes(minute);
            date.setSeconds(m_nSeconds);
//            editTextDateTime.setText(Tools.FormateTimeHMSToString(date.getTime()));
            editTextDateTime.setText(Tools.FormateTimeToString(date.getTime()));
        }
    }

    private class MyOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                Date date;

                switch (m_nDATETYPE) {
                    case DATETIMETYPEDATE: {
//                        date = Tools.FormateStringToDate(editTextDateTime.getText().toString());
                        date = FormateStringToDate(editTextDateTime.getText().toString(), true);

                        Time time = new Time();
                        time.set(date.getTime());

                        DatePickerDialog picker = new DatePickerDialog(getContext(), onDateSetListener, time.year, time.month, time.monthDay);
                        picker.show();
                        break;
                    }

                    case DATETIMETYPETIME: {
//                        date = Tools.FormateStringToDate(editTextDateTime.getText().toString());
                        date = FormateStringToDate(editTextDateTime.getText().toString(), false);
                        m_nSeconds = date.getSeconds();
                        TimePickerDialog picker = new TimePickerDialog(getContext(), onTimeSetListener, date.getHours(), date.getMinutes(), true);
                        picker.show();
                        break;
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
