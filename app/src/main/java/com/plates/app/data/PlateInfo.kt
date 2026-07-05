package com.plates.app.data

data class PlateInfo(
    val code: String,
    val fullName: String,
    val isCanadian: Boolean,
    val bgColor: String,        // hex ARGB, e.g. "#FF1B3A7A"
    val borderColor: String,
    val textColor: String,
    val taglineColor: String,
    val tagline: String,
    val sampleNumber: String
)

object PlateData {

    val all: List<PlateInfo> = listOf(
        // ── United States ─────────────────────────────────────────────────────

        PlateInfo("AL","Alabama",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF888888","Heart of Dixie","4AB·2691"),
        PlateInfo("AK","Alaska",false,"#FF1B3A7A","#FFFFCC00","#FFFFCC00","#FFFFCC00","The Last Frontier","GDL·327"),
        PlateInfo("AZ","Arizona",false,"#FFB8400A","#FF1B3A7A","#FFFFFFFF","#FFFFCC00","Grand Canyon State","BZX·1847"),
        PlateInfo("AR","Arkansas",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF888888","The Natural State","932·VGP"),
        PlateInfo("CA","California",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","California Republic","7ABC·123"),
        PlateInfo("CO","Colorado",false,"#FFFFFFFF","#FF1B7A3A","#FF1B3A7A","#FF1B7A3A","Colorful Colorado","ABC·D12"),
        PlateInfo("CT","Connecticut",false,"#FF1B3A7A","#FFFFFFFF","#FFFFFFFF","#FFDDDDDD","Constitution State","AB·12345"),
        PlateInfo("DE","Delaware",false,"#FF1B3A7A","#FFFFCC00","#FFFFCC00","#FFFFCC00","The First State","PC·12345"),
        PlateInfo("FL","Florida",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FFFF6B00","Sunshine State","ABC·D12"),
        PlateInfo("GA","Georgia",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FFCC2222","Peach State","TBF·4921"),
        PlateInfo("HI","Hawaii",false,"#FFFFFFFF","#FF1B7A3A","#FF1B3A7A","#FF1B7A3A","Aloha State","LLL·100"),
        PlateInfo("ID","Idaho",false,"#FF1B3A7A","#FFFFFFFF","#FFFFFFFF","#FFDDDDDD","Famous Potatoes","1A·B2345"),
        PlateInfo("IL","Illinois",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF888888","Land of Lincoln","AB·12345"),
        PlateInfo("IN","Indiana",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Crossroads of America","123·ABC"),
        PlateInfo("IA","Iowa",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF888888","Hawkeye State","ABC·123"),
        PlateInfo("KS","Kansas",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FFCC8800","Sunflower State","123·ABC"),
        PlateInfo("KY","Kentucky",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Bluegrass State","ABC·123"),
        PlateInfo("LA","Louisiana",false,"#FF1B3A7A","#FFFFCC00","#FFFFFFFF","#FFFFCC00","Pelican State","ABC·123"),
        PlateInfo("ME","Maine",false,"#FFFFFFFF","#FF1B7A3A","#FF1B3A7A","#FF1B7A3A","Vacationland","1234·AB"),
        PlateInfo("MD","Maryland",false,"#FF1A1A1A","#FFFFCC00","#FFFFCC00","#FFCC2222","Old Line State","1AB·2345"),
        PlateInfo("MA","Massachusetts",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","The Spirit of America","1ABC·23"),
        PlateInfo("MI","Michigan",false,"#FF1B3A7A","#FFFFFFFF","#FFFFFFFF","#FFDDDDDD","Great Lakes State","ABC·1234"),
        PlateInfo("MN","Minnesota",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Land of 10,000 Lakes","ABC·123"),
        PlateInfo("MS","Mississippi",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF888888","Magnolia State","ABC·1234"),
        PlateInfo("MO","Missouri",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF888888","Show-Me State","AB1·C2D"),
        PlateInfo("MT","Montana",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Big Sky Country","1·AB1234"),
        PlateInfo("NE","Nebraska",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","The Good Life","ABC·123"),
        PlateInfo("NV","Nevada",false,"#FF1B3A7A","#FFB8B830","#FFB8B830","#FFB8B830","Battle Born","123·ABC"),
        PlateInfo("NH","New Hampshire",false,"#FFFFFFFF","#FF1B7A3A","#FF1B3A7A","#FF1B7A3A","Live Free or Die","123·4567"),
        PlateInfo("NJ","New Jersey",false,"#FFE8D5A3","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Garden State","ABC·12D"),
        PlateInfo("NM","New Mexico",false,"#FFFFCC00","#FFCC2222","#FFCC2222","#FFCC2222","Land of Enchantment","ABC·123"),
        PlateInfo("NY","New York",false,"#FFFFFFFF","#FFFF6B00","#FF1B3A7A","#FFFF6B00","Empire State","ABC·1234"),
        PlateInfo("NC","North Carolina",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FFCC2222","First in Flight","ABC·1234"),
        PlateInfo("ND","North Dakota",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Peace Garden State","ABC·123"),
        PlateInfo("OH","Ohio",false,"#FFFFFFFF","#FFCC2222","#FF1B3A7A","#FFCC2222","Birthplace of Aviation","ABC·1234"),
        PlateInfo("OK","Oklahoma",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B7A3A","Native America","ABC·123"),
        PlateInfo("OR","Oregon",false,"#FF1B3A7A","#FFFFCC00","#FFFFCC00","#FFFFCC00","Pacific Wonderland","ABC·123"),
        PlateInfo("PA","Pennsylvania",false,"#FFFFCC00","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Keystone State","ABC·1234"),
        PlateInfo("RI","Rhode Island",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Ocean State","123·456"),
        PlateInfo("SC","South Carolina",false,"#FF1B3A7A","#FFFFFFFF","#FFFFFFFF","#FFDDDDDD","Palmetto State","ABC·123"),
        PlateInfo("SD","South Dakota",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Great Faces. Great Places.","1AB·234"),
        PlateInfo("TN","Tennessee",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Volunteer State","A12·3BC"),
        PlateInfo("TX","Texas",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FFCC2222","Lone Star State","ABC·1234"),
        PlateInfo("UT","Utah",false,"#FFFFFFFF","#FF1A1A1A","#FF1A1A1A","#FF1B3A7A","Life Elevated","A123·BC"),
        PlateInfo("VT","Vermont",false,"#FF2D6A2D","#FFFFFFFF","#FFFFFFFF","#FFFFCC00","Green Mountain State","ABC·123"),
        PlateInfo("VA","Virginia",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Virginia is for Lovers","ABC·1234"),
        PlateInfo("WA","Washington",false,"#FF2D6A2D","#FFFFFFFF","#FFFFFFFF","#FFFFFFFF","Evergreen State","ABC·1234"),
        PlateInfo("WV","West Virginia",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B7A3A","Wild, Wonderful","1AB·234"),
        PlateInfo("WI","Wisconsin",false,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","America's Dairyland","ABC·1234"),
        PlateInfo("WY","Wyoming",false,"#FFFFCC00","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","The Cowboy State","1·AB·1234"),
        PlateInfo("DC","Washington D.C.",false,"#FFFFFFFF","#FFCC2222","#FFCC2222","#FFCC2222","Taxation Without Representation","DC·1234"),

        // ── Canadian Provinces & Territories ──────────────────────────────────

        PlateInfo("AB","Alberta",true,"#FF1B3A7A","#FFFFCC00","#FFFFFFFF","#FFFFCC00","Wild Rose Country","ABC·1234"),
        PlateInfo("BC","British Columbia",true,"#FF1B3A7A","#FFFFFFFF","#FFFFFFFF","#FFDDDDDD","Beautiful British Columbia","AB·1234"),
        PlateInfo("MB","Manitoba",true,"#FF2D6A2D","#FFFFFFFF","#FFFFFFFF","#FFFFCC00","Friendly Manitoba","ABC·123"),
        PlateInfo("NB","New Brunswick",true,"#FFFFFFFF","#FF8B1A1A","#FF1B3A7A","#FF8B1A1A","Picture Province","ABC·123"),
        PlateInfo("NL","Newfoundland & Lab.",true,"#FF1B3A7A","#FFFFFFFF","#FFFFFFFF","#FFDDDDDD","Canada's Happy Province","ABC·123"),
        PlateInfo("NT","Northwest Territories",true,"#FFFFFFFF","#FF1B3A7A","#FF1B3A7A","#FF1B3A7A","Spectacular NWT","A·12345"),
        PlateInfo("NS","Nova Scotia",true,"#FF1B3A7A","#FFFFFFFF","#FFFFFFFF","#FFDDDDDD","Canada's Ocean Playground","ABC·123"),
        PlateInfo("NU","Nunavut",true,"#FFFFFFFF","#FF8B1A1A","#FF1B3A7A","#FF8B1A1A","Explore Canada's Arctic","A·12345"),
        PlateInfo("ON","Ontario",true,"#FF1B3A7A","#FFFFCC00","#FFFFFFFF","#FFFFCC00","Yours to Discover","ABCD·123"),
        PlateInfo("PE","Prince Edward Island",true,"#FF2D6A2D","#FFFFCC00","#FFFFFFFF","#FFFFCC00","Birthplace of Confederation","ABC·123"),
        PlateInfo("QC","Quebec",true,"#FF1B3A7A","#FFFFFFFF","#FFFFFFFF","#FFDDDDDD","Je me souviens","ABC·123"),
        PlateInfo("SK","Saskatchewan",true,"#FF1B3A7A","#FFFFCC00","#FFFFFFFF","#FFFFCC00","Land of Living Skies","ABC·123"),
        PlateInfo("YT","Yukon",true,"#FF1B3A7A","#FFFFCC00","#FFFFFFFF","#FFFFCC00","The Larger Than Life","ABC·123")
    )

    val usStates = all.filter { !it.isCanadian }
    val canadianProvinces = all.filter { it.isCanadian }
    val totalCount = all.size

    fun findByCode(code: String) = all.find { it.code == code }
}
