package org.zerock.springex.mapper;

import jdk.vm.ci.meta.Local;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

//    @Autowired(required = false)
    @Autowired
    private TodoMapper todoMapper;

    @Test
    public void testTime(){
        log.info("테스트 타임" + todoMapper.getTime());
    }

    @Test
    public void testInsert(){
        TodoVO todoVO = TodoVO.builder()
                .title("스프링 테스트")
                .dueDate(LocalDate.of(2023,11,15))
                .writer("테스트 작성자 4")
                .build();

        todoMapper.insert(todoVO);
    }
}
