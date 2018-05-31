package com.melonltd.naber.rdbms.model.dao;

import com.melonltd.naber.rdbms.model.bean.TestTable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public abstract interface TestTableDao extends JpaRepository<TestTable, String> {
	
	@Query("SELECT a FROM TestTable a WHERE a.id IN (?1)")
	public abstract List<TestTable> findByWirelessModuleId(String[] paramArrayOfString);
}
