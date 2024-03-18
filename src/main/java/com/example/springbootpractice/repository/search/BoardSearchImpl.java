package com.example.springbootpractice.repository.search;

import com.example.springbootpractice.domain.Board;
import com.example.springbootpractice.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl(){super(Board.class);}

    @Override
    public Page<Board> search1(Pageable pageable) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        query.where(board.title.contains("1"));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable){

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if( (types != null && types.length >0) && keyword != null) {
            BooleanBuilder booleanBuilder =new BooleanBuilder();


            for(String type: types){
                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            }

            query.where(booleanBuilder);

        }

        query.where(board.bno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count =query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }

}
