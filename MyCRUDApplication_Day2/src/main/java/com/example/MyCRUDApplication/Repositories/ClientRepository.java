package com.example.MyCRUDApplication.Repositories;

import com.example.MyCRUDApplication.Model.clientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<clientModel> getClients(){
        var clients=new ArrayList<clientModel>();

        String sqlQuery="Select * from client_model ORDER BY id DESC";
        SqlRowSet rows=jdbcTemplate.queryForRowSet(sqlQuery);

        while(rows.next())
        {
            clientModel client=new clientModel();
            client.setId(rows.getInt("id"));
            client.setFirstName(rows.getString("first_name"));
            client.setLastName(rows.getString("last_name"));
            client.setEmail(rows.getString("email"));
            client.setPhone(rows.getString("phone"));
            client.setAddress(rows.getString("address"));
            client.setCreatedAt(rows.getString("created_at"));
            clients.add(client);
        }
        return clients;
    }

    public clientModel getClient(int id){
        String sqlQuery="select * from client_model where id=?";
        SqlRowSet row=jdbcTemplate.queryForRowSet(sqlQuery,id);
        if(row.next())
        {
            clientModel client=new clientModel();
            client.setId(row.getInt("id"));
            client.setFirstName(row.getString("first_name"));
            client.setLastName(row.getString("last_name"));
            client.setEmail(row.getString("email"));
            client.setPhone(row.getString("phone"));
            client.setAddress(row.getString("address"));
            client.setCreatedAt(row.getString("created_at"));

            return client;
        }
        return null;
    }
    public clientModel getClient(String Email){
        String sqlQuery="select * from client_model where email=?";
        SqlRowSet row=jdbcTemplate.queryForRowSet(sqlQuery,Email);
        if(row.next())
        {
            clientModel client=new clientModel();
            client.setId(row.getInt("id"));
            client.setFirstName(row.getString("first_name"));
            client.setLastName(row.getString("last_name"));
            client.setEmail(row.getString("email"));
            client.setPhone(row.getString("phone"));
            client.setAddress(row.getString("address"));
            client.setCreatedAt(row.getString("created_at"));

            return client;
        }
        return null;
    }

    public clientModel createClient(clientModel client){
        String sqlQuery="Insert into client_model (first_name,last_name,email,phone,address,created_at) VALUES (?,?,?,?,?,?)";
        int cnt=jdbcTemplate.update(sqlQuery,client.getFirstName(),client.getLastName(),client.getEmail(),client.getPhone(),client.getAddress(),client.getCreatedAt());

        if(cnt>0)
        {
            int id=jdbcTemplate.queryForObject("select LAST_INSERT_ID()",Integer.class);
            return getClient(id);
        }
        return null;
    }

    public clientModel updateClient(clientModel client){
        String SqlQuery = "UPDATE client_model SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, created_at = ? WHERE id = ?";
        jdbcTemplate.update(SqlQuery,client.getFirstName(),client.getLastName(),client.getEmail(),client.getPhone(),client.getAddress(),client.getCreatedAt(),client.getId());
        return getClient(client.getId());
    }

    public void deleteClient(int id){
        String sqlQuery="delete from client_model where id=?";
        jdbcTemplate.update(sqlQuery,id);
    }

}
