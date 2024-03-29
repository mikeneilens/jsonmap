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

class PubDataMoshiJsonAdapter(moshi: Moshi) : JsonAdapter<PubDataMoshi>() {
    private val options: JsonReader.Options = JsonReader.Options.of("Pubs")

    private val listOfPubMoshiAdapter: JsonAdapter<List<PubMoshi>> =
            moshi.adapter<List<PubMoshi>>(Types.newParameterizedType(List::class.java, PubMoshi::class.java), kotlin.collections.emptySet(), "pubs")

    override fun toString(): String = "GeneratedJsonAdapter(PubDataMoshi)"

    override fun fromJson(reader: JsonReader): PubDataMoshi {
        var pubs: List<PubMoshi>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> pubs = listOfPubMoshiAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'pubs' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = PubDataMoshi(
                pubs = pubs ?: throw JsonDataException("Required property 'pubs' missing at ${reader.path}"))
        return result
    }

    override fun toJson(writer: JsonWriter, value: PubDataMoshi?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("Pubs")
        listOfPubMoshiAdapter.toJson(writer, value.pubs)
        writer.endObject()
    }
}
