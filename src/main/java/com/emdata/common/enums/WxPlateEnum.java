package com.emdata.common.enums;

import lombok.Getter;

/*0	unknown	未知
1	92TypeCivil	92 式民用车
2	arm	警车
3	upDownMilitay	上下军车
4	92TypeArm	92 式武警车
5	leftRightMilitay	左右军车
7	02TypePersonalized	02 式个性化车
8	yellowTwoLine	黄色双行尾牌
9	04NewMilitay	04 式新军车
10	embassy	使馆车
11	oneLineArm	一行结构的新武警车
12	twoLineArm	两行结构的新武警车
13	yellow1225FarmVehicle	黄色 1225 农用车
14	green1325FarmVehicle	绿色 1325 农用车
15	yellow1325FarmVehicle	黄色 1325 结构农用车
16	motorola	摩托车
17	newEnergy	新能源车牌
18	2012TypeOneLineArm	2012 式一行结构新 WJ 总部车牌类型（单行）
19	2012TypeTwoLineArm	2012 式两行结构新 WJ 总部车牌类型（双行）
20	civilAviation	民航车牌类型
100	coach	教练车
101	tempTravl	临时行驶车
102	trailer	挂车
103	consulate	领馆汽车
104	hongKongMacao	港澳入出车
105	tempEntry	临时入境车*/
@Getter
public enum WxPlateEnum {
	
	unknown("unknown","99","未知"),
	TypeCivil("92TypeCivil","02","92 式民用车"),
	arm("arm","31","警车"),
	upDownMilitay("upDownMilitay","32","上下军车"),
	TypeArm("92TypeArm","31","92 式武警车"),
	leftRightMilitay("leftRightMilitay","32","左右军车"),
	TypePersonalized("02TypePersonalized","99","02 式个性化车"),
	yellowTwoLine("yellowTwoLine","01","黄色双行尾牌"),
	NewMilitay("04NewMilitay","32","04 式新军车"),
	embassy("embassy","03","使馆车"),
	oneLineArm("oneLineArm","31","一行结构的新武警车"),
	twoLineArm("twoLineArm","31","两行结构的新武警车"),
	yellow1225FarmVehicle("yellow1225FarmVehicle","13","黄色 1225 农用车"),
	green1325FarmVehicle("green1325FarmVehicle","13","绿色 1325 农用车"),
	yellow1325FarmVehicle("yellow1325FarmVehicle","13","黄色 1325 结构农用车"),
	motorola("motorola","07","摩托车"),
	newEnergy("newEnergy","52","新能源车牌"),
	TypeOneLineArm("2012TypeOneLineArm","31","2012 式一行结构新 WJ 总部车牌类型（单行）"),
	TypeTwoLineArm("2012TypeTwoLineArm","31","2012 式两行结构新 WJ 总部车牌类型（双行）"),
	civilAviation("civilAviation","02","民航车牌类型"),
	coach("coach","16","教练车"),
	tempTravl("tempTravl","20","临时行驶车"),
	trailer("trailer","15","挂车"),
	consulate("consulate","04","领馆汽车"),
	hongKongMacao("hongKongMacao","26","港澳入出车"),
	tempEntry("tempEntry","06","临时入境车"),
	
	
	;
	
	private String hkPlateCode;
	
	private String gbCode;
	
	private String desc;
	
	private WxPlateEnum(String hkPlateCode,String gbCode,String desc) {
		this.hkPlateCode = hkPlateCode;
		this.gbCode = gbCode;
		this.desc = desc;
	}
	
	public static String getGbCode(String hkCode) {
		for(WxPlateEnum e:WxPlateEnum.values()) {
			if (hkCode.equals(e.getHkPlateCode())) {
				return e.getGbCode();
			}
		}
		return hkCode;
	}

}
