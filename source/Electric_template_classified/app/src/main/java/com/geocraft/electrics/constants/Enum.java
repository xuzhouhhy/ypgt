package com.geocraft.electrics.constants;

/**
 * Created by Administrator on 2016/6/6.
 */
public class Enum {


    /**********************************************************低压用户*****************************/
    /**
     * 计量箱型号
     */
    public static final String DATA_SET_NAME_JLXXH = "JLXXH";
    /**
     * 计量箱型号  字段  名称
     */
    public static final String FIELD_NAME_JLXXH_MC = "F_MC";
    /**
     * 配电杆塔物理杆
     */
    public static final String DATA_SET_NAME_PDGT_WLG = "PDGT（WLG）";
    /* 配电杆塔物理杆 字段名
    */
    public static final String PDGT_WLG_FILED_GTMC = "F_GTMC";
    public static final String PDGT_WLG_FOREIGN_KEY_FILED_GTMC2 = "F_SSGT";
    public static final String PDGT_WLG_FOREIGN_KEY_FILED_GTMC1 = "F_SSWLGTYWIDHWLGTMC";
    /**
     * 配电杆塔运行杆
     */
    public static final String DATA_SET_NAME_PDGT_YXG = "PDGT（YXG）";
    /**
     * 柱上变压器
     */
    public static final String DATA_SET_NAME_PD_ZSBYQ = "PD-ZSBYQ";
    /**
     * 柱上断路器
     */
    public static final String DATA_SET_NAME_PD_ZSDLQ = "PD-ZSDLQ";
    /**
     * 柱上负荷开关
     */
    public static final String DATA_SET_NAME_PD_ZSFHKG = "PD-ZSFHKG";
    /**
     * 柱上重合器
     */
    public static final String DATA_SET_NAME_PD_ZSCHQ = "PD-ZSCHQ";
    /**
     * 柱上隔离开关
     */
    public static final String DATA_SET_NAME_PD_ZSGLKG = "PD-ZSGLKG";
    /**
     * 柱上电压互感器
     */
    public static final String DATA_SET_NAME_PD_ZSDYHGQ = "PD-ZSDYHGQ";
    /**
     * 柱上电流互感器
     */
    public static final String DATA_SET_NAME_PD_ZSDLHGQ = "PD-ZSDLHGQ";
    /**
     * 跌落式熔断器
     */
    public static final String DATA_SET_NAME_PD_DLSRDQ = "PD-DLSRDQ";
    /**
     * 柱上接地环
     */
    public static final String DATA_SET_NAME_PD_ZSJDH = "PD-ZSJDH";
    /**
     * 柱上配变监测仪
     */
    public static final String DATA_SET_NAME_PD_ZSPBJCY = "PD-ZSPBJCY";
    /**
     * 柱上高压计量箱
     */
    public static final String DATA_SET_NAME_PD_ZSGYJLX = "PD-ZSGYJLX";
    /**
     * 柱上故障指示器
     */
    public static final String DATA_SET_NAME_PD_ZSGZZSQ = "PD-ZSGZZSQ";
    /**
     * 柱上避雷器
     */
    public static final String DATA_SET_NAME_PD_ZSBLQ = "PD-ZSBLQ";
    /**
     * 柱上电容器
     */
    public static final String DATA_SET_NAME_PD_ZSDRQ = "PD-ZSDRQ";
    /**
     * 变压器
     **/
    public static final String DATA_SET_NAME_BYQ = "BYQ";
    /**
     * 杆塔
     **/
    public static final String DATA_SET_NAME_GT = "GT";
    /**
     * 接入点
     **/
    public static final String DATA_SET_NAME_JRD = "JRD";
    /**
     * 计量箱
     **/
    public static final String DATA_SET_NAME_JLX = "JLX";
    /**
     * 计量箱字段 所属变压器器
     **/
    public static final String CALCULATEBOX_FILED_SSBYQ = "F_SSBYQ";
    /**
     * 计量箱字段 条码编号
     **/
    public static final String CALCULATEBOX_FILED__TMBH = "F_TMBH";
    /**
     * 电能表
     **/
    public static final String DATA_SET_NAME_DNB = "DNB";
    /**
     * 电能表字段 条码编号
     **/
    public static final String METER_FILED__TMBH = "F_TMBH";
    /**
     * 低压用户 组名
     */
    public static String GROUP_NAME_DYYH = "DYYH";
    /**
     * 低压用户 组名 中文名
     */
    public static String CN_GROUP_NAME_DYYH = "低压用户";
    /**
     * 低压客户档案
     */
    public static String DATA_SET_NAME_DYKHDA = "DYKHDA";
    /**
     * 低压客户档案字段 客户编号
     */
    public static String LOWVOLTAGEUSER_FIELD_KHBH = "F_KHBH";
    /**
     * 低压客户档案字段 低压客户名称
     */
    public static String LOWVOLTAGEUSER_FIELD_KHMC = "F_KHMC";
    /**
     * 低压客户档案字段 台区名称
     */
    public static String LOWVOLTAGEUSER_FIELD_TQMC = "F_TQMC";
    /**
     * 低压客户档案字段 台区编号
     */
    public static String LOWVOLTAGEUSER_FIELD_TQBH = "F_TQBH";


    /**********************************************************专线,专变用户用户*****************************/
    /**
     * 低压客户档案字段 计量箱条形码
     */
    public static String LOWVOLTAGEUSER_FIELD_JLXTXM = "F_JLXTXM";
    /**
     * 低压客户档案字段 电能表条形码
     */
    public static String LOWVOLTAGEUSER_FIELD_DNBTXM = "F_DNBTXM";
    /**
     * 计量箱
     */
    public static String DATA_SET_NAME_JLX_J = "JLX（J）";
    /**
     * 计量箱 表箱类型 单体表箱
     */
    public static String CALCULATEBOX_DTBX = "单体表箱";
    /**
     * 计量箱 表箱类型 合体表箱
     */
    public static String CALCULATEBOX_HTBX = "合体表箱";
    /**
     * 计量箱字段 计量箱条码编号
     */
    public static String CALCULATEBOX_FIELD_JLXTMBH = "F_JLXTMBH";
    /**
     * 计量箱字段 挂接设备名称
     */
    public static String CALCULATEBOX_FIELD_GJSBMC = "F_GJSBMC";
    /**
     * 计量箱字段 挂接设备类型
     */
    public static String CALCULATEBOX_FIELD_GJSBLX = "F_GJSBLX";
    /**
     * 计量箱字段 资产单位
     */
    public static String CALCULATEBOX_FIELD_ZCDW = "F_ZCDW";
    /**
     * 计量箱字段 表箱类型
     */
    public static String CALCULATEBOX_FIELD_BXLX = "F_BXLX";
    /**
     * 计量箱字段 行 (多音字)
     */
    public static String CALCULATEBOX_FIELD_H = "F_X";
    /**
     * 计量箱字段 列
     */
    public static String CALCULATEBOX_FIELD_L = "F_L";
    /**
     * 计量箱字段 安装地址
     */
    public static String CALCULATEBOX_FIELD_AZDZ = "F_AZDZ";
    /**
     * 计量箱字段 名称
     */
    public static String CALCULATEBOX_FIELD_MC = "F_MC";
    /**
     * 计量箱组
     */
    public static String DATA_SET_NAME_JLX_J_Z = "JLX（J）Z";
    /**
     * 计量箱组字段 组名
     */
    public static String CALCULATEBOXGROUP_FIELD_ZM = "F_ZM";
    /**
     * 计量箱组字段 表箱类型
     */
    public static String CALCULATEBOXGROUP_FIELD_BXLX = "F_BXLX";
    /**
     * 计量箱组字段 行 (多音字)
     */
    public static String CALCULATEBOXGROUP_FIELD_H = "F_X";
    /**
     * 计量箱组字段 列
     */
    public static String CALCULATEBOXGROUP_FIELD_L = "F_L";
    /**
     * 计量箱组字段 业务系统ID
     */
    public static String CALCULATEBOXGROUP_FIELD_YWXTID = "F_YWXTID";
    /**
     * 计量箱组字段 资产单位
     */
    public static String CALCULATEBOXGROUP_FIELD_ZCDW = "F_ZCDW";
    /**
     * 计量箱组字段 按照地址
     */
    public static String CALCULATEBOXGROUP_FIELD_AZDZ = "F_AZDZ";
    /**
     * 计量箱与电能表的关系
     */
    public static String DATA_SET_NAME_JLXYDNBDGX = "JLXYDNBDGX";
    /**
     * 计量箱与电能表的关系字段 电表条码编号
     */
    public static String METER_FIELD__DBTMBH = "F_DBTMBH";
    /**
     * 计量箱与电能表的关系字段 计量箱条码编号
     */
    public static String METER_FIELD_JLXTMBH = "F_JLXTMBH";
    /**
     * 计量箱与电能表的关系字段 条码行
     */
    public static String METER_FIELD_DBBW_X = "F_DBBW（X）";
    /**
     * 计量箱与电能表的关系字段 条码列
     */
    public static String METER_FIELD_DBBW_L = "F_DBBW（L）";
    /**
     * 终端设备
     */
    public static String DATA_SET_NAME_ZDSB = "ZDSB";
    /**
     * 终端设备 资产编号
     */
    public static String TERMINAL_FIELD_ZCBH = "F_ZCBH";
    /**
     * 专线用户 组名
     */
    public static String GROUP_NAME_ZXYH = "ZXYH";
    /**
     * 专线用户 组名 中文名
     */
    public static String CN_GROUP_NAME_ZXYH = "专线用户";
    /**
     * 专变用户 组名
     */
    public static String GROUP_NAME_ZBYH = "ZBYH";
    /**
     * 专变用户 组名 中文名
     */
    public static String CN_GROUP_NAME_ZBYH = "专变用户";
    /**
     * 高压用户点
     */
    public static String DATA_SET_NAME_GYYHD = "GYYHD";
    /**
     * 高压用户点 中文名
     */
    public static String CN_DATA_SET_NAME_GYYHD = "高压用户点";
    /**
     * 高压用户点字段 名称
     */
    public static String HIGHVOLTAGEUSER_FIELD_MC = "F_MC";
    /**
     * 高压用户点字段 业务系统ID
     */
    public static String HIGHVOLTAGEUSER_FIELD_YWXYID = "F_YWXTID";
    /**
     * 高压用户点字段 用户名称
     */
    public static String HIGHVOLTAGEUSER_FIELD_YHMC = "F_YHMC";
    /**
     * 用户变压器
     */
    public static String DATA_SET_NAME_YHBYQ = "YHBYQ";
    /**
     * 用户变压器字段信息 名称
     */
    public static String USERTRANSFORMER_FIELD_MC = "F_MC";
    /**
     * 用户电缆段
     */
    public static String DATA_SET_NAME_YHDLD = "YHDLD";
    /**
     * 用户电缆段字段 名称
     */
    public static String USERELECTRICCABLE_FIELD_MC = "F_MC";
    /**
     * 用户架空杆塔（物理杆）
     */
    public static String DATA_SET_NAME_YHJKGT_WLG = "YHJKGT（WLG）";
    /**
     * 用户架空杆塔（物理杆）字段   杆塔名称
     */
    public static String USERPHYSICSTOWER_FIELD_GTMC = "F_GTMC";
    /**
     * 用户架空杆塔（物理杆）字段   所属馈线
     */
    public static String USERPHYSICSTOWER_FIELD_SSKX = "F_SSKX";


    /**********************************************************公共资源*****************************/
    /**
     * 用户架空杆塔（物理杆）字段   所属线路
     */
    public static String USERPHYSICSTOWER_FIELD_SSXL = "F_SSXL";
    /**
     * 用户架空杆塔（物理杆）字段   城农网
     */
    public static String USERPHYSICSTOWER_FIELD_CNW = "F_CNW";
    /**
     * 用户架空杆塔（物理杆）字段   上级单位
     */
    public static String USERPHYSICSTOWER_FIELD_SJDW = "F_SJDW";
    /**
     * 用户架空杆塔（物理杆）字段   运行单位
     */
    public static String USERPHYSICSTOWER_FIELD_YXDW = "F_YXDW";
    /**
     * 用户架空杆塔（运行杆）
     */
    public static String DATA_SET_NAME_YHJKGT_YXG = "YHJKGT（YXG）";
    /**
     * 用户架空杆塔（运行杆） 字段 名称
     */
    public static String USERRUNNINGTOWER_FIELD_MC = "F_MC";
    /**
     * 用户架空杆塔（运行杆） 字段 回路
     */
    public static String USERRUNNINGTOWER_FIELD_HLXX = "F_HLXX";
    /**
     * 用户架空杆塔（运行杆） 字段 所属线路
     */
    public static String USERRUNNINGTOWER_FIELD_SSXL = "F_SSXL";
    /**
     * 用户开关组
     */
    public static String DATA_SET_NAME_YHKGZ = "YHKGZ";
    /**
     * 用开关站字段 名称
     */
    public static String USERSWITCHSITE_FIELD_MC = "F_MC";
    /**
     * 用户配电室
     */
    public static String DATA_SET_NAME_YHPDS_F = "YHPDS（F）";
    /**
     * 用户配电室字段信息 名称
     */
    public static String USERELECTRICCITYDISTRIBUTIONROOM_FIELD_F = "F_MC";


    /**********************************************************其他*****************************/
    /**
     * 用户箱式变
     */
    public static String DATA_SET_NAME_YHXSB = "YHXSB";
    /**
     * 用户箱式变字段 名称
     */
    public static String USERBOXTYPETRANSFORMER_FIELD_MC = "F_MC";
    public static String DATA_SET_NAME_YHZBDY = "YHZBDY";
    /**
     * 用户专线
     */
    public static String DATA_SET_NAME_YHZX = "YHZX";
    /**
     * 线路名称
     */
    public static String YHZX_FIELD_XLMC = "F_XLMC";
    /**
     * 所属馈线
     */
    public static String FIELD_SSKX = "F_SSKX";
    /**
     * 所属线路
     */
    public static String FIELD_SSXL = "F_SSXL";
    /**
     * 所属线路(站房)
     */
    public static String FIELD_SSXL_ZF = "F_SSXL（ZF）";
    /**
     * 用户专线 中文名
     */
    public static String CN_DATA_SET_NAME_YHZX = "用户专线";
    /**
     * 用户专线字段信息 线路名称
     */
    public static String USERUNIQUELINE_FILED_XLMC = "F_XLMC";
    /**
     * 用户专线字段信息 运行单位
     */
    public static String USERUNIQUELINE_FILED_YXDW = "F_YXDW";
    /**
     * 用户专线字段信息 上级单位
     */
    public static String USERUNIQUELINE_FILED_SJDW = "F_SJDW";
    /**
     * 用户专线字段信息 资产单位
     */
    public static String USERUNIQUELINE_FILED_ZCDW = "F_ZCDW";
    /**
     * 用户专线字段信息 电压等级
     */
    public static String USERUNIQUELINE_FILED_DYDJ = "F_DYDJ";
    /**
     * 用户专线字段信息 资产性质
     */
    public static String USERUNIQUELINE_FILED_ZCXZ = "F_ZCXZ";
    /**
     * 用户专线字段信息 所属馈线
     */
    public static String USERUNIQUELINE_FILED_SSKX = "F_SSKX";
    /**
     * 用户专线字段信息 城农网
     */
    public static String USERUNIQUELINE_FILED_CNW = "F_CNW";
    /**
     * 公共资源 组名
     */
    public static String GROUP_NAME_GGZY = "GGZY";
    /**
     * 公共资源 组名 中文名
     */
    public static String CN_GROUP_NAME_GGZY = "公共资源";
    /**
     * 计量库房
     */
    public static String DATA_SET_NAME_JLKF = "JLKF";
    /**
     * 计量库房字段 名称
     */
    public static String CALCULATEBOXSTOREHOUSE_FIELD_KFMC = "F_KFMC";
    /**
     * 充电桩
     */
    public static String DATA_SET_NAME_CDZ = "CDZ";
    /**
     * 充换桩字段 名称
     */
    public static String CHARGINGPILE_FIELD_MC = "F_MC";
    /**
     * 充换电站
     */
    public static String DATA_SET_NAME_CHDZ = "CHDZ";
    /**
     * 充换电站字段 名称
     */
    public static String CHARGINGSTATION_FIELD_MC = "F_MC";
    /**
     * 分布式电源
     */
    public static String DATA_SET_NAME_FBSDY = "FBSDY";
    /**
     * 分布式电源字段
     */
    public static String DISTRIBUTEGENERATION_FIELD_YHBH = "F_YHBH";
    /**
     * 服务网点
     */
    public static String DATA_SET_NAME_FWWD = "FWWD";
    /**
     * 服务网点字段 名称
     */
    public static String SERVICENETWORK_FIELD_FWWDMC = "F_FWWDMC";
    /**
     * 其他 组名
     */
    public static String GROUP_NAME_QT = "QT";
    /**
     * 其他 组名 其他
     */
    public static String CN_GROUP_NAME_QT = "其他";
    /**
     * 地址信息
     */
    public static String DATA_SET_NAME_DZXX = "DZXX";
    /**
     * 地址信息字段 名称
     */
    public static String ADDRESS_FIELD_MC = "F_MC";
    /**
     * 地址信息字段 省
     */
    public static String ADDRESS_FIELD_SHENG = "F_S（ZXS、ZZQ）";
    /**
     * 地址信息字段 市
     */
    public static String ADDRESS_FIELD_SHI = "F_S（Z）";
    /**
     * 地址信息字段 县
     */
    public static String ADDRESS_FIELD_XIAN = "F_Q（X）";
    /**
     * 地址信息字段 镇
     */
    public static String ADDRESS_FIELD_ZHEN = "F_Z";
    /**
     * 地址信息字段 乡
     */
    public static String ADDRESS_FIELD_XIANG = "F_X";
    /*******************************************配网用户*******************************/
    /*******************************************配网用户*******************************/
    /**
     * 地址信息字段 村
     */
    public static String ADDRESS_FIELD_CUN = "F_C";
    /**
     * 接入点标识
     */
    public static String DATA_SET_NAME_JRDBS = "JRDBS";
    /**
     * 接入点标识字段 挂接设备名称
     */
    public static String ACCESSPOINT_FIELD_GJSBMC = "F_GJSBMC";
    /**
     * 接入点标识字段 挂接设备类型
     */
    public static String ACCESSPOINT_FIELD_GJSBLX = "F_GJSBLX";
    /**
     * 接入点标识字段 业务系统ID
     */
    public static String ACCESSPOINT_FIELD_YWXTID = "F_YWXTID";
    /**
     * 接入点标识字段 经度
     */
    public static String ACCESSPOINT_FIELD_WD_YZB = "F_WD（YZB）";
    /**
     * 接入点标识字段 纬度
     */
    public static String ACCESSPOINT_FIELD_JD_XZB = "F_JD（XZB）";
    /**
     * 接入点标识字段 高程
     */
    public static String ACCESSPOINT_FIELD__GC = "F_GC";
    /**
     * 馈线信息
     */
    public static String DATA_SET_NAME_KXXX = "KXXX";
    /**
     * 馈线信息字段 名称
     */
    public static String FEEDERLINE_FIELD_MC = "F_MC";
    /**
     * 线路起点设备信息
     */
    public static String DATA_SET_NAME_XLQDSBXX = "XLQDSBXX";
    /**
     * 线路起点设备字段 名称
     */
    public static String STARTDEVICE_FIELD_MC = "F_MC";
    /**
     * 变电站信息
     */
    public static String DATA_SET_NAME_BDZXX = "BDZXX";
    /**
     * 变电站字段信息 名称
     */
    public static String TRANSFORMERSTATION_FIELD_MC = "F_MC";
    /**
     * 台区信息
     */
    public static String DATA_SET_NAME_TQXX = "TQXX";
    /**
     * 台区信息字段  台区名称
     */
    public static String ZONEAREA_FIELD_MC = "F_MC";
    /**
     * 台区信息字段  业务系统ID
     */
    public static String ZONEAREA_FIELD_YWXYID = "F_YWXTID";
    /**
     * 管理单位
     */
    public static String DATA_SET_NAME_GLDW = "GLDW";
    /**
     * 管理单位字段 一级供电公司
     */
    public static String MANAGER_FIELD_YJGDGS = "F_YJGDGS";


    /*******************************************农网用户*******************************/
    /**
     * 管理单位字段 二级供电公司
     */
    public static String MANAGER_FIELD_EJGDGS = "F_EJGDGS";
    /**
     * 管理单位字段 供电所
     */
    public static String MANAGER_FIELD_GDS = "F_GDS";
    /**
     * 行政区
     */
    public static String DATA_SET_NAME_XZQ = "XZQ";
    /**
     * 行政区字段 省（直辖市、自治区）
     */
    public static String ADMINISTRATION_FIELD_SHENG = "F_S（ZXS、ZZQ）";
    /**
     * 行政区字段 市（州）
     */
    public static String ADMINISTRATION_FIELD_SHI = "F_S（Z）";
    /**
     * 行政区字段 县
     */
    public static String ADMINISTRATION_FIELD_XIAN = "F_Q（X）";
    /**
     * 变压器信息
     */
    public static String DATA_SET_NAME_BYQXX = "BYQXX";
    /**
     * 变压器信息字段 名称
     */
    public static String TRANSFORMER_FIELD_MC = "F_MC";
    /**
     * 杆塔字段 名称
     */
    public static String TOWER_FIELD_MC = "F_MC";
    /**
     * 接入点字段 名称
     */
    public static String ACCESSPOINT_FIELD_MC = "F_MC";

    /**********************************************************上饶*****************************/
    public static String DATA_SET_NAME_LINE = "line";

    /**
     * 杆塔号
     */
    public static String DATA_SET_TOWER_ID = "DYXLTZ_F_tower_id";

    public static String LINE_LEDGER = "line_ledger";
    public static String BRANCH_BOX = "branch_box";
    public static String GYCJ = "gycj";
    public static String GYCJ_LINE_F_GH = "F_GH";
    public static String GY_JKXLTZXX = "GY_JKXLTZXX";
    public static String GY_DLXLTZXX = "GY_DLXLTZXX";
    public static String GY_HYGTZXX = "GY_HYGTZXX";
    public static String GY_KBSTZXX = "GY_KBSTZXX";
    public static String GY_FZXTZXX = "GY_FZXTZXX";
    public static String GY_DLFJXTZXX = "GY_DLFJXTZXX";
    public static String GY_XSBYQTZXX = "GY_XSBYQTZXX";
    public static String GY_ZSBYQTZXX = "GY_ZSBYQTZXX";

    /**
     * 电源字段 井号类型
     */
    public static String GY_JKXLTZXX_FIELD_GZlX = "F_gzlx";//杆桩类型
}
