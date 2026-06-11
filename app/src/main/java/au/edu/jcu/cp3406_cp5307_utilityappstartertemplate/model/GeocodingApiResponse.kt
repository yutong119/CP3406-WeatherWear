package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model

data class GeocodingApiResponse(
    val results: List<GeocodingResult>?
)

data class GeocodingResult(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String?
)