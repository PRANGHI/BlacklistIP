package pranghi.miu.blacklistcheck



import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface virustotalapi {


    @GET("/api/v3/ip_addresses/{ip}")
    suspend fun getResponse(@Header("x-apikey") token:String,@Path("ip")  ip:String): response


}