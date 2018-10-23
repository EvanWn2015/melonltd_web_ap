package com.melonltd.naber.rdbms.model.type;

/**
 * UNLIMITED = 無限制
 * RANGE = 範圍 對應活動時間範圍
 * GET_AMOUNT = 可取得數量
 * SET_AMOUNT = 發放數量
 * RANGE_GET = 範圍 + 可取得數量
 * RANGE_SET = 範圍 + 發送數量
 * LIMIT_GET = 幾天 + 可取得數量
 * LIMIT_SET = 幾天 + 發送數量
 * RANGE_LIMIT_GET = 範圍 + 幾天 + 可取得數量
 * RANGE_LIMIT_SET = 範圍 + 幾天 + 發送數量
 * @author evan
 *
 */
public enum ActFuncType {
	UNLIMITED("UNLIMITED"),
	RANGE("RANGE"),
	GET_AMOUNT("GET_AMOUNT"),
	SET_AMOUNT("SET_AMOUNT"),
	RANGE_GET("RANGE_GET"),
	RANGE_SET("RANGE_SET"),
	LIMIT_GET("LIMIT_GET"),
	LIMIT_SET("LIMIT_SET"),
	RANGE_LIMIT_GET("RANGE_LIMIT_GET"),
	RANGE_LIMIT_SET("RANGE_LIMIT_SET");
	
	private String name;

	ActFuncType(String name) {
		this.name = name;
	}
}
