package com.emdata.common.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface HkDictEnum {

    @Getter
    @AllArgsConstructor
    enum PlateType {
        LP_92TypeCivil("92TypeCivil" , "92式民用车"),
        LP_arm("arm" , "警车"),
        LP_upDownMilitay("upDownMilitay" , "上下军车"),
        LP_92TypeArm("92TypeArm" , "92式武警车 "),
        LP_leftRightMilitay("leftRightMilitay" , "左右军车  "),
        LP_02TypePersonalized("02TypePersonalized" , "02式个性化车 "),
        LP_yellowTwoLine("yellowTwoLine" , "黄色双行尾牌 "),
        LP_04NewMilitay("04NewMilitay" , "04式新军车"),
        LP_embassy("embassy" , "使馆车   "),
        LP_oneLineArm("oneLineArm" , "一行结构的新武警车   "),
        LP_twoLineArm("twoLineArm" , "两行结构的新武警车   "),
        LP_yellow1225FarmVehicle("yellow1225FarmVehicle" , "黄色1225农用车 "),
        LP_green1325FarmVehicle("green1325FarmVehicle" , "绿色1325农用车  "),
        LP_yellow1325FarmVehicle("yellow1325FarmVehicle" , "黄色1325结构农用车  "),
        LP_motorola("motorola" , "摩托车  "),
        LP_newEnergy("newEnergy" , "新能源车牌  "),
        LP_2012TypeOneLineArm("2012TypeOneLineArm" , "2012式一行结构新WJ总部车牌类型（单行）"),
        LP_2012TypeTwoLineArm("2012TypeTwoLineArm" , "2012式两行结构新WJ总部车牌类型（双行）"),
        LP_civilAviation("civilAviation" , "民航车牌类型 "),
        LP_coach("coach" , "教练车"),
        LP_tempTravl("tempTravl" , "临时行驶车  "),
        LP_trailer("trailer" , "挂车"),
        LP_consulate("consulate" , "领馆汽车"),
        LP_hongKongMacao("hongKongMacao" , "港澳入出车   "),
        LP_tempEntry("tempEntry" , "临时入境车  "),
        LP_unknown("unknown" , "未知");


        private String code;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    enum VehicleType {

        largeBus("largeBus" , "大型客车"),
        truck("truck" , "货车"),
        mediumBus("mediumBus" , "中型客车");

        private String code;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    enum Color {

        BLUE("蓝"),
        YELLOW("黄"),
        WHITE("白");

        private String name;
    }

    public static final String WORD_1 = "学";
    public static final String WORD_2 = "挂";

    /***
     * 海康号牌种类转国标
     * @param hpzl
     * @param hphm
     * @param hpys
     * @param cllx
     * @return
     */
    public static String hkPlateTypeToStandard(String hpzl , String hphm , String hpys , String cllx) {
        String newHpzl = PlateTypeEnum.TYPE_99.getCode();
        if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_92TypeCivil.getCode())) {
            if (StrUtil.containsAny(hpys , Color.BLUE.getName())) {
                newHpzl = PlateTypeEnum.TYPE_02.getCode();
            } else if (StrUtil.containsAny(hpys , Color.YELLOW.getName())) {
                if (StrUtil.containsAny(hphm , WORD_1)) {
                    newHpzl = PlateTypeEnum.TYPE_16.getCode();
                } else {
                    newHpzl = PlateTypeEnum.TYPE_01.getCode();
                }
            }
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_arm.getCode())) {
            newHpzl = PlateTypeEnum.TYPE_23.getCode();
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_02TypePersonalized.getCode())) {
            if (StrUtil.containsAny(hpys , Color.YELLOW.getName())) {
                newHpzl = PlateTypeEnum.TYPE_01.getCode();
            } else if (StrUtil.containsAny(hpys , Color.WHITE.getName())) {
                newHpzl = PlateTypeEnum.TYPE_02.getCode();
            }
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_yellowTwoLine.getCode())) {
            if (StrUtil.containsAny(hphm , WORD_2)) {
                newHpzl = PlateTypeEnum.TYPE_15.getCode();
            } else {
                newHpzl = PlateTypeEnum.TYPE_01.getCode();
            }
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_embassy.getCode())) {
            newHpzl = PlateTypeEnum.TYPE_03.getCode();
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_yellow1225FarmVehicle.getCode())) {
            newHpzl = PlateTypeEnum.TYPE_13.getCode();
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_green1325FarmVehicle.getCode())) {
            newHpzl = PlateTypeEnum.TYPE_14.getCode();
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_yellow1325FarmVehicle.getCode())) {
            newHpzl = PlateTypeEnum.TYPE_15.getCode();
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_motorola.getCode())) {
            if (StrUtil.containsAny(hpys , Color.YELLOW.getName())) {
                newHpzl = PlateTypeEnum.TYPE_07.getCode();
            } else if (StrUtil.containsAny(hpys , Color.BLUE.getName())) {
                newHpzl = PlateTypeEnum.TYPE_08.getCode();
            }
        } else if (StrUtil.equals(hpzl , HkDictEnum.PlateType.LP_newEnergy.getCode())) {
            if (StrUtil.equalsAnyIgnoreCase(cllx , HkDictEnum.VehicleType.largeBus.getCode() , HkDictEnum.VehicleType.truck.getCode() , HkDictEnum.VehicleType.mediumBus.getCode())) {
                newHpzl = PlateTypeEnum.TYPE_51.getCode();
            } else {
                newHpzl = PlateTypeEnum.TYPE_52.getCode();
            }
        }
        return newHpzl;
    }

}
