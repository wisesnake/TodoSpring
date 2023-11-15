package org.zerock.springex.mapper;


import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.TodoDTO;

import java.util.List;


public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

    TodoVO selectOne(Long tno);

    void modify(TodoVO vo);

    void remove(Long tno);
}
