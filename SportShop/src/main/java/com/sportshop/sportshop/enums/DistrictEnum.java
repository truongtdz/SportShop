package com.sportshop.sportshop.enums;

public enum DistrictEnum {
    // District in HaNoi
    BA_DINH(CityEnum.HA_NOI),
    CAU_GIAY(CityEnum.HA_NOI),
    DONG_DA(CityEnum.HA_NOI),
    HOAN_KIEM(CityEnum.HA_NOI),
    THANH_XUAN(CityEnum.HA_NOI),
    HOANG_MAI(CityEnum.HA_NOI),

    // District in HCM
    DISTRICT_1(CityEnum.HO_CHI_MINH),
    DISTRICT_2(CityEnum.HO_CHI_MINH),
    DISTRICT_3(CityEnum.HO_CHI_MINH),
    DISTRICT_4(CityEnum.HO_CHI_MINH),
    TAN_BINH(CityEnum.HO_CHI_MINH),
    BINH_TANH(CityEnum.HO_CHI_MINH),

    ;

    private final CityEnum city;

    DistrictEnum(CityEnum city) {
        this.city = city;
    }

    public CityEnum getCity() {
        return this.city;
    }
}
