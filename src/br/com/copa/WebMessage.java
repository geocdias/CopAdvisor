package br.com.copa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;

//Autor George Dias

/*Classe responsável em criar uma ponte, fazendo a conexão com o servidor, 
 * para enviar as requisições e receber as respostas
 */



public class WebMessage extends Activity{
	
	 private HttpClient httpclient = new DefaultHttpClient();
     private HttpPost httppost = new HttpPost("http://192.168.1.4:8080/CopaAdvisorServer/principal");

//Método responsavel em criar a requisição de busca de um estadio no servidor	
    public Estadio executeRequest(String nomeEstadio){
                     
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("op", "estadio"));
            nameValuePairs.add(new BasicNameValuePair("nomeEstadio", nomeEstadio));
            
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Executa HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity mensagem = response.getEntity();//pega o json
             
            String resp =  EntityUtils.toString(mensagem);// converte o conteudo em string
                                     
           //Seta a resposta como um objeto JSON para acessar as informações
           	JSONObject obJson = new JSONObject(resp);
             
            Estadio estadio = new Estadio(obJson);
            return estadio;
                     
        } catch (ClientProtocolException e) {
        	 getMensage("ERROR" , e.getMessage());
        } catch (IOException e) {
        	 getMensage("ERROR" , "IO-ERROR "+e.getMessage());
        } catch (JSONException e1) {
        	 getMensage("ERROR" , "IO-ERROR "+ e1.getMessage());
        }
        
		return null;
		
    }
    
    
    //Método responsável por enviar a requisição de busca dos estádios ao servidor. 
    public List <String> buscaEstadiosRequest(){
        
    	List <String>estadioList = new ArrayList<String>();
    	Estadio estadio = new Estadio();
        try {
           //difine o atributo op que sera passado pelo método POST para o servidor
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("op","listarEstadios"));
                      
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Executa HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity result= response.getEntity();//pega o jsonArray
             
            String resp =  EntityUtils.toString(result);// converte o conteudo em string
                                     
           //Seta a resposta como um objeto JSONArray para acessar as informações
           	JSONArray estadioJsonList = new JSONArray(resp);
         
           	//constroi o array com os nomes dos estadios
           	for(int i=0; i < estadioJsonList.length(); i++){
        	   estadio.setNome(estadioJsonList.getJSONObject(i));
        	   estadioList.add(estadio.getNome());
        	  
           }
           
            return estadioList;
            
                     
        } catch (ClientProtocolException e) {
        	 getMensage("ERROR" , e.getMessage());
        } catch (IOException e) {
        	 getMensage("ERROR" , "IO-ERROR "+e.getMessage());
        } catch (JSONException e1) {
        	 getMensage("ERROR" , "IO-ERROR "+ e1.getMessage());
        }
        
		return null;
		
    }
    
    
    public void getMensage(String title,String mensage){
     	 
     	 AlertDialog.Builder mensagem = new AlertDialog.Builder(WebMessage.this);
     	 mensagem.setTitle(title);
     	 mensagem.setMessage(mensage);
     	 mensagem.setNeutralButton("Fechar", null);
     	 mensagem.show();
     	 
     	 
      }
}
