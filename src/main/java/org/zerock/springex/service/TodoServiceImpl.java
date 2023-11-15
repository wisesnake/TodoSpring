package org.zerock.springex.service;


import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TodoMapper todoMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info("service - register() invoked");
        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);
        log.info("글 등록 완료");
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {


        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        int total = todoMapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;

    }

    @Override
    public TodoDTO selectOne(Long tno) {
        TodoVO vo = todoMapper.selectOne(tno);

        TodoDTO dto = modelMapper.map(vo,TodoDTO.class);

        return dto;
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO vo = modelMapper.map(todoDTO,TodoVO.class);
        todoMapper.modify(vo);
    }

    @Override
    public void remove(Long tno) {
        todoMapper.remove(tno);
    }

}
