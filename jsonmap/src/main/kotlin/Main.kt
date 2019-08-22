import com.beust.klaxon.FieldRenamer
import com.beust.klaxon.Klaxon

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

fun main(args: Array<String>) {
    useKlaxon()
    useJackson()
    useMoshi()
}

@JsonClass(generateAdapter = true)
class PubDataMoshi(@Json(name = "Pubs" )val pubs:List<PubMoshi>)

@JsonClass(generateAdapter = true)
class PubMoshi(
    @Json(name = "Name" )       val name: String,
    @Json(name = "Address" )    val address: String,
    @Json(name = "Branch" )     val branch: String,
    @Json(name = "Id" )         val id: String,
    @Json(name = "PubService" ) val pubService:String,
    @Json(name = "Dummy" )      val dummy:String = "Not sent",
    @Json(name = "RegularBeers" ) val regularBeers: List<String> = listOf<String>()
)

fun useMoshi() {

    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<PubDataMoshi> = moshi.adapter(PubDataMoshi::class.java)
    val pubData = adapter.fromJson(testData)

    val pubs = pubData?.pubs ?: listOf<PubMoshi>()
    val regularBeers = pubs[0].regularBeers

    println("Moshi ${pubs[0].name} $regularBeers ${pubs[0].dummy} ")
}

class PubDataJackson(val Pubs:Map<Any, List<PubJackson>>)
class PubJackson(
    @JsonProperty("Name")        val name: String,
    @JsonProperty("Address")     val address: String,
    @JsonProperty("Branch")      val branch: String,
    @JsonProperty("Id")          val id: String,
    @JsonProperty("PubService")  val pubService:String,
    @JsonProperty("Dummy")       val dummy:String = "Not sent",
    @JsonProperty("RegularBeers")val regularBeers:List<String> = listOf<String>()

)

fun useJackson() {
    val jacksonMapper = ObjectMapper().registerModule(KotlinModule()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val pubData:PubDataJackson = jacksonMapper.readValue(testData)
    val pubs = pubData.Pubs["Pubs"] ?: listOf<PubJackson>()
    val regularBeers = pubs[0].regularBeers

    println("Jacks ${pubs[0].name} $regularBeers ${pubs[0].dummy}")
}

class PubDataKlaxon(val pubs:List<PubKlaxon>)
class PubKlaxon(
    val name: String,
    val branch: String,
    val id: String,
    val pubService:String,
    val dummy:String = "Not sent",
    val regularBeers:List<String> = listOf<String>()
)

fun useKlaxon(){
    val renamer = object: FieldRenamer {
        override fun toJson(fieldName: String) = fieldName.capitalize()
        override fun fromJson(fieldName: String) = fieldName.toLowerCase()
    }
    val klaxon = Klaxon().fieldRenamer(renamer)

    val pubData = klaxon.parse<PubDataKlaxon>(testData)
    val pubs = pubData?.pubs ?: listOf<PubKlaxon>()
    val regularBeers = pubs[0].regularBeers

    println("klaxo ${pubs[0].name} $regularBeers ${pubs[0].dummy}"  )
}

val testData = "{\"Pubs\":[{\n" +
        "  \"Name\": \"Cask and Glass\",\n" +
        "  \"Address\": \"39 Palace Street Victoria London SW1E 5HN\",\n" +
        "  \"Town\": \"London\",\n" +
        "  \"PostCode\": \"SW1E 5HN\",\n" +
        "  \"PhotoURL\": \"https://whatpub.com/img/WLD/15938/cask-glass-london/200/150\",\n" +
        "  \"Telephone\": \"02078347630\",\n" +
        "  \"OpeningTimes\": \"\",\n" +
        "  \"MealTimes\": \"12 - 2.30 Mon - Sat\",\n" +
        "  \"Owner\": \"Shepherd Neame\",\n" +
        "  \"About\": \"First licensed for beer only in 1862 as the Duke of Cambridge and only selling beer in half pints for a long time, it changed names in 1962. This attractive one-room pub on the route between Buckingham Palace and Westminster Cathedral, adorned with flowers in summer, is a haven for tourists, office workersÂ‚ Â†and local residents. The wood-panelled bar has pictures of local scenes and politicians. Look out for the bull#039;s-eye windows and the two paintings of the pub on the way to the toilets. A cosy place for a pint after (or instead of)Â‚ Â†visiting the sights. Food is toasted sandwiches only!\",\n" +
        "  \"GuestBeerDesc\": \"This pub serves 2 changing beers. \",\n" +
        "  \"Lng\": -0.1403,\n" +
        "  \"Lat\": 51.4985,\n" +
        "  \"RegularBeers\": [\n" +
        "    \"Shepherd Neame Master Brew\",\n" +
        "    \"Shepherd Neame Spitfire\"\n" +
        "  ],\n" +
        "  \"GuestBeers\": [\n" +
        "    \"Shepherd Neame --seasonal--\",\n" +
        "    \"Shepherd Neame --varies--\",\n" +
        "    \"Shepherd Neame Whitstable Bay Pale Ale\"\n" +
        "  ],\n" +
        "  \"Features\": [\n" +
        "    \"Real Ale Available\",\n" +
        "    \"Cask Marque Accredited\",\n" +
        "    \"Quiet Pub\"\n" +
        "  ],\n" +
        "  \"Facilities\": [\n" +
        "    \"Lunchtime Meals - Toasted sandwiches.\",\n" +
        "    \"Pub Garden\",\n" +
        "    \"Newspapers\",\n" +
        "    \"Wifi\"\n" +
        "  ],\n" +
        "  \"PubService\": \"https://pubcrawlapi.appspot.com/pub/?v=1&id=15938&branch=WLD&uId=mike&pubs=no&realAle=yes&memberDiscount=no&town=London\",\n" +
        "  \"Id\": \"15938\",\n" +
        "  \"Branch\": \"WLD\",\n" +
        "  \"CreateTS\": \"2019-05-16 19:31:39\",\n" +
        "  \"Message\": {\n" +
        "    \"Status\": 0,\n" +
        "    \"Text\": \"Pub retrieved.\"\n" +
        "  }\n" +
        "}]}"







