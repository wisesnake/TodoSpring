package org.zerock.springex.service;

import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;


public interface TodoService {
    void register(TodoDTO todoDTO);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    TodoDTO selectOne(Long tno);

    void modify(TodoDTO todoDTO);

    void remove(Long tno);
}
