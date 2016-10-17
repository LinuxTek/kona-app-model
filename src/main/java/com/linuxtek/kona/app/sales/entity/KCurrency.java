package com.linuxtek.kona.app.sales.entity;

import java.util.LinkedHashMap;

import com.linuxtek.kona.data.entity.KEnumObject;
import com.linuxtek.kona.data.entity.KEnumUtil;

public enum KCurrency implements KEnumObject {

    AED(1,"United Arab Emirates","Dirhams"),
    AFN(2,"Afghanistan","Afghanis"),
    ALL(3,"Albania","Leke"),
    AMD(4,"Armenia","Drams"),
    ANG(5,"Netherlands Antilles","Guilders (also called Florins)"),
    AOA(6,"Angola","Kwanza"),
    ARS(7,"Argentina","Pesos"),
    AUD(8,"Australia","Dollars"),
    AWG(9,"Aruba","Guilders (also called Florins)"),
    AZM(10,"Azerbaijan","Manats [obsolete]"),
    AZN(11,"Azerbaijan","New Manats"),
    BAM(12,"Bosnia and Herzegovina","Convertible Marka"),
    BBD(13,"Barbados","Dollars"),
    BDT(14,"Bangladesh","Taka"),
    BGN(15,"Bulgaria","Leva"),
    BHD(16,"Bahrain","Dinars"),
    BIF(17,"Burundi","Francs"),
    BMD(18,"Bermuda","Dollars"),
    BND(19,"Brunei Darussalam","Dollars"),
    BOB(20,"Bolivia","Bolivianos"),
    BRL(21,"Brazil","Brazil Real"),
    BSD(22,"Bahamas","Dollars"),
    BTN(23,"Bhutan","Ngultrum"),
    BWP(24,"Botswana","Pulas"),
    BYR(25,"Belarus","Rubles"),
    BZD(26,"Belize","Dollars"),
    CAD(27,"Canada","Dollars"),
    CDF(28,"Congo/Kinshasa","Congolese Francs"),
    CHF(29,"Switzerland","Francs"),
    CLP(30,"Chile","Pesos"),
    CNY(31,"China","Yuan Renminbi"),
    COP(32,"Colombia","Pesos"),
    CRC(33,"Costa Rica","Colones"),
    RSD(34,"Serbia","Dinars"),
    CUP(35,"Cuba","Pesos"),
    CVE(36,"Cape Verde","Escudos"),
    CYP(37,"Cyprus","Pounds"),
    CZK(38,"Czech Republic","Koruny"),
    DJF(39,"Djibouti","Francs"),
    DKK(40,"Denmark","Kroner"),
    DOP(41,"Dominican Republic","Pesos"),
    DZD(42,"Algeria","Algeria Dinars"),
    EEK(43,"Estonia","Krooni"),
    EGP(44,"Egypt","Pounds"),
    ERN(45,"Eritrea","Nakfa"),
    ETB(46,"Ethiopia","Birr"),
    EUR(47,"Euro Member Countries","Euro"),
    FJD(48,"Fiji","Dollars"),
    FKP(49,"Falkland Islands (Malvinas)","Pounds"),
    GBP(50,"United Kingdom","Pounds"),
    GEL(51,"Georgia","Lari"),
    GGP(52,"Guernsey","Pounds"),
    GHC(53,"Ghana","Cedis"),
    GIP(54,"Gibraltar","Pounds"),
    GMD(55,"Gambia","Dalasi"),
    GNF(56,"Guinea","Francs"),
    GTQ(57,"Guatemala","Quetzales"),
    GYD(58,"Guyana","Dollars"),
    HKD(59,"Hong Kong","Dollars"),
    HNL(60,"Honduras","Lempiras"),
    HRK(61,"Croatia","Kuna"),
    HTG(62,"Haiti","Gourdes"),
    HUF(63,"Hungary","Forint"),
    IDR(64,"Indonesia","Rupiahs"),
    ILS(65,"Israel","New Shekels"),
    IMP(66,"Isle of Man","Pounds"),
    INR(67,"India","Rupees"),
    IQD(68,"Iraq","Dinars"),
    IRR(69,"Iran","Rials"),
    ISK(70,"Iceland","Kronur"),
    JEP(71,"Jersey","Pounds"),
    JMD(72,"Jamaica","Dollars"),
    JOD(73,"Jordan","Dinars"),
    JPY(74,"Japan","Yen"),
    KES(75,"Kenya","Shillings"),
    KGS(76,"Kyrgyzstan","Soms"),
    KHR(77,"Cambodia","Riels"),
    KMF(78,"Comoros","Francs"),
    KPW(79,"Korea (North)","Won"),
    KRW(80,"Korea (South)","Won"),
    KWD(81,"Kuwait","Dinars"),
    KYD(82,"Cayman Islands","Dollars"),
    KZT(83,"Kazakhstan","Tenge"),
    LAK(84,"Laos","Kips"),
    LBP(85,"Lebanon","Pounds"),
    LKR(86,"Sri Lanka","Rupees"),
    LRD(87,"Liberia","Dollars"),
    LSL(88,"Lesotho","Maloti"),
    LTL(89,"Lithuania","Litai"),
    LVL(90,"Latvia","Lati"),
    LYD(91,"Libya","Dinars"),
    MAD(92,"Morocco","Dirhams"),
    MDL(93,"Moldova","Lei"),
    MGA(94,"Madagascar","Ariary"),
    MKD(95,"Macedonia","Denars"),
    MMK(96,"Myanmar (Burma)","Kyats"),
    MNT(97,"Mongolia","Tugriks"),
    MOP(98,"Macau","Patacas"),
    MRO(99,"Mauritania","Ouguiyas"),
    MTL(100,"Malta","Liri"),
    MUR(101,"Mauritius","Rupees"),
    MVR(102,"Maldives (Maldive Islands)","Rufiyaa"),
    MWK(103,"Malawi","Kwachas"),
    MXN(104,"Mexico","Pesos"),
    MYR(105,"Malaysia","Ringgits"),
    MZM(106,"Mozambique","Meticais [obsolete]"),
    MZN(107,"Mozambique","Meticais [newer unit same name]"),
    NAD(108,"Namibia","Dollars"),
    NGN(109,"Nigeria","Nairas"),
    NIO(110,"Nicaragua","Cordobas"),
    NOK(111,"Norway","Krone"),
    NPR(112,"Nepal","Nepal Rupees"),
    NZD(113,"New Zealand","Dollars"),
    OMR(114,"Oman","Rials"),
    PAB(115,"Panama","Balboa"),
    PEN(116,"Peru","Nuevos Soles"),
    PGK(117,"Papua New Guinea","Kina"),
    PHP(118,"Philippines","Pesos"),
    PKR(119,"Pakistan","Rupees"),
    PLN(120,"Poland","Zlotych"),
    PYG(121,"Paraguay","Guarani"),
    QAR(122,"Qatar","Rials"),
    ROL(123,"Romania","Lei [obsolete]"),
    RON(124,"Romania","New Lei"),
    RUB(125,"Russia","Rubles"),
    RWF(126,"Rwanda","Rwanda Francs"),
    SAR(127,"Saudi Arabia","Riyals"),
    SBD(128,"Solomon Islands","Dollars"),
    SCR(129,"Seychelles","Rupees"),
    SDG(130,"Sudan","Dinars"),
    SEK(131,"Sweden","Kronor"),
    SGD(132,"Singapore","Dollars"),
    SHP(133,"Saint Helena","Pounds"),
    SIT(134,"Slovenia","Tolars [obsolete]"),
    SKK(135,"Slovakia","Koruny"),
    SLL(136,"Sierra Leone","Leones"),
    SOS(137,"Somalia","Shillings"),
    SPL(138,"Seborga","Luigini"),
    SRD(139,"Suriname","Dollars"),
    STD(140,"São Tome and Principe","Dobras"),
    SVC(141,"El Salvador","Colones"),
    SYP(142,"Syria","Pounds"),
    SZL(143,"Swaziland","Emalangeni"),
    THB(144,"Thailand","Baht"),
    TJS(145,"Tajikistan","Somoni"),
    TMM(146,"Turkmenistan","Manats"),
    TND(147,"Tunisia","Dinars"),
    TOP(148,"Tonga","Pa''anga"),
    TRY(149,"Turkey","New Lira"),
    TTD(150,"Trinidad and Tobago","Dollars"),
    TVD(151,"Tuvalu","Tuvalu Dollars"),
    TWD(152,"Taiwan","New Dollars"),
    TZS(153,"Tanzania","Shillings"),
    UAH(154,"Ukraine","Hryvnia"),
    UGX(155,"Uganda","Shillings"),
    USD(156,"United States of America","Dollars"),
    UYU(157,"Uruguay","Pesos"),
    UZS(158,"Uzbekistan","Sums"),
    VEB(159,"Venezuela","Bolivares"),
    VND(160,"Viet Nam","Dong"),
    VUV(161,"Vanuatu","Vatu"),
    WST(162,"Samoa","Tala"),
    XAF(163,"Communauté Financière Africaine BEAC","Francs"),
    XAG(164,"Silver","Ounces"),
    XAU(165,"Gold","Ounces"),
    XCD(166,null,"East Caribbean Dollars"),
    XDR(167,null,"International Monetary Fund (IMF) Special Drawing Rights"),
    XOF(168,"Communauté Financière Africaine BCEAO","Francs"),
    XPD(169,null,"Palladium Ounces"),
    XPF(170,null,"Comptoirs Français du Pacifique Francs"),
    XPT(171,"Platinum","Ounces"),
    YER(172,"Yemen","Rials"),
    ZAR(173,"South Africa","Rand"),
    ZMK(174,"Zambia","Kwacha"),
    ZWD(175,"Zimbabwe","Zimbabwe Dollars");


    // ---------------------------------------------------------------
    
    private Long id;
    private String country;
    private String displayName;
    private boolean enabled = true;


    private KCurrency(int id, String country, String displayName) {
        this.id = Long.valueOf(id);
        this.country = country;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCountry() {
        return country;
    }
    
    public boolean isEnabled() {
    	return enabled;
    }

    @Override
    public String toString() {
        return KEnumUtil.toString(this);
    }

    public static KCurrency getInstance(Long id) {
        return KEnumUtil.getInstance(KCurrency.class, id);
    }

    public static LinkedHashMap<Long,String> getMap() {
        return KEnumUtil.getMap(KCurrency.class);
    }

    public static LinkedHashMap<String,String> getStringMap() {
        return KEnumUtil.getStringMap(KCurrency.class);
    }
}
