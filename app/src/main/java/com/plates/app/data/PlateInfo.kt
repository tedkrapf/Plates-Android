package com.plates.app.data

data class PlateInfo(
    val code: String,
    val fullName: String,
    val isCanadian: Boolean,
    val plateBackground: Long,
    val plateBorder: Long,
    val plateText: Long,
    val taglineText: Long,
    val tagline: String,
    val sampleNumber: String
)

object PlateData {

    val all: List<PlateInfo> = listOf(
        // ── United States ──────────────────────────────────────────────────────

        PlateInfo("AL", "Alabama", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF6B6B6B,
            "Heart of Dixie", "4AB·2691"),

        PlateInfo("AK", "Alaska", false,
            0xFF1B3A7A, 0xFFFFD700, 0xFFFFD700, 0xFFFFD700,
            "The Last Frontier", "GDL·327"),

        PlateInfo("AZ", "Arizona", false,
            0xFFB8400A, 0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFD700,
            "Grand Canyon State", "BZX·1847"),

        PlateInfo("AR", "Arkansas", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF6B6B6B,
            "The Natural State", "932·VGP"),

        PlateInfo("CA", "California", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "California Republic", "7ABC·123"),

        PlateInfo("CO", "Colorado", false,
            0xFFFFFFFF, 0xFF1B7A3A, 0xFF1B3A7A, 0xFF1B7A3A,
            "Colorful Colorado", "ABC·D12"),

        PlateInfo("CT", "Connecticut", false,
            0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFDDDDDD,
            "Constitution State", "AB·12345"),

        PlateInfo("DE", "Delaware", false,
            0xFF1B3A7A, 0xFFFFD700, 0xFFFFD700, 0xFFFFD700,
            "The First State", "PC·12345"),

        PlateInfo("FL", "Florida", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFFFF6B00,
            "Sunshine State", "ABC·D12"),

        PlateInfo("GA", "Georgia", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFFCC2222,
            "Peach State", "TBF·4921"),

        PlateInfo("HI", "Hawaii", false,
            0xFFFFFFFF, 0xFF1B7A3A, 0xFF1B3A7A, 0xFF1B7A3A,
            "Aloha State", "LLL·100"),

        PlateInfo("ID", "Idaho", false,
            0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFDDDDDD,
            "Famous Potatoes", "1A·B2345"),

        PlateInfo("IL", "Illinois", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF6B6B6B,
            "Land of Lincoln", "AB·12345"),

        PlateInfo("IN", "Indiana", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Crossroads of America", "123·ABC"),

        PlateInfo("IA", "Iowa", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF6B6B6B,
            "Hawkeye State", "ABC·123"),

        PlateInfo("KS", "Kansas", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFFCC8800,
            "Sunflower State", "123·ABC"),

        PlateInfo("KY", "Kentucky", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Bluegrass State", "ABC·123"),

        PlateInfo("LA", "Louisiana", false,
            0xFF1B3A7A, 0xFFFFD700, 0xFFFFFFFF, 0xFFFFD700,
            "Pelican State", "ABC·123"),

        PlateInfo("ME", "Maine", false,
            0xFFFFFFFF, 0xFF1B7A3A, 0xFF1B3A7A, 0xFF1B7A3A,
            "Vacationland", "1234·AB"),

        PlateInfo("MD", "Maryland", false,
            0xFF1A1A1A, 0xFFFFD700, 0xFFFFD700, 0xFFCC2222,
            "Old Line State", "1AB·2345"),

        PlateInfo("MA", "Massachusetts", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "The Spirit of America", "1ABC·23"),

        PlateInfo("MI", "Michigan", false,
            0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFDDDDDD,
            "Great Lakes State", "ABC·1234"),

        PlateInfo("MN", "Minnesota", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Land of 10,000 Lakes", "ABC·123"),

        PlateInfo("MS", "Mississippi", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF6B6B6B,
            "Magnolia State", "ABC·1234"),

        PlateInfo("MO", "Missouri", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF6B6B6B,
            "Show-Me State", "AB1·C2D"),

        PlateInfo("MT", "Montana", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Big Sky Country", "1·AB1234"),

        PlateInfo("NE", "Nebraska", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "The Good Life", "ABC·123"),

        PlateInfo("NV", "Nevada", false,
            0xFF1B3A7A, 0xFFB8B830, 0xFFB8B830, 0xFFB8B830,
            "Battle Born", "123·ABC"),

        PlateInfo("NH", "New Hampshire", false,
            0xFFFFFFFF, 0xFF1B7A3A, 0xFF1B3A7A, 0xFF1B7A3A,
            "Live Free or Die", "123·4567"),

        PlateInfo("NJ", "New Jersey", false,
            0xFFE8D5A3, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Garden State", "ABC·12D"),

        PlateInfo("NM", "New Mexico", false,
            0xFFFFD700, 0xFFCC2222, 0xFFCC2222, 0xFFCC2222,
            "Land of Enchantment", "ABC·123"),

        PlateInfo("NY", "New York", false,
            0xFFFFFFFF, 0xFFFF6B00, 0xFF1B3A7A, 0xFFFF6B00,
            "Empire State", "ABC·1234"),

        PlateInfo("NC", "North Carolina", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFFCC2222,
            "First in Flight", "ABC·1234"),

        PlateInfo("ND", "North Dakota", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Peace Garden State", "ABC·123"),

        PlateInfo("OH", "Ohio", false,
            0xFFFFFFFF, 0xFFCC2222, 0xFF1B3A7A, 0xFFCC2222,
            "Birthplace of Aviation", "ABC·1234"),

        PlateInfo("OK", "Oklahoma", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B7A3A,
            "Native America", "ABC·123"),

        PlateInfo("OR", "Oregon", false,
            0xFF1B3A7A, 0xFFFFD700, 0xFFFFD700, 0xFFFFD700,
            "Pacific Wonderland", "ABC·123"),

        PlateInfo("PA", "Pennsylvania", false,
            0xFFFFD700, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Keystone State", "ABC·1234"),

        PlateInfo("RI", "Rhode Island", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Ocean State", "123·456"),

        PlateInfo("SC", "South Carolina", false,
            0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFDDDDDD,
            "Palmetto State", "ABC·123"),

        PlateInfo("SD", "South Dakota", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Great Faces. Great Places.", "1AB·234"),

        PlateInfo("TN", "Tennessee", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Volunteer State", "A12·3BC"),

        PlateInfo("TX", "Texas", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFFCC2222,
            "Lone Star State", "ABC·1234"),

        PlateInfo("UT", "Utah", false,
            0xFFFFFFFF, 0xFF1A1A1A, 0xFF1A1A1A, 0xFF1B3A7A,
            "Life Elevated", "A123·BC"),

        PlateInfo("VT", "Vermont", false,
            0xFF2D6A2D, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFD700,
            "Green Mountain State", "ABC·123"),

        PlateInfo("VA", "Virginia", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Virginia is for Lovers", "ABC·1234"),

        PlateInfo("WA", "Washington", false,
            0xFF2D6A2D, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
            "Evergreen State", "ABC·1234"),

        PlateInfo("WV", "West Virginia", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B7A3A,
            "Wild, Wonderful", "1AB·234"),

        PlateInfo("WI", "Wisconsin", false,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "America's Dairyland", "ABC·1234"),

        PlateInfo("WY", "Wyoming", false,
            0xFFFFD700, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "The Cowboy State", "1·AB·1234"),

        PlateInfo("DC", "Washington D.C.", false,
            0xFFFFFFFF, 0xFFCC2222, 0xFFCC2222, 0xFFCC2222,
            "Taxation Without Representation", "DC·1234"),

        // ── Canadian Provinces & Territories ──────────────────────────────────

        PlateInfo("AB", "Alberta", true,
            0xFF1B3A7A, 0xFFFFD700, 0xFFFFFFFF, 0xFFFFD700,
            "Wild Rose Country", "ABC·1234"),

        PlateInfo("BC", "British Columbia", true,
            0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFDDDDDD,
            "Beautiful British Columbia", "AB·1234"),

        PlateInfo("MB", "Manitoba", true,
            0xFF2D6A2D, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFD700,
            "Friendly Manitoba", "ABC·123"),

        PlateInfo("NB", "New Brunswick", true,
            0xFFFFFFFF, 0xFF8B1A1A, 0xFF1B3A7A, 0xFF8B1A1A,
            "Picture Province", "ABC·123"),

        PlateInfo("NL", "Newfoundland & Lab.", true,
            0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFDDDDDD,
            "Canada's Happy Province", "ABC·123"),

        PlateInfo("NT", "Northwest Territories", true,
            0xFFFFFFFF, 0xFF1B3A7A, 0xFF1B3A7A, 0xFF1B3A7A,
            "Spectacular NWT", "A·12345"),

        PlateInfo("NS", "Nova Scotia", true,
            0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFDDDDDD,
            "Canada's Ocean Playground", "ABC·123"),

        PlateInfo("NU", "Nunavut", true,
            0xFFFFFFFF, 0xFF8B1A1A, 0xFF1B3A7A, 0xFF8B1A1A,
            "Explore Canada's Arctic", "A·12345"),

        PlateInfo("ON", "Ontario", true,
            0xFF1B3A7A, 0xFFFFD700, 0xFFFFFFFF, 0xFFFFD700,
            "Yours to Discover", "ABCD·123"),

        PlateInfo("PE", "Prince Edward Island", true,
            0xFF2D6A2D, 0xFFFFD700, 0xFFFFFFFF, 0xFFFFD700,
            "Birthplace of Confederation", "ABC·123"),

        PlateInfo("QC", "Québec", true,
            0xFF1B3A7A, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFDDDDDD,
            "Je me souviens", "ABC·123"),

        PlateInfo("SK", "Saskatchewan", true,
            0xFF1B3A7A, 0xFFFFD700, 0xFFFFFFFF, 0xFFFFD700,
            "Land of Living Skies", "ABC·123"),

        PlateInfo("YT", "Yukon", true,
            0xFF1B3A7A, 0xFFFFD700, 0xFFFFFFFF, 0xFFFFD700,
            "The Larger Than Life", "ABC·123")
    )

    val usStates: List<PlateInfo> = all.filter { !it.isCanadian }
    val canadianProvinces: List<PlateInfo> = all.filter { it.isCanadian }

    fun findByCode(code: String): PlateInfo? = all.find { it.code == code }

    val totalCount: Int = all.size
}
