package com.example.springbootpractice.service;

import com.example.springbootpractice.dto.BoardDTO;
import com.example.springbootpractice.dto.PageRequestDTO;
import com.example.springbootpractice.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        log.info (boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("sample title")
                .content("Sample content")
                .writer(("user01"))
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);

    }

    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("update test")
                .content("update test??")
                .build();

        boardService.modify(boardDTO);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }
}
