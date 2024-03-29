// Code generated by moshi-kotlin-codegen. Do not edit.
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.NullPointerException
import kotlin.String
import kotlin.collections.List

class PubMoshiJsonAdapter(moshi: Moshi) : JsonAdapter<PubMoshi>() {
    private val options: JsonReader.Options =
            JsonReader.Options.of("Name", "Address", "Branch", "Id", "PubService", "Dummy", "RegularBeers")

    private val stringAdapter: JsonAdapter<String> =
            moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val listOfStringAdapter: JsonAdapter<List<String>> =
            moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java), kotlin.collections.emptySet(), "regularBeers")

    override fun toString(): String = "GeneratedJsonAdapter(PubMoshi)"

    override fun fromJson(reader: JsonReader): PubMoshi {
        var name: String? = null
        var address: String? = null
        var branch: String? = null
        var id: String? = null
        var pubService: String? = null
        var dummy: String? = null
        var regularBeers: List<String>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> name = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                1 -> address = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'address' was null at ${reader.path}")
                2 -> branch = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'branch' was null at ${reader.path}")
                3 -> id = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                4 -> pubService = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'pubService' was null at ${reader.path}")
                5 -> dummy = stringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'dummy' was null at ${reader.path}")
                6 -> regularBeers = listOfStringAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'regularBeers' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = PubMoshi(
                name = name ?: throw JsonDataException("Required property 'name' missing at ${reader.path}"),
                address = address ?: throw JsonDataException("Required property 'address' missing at ${reader.path}"),
                branch = branch ?: throw JsonDataException("Required property 'branch' missing at ${reader.path}"),
                id = id ?: throw JsonDataException("Required property 'id' missing at ${reader.path}"),
                pubService = pubService ?: throw JsonDataException("Required property 'pubService' missing at ${reader.path}"))
        result = PubMoshi(
                name = name,
                address = address,
                branch = branch,
                id = id,
                pubService = pubService,
                dummy = dummy ?: result.dummy,
                regularBeers = regularBeers ?: result.regularBeers)
        return result
    }

    override fun toJson(writer: JsonWriter, value: PubMoshi?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("Name")
        stringAdapter.toJson(writer, value.name)
        writer.name("Address")
        stringAdapter.toJson(writer, value.address)
        writer.name("Branch")
        stringAdapter.toJson(writer, value.branch)
        writer.name("Id")
        stringAdapter.toJson(writer, value.id)
        writer.name("PubService")
        stringAdapter.toJson(writer, value.pubService)
        writer.name("Dummy")
        stringAdapter.toJson(writer, value.dummy)
        writer.name("RegularBeers")
        listOfStringAdapter.toJson(writer, value.regularBeers)
        writer.endObject()
    }
}
