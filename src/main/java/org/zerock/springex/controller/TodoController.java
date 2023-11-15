package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){

        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void registerGet(){
        log.info("register GET request");
    }

    @PostMapping ("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult result, RedirectAttributes redirectAttributes){
        //@Valid 유효성 검사를 통과하지 못할 경우, BindingResult result 객체의 hasErrors() 메소드는 true를 반환하게 됨.
        //예를들어, 해당 Valid 어노테이션은 TodoDTO에 존재하는 필드명에 대응해서 들어온 Form 의 name 에 바인딩 된 value를
        //TodoDTO의 필드의 데이터타입에 맞게 변환하는 작업을 거치는데, 이 과정에서 변환이 실패하게 되면 result.hasErrors()는 true가 됨.
        //ex) int age; 인데, form의 name="age" 의 value가 "다섯살" 로 들어온 경우 integer 로 변환을 실패하여 에러를 반환함.
        log.info("register post");
        todoService.register(todoDTO);
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("error",result.getFieldError());
            return "redirect:/todo/register";
        }
        return "redirect:/todo/list";
        //여기서의 리디렉션은 insert post요청이 새로고침으로 재실행되는걸 막기위해서, 클라이언트의 URI를 바꾸기 위함.
    }
    @GetMapping({"/read", "/modify"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model){

        TodoDTO todoDTO = todoService.selectOne(tno);
        log.info("Controller selectOne 결과 : "+todoDTO);

        model.addAttribute("dto", todoDTO );

    }
    @PostMapping(value="/modify")
    public String modify(@Valid TodoDTO todoDTO){
        Long tno = todoDTO.getTno();
        log.info("해당 DTO로 수정요청 : "+todoDTO);
        todoService.modify(todoDTO);
        log.info("수정완료," + tno + "번으로 리디렉션");
        return "redirect:/todo/read?tno="+tno;
    }

    @PostMapping("/remove")
    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("-------------remove------------------");
        log.info("tno: " + tno);

        todoService.remove(tno);
        redirectAttributes.addAttribute("link",pageRequestDTO.getLink());
        return "redirect:/todo/list";
    }


}
