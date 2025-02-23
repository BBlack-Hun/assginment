package org.example.test1.domain.board.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * BoardService class.
 *
 * <p>
 * date: 2025-02-23
 * </p>
 *
 * @author : 김정훈
 * @version : V1.0.0
 */
@Service
public class BoardService {

	public Map<String, Object> getCategory(String keyword) {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> group = getDataSet();
		findValueInNestedMap(keyword, group, returnMap);
		return returnMap;
	}

	private Map<String, Object> getDataSet() {
		Map<String, Object> targetMap = new HashMap<>();
		Map<String, Object> initExoData = new HashMap<>();
		initExoData.put("공지사항", 1);
		initExoData.put("첸", 2);
		initExoData.put("백현", 3);
		initExoData.put("시우민", 4);

		Map<String, Object> initBTSData = new HashMap<>();
		initBTSData.put("공지사항", 5);
		initBTSData.put("익명게시판", 6);
		initBTSData.put("뷔", 7);

		Map<String, Object> initBlackPinkData = new HashMap<>();
		initBlackPinkData.put("공지사항", 8);
		initBlackPinkData.put("익명게시판", 6);
		initBlackPinkData.put("로제", 8);

		Map<String, Object> initBoyData = new HashMap<>();
		initBoyData.put("엑소", initExoData);
		initBoyData.put("방탄소년단", initBTSData);

		Map<String, Object> initGirlData = new HashMap<>();
		initGirlData.put("블랙핑크", initBlackPinkData);

		targetMap.put("남자", initBoyData);
		targetMap.put("여자", initGirlData);

		return targetMap;
	}

	private void findValueInNestedMap(String keyword, Map<String, Object> dataMap, Map<String, Object> targetMap) {
		if (dataMap.containsKey(keyword)) {
			if (dataMap.get(keyword) instanceof Map) {
				targetMap.putAll((Map<? extends String, ?>) dataMap.get(keyword));
			} else {
				Map<String, Object> tempMap = new HashMap<>();
				tempMap.put(keyword, dataMap.get(keyword));
				if (targetMap.isEmpty()) {
					targetMap.putAll(tempMap);
				} else {
					List<Integer> tempMapList = new ArrayList<>();
					if (targetMap.get(keyword) instanceof List<?>) {
						tempMapList.addAll((Collection<? extends Integer>) targetMap.get(keyword));
					} else {
						tempMapList.add((Integer) targetMap.get(keyword));
					}
					tempMapList.add((Integer) tempMap.get(keyword));
					targetMap.put(keyword, new ArrayList<>(new HashSet<>(tempMapList)));
				}
			}
		} else {
			for (String key : dataMap.keySet()) {
				if (dataMap.get(key) instanceof Map) {
					Map<String, Object> subData = (Map<String, Object>) dataMap.get(key);
					findValueInNestedMap(keyword, subData, targetMap);
				}
			}
		}
	}
}
