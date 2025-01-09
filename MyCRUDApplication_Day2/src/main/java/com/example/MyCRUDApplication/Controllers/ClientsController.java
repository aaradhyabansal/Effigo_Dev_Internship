package com.example.MyCRUDApplication.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.MyCRUDApplication.DTO.ClientDto;
import com.example.MyCRUDApplication.Model.clientModel;
import com.example.MyCRUDApplication.Repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Correct Model import
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientRepository repo;

    @GetMapping
    public String getClients(Model model)
    {
         List<clientModel> clients=repo.getClients();
         model.addAttribute("clients",clients);
         return "clients/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model)
    {
        ClientDto client=new ClientDto();
        model.addAttribute("client",client);
        return "clients/create";
    }

    @PostMapping("/create")
    public String CreateClient(@Valid @ModelAttribute ClientDto clientDto, BindingResult result)
    {
        if(repo.getClient(clientDto.getEmail())!=null)
        {
            result.addError(new FieldError("clientDto","email",clientDto.getEmail(),false,null,null,"Email Already Exists"));
        }
        if(result.hasErrors())
        {
            return "clients/create";
        }
        clientModel client=new clientModel();

        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        repo.createClient(client);

        return "redirect:/clients";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model,@RequestParam int id)
    {
        clientModel client=repo.getClient(id);
        if(client==null)
        {
            return "redirect:/clients";
        }
        model.addAttribute("client",client);

        ClientDto clientDto=new ClientDto();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setAddress(client.getAddress());

        model.addAttribute("clientDto",clientDto);
        return "clients/edit";
    }
    @PostMapping("/edit")
    public String updateClient(Model model,@RequestParam int id,@Valid @ModelAttribute ClientDto clientDto, BindingResult result)
    {
        clientModel client=repo.getClient(id);
        if(client==null)
        {
            return "redirect:/clients";
        }
        model.addAttribute("client",client);
        if(result.hasErrors())
        return "clients/edit";

        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());

        System.out.println("feifnrinf");
        repo.updateClient(client);
        return "redirect:/clients";
    }
    @GetMapping("/delete")
    public String deleteClient(@RequestParam int id)
    {
        repo.deleteClient(id);
        return "redirect:/clients";
    }
}
