package stu.cn.ua.kurs4.controllers.view;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.kurs4.model.domain.QueueRow;
import stu.cn.ua.kurs4.model.services.people.ClientService;
import stu.cn.ua.kurs4.model.services.people.OperatorService;


@Controller
public class ViewController {
    private final ClientService clientService;

    private final OperatorService operatorService;


    public ViewController(ClientService clientService, OperatorService operatorService) {
        this.clientService = clientService;
        this.operatorService = operatorService;
    }

    @RequestMapping("/")
    public String showMain(Model model){
        return "index";
    }

    @RequestMapping("/webcab/show")
    public String showWebCab(Model model){
        return "webcab";
    }

    @RequestMapping("webcab/calendar/show/")
    public String showCalendar(Model model){
        model.addAttribute("queueRow", new QueueRow());
        return "calendar";
    }

    @PostMapping("/webcab/calendar/queueRegistration/show/")
    public String submitCalendarForm(@ModelAttribute("queueRow") QueueRow queueRow, Model model) {
        model.addAttribute("dateTimeParam", queueRow.getDateTime());
        model.addAttribute("queueRow", new QueueRow());
        return "queueRegistration";
    }

    @RequestMapping("webcab/docs/{id}/show")
    public String showDocuments(@PathVariable Long id,Model model){
        model.addAttribute("documentList",clientService.getDocumentsByClientId(id));
        return "documents";
    }

}
