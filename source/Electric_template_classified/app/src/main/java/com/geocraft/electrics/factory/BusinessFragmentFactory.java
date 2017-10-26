package com.geocraft.electrics.factory;

import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.ui.fragment.CoordinateFragment_;
import com.geocraft.electrics.ui.fragment.PhotoManagerFragment;
import com.geocraft.electrics.ui.fragment.PhotoManagerFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.AccessPointNWBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.CalculateBoxBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.CalculateBoxGroupBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.CalculateBoxNWBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.CalculateBoxStoreHouseBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ChargingPileBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ChargingStationBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ColumnCapacitorBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ColumnCoincidenceDeviceBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ColumnDistributionMonitorBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ColumnFailureIndicatorBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ColumnIsolationSwitchBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ColumnVoltageTransformerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.CurrentTransformerOnColumnBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.DistributedGenerationBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ElectricPhysicsTowerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ElectricRunningTowerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ElectricTransFormerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.FuseSwitchBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.GroundRingOnThePoleBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.HighVoltageMeteringBoxOnColumnBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.HighVoltageUserBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.LineGroupBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.LoadSwitchOnColumnBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.LowVoltageBrachBoxBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.LowVoltageUserBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.MeterBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.MeterNWBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.PoleMountedcircuitbreakerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.ServiceNetworkBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.SurgeArresterBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.TerminalBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.TowerNWBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.TransformerNWBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserBoxTypeTransFormerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserElectricCableBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserElectricityDistributionRoomBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserPhysicsTowerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserRunningTowerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserSelfContainedPowerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserSwitchSiteBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserTransformerBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.UserUniqueLineBasicFragment_;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.XLTZ_DY_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.AccessPointNWNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.AccessPointNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.AddressNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.AdministratorNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.CalculateBoxNWNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.CalculateBoxNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.CalculateBoxNecessaryGroupFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.CalculateBoxStoreHouseNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.CalculateBoxTypeNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ChargingPileNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ChargingStationNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ColumnCapcitorNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ColumnCoincidenceDeviceNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ColumnDistributionMonitorNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ColumnFailureIndicatorNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ColumnIsolationSwitchNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ColumnVoltageTransformerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.CurrentTransformerOnColumnNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.DistributedGenerationNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ElectricPhysicsTowerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ElectricRunningTowerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ElectricTransFormerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.FeederLineNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.FuseSwitchNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.GroundRingOnThePoleNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.HighVoltageMeteringBoxOnColumnNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.HighVoltageUserNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.LoadSwitchOnColumnNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.LowVoltageUserNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ManagerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.MeterNWNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.MeterNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.PoleMountedcircuitbreakerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ServiceNetworkNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.StartDeviceNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.SurgeArresterNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.TerminalNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.TowerNWNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.TransformerNWNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.TransformerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.TransformerStationNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserBoxTypeTransFormerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserElectricCableNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserElectricityDistributionRoomNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserPhysicsTowerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserRunningTowerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserSelfContainedPowerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserSwitchSiteNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserTransformerNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.UserUniqueLineNecessaryFragment_;
import com.geocraft.electrics.ui.fragment.business_necessary_fragment.ZoneAreaNecessaryFragment_;
import com.geocraft.electrics.utils.PinYinUtil;

/**
 * Created by Administrator on 2016/6/9.
 */
public class BusinessFragmentFactory {

    private String mDataSetName;

    public BusinessFragmentFactory(String dataSetName) {
        this.mDataSetName = dataSetName;
    }

    public BusinessFragment getBasicDataFragment(boolean showBasic) {
        if (showBasic) {
            mDataSetName = PinYinUtil.getFirstSpell(mDataSetName);
            if (mDataSetName.equals(Enum.DATA_SET_NAME_JLX_J)) {
                return new CalculateBoxBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLX_J_Z)) {
                return new CalculateBoxGroupBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_DYKHDA)) {
                return new LowVoltageUserBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_ZDSB)) {
                return new TerminalBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_GYYHD)) {
                return new HighVoltageUserBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHXSB)) {
                return new UserBoxTypeTransFormerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHDLD)) {
                return new UserElectricCableBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHPDS_F)) {
                return new UserElectricityDistributionRoomBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHBYQ)) {
                return new UserTransformerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHJKGT_WLG)) {
                return new UserPhysicsTowerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHJKGT_YXG)) {
                return new UserRunningTowerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHZX)) {
                return new UserUniqueLineBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHKGZ)) {
                return new UserSwitchSiteBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHZBDY)) {
                return new UserSelfContainedPowerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLKF)) {
                return new CalculateBoxStoreHouseBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_CDZ)) {
                return new ChargingPileBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_CHDZ)) {
                return new ChargingStationBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_FBSDY)) {
                return new DistributedGenerationBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_FWWD)) {
                return new ServiceNetworkBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLXYDNBDGX)) {
                return new MeterBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PDGT_WLG)) {
                return new ElectricPhysicsTowerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PDGT_YXG)) {
                return new ElectricRunningTowerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSBYQ)) {
                return new ElectricTransFormerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSDLQ)) {
                return new PoleMountedcircuitbreakerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSFHKG)) {
                return new LoadSwitchOnColumnBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSCHQ)) {
                return new ColumnCoincidenceDeviceBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSGLKG)) {
                return new ColumnIsolationSwitchBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSDYHGQ)) {
                return new ColumnVoltageTransformerBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSDLHGQ)) {
                return new CurrentTransformerOnColumnBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_DLSRDQ)) {
                return new FuseSwitchBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSJDH)) {
                return new GroundRingOnThePoleBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSPBJCY)) {
                return new ColumnDistributionMonitorBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSGYJLX)) {
                return new HighVoltageMeteringBoxOnColumnBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSGZZSQ)) {
                return new ColumnFailureIndicatorBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSBLQ)) {
                return new SurgeArresterBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSDRQ)) {
                return new ColumnCapacitorBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_BYQ)) {
                return new TransformerNWBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_GT)) {
                return new TowerNWBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JRD)) {
                return new AccessPointNWBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLX)) {
                return new CalculateBoxNWBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_DNB)) {
                return new MeterNWBasicFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_LINE)) {
                return new LineGroupBasicFragment_();
            } else if (mDataSetName.equals(Enum.LINE_LEDGER)) {
                return new XLTZ_DY_();
            } else if(mDataSetName.equals(Enum.BRANCH_BOX)){
                return new LowVoltageBrachBoxBasicFragment_();
            }else {
                return null;
            }
        } else {
            return null;
        }

    }

    public BusinessFragment getNecessaryFragment(boolean showNecessary) {
        if (showNecessary) {
            mDataSetName = PinYinUtil.getFirstSpell(mDataSetName);
            if (mDataSetName.equals(Enum.DATA_SET_NAME_JLX_J)) {
                return new CalculateBoxNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLX_J_Z)) {
                return new CalculateBoxNecessaryGroupFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_DYKHDA)) {
                return new LowVoltageUserNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_ZDSB)) {
                return new TerminalNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_GYYHD)) {
                return new HighVoltageUserNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHXSB)) {
                return new UserBoxTypeTransFormerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHDLD)) {
                return new UserElectricCableNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHPDS_F)) {
                return new UserElectricityDistributionRoomNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHBYQ)) {
                return new UserTransformerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHJKGT_WLG)) {
                return new UserPhysicsTowerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHJKGT_YXG)) {
                return new UserRunningTowerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHZX)) {
                return new UserUniqueLineNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHKGZ)) {
                return new UserSwitchSiteNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_YHZBDY)) {
                return new UserSelfContainedPowerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JRDBS)) {
                return new AccessPointNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_BDZXX)) {
                return new TransformerStationNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_KXXX)) {
                return new FeederLineNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_TQXX)) {
                return new ZoneAreaNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_XLQDSBXX)) {
                return new StartDeviceNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLKF)) {
                return new CalculateBoxStoreHouseNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_CDZ)) {
                return new ChargingPileNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_CHDZ)) {
                return new ChargingStationNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_FBSDY)) {
                return new DistributedGenerationNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_FWWD)) {
                return new ServiceNetworkNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLXYDNBDGX)) {
                return new MeterNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_DZXX)) {
                return new AddressNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_GLDW)) {
                return new ManagerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_XZQ)) {
                return new AdministratorNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PDGT_WLG)) {
                return new ElectricPhysicsTowerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PDGT_YXG)) {
                return new ElectricRunningTowerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_BYQXX)) {
                return new TransformerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSBYQ)) {
                return new ElectricTransFormerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSDLQ)) {
                return new PoleMountedcircuitbreakerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSFHKG)) {
                return new LoadSwitchOnColumnNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSCHQ)) {
                return new ColumnCoincidenceDeviceNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSGLKG)) {
                return new ColumnIsolationSwitchNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSDYHGQ)) {
                return new ColumnVoltageTransformerNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSDLHGQ)) {
                return new CurrentTransformerOnColumnNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_DLSRDQ)) {
                return new FuseSwitchNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSJDH)) {
                return new GroundRingOnThePoleNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSPBJCY)) {
                return new ColumnDistributionMonitorNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSGYJLX)) {
                return new HighVoltageMeteringBoxOnColumnNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSGZZSQ)) {
                return new ColumnFailureIndicatorNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSBLQ)) {
                return new SurgeArresterNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_PD_ZSDRQ)) {
                return new ColumnCapcitorNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLXXH)) {
                return new CalculateBoxTypeNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_BYQ)) {
                return new TransformerNWNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_GT)) {
                return new TowerNWNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JRD)) {
                return new AccessPointNWNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_JLX)) {
                return new CalculateBoxNWNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.DATA_SET_NAME_DNB)) {
                return new MeterNWNecessaryFragment_();
            } else if (mDataSetName.equals(Enum.LINE_LEDGER)) {
                return new XLTZ_DY_();
            } else if(mDataSetName.equals(Enum.BRANCH_BOX)){
                return new LowVoltageBrachBoxBasicFragment_();
            }else {
                return null;
            }
        } else {
            return null;
        }
    }

    public PhotoManagerFragment getPhotoManagerFragment(boolean isShowPhoto) {
        if (isShowPhoto) {
            return new PhotoManagerFragment_();
        } else {
            return null;
        }
    }

    public BusinessFragment getCoordinateFragment(boolean isShowCoordinate) {
        if (isShowCoordinate) {
            return new CoordinateFragment_();
        } else {
            return null;
        }
    }
}
