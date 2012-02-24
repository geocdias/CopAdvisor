/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.copa.controller;

import br.copa.dao.EstadioDAO;
import br.copa.model.Estadio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.catalina.util.Base64;

/**
 *
 * @author George
 */
public class EstadioController {
    private Estadio estadio = new Estadio();
    private EstadioDAO estadioDao  = new EstadioDAO();
    private List<Estadio> estadioList  = new ArrayList<Estadio>();
    private JSONObject jsonObj = new JSONObject();
    private JSONArray arrayEstadio = new JSONArray();
    private  Iterator it;
    
    
    public JSONObject getEstadioByName(String nome)throws JSONException{
      
        estadio = estadioDao.getEstadio(nome);

        jsonObj.put("nome", estadio.getNome());
        jsonObj.put("descricao", estadio.getDescricao());
        jsonObj.put("cidade", estadio.getCidade());
        jsonObj.put("foto", Base64.encode(estadio.getFoto()));

        return jsonObj;
    
    }
    public Estadio getEstadioById(int id){
      
     estadio = estadioDao.getEstadio(id);
     
     return estadio;
    
    }
    
    public JSONArray getEstadioList() throws JSONException{
   
       estadioList = estadioDao.getEstadios();
       it = estadioList.iterator();
       while (it.hasNext()) {
            estadio = (Estadio) it.next();
            jsonObj.put("nome", estadio.getNome());
            arrayEstadio.add(jsonObj);
        }
       
        return arrayEstadio;
    
    }
    
    
    
    
}
