package org.example.test1.domain.board.api;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.example.test1.domain.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * BoardController class.
 *
 * <p>
 * date: 2025-02-23
 * </p>
 *
 * @author : 김정훈
 * @version : V1.0.0
 */
@Slf4j
@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getCategory(@RequestParam("keyword") String keyword) {
		Map<String, Object> resultMap = boardService.getCategory(keyword);
		return ResponseEntity.ok(resultMap );
	}
}
