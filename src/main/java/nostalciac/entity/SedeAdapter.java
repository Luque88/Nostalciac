/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author tss
 */
public class SedeAdapter implements JsonbAdapter<Sede,JsonObject> {

    @Override
    public JsonObject adaptToJson(Sede sede) throws Exception {
        return Json.createObjectBuilder()
                .add("id",sede.getId())
                .add("nome", sede.getNome())
                .build();
    }

    @Override
    public Sede adaptFromJson(JsonObject o) throws Exception {
        return new Sede(
            o.getInt("id"),
            o.getString("nome"));
    }
    
}
