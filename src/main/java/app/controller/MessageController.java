package app.controller;

import app.model.Conversation;
import app.service.MessageServiceImpl;
import app.util.GenericUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;

    @GetMapping
    public ResponseEntity<List<Conversation>> listAllConversations(){
        try{
            Map<String,Object> res = new HashMap<>();
            List<Conversation> conversations = messageService.listAllConversations();
            if (conversations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            };
            return new ResponseEntity<>(conversations, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<List<Map<String,Object>>> sendEmail(@RequestBody Map<String, Object> param){
        List<Map<String,Object>> res = new ArrayList<>();
        try {
            res = messageService.sendEmail(GenericUtil.object2list(param.get("recevier_ids")), (String) param.get("message"));
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
