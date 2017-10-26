package com.geocraft.electrics.ui.fragment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BaseDialogFragment;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.utils.BASE64;
import com.geocraft.electrics.utils.DeviceIDManager;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.T;

/**
 * Created by hanpengfei on 2016/6/6.
 */
@EFragment(R.layout.dialogfragment_register)
public class RegisterFragment extends BaseDialogFragment {
	@ViewById
	EditText etVersion;
	@ViewById
	EditText etID;
	@ViewById
	EditText etCode;
	@ViewById
	TextView tvTip;
	@ViewById
	Button btnReg;

	private boolean mIsRegistered;

	@AfterViews
	void init() {
		try {
			etVersion.setText("" + ElectricApplication_.getInstance().getVersionName(this.getActivity().getApplicationContext()));
			etID.setText(getDeviceCode());
			if (ElectricApplication_.getInstance().checkRegisterStatus()) {

				tvTip.setText(getString(R.string.registered));
				mIsRegistered = true;
			} else {
				tvTip.setText(getString(R.string.unregistered));
				mIsRegistered = false;
			}
		} catch (Exception e) {
			L.printException(e);
		}
	}

	@Click
	void btnReg() {
		L.i("clicked");
		//若已经注册成功
		if (mIsRegistered) {
			T.showShort(getActivity().getApplicationContext(), R.string.registered_not_register_again);
		} else {
			String registerCode = etCode.getText().toString();

			boolean result = verify(registerCode);
			if (result) {
				T.showShort(getActivity().getApplicationContext(), R.string.register_success);
				FileUtils.writeFile(ConstPath.getRegisterPath(), registerCode);
				tvTip.setText(getString(R.string.registered));
				mIsRegistered = true;
				ElectricApplication_.getInstance().mIsRegistered = true;
			} else {
				T.showShort(getActivity().getApplicationContext(), R.string.register_failure);
			}
		}
	}

	private String getDeviceCode() {
		String strResult = "";
		try {
			String strDeviceID = DeviceIDManager.GetDeviceID();
			L.i("strDeviceID:" + strDeviceID);
			String strBase64 = BASE64.Encode2(strDeviceID);
			L.i("strBase64:" + strBase64);
			strResult = BASE64.BaseToLongFormat(strBase64, 9);
			L.i("strResult:" + strResult);
		} catch (Exception ex) {
			strResult = "";
			L.printException(ex);
		}
		return strResult;
	}


	/**
	 * * 根据输入码判断注册是否成功
	 *
	 * @param password
	 * @return
	 */
	private boolean verify(String password) {
		boolean bReg = false;
		String strPassword = password;
		try {
			if (!strPassword.isEmpty() && strPassword.length() > 5) {
				String strDeviceID = DeviceIDManager.GetDeviceID();
				L.i("strDeviceID:" + strDeviceID);
				String strBase64 = BASE64.Encode2(strDeviceID);
				L.i("strBase64:" + strBase64);
				String strDeviceCode = BASE64.BaseToLongFormat(strBase64, 9);
				L.i("strDeviceCode:" + strDeviceCode);
				String strLast5 = "";
				if (strDeviceCode.length() > 5) {
					strLast5 = strDeviceCode.substring(strDeviceCode.length() - 5, strDeviceCode.length());
					L.i("strLast5:" + strLast5);
				}
				long DeviceCode = Utils.ConverLong(strDeviceCode);
				L.i("DeviceCode:" + DeviceCode);
				long nResult = (long) (DeviceCode * 0.8 + Utils.ConverLong(strLast5) * 0.7 + 3);
				L.i("nResult:" + nResult);
				long nPassword = Utils.ConverLong(strPassword);
				L.i("nResult:" + nResult);
				if (nResult == nPassword) {
					bReg = true;
				}
			}
		} catch (Exception ex) {
			L.printException(ex);
			bReg = false;
		}
		return bReg;
	}
}
