package pranghi.miu.blacklistcheck

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers


object RetrofitClient{

    val baseurl="https://www.virustotal.com"

    val instance: virustotalapi by lazy {
        Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(virustotalapi::class.java)

    }



}



class MainActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainlayout=findViewById<LinearLayout>(R.id.main)
        val IPlist= listOf("8.8.8.8","84.32.188.57","185.100.64.32")

        CoroutineScope(Dispatchers.IO).launch {
            try{

                for( ip in IPlist) {
                    val response = RetrofitClient.instance.getResponse(
                        "5af2b2972f2200056e77182a172fdeaedde4a312f410a3f54cd9056db0e6c40c",
                        ip
                    )
//                val responseData = response.getAsJsonObject("data")
//                val malicious =  responseData.getAsJsonObject("attributes").getAsJsonObject("last_analysis_stats").getAsJsonPrimitive("malicious").asString

                    val malicious = response.data.attributes.last_analysis_stats.malicious
                    runOnUiThread {

                        var text = TextView(this@MainActivity)
                        text.setBackgroundColor(Color.LTGRAY)
                        text.text = malicious.toString()
                        mainlayout.addView(text)


                    }

                    Log.d("virusdata", malicious.toString())
                }
            }
            catch(e:Exception){

                Log.d("Exception", e.toString())
            }
        }
        }



}
