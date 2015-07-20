package org.androidtown.mytap;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Created by constant on 15. 7. 16..
 */
public class NetManager {



    public static HttpClient getHttpClient(){
        HttpClient httpclient = null;
        HttpParams hp = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(hp, 10000);
        HttpConnectionParams.setSoTimeout(hp, 10000) ;
        httpclient = new DefaultHttpClient(hp);

        return httpclient ;
    }
    //HttpPost
    public static HttpPost getPost(String url){

        HttpPost post =  new HttpPost( url );
        post.setHeader("User-Agent","ANDROID");
        return post;
    }
}
