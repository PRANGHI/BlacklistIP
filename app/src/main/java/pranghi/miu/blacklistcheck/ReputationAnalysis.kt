package pranghi.miu.blacklistcheck

import com.google.gson.JsonObject


data class response(
    val data:Data
)
data class Data(
    val attributes:Attributes
)

data class ReputationAnalysis (
    val malicious:String?=null,
    val suspicous:Double?=null,
    val undetected:Double?=null,
    val harmless:Double?=null
)

data class Attributes(
    val last_analysis_stats:ReputationAnalysis
)
