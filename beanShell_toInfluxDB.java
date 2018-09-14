import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

//Escape the string values before posting the data
String escapeValue(String val){
val = val.replaceAll(",", "\\,")
.replaceAll(" ", "\\ ")
.replaceAll("=", "\\=")
.trim();
return val;
}

//Post the result to influxDB 
void PostMeasurement(String metric){
httpclient = new DefaultHttpClient(new BasicHttpParams());
httpPost = new HttpPost(); 
httpPost.setURI(new URI("http://localhost:8086/write?db=isna"));
httpPost.setEntity(new StringEntity(metric));
HttpResponse response = httpclient.execute(httpPost);
EntityUtils.consumeQuietly(response.getEntity());
}

//prepare string for request
result = new StringBuilder();

status = "Failure";
if(sampleResult.isSuccessful())
status = "Success";

result.append("samples,")
.append("tag=")
.append("${myTag}")
.append(",label=")
.append(escapeValue(sampleResult.getSampleLabel()))
.append(",thread=")
.append(escapeValue(sampleResult.getThreadName()))
.append(",status=")
.append(status)
.append(" ")
.append("count=")
.append(sampleResult.getSampleCount())
.append(",threads=")
.append(sampleResult.getAllThreads())
.append(",load_time=")
.append(sampleResult.getTime())
.append(",latency_time=")
.append(sampleResult.getLatency())
.append(",connect_time=")
.append(sampleResult.getConnectTime())
.append(",idle_time=")
.append(sampleResult.getIdleTime())
.append(",start_time=")
.append(sampleResult.getStartTime())
.append(",end_time=")
.append(sampleResult.getEndTime())
.append(",responsecode=")
.append(sampleResult.getResponseCode())
.append(",recieve=")
.append(sampleResult.getBytesAsLong())
.append(",send=")
.append(sampleResult.getSentBytes())
.append(",timestamp=")
.append(sampleResult.getTimeStamp()) 
.append("000000")
.append( " ")
.append(sampleResult.getTimeStamp())
.append("000000");

//To call the function which posts the data
PostMeasurement(result.toString());
