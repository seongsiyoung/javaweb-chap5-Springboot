package com.example.springbootpractice.service;


import com.example.springbootpractice.dto.BoardDTO;
import com.example.springbootpractice.dto.PageRequestDTO;
import com.example.springbootpractice.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
