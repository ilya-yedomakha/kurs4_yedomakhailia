package stu.cn.ua.kurs4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.kurs4.model.domain.QueueRow;
import stu.cn.ua.kurs4.model.services.QueueRowService;

import java.util.List;
@RestController
public class QueueRowController {
    @Autowired
    private QueueRowService queueService;

    @GetMapping("queue/{id}")
    public QueueRow getQueueRow(@PathVariable Long id){
        return queueService.findById(id);
    }

    @GetMapping("/queue/getAll")
    public List<QueueRow> getQueues(){
        return queueService.findAll();
    }

    @PostMapping("/queueRow/")
    public QueueRow saveQueueRow(@RequestBody QueueRow queueRow){
        return queueService.save(queueRow);
    }
}
