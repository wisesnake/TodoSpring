package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;

import java.io.PrintWriter;
import java.time.LocalDate;

@Log4j2
@Controller
@RequestMapping("/todo")
public class SampleController {
//스프링 MVC 컨트롤러의 특징
    //1)상속이나 인터페이스를 구현하는 방식을 사용하지 않고, 어노테이션으로 처리 가능
    //2)오버라이드 없이 필요한 메서드를 정의
    //3)메소드의 파라미터를 기본 자료형이나 객체 자료형으로 마음대로 지정
    //4)메소드의 리턴타입도 void,string 객체 등 다양한 타입을 사용할 수 있음.


    //Annotation for mapping HTTP GET requests onto specific handler methods.
    //Specifically, @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
//    @GetMapping("/hello")
//    public void hello(){
//        log.info("hello~~~~");
//    }
//
//    @RequestMapping("/list")
//    public void list(){
//        log.info("todolist....");
//    }
//
//    //@RequestMapping(value="/register", method = RequestMethod.GET)
//    @GetMapping("/register")
//    public void registerGET(){
//        log.info("GET todo register ....");
//    }
//    @PostMapping("/register")
//    public void registerPOST(){
//        log.info("POST todo register ....");
//    }


    //Spring MVC 특징
    //1)단순 파라미터 자동 수집
    //-기본 자료형의 경우에는 자동으로 형 변환 처리 가능
    //-객체 자료형의 경우는 setXXX()의 동작을 통해 처리
    //-객체 자료형의 경우 생성자가 없구나 파라미터가 없는 생성자가 필요함.(자바빈즈)
    @GetMapping("/ex1")
    public void ex1( String name, int age){
        log.info("ex1 init...");
        log.info(name);
        log.info(age);
    }

    // Spring MVC의 파라미터는 기본적으로 요청에 전달된 파라미터 이름을 기준으로 동작하지만,
    // 간혹 파라미터가 전달되지 않으면 문제가 발생할 수 있다 이런 경우라면 @RequestParam을 고려
    @GetMapping("/ex2")
    @ResponseBody
    public void ex2(@RequestParam String name,@RequestParam int age){
        log.info("ex2 init...");
        log.info(name);
        log.info(age);

    }

    //Formatter 를 이용한 파라미터의 커스텀 처리
    //기본적으로 HTTP는 문자열로 데이터를 전달하기 때문에, 컨트롤러는 문자열을 기준으로 특정한 클래스의 객체로 처리하는 작업이 진행 됨,
    //그래서 문제는 날짜 관련 타입 브라우저에서 2023-11-14 와 같은 형태의 문자열을 Date 나 LocalDateTime등으로 변환하는 작업이 많이 필요하지만
    //이에 대한 파라미터 수집은 에러가 발생함.
    //그럴 경우 Formatter사용, 문자열을 객체로,객체를 문자열로


    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3...");
        log.info("dueDate : " + dueDate);
    }
    @GetMapping("/ex4.do")
    public void ex4(Model model){

        log.info("ex4...");
        model.addAttribute("msg","점심메뉴");

    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){
        log.info("ex5......");
        redirectAttributes.addAttribute("name","hong");
        return "redirect:/todo/ex6";
    }


    //Java Beans 와 @ModelAttribute
    //스프링 MVC의 컨트롤러는 파라미터로 getter/setter를 이용하는 java beans의 형식의 사용자 정의 클래스가 파라미터인 경우에는
    //자동으로 화면까지 객체를 전달함

    @GetMapping("/ex6")
    public void ex6Get(TodoDTO todoDTO,@RequestParam String name){
        log.info(todoDTO);
        log.info(name);
    }

    @PostMapping ("/ex6")
    public void ex6Post(TodoDTO todoDTO){
        log.info(todoDTO);
    }



    //Spring MVC 에서 컨트롤러 클래스 안의 메서드이 리턴타입
    //void : 컨트롤러의 Mapping annotation의 value를 그대로 view resolver를 이용하여 검색 후
    //String : 문자열을 그대로 view resolver를 이용하여 검색 후 URL로 변환
    // 객체,배열,기본자료형
    // ResponseEntity
    // 일반적으로 view가 따로 있는 경우 void나 String을 이용하고
    // JSON타입을 이용할 때는 객체나 REsponseEntity *( 상태 코드도 같이 전달) 타입을 주로 사용한다.


    @GetMapping("/ex7")
    public void ex7(String name,int age){
        log.info("name"+name);
        log.info("age"+age);

    }
    @GetMapping("/ex8")
    public String ex8(){
        log.info("ex5......");
        return "/todo/ex61243";
    }


}
